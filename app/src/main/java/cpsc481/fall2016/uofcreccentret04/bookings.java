package cpsc481.fall2016.uofcreccentret04;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bookings extends AppCompatActivity {

    // tracks if current bookings is shown
    boolean showbookings = false;
    ExpandableListView currentbookingsListView;
    ExpandableListAdapter currentbookingsListAdapter;
    List<String> currentbookingsListTitle;
    HashMap<String, List<String>> currentbookingsListDetail;

    // Menu Dock Object
    MenuDock md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        getSupportActionBar().setTitle("Book a Court");

        // Menu Dock Init
        md = new MenuDock(this);

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

        // init current bookings
        currentbookingsListView = (ExpandableListView) findViewById(R.id.currentbookings);
        currentbookingsListDetail = CurrentBookingsListDataPump.getData();
        currentbookingsListTitle = new ArrayList<String>(currentbookingsListDetail.keySet());
        currentbookingsListAdapter = new CurrentBookingsListAdapter(this, currentbookingsListTitle, currentbookingsListDetail);
        currentbookingsListView.setAdapter(currentbookingsListAdapter);
        currentbookingsListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener(){
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), currentbookingsListTitle.get(groupPosition) + "List Expanded.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        currentbookingsListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener(){
            @Override
            public void onGroupCollapse(int groupPosition){
                Toast.makeText(getApplicationContext(),
                        currentbookingsListTitle.get(groupPosition) + "List collapsed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        currentbookingsListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        currentbookingsListTitle.get(groupPosition)
                                + " -> "
                                + currentbookingsListDetail.get(
                                currentbookingsListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    // shows user's current booked rooms
   /* public void toggleCurrentBookings(View view) {

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
    }*/

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


}
