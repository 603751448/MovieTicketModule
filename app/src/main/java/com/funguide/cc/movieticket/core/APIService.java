package com.funguide.cc.movieticket.core;


import com.funguide.cc.movieticket.model.Cinema;
import com.funguide.cc.movieticket.model.Film;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by tom on 2016/3/25.
 */
public interface APIService {

    /**3.1热门影片**/
    @GET("/v1/film/hot_films_list")
    Call<Film> getHotFilms(@Query("cityId") int cityId, @QueryMap Map<String, String> options);
    /**3.2影片列表**/
    @GET("/v1/film/films_list")
    Call<Film> getFilms(@Query("cityId") int cityId,@QueryMap Map<String,String> options);
    /**3.3即将上映的影片**/
    @GET("/v1/film/coming_films_list")
    Call<Film> getComingFilms(@Query("cityId") int cityId,@QueryMap Map<String,String> options);
    /**3.4 影片详情**/
    @GET("/v1/film/film_info")
    Call<Film> getFilmInfo(@Query("filmId") int filmId,@QueryMap Map<String,String> options);
    /**3.5 影片评论**/
    @GET("/v1/film/film_comments")
    Call<Film> getFileComments(@Query("filmId") int filmId,@QueryMap Map<String,String> options);
    /**3.6 添加评论**/
    @GET("/v1/film/add_comment")
    Call<Film> addComment(@Query("filmId") int filmId,@QueryMap Map<String,String> options);
//4 影院
    /**4.1 推荐影院**/
    @GET("/v1/cinema/hot_cinemas_list")
    Call<Cinema> getHotCinemas(@Query("cityId") int cityId,@QueryMap Map<String,String> options);
    /**4.2 影院列表**/
    @GET("/v1/cinema/cinemas_list")
    Call<Cinema> getCinemas(@Query("cityId") int cityId,@QueryMap Map<String,String> options);
    /**4.3 影院详情**/
    @GET("/v1/cinema/cinema_info")
    Call<Film> getCinemaInfo(@Query("key") String key,@Query("cinemaId") int cinemaId,@QueryMap Map<String,String> options);
    /**4.4 去过的影院列表**/
    @GET("/v1/cinema/gone_cinemas_list")
    Call<Cinema> goneCinemas(@Query("cityId") int cityId,@QueryMap Map<String,String> options);

//5 排期
    /**5.1 排期日期**/
    @GET("/v1/even/evens_date")
    Call<Film> getEvensData(@Query("filmId") int filmId,@Query("cinemaId") int cinemaId,@QueryMap Map<String,String> options);
    /**5.2 排期列表接口**/
    @GET("/v1/even/evens_list")
    Call<Film> getEvens(@Query("data") int data,@Query("key") String key,@Query("cityId") int cityId,@Query("filmId") String filmId,@Query("cinemaId") String cinemaId,@QueryMap Map<String,String> options);
//6 源排期接口(比价接口)
    /**6.源排期接口(比价接口)**/
    @GET("/v1/source_even/source_evens_list")
    Call<Film> getSourceEvens(@Query("evenId") int evenId,@QueryMap Map<String,String> options);
//7锁座接口
    /**7.锁座接口**/
    @FormUrlEncoded
    @POST("/v1/seat/lock_seats")
    Call<Film> lockSeats(@Field("evenId") int evenId, @Field("seats") String seats, @Field("mobile") String mobile, @FieldMap Map<String,String> options);
//8解锁
    /**8.解锁**/
    @FormUrlEncoded
    @POST("/v1/seat/unlock_seats")
    Call<Film> unlockSeats( @FieldMap Map<String,String> options);
//9确认订单
    /**9.确认订单**/
    @FormUrlEncoded
    @POST("/v1/zhifu/create_order")
    Call<Film> createOrder(@Field("orderNo") String orderNo,@Field("payType") int payType,@Field("payMerchantNo") String payMerchantNo,@FieldMap Map<String,String> options);
//10.订单详情
    /**10.订单详情**/
    @FormUrlEncoded
    @POST("/v1//info/query_order")
    Call<Film> queryOrder(@Field("key") String key,@Field("orderNo") String orderNo,@FieldMap Map<String,String> options);
//11.购票记录
    /**11.购票记录**/
    @GET("/v1/info/orders")
    Call<Film> getOrders(@Query("page") String page,@Query("key") String key,@Query("mobile") String moblie,@QueryMap Map<String,String> options);
//12.优惠劵列表
    /**12.优惠劵列表**/
    @GET("/v1/youhui/yhqs_list")
    Call<Film> getYhqs(@QueryMap Map<String,String> options);

}
