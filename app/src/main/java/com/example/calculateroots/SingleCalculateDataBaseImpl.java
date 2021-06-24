package com.example.calculateroots;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SingleCalculateDataBaseImpl implements Serializable {
    ArrayList<SingleCalculate> items = new ArrayList<>();
    private static Context context = null;
    public UUID workerID=null;
    WorkManager workManager;


    private static final MutableLiveData<List<SingleCalculate>> toDoItemLiveDataMutable = new MutableLiveData<>();
    public static final LiveData<List<SingleCalculate>> toDoItemLiveDataPublic = toDoItemLiveDataMutable;

    public SingleCalculateDataBaseImpl(Context context) {
        SingleCalculateDataBaseImpl.context = context;
        this.workManager=WorkManager.getInstance(SingleCalculateDataBaseImpl.context);
    }

    public List<SingleCalculate> getCurrentItems() {
        return this.items;
    }


    public void addItem(int inputNumber){

        SingleCalculate newCalculate=new SingleCalculate();
        newCalculate.setText("Calculating roots for "+String.valueOf(inputNumber));

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class)
                .setInputData(new Data.Builder().putLong("inputNumber",inputNumber).build()).build();
        workManager.enqueue(request);
    }


    public void deleteItem(SingleCalculate singleCalculate) {
        this.items.remove(singleCalculate);
//        sortItems();

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(String.valueOf(item.getId()));
//        editor.clear();
//        editor.apply();

//        toDoItemLiveDataMutable.setValue(new ArrayList<>(items));


    }
}
