package com.manoranjan.newshunt1.Service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryService {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            //.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addNetworkInterceptor(new Interceptor() {

                @Override

                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            //.addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNTNlNDQ2MmI4NTU3MzMzNDM3NzlhMDYwZDhkODU4MmYwNTU3ZGE2NGNhYjQ1M2E3MTZjODRmYTA2MGQ2NThkYjA4MGFmNDAyZDEwZTI0NDAiLCJpYXQiOjE2MDY5ODY3MDEsIm5iZiI6MTYwNjk4NjcwMSwiZXhwIjoxNjM4NTIyNzAxLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.6iR3rpbgnhw7m_6pqfVUliQaXjSo8psiBhS3FmJA4vzDsh3BUZ5FOeiJtVYWiEDN1H4jk8VFh5yMNRRMLoyw8gsFOFWNXAlXpVqt9fMM76I_CFkOxe5qv31g8ipgPQwACa7MgzZDl3UZ6W7-fZCvtoCNHdF2Kd-cFSWfCvleiRslL01gMbBCcg-dyivCwZ29kuwA81sSlprCj4yJM_2pmWVLJgXft3UEJtkwRj93dwlT_Gqj4Sef6XxXyMkyDZJPOhYtnUsiNu2-p8kdt-LEDtEapqJUYn81-7ScrwUglq2X3I1EPfvm5BgTsCd61AUPxHq9ZTxmUKVHcyJ8g8ebgSG6Qa3I8DH3XPpRR1GWKz7JcHCk2zo3qy8eULN_6gHNXulTP_hKTj22z9akrdrcdRh6d2JmGgMku3iopMurk2MEQgx6d8yDTpcysJYm80pdAGV4IP7Hvu3almT7pG782nmDJNYcN91soF9-n_uO13gOpl-YjejvyzcOfsFjdtRgdD_ZIGhXKkFfxLZIf4sZIYASsRRuIcLVoi8n_BbKHRI0Fn02yz0Tb7NVABv9kggyOG5vLmUlpky74ts2AqNyr3NT6PU46ozRo8dFenV_503Jb8v7vL3rb7j1lQu3ND0hJzUB6KVrSnUCoLGfxfy3_SvcHZ5GEWES87hFBSNIl0s")
                            .build();
                    return chain.proceed(request);
                }
            }).build();
    private Retrofit retrofit = null;

    public ApiService getAPI() {

        String BASE_URL = ApiLinks.baseurl;

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(ApiLinks.base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit.create(ApiService.class);
    }
}
