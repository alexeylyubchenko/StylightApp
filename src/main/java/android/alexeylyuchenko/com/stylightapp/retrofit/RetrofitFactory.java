package android.alexeylyuchenko.com.stylightapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexey on 2/8/2017.
 */

public class RetrofitFactory {

    public static <T> T createRetrofitService (Class<T> tClass, String endPoint) {

        Retrofit retrofitAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        T service = retrofitAdapter.create(tClass);

        return service;
    }
}
