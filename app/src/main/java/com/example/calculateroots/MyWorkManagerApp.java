//package com.example.calculateroots;
//
//import android.app.Application;
//
//import androidx.annotation.Nullable;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.Observer;
//import androidx.work.Data;
//import androidx.work.OneTimeWorkRequest;
//import androidx.work.WorkInfo;
//import androidx.work.WorkManager;
//
//import java.util.UUID;
//
//public class MyWorkManagerApp extends Application {
//   public UUID workerID=null;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
////        WorkManager workManager = WorkManager.getInstance(this);
////        workManager.cancelAllWork();// TODO check if needed
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class)
//                .setInputData(new Data.Builder().putLong("inputNumber",500).build()).build();
//
//
//        workManager.enqueue(request);
//        workerID=request.getId();
//        LiveData<WorkInfo> workInfoByIdLiveData = workManager.getWorkInfoByIdLiveData(workerID);
//        workInfoByIdLiveData.observeForever(new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(WorkInfo workInfo) {
//                System.out.println("observed"+ workInfo);
//            }
//
//
//        });
//    }
//}
