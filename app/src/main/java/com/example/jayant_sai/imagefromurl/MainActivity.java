package com.example.jayant_sai.imagefromurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import data.Dog;
import network.RetrofitClient;
import network.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.NetworkUtil;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pDilog)
    ProgressBar pDilog;
    private GridLayoutManager lLayout;
    private ArrayList<Dog> ImageList = new ArrayList<>();
    RecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getImages();



    }

    private void getImages() {
        if (NetworkUtil.isNetworkAvailble(this)) {
            pDilog.setVisibility(View.VISIBLE);
            RetrofitInterface ret = RetrofitClient.getClient().create(RetrofitInterface.class);
            Call<JsonElement> call = ret.getImages();
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    if (BuildConfig.DEBUG) {
                        Log.d("String", "onResponse:  response" + response.body().toString());
                    }

                    if (response.code() == 200) {

                        JsonObject moviesResponse = null;

                        JsonObject pugs = response.body().getAsJsonObject();
                        JsonArray imageArray = pugs.getAsJsonArray("pugs");
                        for (int i = 0; i < imageArray.size(); i++) {
                            Dog d = new Dog();
                            d.setUrl(imageArray.get(i).getAsString());
                            Log.d("dogs", "onResponse: " + imageArray.get(i).getAsString());
                            ImageList.add(d);

                        }
//                            mAdapter = new MoviesAdapter(moviesList, this);
                        Log.d("dogs", "onResponse: " + ImageList.size());

                        mAdapter = new RecyclerViewAdapter(MainActivity.this, ImageList);
                        lLayout = new GridLayoutManager(MainActivity.this, 3);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(lLayout);
                        recyclerView.setAdapter(mAdapter);
                        pDilog.setVisibility(View.GONE);


                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                }
            });
        }

    }
}
