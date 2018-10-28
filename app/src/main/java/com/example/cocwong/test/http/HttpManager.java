package com.example.cocwong.test.http;

import android.text.TextUtils;

import com.example.cocwong.test.bean.LatestBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求类
 */
public class HttpManager extends HttpBase {
    private int timeout = 10000;
    private static HttpManager instance;
    private String baseUrl = "http://goosetalk.com";
    private Api api;

    private HttpManager() {
        init();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.addInterceptor(getHeaders());
        builder.addNetworkInterceptor(new HttpLoggingInterceptor());
        OkHttpClient client = builder.build();

        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl(baseUrl);
        builder1.addConverterFactory(GsonConverterFactory.create());
        builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder1.client(client);
        Retrofit retrofit = builder1.build();
        api = retrofit.create(Api.class);
    }

    /**
     * @return 单例
     */
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 最新
     */
    public void getLatest(int page, Callback<LatestBean> callback) {
        handleResponse(api.getLatest(page, "hot"), callback);
    }

    /**
     * 处理observable
     *
     * @param observable observable
     */
    private <T> void handleResponse(Observable<ResponseEntity<T>> observable, final Callback<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callback.onStart(d);
                    }

                    @Override
                    public void onNext(ResponseEntity<T> tResponseEntity) {
                        if (TextUtils.equals(tResponseEntity.getResCode(), "1000")) {
                            callback.onSuccess(tResponseEntity.getResData());
                        } else {
                            callback.onFailed(tResponseEntity.getResMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("网络错误信息返回："+e.getMessage());
                        callback.onFailed("error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
