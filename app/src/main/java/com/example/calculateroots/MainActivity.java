package com.example.calculateroots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public UUID workerID = null;
    public SingleCalculateDataBaseImpl dataBase = null;
    WorkManager workManager;
SingleCalculateApplication singeltonApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singeltonApp=(SingleCalculateApplication) getApplication();
        workManager = WorkManager.getInstance(this);

        workManager.cancelAllWork();
        workManager.pruneWork();

        if (savedInstanceState == null || !savedInstanceState.containsKey("dataBase")) {
            dataBase = singeltonApp.getDataBase();
        } else {
            dataBase = (SingleCalculateDataBaseImpl) savedInstanceState.getSerializable("dataBase");
        }



        RecyclerView recyclerTodoItemsList = findViewById(R.id.recyclerCalculateRoot);
        EditText editTextInsertNumber = findViewById(R.id.editTextInsertNumber);
        FloatingActionButton buttonStartCalculate = findViewById(R.id.buttonAddNumberToCalculate);

        SingleCalculateAdapter adapter = new SingleCalculateAdapter(dataBase,this);
        recyclerTodoItemsList.setAdapter(adapter);
        recyclerTodoItemsList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        LiveData<List<WorkInfo>> allCalculateRoots = workManager.getWorkInfosByTagLiveData("calculate_root");
        allCalculateRoots.observe(this,new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
//                System.out.println("hello");
                        for (WorkInfo workInfo : workInfos) {
                            Data progress = workInfo.getProgress();
                            String id = workInfo.getId().toString();
                            Data outputData = workInfo.getOutputData();
                            if (workInfo.getState() == WorkInfo.State.RUNNING) {
                                dataBase.inProgress(progress.getInt("current_pro", -50), id);
                                adapter.notifyDataSetChanged();

                            } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                dataBase.finishProgress(outputData.getLong("first_root", -1), outputData.getLong("second_root", -1), workInfo.getId().toString());
                                adapter.notifyDataSetChanged();

                            }


                        }
                    }
                });
//
//
//
//
//
//
//
//                    if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
//                        Data outputData = workInfo.getOutputData();
//                        dataBase.finishProgress(outputData.getLong("first_root", -1), outputData.getLong("second_root", -1), workInfo.getId().toString());
////                        System.out.println("nummmmm"+outputData.getLong("first_root",0));
//                    } else {
//                        Data progress = workInfo.getProgress();
//                        dataBase.inProgress(progress.getLong("current", -1), workInfo.getId().toString());
//
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });


        //        dataBase.getToDoItemLiveDataPublic().observe(this, new Observer<List<TodoItem>>() {
//            @Override
//            public void onChanged(List<TodoItem> todoItems) {
//                {
//                    adapter.notifyDataSetChanged();
//
//                }
//            }
//        });

        editTextInsertNumber.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                String newText = editTextInsertNumber.getText().toString();
            }
            // text did change
        });
        buttonStartCalculate.setOnClickListener(v -> {
            String userInputString = editTextInsertNumber.getText().toString();
            if (!userInputString.equals("")) {
                long userInputLong=Long.parseLong(userInputString);
                SingleCalculate newSingleCalculate=new SingleCalculate(userInputLong);
                OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class).addTag("calculate_root")
                        .setInputData(new Data.Builder().putLong("inputNumber", userInputLong).build()).build();
                workManager.enqueue(request);
                newSingleCalculate.id=request.getId().toString();
                dataBase.addItem(newSingleCalculate);
                adapter.notifyDataSetChanged();
                editTextInsertNumber.setText("");
            }
        });


    }
}

//
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.Observer;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.work.Data;
//import androidx.work.OneTimeWorkRequest;
//import androidx.work.WorkInfo;
//import androidx.work.WorkManager;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.List;
//import java.util.UUID;
//
////import huji.postpc.y2021.extremerootscalculation.workers.CalculateRootsWorker;
//
//public class MainActivity extends AppCompatActivity {
//
//    SingleCalculateApplication app;
//    WorkManager workManager;
//    SingleCalculateDataBaseImpl database;
//    SingleCalculateAdapter adapter;
////    Data.Builder builder;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        app = (SingleCalculateApplication) getApplication();
//        workManager = WorkManager.getInstance(this);
//
//        workManager.cancelAllWork();
//        workManager.pruneWork();
//
//        if (savedInstanceState == null || !savedInstanceState.containsKey("dataBase")) {
//            database = app.getDataBase();
//        } else {
//            database = (SingleCalculateDataBaseImpl) savedInstanceState.getSerializable("dataBase");
//        }
//
//        FloatingActionButton buttonCalculate = findViewById(R.id.buttonAddNumberToCalculate);
//        EditText editTextInsertNumber = findViewById(R.id.editTextInsertNumber);
//        RecyclerView recyclerView = findViewById(R.id.recyclerCalculateRoot);
//
//        LinearLayoutManager layout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(layout);
//
//        adapter = new SingleCalculateAdapter(  database,this);
//        recyclerView.setAdapter(adapter);
//
//        // listen to the status (liveData) of the request
//        LiveData<List<WorkInfo>> allLiveData = workManager.getWorkInfosByTagLiveData("calculation");
//        allLiveData.observe(this, new Observer<List<WorkInfo>>() {
//            @Override
//            public void onChanged(List<WorkInfo> workInfos) {
//                for (WorkInfo workInfo : workInfos) {
//                    Data progress = workInfo.getProgress();
//                    String id = workInfo.getId().toString();
//                    Data workData = workInfo.getOutputData();
//                    if (workInfo.getState() == WorkInfo.State.RUNNING) {
//                        int calcProgress = progress.getInt("current", 0);
//                        database.inProgress(calcProgress,id );
//                        adapter.notifyDataSetChanged();
//                    } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
//                        long root1 = workData.getLong("first_root", -1);
//                        long root2 = workData.getLong("second_root", -1);
//                        database.finishProgress( root1, root2,id);
//                        adapter.notifyDataSetChanged();
//                    }
//
//                }
//            }
//        });
//
//        buttonCalculate.setOnClickListener(v -> {
//            long inputNumber = Long.parseLong(editTextInsertNumber.getText().toString());
//            SingleCalculate newCalculation = new SingleCalculate("","Calculating roots for "+String.valueOf(inputNumber),inputNumber,0);
//            setOneTimeWorkRequest(newCalculation);
//            database.addItem(newCalculation);
//            adapter.notifyDataSetChanged();
//            editTextInsertNumber.setText("");
//        });
//    }
//
//    private void setOneTimeWorkRequest(SingleCalculate calculation) {
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculateRootsWorker.class)
//                .addTag("calculation")
//                .setInputData(
//                        new Data.Builder()
//                                .putLong("inputNumber", calculation.getInputNumber())
//                                .build()
//                )
//                .build();
//        workManager.enqueue(request);
//
//        calculation.setId(  request.getId().toString());
//        System.out.println("first id: " + request.getId());
//    }
//}