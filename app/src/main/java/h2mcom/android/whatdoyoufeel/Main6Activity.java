package h2mcom.android.whatdoyoufeel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    String Responseurl = "https://api.openweathermap.org/data/2.5/forecast?id=6357225&units=metric&appid=ee3ecd0b86b1ff088bd6cb62864b2ec3";
    private ForecastingAdapter madapter;
    ProgressBar mprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main6 );
        mprogress  =(ProgressBar)findViewById( R.id.loading_indicator );
        madapter = new ForecastingAdapter( this,new ArrayList<TextForecasting>(  ) );
        ListView listViewOfMoscow = (ListView)findViewById( R.id.show_forecasting_for_FuentePalmera);
        listViewOfMoscow.setAdapter( madapter );
        WeatherForecastingAsyncTask task = new WeatherForecastingAsyncTask();
        task.execute(Responseurl);
    }
    private class WeatherForecastingAsyncTask extends AsyncTask<String,Void,ArrayList<TextForecasting>> {

        @Override
        protected ArrayList<TextForecasting> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }
            ArrayList<TextForecasting> result = QuerylistForecasting.fetchweatherData(strings[0]);
            return result;
        }
        @Override
        protected void onPostExecute(ArrayList<TextForecasting> data) {
            mprogress.setVisibility( View.GONE );

            madapter.clear();

            if (data != null && !data.isEmpty()) {
                madapter.addAll(data);
            }
        }
    }

}
