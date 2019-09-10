package id.go.lapan.majalahlapan.data.remote;

import android.text.TextUtils;
import id.go.lapan.majalahlapan.BuildConfig;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ConfigRetrofit {

    private static Retrofit setInit() {
        //URL Server API
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor);
        }
        OkHttpClient okHttpClient = builder
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    public static Api service() {
                return setInit().create(Api.class);
        }



}
