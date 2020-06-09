package com.jiyun.frame.interceptor;


import com.jiyun.frame.mvp.BaseObesrver;
import com.jiyun.frame.api.ServerAddressConfig;
import com.jiyun.frame.api.ApiService;
import com.jiyun.frame.mvp.ICommonPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {
    public NetManager() {
    }

    public static volatile NetManager sNetManager;

    //单例懒汉创建对象
    public static NetManager getInstance() {
        if (sNetManager == null) {
            synchronized (NetManager.class) {
                sNetManager = new NetManager();
            }
        }
        return sNetManager;
    }

    public <T> ApiService getService(T...ts){
        String baseUrl= ServerAddressConfig.BASE_URL;
        if (ts!=null&&ts.length!=0){
            baseUrl= (String) ts[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
                .create(ApiService.class);
    }

    private OkHttpClient initClient() {
        OkHttpClient build = new OkHttpClient().newBuilder()
                .addInterceptor(new CommonHeadersInterceptor())
                .addInterceptor(new CommonParamsInterceptor())
                .addInterceptor(initLogInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        return build;
    }

    private Interceptor initLogInterceptor() {
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        return log;
    }

    public <T,O> void netWork(Observable<T> localTestInfo, ICommonPresenter iCommonPresenter, int apiConfig, O...os){
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObesrver() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iCommonPresenter.addObserver(d);
                    }

                    @Override
                    public void onSuccess(Object values) {
                        int os1 = 0;
                        if (os !=null) {
                             os1 = (Integer) os[0];
                        }
                        iCommonPresenter.netSuccess(apiConfig,os1,values);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        iCommonPresenter.netFailed(apiConfig,throwable);
                    }
                });
    }


    public <T,O> void netWorkByConsumer(Observable<T> localTestInfo,ICommonPresenter iCommonPresenter,int apiConfig,int loadTypeConfig,O...os){
        Disposable subscribe = localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(values -> iCommonPresenter.netSuccess(apiConfig,loadTypeConfig,values,os),
                        throwable -> iCommonPresenter.netFailed(apiConfig,throwable));
        iCommonPresenter.addObserver(subscribe);
    }
}
