package cpsc481.fall2016.uofcreccentret04;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by Spencer on 11/10/2016.
 */

public class MenuDock {

    public SlidingUpPanelLayout menu_dock;
    private Activity activity;

    public MenuDock(Activity a) {

        activity = a;
        menu_dock = (SlidingUpPanelLayout) activity.findViewById(R.id.menu_dock);

        ListView menuList = (ListView) activity.findViewById(R.id.menu_list);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

                if (position == 0) {
                    Intent i = new Intent(view.getContext(),camps.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 1) {
                    Intent i = new Intent(view.getContext(),activities.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 2) {
                    Intent i = new Intent(view.getContext(),levelup.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 3) {
                    Intent i = new Intent(view.getContext(),programs.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 4) {
                    Intent i = new Intent(view.getContext(),fitness_centre.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 5) {
                    Intent i = new Intent(view.getContext(),bookings.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 6) {
                    Intent i = new Intent(view.getContext(),mycalendar.class);
                    activity.startActivityForResult(i, 0);
                }
                else if (position == 7) {
                    Intent i = new Intent(view.getContext(),Home.class);
                    activity.startActivityForResult(i, 0);
                }

            }
        });

        String[] menuOptions ={
                "Camps",
                "Activities",
                "Level Up!",
                "Programs",
                "Fitness Centre",
                "Bookings",
                "My Calendar",
                "Home"
        };

        Integer[] menuImgs={
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1,
                R.drawable.menu1
        };

        menu_list_adapter menuAdapter = new menu_list_adapter(activity, menuOptions, menuImgs);
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

                    Button ob = (Button) activity.findViewById(R.id.openMenuB);
                    Button cb = (Button) activity.findViewById(R.id.closeMenuB);
                    ob.setVisibility(View.INVISIBLE);
                    cb.setVisibility(View.VISIBLE);

                }
                else if(menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED) {

                    Button ob = (Button) activity.findViewById(R.id.openMenuB);
                    Button cb = (Button) activity.findViewById(R.id.closeMenuB);
                    cb.setVisibility(View.INVISIBLE);
                    ob.setVisibility(View.VISIBLE);

                }
                else {
                    Button ob = (Button) activity.findViewById(R.id.openMenuB);
                    Button cb = (Button) activity.findViewById(R.id.closeMenuB);
                    cb.setVisibility(View.INVISIBLE);
                    ob.setVisibility(View.INVISIBLE);

                }
            }
        });

        Button ob = (Button) activity.findViewById(R.id.openMenuB);
        Button cb = (Button) activity.findViewById(R.id.closeMenuB);

        ob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

    }

}
