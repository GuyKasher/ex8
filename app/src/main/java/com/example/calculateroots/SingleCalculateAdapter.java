package com.example.calculateroots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

public class SingleCalculateAdapter extends RecyclerView.Adapter<SingleCalculateHolder> {
     SingleCalculateDataBaseImpl singleCalculateDataBaseImpl ;
    Context c;
    public SingleCalculateAdapter(@NonNull SingleCalculateDataBaseImpl singleCalculateDataBaseImpl, Context c) {
        this.singleCalculateDataBaseImpl = singleCalculateDataBaseImpl;
        this.c=c;
    }


    @NonNull
    @Override
    public SingleCalculateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.calculate_root_row, parent, false);
        return new SingleCalculateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleCalculateHolder holder, int position) {
//
        SingleCalculate singleCalculate = this.singleCalculateDataBaseImpl.items.get(position);
//        int currentPresent= (int) (singleCalculate.getCurrentNumberInCalculation()/singleCalculate.getInputNumber()*100);
        holder.rootProgress.setText(singleCalculate.text);
//        System.out.print(singleCalculate.getText());
        holder.progressBar.setProgress(singleCalculate.progress);
//        holder.rootProgress.setText(holder.getCalculationText(singleCalculate));
        holder.deleteButton.setOnClickListener(v -> {

            singleCalculateDataBaseImpl.deleteItem(singleCalculate);
//            this.todoItemsDataBase.sortItems();
            SingleCalculateApplication.getInstance().getWorkManager().cancelWorkById(UUID.fromString(singleCalculate.id));
            this.notifyDataSetChanged();

        });



//        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {

        return this.singleCalculateDataBaseImpl.getCurrentItems().size();
    }
}

