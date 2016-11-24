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
import android.widget.AdapterView;
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

public class Bookings extends AppCompatActivity {

    private WeekView wv;

    // Menu Dock Object
    MenuDock md;
    Calendar c = Calendar.getInstance();
    CalendarHandler ch;

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
        ch = new CalendarHandler(this);

        // Get a reference for the week view in the layout.
        wv = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        wv.setOnEventClickListener(ch.complexEventClickListener());

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        wv.setMonthChangeListener(ch.adaptiveMonthChangeListener());

        // Set long press listener for events.
        wv.setEventLongPressListener(ch.theEventLongPressListener());

        // Set hour
        double hour = (double) c.get(Calendar.HOUR_OF_DAY);
        wv.goToHour(hour);

        // END CALENDAR

        // handle Filter spinner
        Spinner filter_spinner = (Spinner) findViewById(R.id.filter_booking_dropdown);
        // set default value
        filter_spinner.setSelection(2);
        filter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Spinner court_spinner = (Spinner) findViewById(R.id.court_booking_dropdown);
                int court_type = court_spinner.getSelectedItemPosition();

                // show All courts (mine, open racquet, open squash)
                if(court_type == 0 && position == 2){
                    ch.setWhatType(null);
                }

                // show all open courts
                if(court_type == 0 && position == 0){
                    ch.setWhatType(new String[] {"4", "5"});
                }

                // show open racquet courts
                else if(court_type == 1 && position == 0){
                    ch.setWhatType(new String[] {"5"});
                }

                // show open squash courts
                else if(court_type == 2 && position == 0){
                    ch.setWhatType(new String[] {"4"});
                }

                // show all my courts
                else if(court_type == 0 && position == 1){
                    ch.setWhatType(new String[] {"2", "3"});
                }

                // show my racquet courts
                else if(court_type == 1 && position == 1){
                    ch.setWhatType(new String[] {"3"});
                }

                // show my squash courts
                else if(court_type == 2 && position == 1){
                    ch.setWhatType(new String[] {"2"});
                }

                // refresh calendar
                wv.notifyDatasetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // handle Court filter
        Spinner court_spinner = (Spinner) findViewById(R.id.court_booking_dropdown);
        // set default value
        court_spinner.setSelection(0);
        court_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Spinner filter_spinner = (Spinner) findViewById(R.id.filter_booking_dropdown);
                int filter_type = filter_spinner.getSelectedItemPosition();

                // show All courts (mine, open racquet, open squash)
                if(position == 0 && filter_type == 2){
                    ch.setWhatType(null);
                }

                // show all open courts
                if(position == 0 && filter_type == 0){
                    ch.setWhatType(new String[] {"4", "5"});
                }

                // show open racquet courts
                else if(position == 1 && filter_type == 0){
                    ch.setWhatType(new String[] {"5"});
                }

                // show open squash courts
                else if(position == 2 && filter_type == 0){
                    ch.setWhatType(new String[] {"4"});
                }

                // show all my courts
                else if(position == 0 && filter_type == 1){
                    ch.setWhatType(new String[] {"2", "3"});
                }

                // show my racquet courts
                else if(position == 1 && filter_type == 1){
                    ch.setWhatType(new String[] {"3"});
                }

                // show my squash courts
                else if(position == 2 && filter_type == 1){
                    ch.setWhatType(new String[] {"2"});
                }

                // refresh calendar
                wv.notifyDatasetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                        double hour = (double) cobj.get(Calendar.HOUR_OF_DAY);

                        // make calendar jump to date
                        wv.goToDate(cobj);
                        wv.goToHour(hour);

                        // set Current date at top
                        String datestr = "";


                        String day = cobj.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.CANADA);
                        String month = cobj.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.CANADA);
                        String day_of_month = String.valueOf(cobj.get(Calendar.DAY_OF_MONTH));

                        datestr += day + ", " + month + ". " + day_of_month;

                        TextView tv = (TextView) findViewById(R.id.date_bookings_string);
                        tv.setText(datestr);


                    }}, myear, mmonth, mday);

        datePickerDialog.show();

    }

}
