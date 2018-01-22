package keleg.nespresso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.capsules_list);
        ArrayList<NespressoCapsule> capsules = new ArrayList<>();
        capsules.add(
                new NespressoCapsule(0,"Fortissio Lungo", R.mipmap.fortissio_lungo));
        capsules.add(
                new NespressoCapsule(0,"Linizio Lungo", R.mipmap.linizio_lungo));
        listView.setAdapter(new CapsulesAdapter(MainActivity.this, capsules));
    }
}
