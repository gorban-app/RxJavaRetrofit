package ru.xpcom.rxjavaretrofit;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String URL_API = "https://api.publicapis.org/";

    private static Retrofit retrofit = null;
    private RetrofitFactory() {}

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Builder()
                    .baseUrl(URL_API)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
