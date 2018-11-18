package h2mcom.android.whatdoyoufeel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ForecastingAdapter extends ArrayAdapter<TextForecasting> {
    public ForecastingAdapter(@NonNull Context context, @NonNull ArrayList<TextForecasting> forecastings) {
        super( context, 0, forecastings );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listforecastingItemView = convertView;
        if (listforecastingItemView == null){
            listforecastingItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_of_forecasting_item, parent, false);
        }

        final TextForecasting detailsOfItem = getItem( position );

        final TextView date = (TextView)listforecastingItemView.findViewById( R.id.data_forecasting );

        date.setText( detailsOfItem.getData() );

        final TextView temperature = (TextView)listforecastingItemView.findViewById( R.id.temp_forecasting );

         temperature.setText( formatTemp( ( detailsOfItem.getTemperature() ) )+" °C" );

return listforecastingItemView;
    }

    private String formatTemp(double Temp) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0");
        return magnitudeFormat.format(Temp);
    }

}
