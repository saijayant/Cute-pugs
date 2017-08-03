package network;

import com.google.gson.JsonElement;

import data.Dog;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by macbookpro on 03/08/17.
 */


public interface RetrofitInterface {

    @GET("getImages")
    Call<JsonElement> getImages();


}

