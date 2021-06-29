package com.example.calculateroots;

import android.app.Application;

import androidx.work.WorkManager;

public class SingleCalculateApplication extends Application {
    private SingleCalculateDataBaseImpl dataBase;
    public SingleCalculateDataBaseImpl getDataBase(){
        return dataBase;
    }
    WorkManager workManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        workManager=null;
        this.dataBase=new SingleCalculateDataBaseImpl(this);


    }

    public void setWorkManager(WorkManager workManager) {
        this.workManager = workManager;
    }

    private static SingleCalculateApplication instance=null;
    public static SingleCalculateApplication getInstance(){
        return instance;
    }


}
