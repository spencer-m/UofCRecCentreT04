package cpsc481.fall2016.uofcreccentret04;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toptoolbar);
        setSupportActionBar(mToolbar);
        TextView title = (TextView) findViewById(R.id.toptoolbartitle);
        title.setText(R.string.mycalendar);

        // Menu Dock Init
        md = new MenuDock(this);

        // CALENDAR STUFF

        CalendarHandler ch = new CalendarHandler(this);

        // only my bookings will be shown
        ch.setWhatType(new String[] {"2", "3"});

        // Get a reference for the week view in the layout.
        weekView = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(ch.complexEventClickListener());

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(ch.adaptiveMonthChangeListener());

        // Set long press listener for events.
        weekView.setEventLongPressListener(ch.theEventLongPressListener());

        // Set hour
        weekView.goToHour(8.0);

        // END CALENDAR
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
