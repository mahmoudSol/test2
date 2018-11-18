package h2mcom.android.whatdoyoufeel;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    String Responseurl = "https://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743,5908999,6357225&units=metric&appid=ee3ecd0b86b1ff088bd6cb62864b2ec3";
    private CityAdapter adapter;
    ProgressBar loadingIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        loadingIndicator = (ProgressBar)findViewById( R.id.loading_indicator );
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.contact_us);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/DawaDoz/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData( Uri.parse(url));
                startActivity(i);
            }
        });





         adapter = new CityAdapter( this, new ArrayList<Text>(  ) );

        ListView listView = (ListView) findViewById( R.id.list_view_show_cites );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Text currentdetail = adapter.getItem( position );
                if (position == 0){
                    Intent intent = new Intent( getBaseContext(),Main2Activity.class );
                    intent.putExtra( "index", position+1);
                    startActivity( intent );
                } else if (position ==1){
                    Intent intent = new Intent( getBaseContext(),Main3Activity.class );
                    intent.putExtra( "index", position+1);
                    startActivity( intent );
                }else if (position == 2) {
                    Intent intent = new Intent( getBaseContext(),Main4Activity.class );
                    intent.putExtra( "index", position+1);
                    startActivity( intent );
                }else if (position == 3){
                    Intent intent = new Intent( getBaseContext(),Main5Activity.class );
                    intent.putExtra( "index", position+1);
                    startActivity( intent );
                } else if (position == 4){
                    Intent intent = new Intent( getBaseContext(),Main5Activity.class );
                    intent.putExtra( "index", position+1);
                    startActivity( intent );
                } else if (position == 5){
                    Intent intent = new Intent( getBaseContext(),Main6Activity.class );
                    intent.putExtra( "index", position+1);
                    startActivity( intent );
                }


            }
        } );


        WeatherAsyncTask task = new WeatherAsyncTask();
        task.execute(Responseurl);
    }
    private class WeatherAsyncTask extends AsyncTask<String,Void,ArrayList<Text>>{

        @Override
        protected ArrayList<Text> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }
            ArrayList<Text> result = Querylist.fetchweatherData(strings[0]);
            return result;
        }
        @Override
        protected void onPostExecute(ArrayList<Text> data) {

            loadingIndicator.setVisibility( View.GONE );

            adapter.clear();

            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }
}

