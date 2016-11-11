package cpsc481.fall2016.uofcreccentret04;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;


public class Home extends AppCompatActivity {

    // http://stackoverflow.com/questions/19244411/center-align-title-in-action-bar-using-styles-in-android

    /**
     * ViewGroup decorView= (ViewGroup) this.getWindow().getDecorView();
     LinearLayout root= (LinearLayout) decorView.getChildAt(0);
     FrameLayout titleContainer= (FrameLayout) root.getChildAt(0);
     TextView title= (TextView) titleContainer.getChildAt(0);
     title.setGravity(Gravity.CENTER);
     */

    /**
     * Source:
     * https://github.com/umano/AndroidSlidingUpPanel
     */

    // Menu Dock Object
    MenuDock md;

    // ACTIONS TO CREATE THE HOME SCREEN
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Menu Dock Init
        md = new MenuDock(this);
    }

    // BUTTON THAT SWITCHES TO THE FITNESS CENTRE SCREEN
    public void toFitnessCentre(View view) {
        Intent toFitnessCentre = new Intent(view.getContext(),fitness_centre.class);
        startActivityForResult(toFitnessCentre, 0);
    }

    // BUTTON THAT SWITCHES TO THE BOOKINGS SCREEN
    public void toBookings(View view) {
        Intent toBookings = new Intent(view.getContext(),bookings.class);
        startActivityForResult(toBookings, 0);
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
