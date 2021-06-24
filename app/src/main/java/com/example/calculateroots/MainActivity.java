package com.example.calculateroots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public UUID workerID = null;
    public SingleCalculateDataBaseImpl dataBase = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null || !savedInstanceState.containsKey("dataBase")) {
            dataBase = SingleCalculateApplication.getInstance().getDataBase();
        } else {
            dataBase = (SingleCalculateDataBaseImpl) savedInstanceState.getSerializable("dataBase");
        }

        RecyclerView recyclerTodoItemsList = findViewById(R.id.recyclerCalculateRoot);
        EditText editTextInsertNumber = findViewById(R.id.editTextInsertNumber);
        FloatingActionButton buttonStartCalculate = findViewById(R.id.buttonAddNumberToCalculate);

        SingleCalculateAdapter adapter = new SingleCalculateAdapter(dataBase);
        recyclerTodoItemsList.setAdapter(adapter);
        recyclerTodoItemsList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

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
                dataBase.addItem(Integer.parseInt(userInputString));
                adapter.notifyDataSetChanged();
                editTextInsertNumber.setText("");
            }
        });


    }
}