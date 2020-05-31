package com.jiyun.frame;

import io.reactivex.disposables.Disposable;

public interface ICommonPresenter<P> extends ICommonView {
    void getData(int apiConfig, int loadTypeConfig, P... ps);

    void addObserver(Disposable disposable);
}
