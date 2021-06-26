package com.example.calculateroots;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CalculateRootsWorker extends Worker {
    public CalculateRootsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        long inputNumber = getInputData().getLong("inputNumber", 0);
        long curRoot = 2;
//        boolean finishCal = false;

        while (true) {
            if (inputNumber % curRoot == 0) {
//                finishCal=true;

                break;
            } else {
                if (curRoot%100==0) {
                   Log.d("num:" + inputNumber , "  " + String.valueOf(curRoot));
                }setProgressAsync(new Data.Builder().putLong("current",curRoot).build());
                curRoot += 1;
            }
        }


        return Result.success(new Data.Builder()
                .putLong("first_root", curRoot)
                .putLong("second_root", inputNumber / curRoot)
                .build()
        );


    }
}
