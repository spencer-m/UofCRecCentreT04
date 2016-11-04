package cpsc481.fall2016.uofcreccentret04;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
