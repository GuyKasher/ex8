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
        setProgressAsync(
                new Data.Builder()
                        .putInt("calcProgress", 0)
                        .build()
        );
    }

    @NonNull
    @Override
    public Result doWork() {
        // get the inputData from the request
        final int id = getInputData().getInt("id", 0);
        final long number = getInputData().getLong("inputNumber", 0);

        // save id and number in the work's progress

        long startingNumber=SingleCalculateApplication.getInstance().getDataBase().getSingleCalculateById(getId().toString()).currentNumberInCalculation;
        for (long i =startingNumber ; i <= number; i++) {
            System.out.println("number " + number + ", calcProgress: " + i);
            if (i >= 1000 && (i % 1000 == 0)) {
                // save the calculation progress in the work's progress
                setProgressAsync(
                        new Data.Builder()
                                .putInt("current_pro", (int)(i * 100 / number))
                                .build()
                );
            }


            if (number % i == 0) {

                return Result.success(
                        new Data.Builder()
                                .putLong("first_root", i)
                                .putLong("second_root", number / i)
                                .build()
                );
            }
        }

        return null;
    }
}

