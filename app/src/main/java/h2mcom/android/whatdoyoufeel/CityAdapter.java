package h2mcom.android.whatdoyoufeel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<Text> {
    public CityAdapter(@NonNull Context context, @NonNull ArrayList<Text> list) {
        super( context, 0, list );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Text detailsOfItem = getItem( position );
         final TextView nameOfCites = (TextView)listItemView.findViewById( R.id.show_city );

         nameOfCites.setText( detailsOfItem.getNameOfCity() );

         final TextView temperature = (TextView)listItemView.findViewById( R.id.show_temperature );

         temperature.setText(formatTemp( detailsOfItem.getTemperature() ) +" °C"  );

return listItemView;
    }
    private String formatTemp(double Temp) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0");
        return magnitudeFormat.format(Temp);
    }
}
