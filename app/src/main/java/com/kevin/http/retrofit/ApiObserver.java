package com.kevin.http.retrofit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * <p>
 * - Created by: yongzhi.
 * <br>
 * -       Date: 17-9-13.
 */

public abstract class ApiObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        onStart(disposable);
    }

    public abstract void onStart(Disposable disposable);

    @Override
    public abstract void onNext(@NonNull T t);

    @Override
    public void onError(@NonNull Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
