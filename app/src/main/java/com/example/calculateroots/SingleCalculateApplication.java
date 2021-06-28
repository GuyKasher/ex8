package com.example.calculateroots;

import android.app.Application;

public class SingleCalculateApplication extends Application {
    private SingleCalculateDataBaseImpl dataBase;
    public SingleCalculateDataBaseImpl getDataBase(){
        return dataBase;
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        instance=this;

        this.dataBase=new SingleCalculateDataBaseImpl(this);


    }





}
