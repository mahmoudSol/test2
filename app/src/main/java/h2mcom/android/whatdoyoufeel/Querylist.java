package h2mcom.android.whatdoyoufeel;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class Querylist {
    private static final String LOG_TAG = Querylist.class.getSimpleName();
    private Querylist() {
    }

public static ArrayList<Text> fetchweatherData (String RequstUrl){
        
        URL url = createurl(RequstUrl);
        String JsonResponse = null;
    try {
        JsonResponse = makeHttpConnecting(url);
    } catch (IOException e) {
        e.printStackTrace();
    }
    ArrayList<Text> weather = detailOfWeather(JsonResponse);
     
    return weather;

}

    private static ArrayList<Text> detailOfWeather(String jsonResponse) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty( jsonResponse )){
            return null;
        }

        ArrayList<Text> weathers = new ArrayList<>(  );
        // Create a JSONObject from the JSON response string
        try {
            JSONObject object = new JSONObject( jsonResponse );
            JSONArray listArray = object.getJSONArray( "list" );
            // For each weather in the listArray, create an {@link weather} object
            for (int i = 0;i<listArray.length();i++){
                // Get a single object at position i within the list of Cities
                JSONObject currentlist = listArray.getJSONObject( i );
                String nameOfCity = currentlist.getString( "name" );
                JSONObject mainofList = currentlist.getJSONObject( "main" );
                double temp = mainofList.getDouble( "temp" );
                Text weather = new Text( nameOfCity,temp );
                weathers.add( weather );

            }

        } catch (JSONException e) {
            e.printStackTrace();
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("Querylist", "Problem parsing the earthquake JSON results", e);
        }
        return weathers;

    }

    private static String makeHttpConnecting(URL url) throws IOException {
        String JsonResponse="";
        if (url == null){
            return JsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                JsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Weather JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return JsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder(  );
        if (inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }

        }
        return output.toString();

    }

    private static URL createurl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

}
