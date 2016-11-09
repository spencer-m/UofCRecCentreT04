package cpsc481.fall2016.uofcreccentret04;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity {

    // Menu Dock Object
    private SlidingUpPanelLayout menu_dock;

    // ACTIONS TO CREATE THE HOME SCREEN
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu_dock = (SlidingUpPanelLayout) findViewById(R.id.menu_dock);
        initMenuDock();
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

    // Menu Dock Code
    public void initMenuDock() {



        ListView menuList = (ListView) findViewById(R.id.menu_list);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Code to switch screens would be here
                Toast.makeText(Home.this, "onItemClick"+position, Toast.LENGTH_SHORT).show();
                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        String[] menuOptions ={
                "We",
                "Are",
                "Lit",
                "Fam",
        };

        Integer[] menuImgs={
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
        };

        menu_list_adapter menuAdapter = new menu_list_adapter(this, menuOptions, menuImgs);
        menuList.setAdapter(menuAdapter);

        // TODO: figure out tapping on the grey area closes the menu dock

        menu_dock.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (menu_dock != null &&
                (menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
