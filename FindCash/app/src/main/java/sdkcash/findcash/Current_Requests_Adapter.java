package sdkcash.findcash;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bhaskar on 10/23/16.
 */

public class Current_Requests_Adapter extends ArrayAdapter<Current_Requests_Book> {

    Context context;
    int layoutResourceId;
    ArrayList<Current_Requests_Book> data;

    public Current_Requests_Adapter(Context context, int layoutResourceId, ArrayList<Current_Requests_Book> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
      //       R.array.planets_array, android.R.layout.simple_spinner_item);
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.single_request, null);
        }

        Current_Requests_Book p = getItem(position);

        if (p != null) {

            TextView moneyAmountHave = (TextView) v.findViewById(R.id.txtMoneyAmtHave);
            TextView moneyAmountWant= (TextView) v.findViewById(R.id.txtMoneyAmtWant);
            TextView AirportFrom = (TextView) v.findViewById(R.id.txtAirportFrom);
            TextView AirportTo = (TextView) v.findViewById(R.id.txtAirportTo);
            TextView currencyHave = (TextView) v.findViewById(R.id.txtCurrHave);
            TextView currencyWant = (TextView) v.findViewById(R.id.txtCurrWant);
            TextView timeArrival = (TextView) v.findViewById(R.id.txtTimeArr);
            TextView timeDeparture = (TextView) v.findViewById(R.id.txtTimeDept);
            TextView meetingLocation = (TextView) v.findViewById(R.id.txtMeetLoc);



            if (moneyAmountHave!= null) {
                moneyAmountHave.setText(p.getMoneyAmountHave());
            }
            if (moneyAmountWant!= null) {
                moneyAmountWant.setText(p.getMoneyAmountWant());
            }
            if (AirportFrom != null) {
                AirportFrom.setText(p.getAirportFrom());
            }
            if (AirportTo != null) {
                AirportTo.setText(p.getAirportTo());
            }
            if (currencyHave != null) {
                currencyHave.setText(p.getCurrenyHave() + " : ");
            }
            if (currencyWant != null) {
                currencyWant.setText(p.getCurrenyWant() + " : ");
            }
            if (timeArrival != null) {
                timeArrival.setText(p.getTimeArriving());
            }
            if (timeDeparture != null) {
                timeDeparture.setText(p.getTimeDeparting());
            }
            if (meetingLocation != null) {
                meetingLocation.setText(p.getMeetingLocation());
            }
        }

        return v;
    }
}
