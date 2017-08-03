package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jayant_sai.imagefromurl.MainActivity;
import com.example.jayant_sai.imagefromurl.R;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import data.Dog;
import retrofit2.Callback;

/**
 * Created by macbookpro on 03/08/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public ArrayList<Dog> itemList;
    private Context context;

    public RecyclerViewAdapter(MainActivity context, ArrayList<Dog> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_list, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d("dogs", "onBindViewHolder: "+itemList.get(position).getUrl());
            Picasso.with(viewHolder.itemView.getContext())
                    .load(itemList.get(position)
                            .getUrl())
                    .placeholder(R.drawable.ic_collections_black_24dp)
                    .error(R.drawable.ic_not_interested_black_24dp)
                    .into(viewHolder.imgViewIcon);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.image);
        }
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

