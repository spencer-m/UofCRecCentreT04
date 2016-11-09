package cpsc481.fall2016.uofcreccentret04;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private SlidingDrawer drawer;
    private Button handle, clickMe;
    private TextView text1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this.getApplicationContext();

        handle = (Button) findViewById(R.id.handle);
        clickMe = (Button) findViewById(R.id.click);
        drawer=(SlidingDrawer)findViewById(R.id.slidingDrawer);

        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                handle.setText("-");
            }
        });

        drawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {

            @Override
            public void onDrawerClosed() {
                handle.setText("+");
            }
        });

        clickMe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "The button is clicked", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void toFitnessCentre(View view) {
        Intent toFitnessCentre = new Intent(view.getContext(),fitness_centre.class);
        startActivityForResult(toFitnessCentre, 0);
    }

    public void toBookings(View view) {
        Intent toBookings = new Intent(view.getContext(),bookings.class);
        startActivityForResult(toBookings, 0);
    }

    // its time
}
