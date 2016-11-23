package cpsc481.fall2016.uofcreccentret04;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class bookings extends AppCompatActivity {

    private WeekView wv;

    // Menu Dock Object
    MenuDock md;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        getSupportActionBar().setTitle("Book a Court");

        // Menu Dock Init
        md = new MenuDock(this);

        // set Current date at top
        String datestr = "";


        String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.CANADA);
        String month = c.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.CANADA);
        String day_of_month = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        datestr += day + ", " + month + ". " + day_of_month;

        TextView tv = (TextView) findViewById(R.id.date_bookings_string);
        tv.setText(datestr);


        // sets title background color
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E30C00")));
        bar.isHideOnContentScrollEnabled();

        // Create calendar
        CalendarHandler ch = new CalendarHandler(this);

        //ch.setWhatType(new String[] {"0", "1"});

        // Get a reference for the week view in the layout.
        wv = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        wv.setOnEventClickListener(ch.simpleEventClickListener());

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        wv.setMonthChangeListener(ch.simpleMonthChangeListener());

        // Set long press listener for events.
        wv.setEventLongPressListener(ch.theEventLongPressListener());


        // END CALENDAR


    }


    // Handles Cancel button
    public void toHome(View view) {
        Intent toHome = new Intent(view.getContext(), Home.class);
        startActivityForResult(toHome, 0);
        finish();
    }

    // Handles Search button
    public void toSearch(View view) {
        // open search activity
    }

    @Override
    public void onBackPressed() {
        if (md.menu_dock != null &&
                (md.menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || md.menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            md.menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }


    public void openDatePicker(View view) {

        int myear = c.get(Calendar.YEAR);
        int mmonth = c.get(Calendar.MONTH);
        int mday = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // handle date set
                        Calendar cobj = Calendar.getInstance();

                        cobj.set(year, monthOfYear, dayOfMonth);

                    }}, myear, mmonth, mday);

        datePickerDialog.show();

    }
}
