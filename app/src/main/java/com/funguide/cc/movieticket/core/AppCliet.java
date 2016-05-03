package com.funguide.cc.movieticket.core;


import com.funguide.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 */
public class AppCliet {

    private static final String baseUrl="http://ip:port/";

    //日志拦截器
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    // private static  OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient gethttpClient(){
        if (Config.isDebug){
            //添加日志
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient.addInterceptor(loggingInterceptor);
        }
        httpClient.retryOnConnectionFailure(true)//连接失败重连
                .connectTimeout(15, TimeUnit.SECONDS);//连接超时
        return httpClient.build();
    }

    public static <S> S createSerice(Class<S>  serviceClass) {
        Retrofit retrofit = AppCliet.builder.client(gethttpClient()).build();
        return  retrofit.create(serviceClass);
    }

    /**
     * authToken 认证
     * @param serviceClass
     * @param authToken
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (authToken!=null){
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        Retrofit retrofit = builder.client(gethttpClient()).build();
        return retrofit.create(serviceClass);
    }

    /**
     * AccessToken 认证
     * @param serviceClass
     * @param token
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, final AccessToken token) {
        if (token != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization",
                                    token.getTokenType() + " " + token.getAccessToken())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        Retrofit retrofit = builder.client(gethttpClient()).build();
        return retrofit.create(serviceClass);
    }
    public class AccessToken {

        private String accessToken;
        private String tokenType;

        public String getAccessToken() {
            return accessToken;
        }

        public String getTokenType() {
            // OAuth requires uppercase Authorization HTTP header value for token type
            if ( ! Character.isUpperCase(tokenType.charAt(0))) {
                tokenType =
                        Character
                                .toString(tokenType.charAt(0))
                                .toUpperCase() + tokenType.substring(1);
            }
            return tokenType;
        }
    }
}
