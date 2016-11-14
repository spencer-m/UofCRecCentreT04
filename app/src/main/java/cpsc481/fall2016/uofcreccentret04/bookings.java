package cpsc481.fall2016.uofcreccentret04;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class bookings extends AppCompatActivity {

    // tracks if current bookings is shown
    boolean showbookings = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        getSupportActionBar().setTitle("Book a Court");

        // sets title background color
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E30C00")));
        bar.isHideOnContentScrollEnabled();

        // Populate hour spinner
        // create data for spinner
        List<String> hour_arr_data = new ArrayList<String>();
        for(int i = 1; i <= 12; i++){
            hour_arr_data.add((Integer.toString(i)));
        }
        // enter data to spinner
        Spinner hour_spinner = (Spinner)findViewById(R.id.hour_booking_dropdown);
        ArrayAdapter<String> hour_spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hour_arr_data);
        hour_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour_spinner.setAdapter(hour_spinner_adapter);

        // Populate minute spinners
        // create data for spinner
        List<String> min_arr_data = new ArrayList<String>();
        for(int i = 0; i <= 59; i++){
            min_arr_data.add((Integer.toString(i)));
        }
        // enter data to spinner
        Spinner min_spinner = (Spinner)findViewById(R.id.minute_booking_dropdown);
        ArrayAdapter<String> leftmin_spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, min_arr_data);
        leftmin_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        min_spinner.setAdapter(leftmin_spinner_adapter);


    }

    // shows user's current booked rooms
    public void toggleCurrentBookings(View view) {

        ImageButton button = (ImageButton)findViewById(R.id.expandBookings);

        if(showbookings == false){
            // clear button image
            button.setImageResource(android.R.color.transparent);
            //change imagebutton image
            button.setImageResource(R.drawable.collapse);

            showbookings = true;

        }else{
            //change imagebutton image
            button.setImageResource(android.R.color.transparent);
            button.setImageResource(R.drawable.expand);

            showbookings = false;
        }
    }
}
