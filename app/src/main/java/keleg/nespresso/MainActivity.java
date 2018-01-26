package keleg.nespresso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.capsules_list);
        List<String> cap_names = Arrays.asList("Robusta Uganda", "Arabica Ethiopia Harrar", "Kazaar",
                "Dharkan", "Ristretto", "Arpeggio", "Roma", "Livanto",
                "Capriccio", "Volluto", "Cosi", "Indriya from India",
                "Rosabaya de Colombia", " Dulsão Do Brasil", "Bukeela ka Ethiopia",
                "Envivo Lungo", "Fortissio Lungo", "Linizio Lungo", "Vivalto Lungo",
                "Arpeggio Decaffeinato", "Volluto Decaffeinato",
                "Vivalto Lungo Decaffeinato", "Ciocattino", "Vanilio", "Caramelito" );
        List<Integer> cap_images = Arrays.asList(R.mipmap.robusta_uganda,
                R.mipmap.arabica_ethiopia_harrar, R.mipmap.kazaar,
                R.mipmap.dharkan, R.mipmap.ristretto, R.mipmap.arpeggio, R.mipmap.roma,
                R.mipmap.livanto, R.mipmap.capriccio, R.mipmap.volluto, R.mipmap.cosi,
                R.mipmap.indriya_from_india, R.mipmap.rosabaya_de_colombia,
                R.mipmap.dulsao_do_brasil, R.mipmap.bukeela_ka_ethiopia,
                R.mipmap.envivo_lungo, R.mipmap.fortissio_lungo, R.mipmap.linizio_lungo,
                R.mipmap.vivalto_lungo,R.mipmap.arpeggio_decaffeinato,
                R.mipmap.volluto_decaffeinato, R.mipmap.vivalto_lungo_decaffeinato,
                R.mipmap.ciocattino, R.mipmap.vanilio, R.mipmap.caramelito);
        ArrayList<NespressoCapsule> capsules = new ArrayList<>();
        for (int i=0;i<cap_images.size();++i)
        {
            capsules.add(
                    new NespressoCapsule(0, cap_names.get(i), cap_images.get(i)));
        }

//        capsules.add(
//                new NespressoCapsule(5,"Linizio Lungo", R.mipmap.linizio_lungo));
        Collections.sort(capsules);
        listView.setAdapter(new CapsulesAdapter(MainActivity.this, capsules));
    }
}
