package cpsc481.fall2016.uofcreccentret04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class programs extends AppCompatActivity {

    // Menu Dock Object
    MenuDock md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);
        getSupportActionBar().setTitle("Programs");
        // Menu Dock Init
        md = new MenuDock(this);
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
