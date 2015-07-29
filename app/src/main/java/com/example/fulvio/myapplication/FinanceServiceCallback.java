package com.example.fulvio.myapplication;


public interface FinanceServiceCallback {

    void serviceSuccess(Quote quote);

    void serviceFailure(Exception exception);
}
