package cpsc481.fall2016.uofcreccentret04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void toFitnessCentre(View view) {

        Intent toFitnessCentre = new Intent(view.getContext(),fitness_centre.class);
        startActivityForResult(toFitnessCentre, 0);
    }
}
