package com.example.calculateroots;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SingleCalculateDataBaseImpl implements Serializable {
    ArrayList<SingleCalculate> items;
    Context context;

    private static SharedPreferences sharedPreferences = null;


//    private static final MutableLiveData<List<SingleCalculate>> toDoItemLiveDataMutable = new MutableLiveData<>();
//    public static final LiveData<List<SingleCalculate>> toDoItemLiveDataPublic = toDoItemLiveDataMutable;

    public SingleCalculateDataBaseImpl(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
        sharedPreferences = context.getSharedPreferences("local_db_toDoItems", Context.MODE_PRIVATE);
        initialize();
    }

    private void initialize() {
        Set<String> keys = sharedPreferences.getAll().keySet();
        for (String k : keys) {
            String itemString = sharedPreferences.getString(k, null);
            SingleCalculate singleCalculate = new SingleCalculate().stringToSingleCalculate(itemString);
            if (singleCalculate != null) {
                items.add(singleCalculate);
            }
        }
    }

    public List<SingleCalculate> getCurrentItems() {
        return this.items;
    }


    public void addItem(SingleCalculate singleCalculate) {

        this.items.add(singleCalculate);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(singleCalculate.id, singleCalculate.itemToString());
        editor.apply();



    }

    public SingleCalculate getSingleCalculateById(String itemId) {
        for (SingleCalculate singleCalculate : items) {
            if (singleCalculate.id.equals(itemId)) {
                return singleCalculate;
            }
        }
        return null;
    }

    public void deleteItem(SingleCalculate singleCalculate) {
        this.items.remove(singleCalculate);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(singleCalculate.id);
        editor.apply();


    }


    public void inProgress(int progress, String ID) {
        for (SingleCalculate singleCalculate : items) {
            if (singleCalculate.id.equals(ID)) {
                singleCalculate.progress = progress;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(singleCalculate.id, singleCalculate.itemToString());
                editor.apply();
                return;
            }
        }




    }

    public void finishProgress(long first_root, long second_root, String ID) {

        String newText;
        for (SingleCalculate singleCalculate : items) {
            if (singleCalculate.id.equals(ID)) {
                if (first_root == singleCalculate.inputNumber) {
                    newText = "The number " + (singleCalculate.inputNumber) + " is prime";
                } else {
                    newText = "Roots for " + (singleCalculate.inputNumber) + ": " +
                            (first_root) + " and " + (second_root);
                }
                singleCalculate.text = newText;
                singleCalculate.progress = 100;
                singleCalculate.root1 = first_root;
                singleCalculate.root2 = second_root;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(singleCalculate.id, singleCalculate.itemToString());
                editor.apply();
                return;

            }
        }
    }
}