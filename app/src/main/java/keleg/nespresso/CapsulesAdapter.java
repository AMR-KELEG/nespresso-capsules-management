package keleg.nespresso;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CapsulesAdapter extends ArrayAdapter<NespressoCapsule> {
    public CapsulesAdapter(Context context, ArrayList<NespressoCapsule> capsules)
    {
        super(context, 0, capsules);
    }

    public void incrementCapsule(int position)
    {
        super.getItem(position).no_of_capsules++;
        super.notifyDataSetChanged();
    }

    public void decrementCapsule(int position)
    {
        NespressoCapsule capsule = super.getItem(position);
        if (capsule.no_of_capsules == 0)
        {
            Toast.makeText(super.getContext(), capsule.name + ": Empty Inventory!", Toast.LENGTH_SHORT).show();
            return ;
        }
        capsule.no_of_capsules--;
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        final NespressoCapsule capsule = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_capsule, parent, false);
        }
        // Lookup view for data population
        ImageView capImage = convertView.findViewById(R.id.capsule_image);
        TextView capName =  convertView.findViewById(R.id.capsule_name);
        TextView capNo = convertView.findViewById(R.id.capsule_count);
        Button incButton = convertView.findViewById(R.id.capsule_inc);
        Button decButton = convertView.findViewById(R.id.capsule_dec);

        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Amr", capsule.name);
                incrementCapsule(position);
            }
        });

        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Amr", capsule.name);
                decrementCapsule(position);
            }
        });
        // Populate the data into the template view using the data object
        capName.setText(capsule.name);
        capImage.setImageResource(capsule.image_id);
        capNo.setText(String.valueOf(capsule.no_of_capsules));

        // Return the completed view to render on screen
        return convertView;
    }
}
