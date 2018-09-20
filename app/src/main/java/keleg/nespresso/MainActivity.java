package keleg.nespresso;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.capsules_list);
        final List<String> cap_names = Arrays.asList("Robusta Uganda", "Arabica Ethiopia Harrar", "Kazaar",
                "Dharkan", "Ristretto", "Arpeggio", "Roma", "Livanto",
                "Capriccio", "Volluto", "Cosi", "Indriya from India",
                "Rosabaya de Colombia", " Dulsão Do Brasil", "Bukeela ka Ethiopia",
                "Envivo Lungo", "Fortissio Lungo", "Linizio Lungo", "Vivalto Lungo",
                "Arpeggio Decaffeinato", "Volluto Decaffeinato",
                "Vivalto Lungo Decaffeinato", "Ciocattino", "Vanilio", "Caramelito" );
        final List<Integer> cap_images = Arrays.asList(R.mipmap.robusta_uganda,
                R.mipmap.arabica_ethiopia_harrar, R.mipmap.kazaar,
                R.mipmap.dharkan, R.mipmap.ristretto, R.mipmap.arpeggio, R.mipmap.roma,
                R.mipmap.livanto, R.mipmap.capriccio, R.mipmap.volluto, R.mipmap.cosi,
                R.mipmap.indriya_from_india, R.mipmap.rosabaya_de_colombia,
                R.mipmap.dulsao_do_brasil, R.mipmap.bukeela_ka_ethiopia,
                R.mipmap.envivo_lungo, R.mipmap.fortissio_lungo, R.mipmap.linizio_lungo,
                R.mipmap.vivalto_lungo,R.mipmap.arpeggio_decaffeinato,
                R.mipmap.volluto_decaffeinato, R.mipmap.vivalto_lungo_decaffeinato,
                R.mipmap.ciocattino, R.mipmap.vanilio, R.mipmap.caramelito);

        db = AppDatabase.getDatabase(this);
        List<NespressoCapsule> database_capsules = db.capsuleDao().getAll();
        for (int i=0; i< cap_names.size(); i++){
            String cap_name = cap_names.get(i);
            int cap_image_id = cap_images.get(i);
            boolean new_cap = true;
            for (int j=0; j< database_capsules.size(); j++){
                if (database_capsules.get(j).getName().equals(cap_name)){
                    new_cap = false;
                    break;
                }
            }
            if (new_cap)
            {
                db.capsuleDao().insert(new NespressoCapsule(0, cap_name, cap_image_id));
            }
        }

        database_capsules = db.capsuleDao().getAll();
        Collections.sort(database_capsules);
        listView.setAdapter(new CapsulesAdapter(MainActivity.this, ((ArrayList<NespressoCapsule>) database_capsules)));
        final Handler handler=new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<listView.getAdapter().getCount(); i++)
                {
                    db.capsuleDao().update((NespressoCapsule) listView.getAdapter().getItem(i));
                }
                handler.removeCallbacks(this);
                handler.postDelayed(this,10000);
            }
        };
        handler.postDelayed(update,10000);
    }
}
