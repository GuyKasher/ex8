package com.example.calculateroots;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
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


    public void addItem(long inputNumber){

        SingleCalculate newCalculate=new SingleCalculate();
        newCalculate.setText("Calculating roots for "+String.valueOf(inputNumber));
        newCalculate.setInputNumber(inputNumber);


        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class).addTag("calculate_root")
                .setInputData(new Data.Builder().putLong("inputNumber",inputNumber).build()).build();
//        workManager.enqueue(request);
        workManager.enqueueUniqueWork("job_"+String.valueOf(inputNumber), ExistingWorkPolicy.REPLACE,request);
        newCalculate.setId(request.getId().toString());
        this.items.add(newCalculate);

        toDoItemLiveDataMutable.setValue(new ArrayList<>(items));


    }

    public SingleCalculate getSingleCalculateById(String itemId){
        for (SingleCalculate singleCalculate : items) {
            if (singleCalculate.getId().equals(itemId)) {
                return singleCalculate;
            }
        }
        return null;
    }

    public void deleteItem(SingleCalculate singleCalculate) {
        this.items.remove(singleCalculate);
//        sortItems();

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(String.valueOf(item.getId()));
//        editor.clear();
//        editor.apply();

        toDoItemLiveDataMutable.setValue(new ArrayList<>(items));


    }



    public void inProgress(long curNumber,String ID) {
        SingleCalculate oldItem = getSingleCalculateById(ID);
        if (oldItem == null) return;

        SingleCalculate newItem = new SingleCalculate(ID,oldItem.text,oldItem.getInputNumber(),curNumber);
        items.remove(oldItem);
        items.add(newItem);

        toDoItemLiveDataMutable.setValue(new ArrayList<>(items));

    }

    public void finishProgress(long first_root, long second_root,String ID) {
        SingleCalculate oldItem = getSingleCalculateById(ID);
        if (oldItem == null) return;
        String newText;
        if (first_root==oldItem.getInputNumber()){
            newText="The number "+String.valueOf(oldItem.getInputNumber())+" is prime";
        }
        else {
            newText = "Roots for " + String.valueOf(oldItem.getInputNumber()) + ": " +
                    String.valueOf(first_root) + " and " + String.valueOf(second_root);
        }
        SingleCalculate newItem = new SingleCalculate(ID,newText,oldItem.getInputNumber(),oldItem.getCurrentNumberInCalculation());
        items.remove(oldItem);
        items.add(newItem);

        toDoItemLiveDataMutable.setValue(new ArrayList<>(items));

    }
}
