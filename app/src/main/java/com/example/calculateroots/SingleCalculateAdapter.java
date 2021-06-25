package com.example.calculateroots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SingleCalculateAdapter extends RecyclerView.Adapter<SingleCalculateHolder> {
    private SingleCalculateDataBaseImpl singleCalculateDataBaseImpl = SingleCalculateApplication.getInstance().getDataBase();

    public SingleCalculateAdapter(@NonNull SingleCalculateDataBaseImpl singleCalculateDataBaseImpl) {
        this.singleCalculateDataBaseImpl = singleCalculateDataBaseImpl;
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

        SingleCalculate singleCalculate = this.singleCalculateDataBaseImpl.getCurrentItems().get(position);



        holder.deleteButton.setOnClickListener(v -> {

            singleCalculateDataBaseImpl.deleteItem(singleCalculate);
//            this.todoItemsDataBase.sortItems();
            this.notifyDataSetChanged();

        });

        holder.rootProgress.setText(singleCalculate.getText());


//        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {

        return this.singleCalculateDataBaseImpl.getCurrentItems().size();
    }
}

