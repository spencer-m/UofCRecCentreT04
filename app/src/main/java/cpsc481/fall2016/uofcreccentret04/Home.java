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

    public void openFirstClosures(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setTitle("January 2017 Track Closures");

        //Spanned stat = Html.fromHtml("Today: <strong><font color = '#33A030'>OPEN</font></strong><br><br>Nov. 15 - Closed<br>Nov.21 - Closed");

        Spanned stat = Html.fromHtml("January 1-7: Closed<br>January 30: Closed");

        alertDialogBuilder.setMessage(stat);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void openSecondClosures(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setTitle("February 2017 Track Closures");

        //Spanned stat = Html.fromHtml("Today: <strong><font color = '#33A030'>OPEN</font></strong><br><br>Dec. 24-26 - Closed<br>Dec. 31 - Closed");

        Spanned stat = Html.fromHtml("February 14: Closed<br>February 18: Closed<br>February 24: Closed");

        alertDialogBuilder.setMessage(stat);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void openThirdClosures(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setTitle("March 2017 Track Closures");

        //Spanned stat = Html.fromHtml("Today: <strong><font color = '#33A030'>OPEN</font></strong><br><br>Dec. 24-26 - Closed<br>Dec. 31 - Closed");

        Spanned stat = Html.fromHtml("March 1-4: Closed<br>March 22: Closed<br>March 30: Closed");

        alertDialogBuilder.setMessage(stat);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void openFourthClosures(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setTitle("April 2017 Track Closures");

        //Spanned stat = Html.fromHtml("Today: <strong><font color = '#33A030'>OPEN</font></strong><br><br>Dec. 24-26 - Closed<br>Dec. 31 - Closed");

        Spanned stat = Html.fromHtml("April 11: Closed<br>April 12: Closed");

        alertDialogBuilder.setMessage(stat);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
