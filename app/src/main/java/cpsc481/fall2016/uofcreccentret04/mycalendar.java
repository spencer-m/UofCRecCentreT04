package cpsc481.fall2016.uofcreccentret04;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Calendar;

public class mycalendar extends AppCompatActivity{

    // Menu Dock Object
    MenuDock md;

    private WeekView weekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycalendar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customaction_bar);
        TextView tv = (TextView) findViewById(R.id.headerText);
        tv.setText(R.string.mycalendar);

        // Menu Dock Init
        md = new MenuDock(this);

        // CALENDAR STUFF

        CalendarHandler ch = new CalendarHandler(this);

        // Get a reference for the week view in the layout.
        weekView = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(ch.theEventClickListener());

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(ch.theMonthChangeListener());

        // Set long press listener for events.
        weekView.setEventLongPressListener(ch.theEventLongPressListener());

        // END CALENDAR

        TextView tvv =  (TextView) findViewById(R.id.testbox);
        //tvv.setText(ch.whatIsGoogleData());
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
