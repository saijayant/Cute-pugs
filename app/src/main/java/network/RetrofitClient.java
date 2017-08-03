package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macbookpro on 03/08/17.
 */

public class RetrofitClient {
        private static Retrofit retrofit = null;


        public static Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://private-e1b8f4-getimages.apiary-mock.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

