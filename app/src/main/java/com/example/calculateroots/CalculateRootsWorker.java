package com.example.calculateroots;

import android.content.Context;

import androidx.annotation.NonNull;
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
        long curRoot=2;
        boolean finishCal=false;

        while (true){
            if (inputNumber%curRoot==0){
                finishCal=true;
                break;
            }
            else {
                curRoot+=1;
            }
        }
        // TODO the root are curRoot and numberToCalculateRootsFor/curRoot
    return Result.success();


    }
}
