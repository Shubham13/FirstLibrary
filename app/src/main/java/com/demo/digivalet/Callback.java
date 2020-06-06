package com.demo.digivalet;

public interface Callback {

    void onSuccess(boolean signInComplete);

    void onError(String error);
}
