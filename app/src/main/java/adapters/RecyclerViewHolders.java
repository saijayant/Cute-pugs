package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jayant_sai.imagefromurl.R;

/**
 * Created by macbookpro on 03/08/17.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView countryName;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
