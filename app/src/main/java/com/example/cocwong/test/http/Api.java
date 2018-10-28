package com.example.cocwong.test.http;

import com.example.cocwong.test.bean.LatestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    /**
     * @return 最新
     */
    @GET("/api/card/listv2")
    Observable<ResponseEntity<LatestBean>> getLatest(@Query("page") int page, @Query("type") String type);
}
