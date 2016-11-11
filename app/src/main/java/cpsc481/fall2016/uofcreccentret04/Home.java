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

        menu_dock.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        menu_dock.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                return false;
            }
        });

        menu_dock.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

                if(menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {

                    Button ob = (Button) findViewById(R.id.openMenuB);
                    Button cb = (Button) findViewById(R.id.closeMenuB);
                    ob.setVisibility(View.INVISIBLE);
                    cb.setVisibility(View.VISIBLE);

                }
                else if(menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED) {

                    Button ob = (Button) findViewById(R.id.openMenuB);
                    Button cb = (Button) findViewById(R.id.closeMenuB);
                    cb.setVisibility(View.INVISIBLE);
                    ob.setVisibility(View.VISIBLE);

                }
                else {
                    Button ob = (Button) findViewById(R.id.openMenuB);
                    Button cb = (Button) findViewById(R.id.closeMenuB);
                    cb.setVisibility(View.INVISIBLE);
                    ob.setVisibility(View.INVISIBLE);

                }
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

    public void openMenu(View view) {
        Toast.makeText(Home.this, "OPENMENU", Toast.LENGTH_SHORT).show();
        menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    public void closeMenu(View view) {
        Toast.makeText(Home.this, "CLOSEMENU", Toast.LENGTH_SHORT).show();
        menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }


}
