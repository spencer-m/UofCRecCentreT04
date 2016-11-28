package cpsc481.fall2016.uofcreccentret04;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;


import com.sothree.slidinguppanel.SlidingUpPanelLayout;


public class Home extends AppCompatActivity {

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

    @Override
    public void onBackPressed() {
        if (md.menu_dock != null &&
                (md.menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || md.menu_dock.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            md.menu_dock.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    public void openNovTClosures(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setTitle("November Track Closures");

        Spanned stat = Html.fromHtml("Today: <strong><font color = '#33A030'>OPEN</font></strong><br><br>Nov. 15 - Closed<br>Nov.21 - Closed");

        alertDialogBuilder.setMessage(stat);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void openDecTClosures(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setTitle("December Track Closures");

        Spanned stat = Html.fromHtml("Today: <strong><font color = '#33A030'>OPEN</font></strong><br><br>Dec. 24-26 - Closed<br>Dec. 31 - Closed");

        alertDialogBuilder.setMessage(stat);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
