package com.example.calculateroots;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SingleCalculateHolder extends RecyclerView.ViewHolder {
    TextView rootProgress;
    Button deleteButton;
    ProgressBar progressBar;
    private final Context context;


    public SingleCalculateHolder(@NonNull View itemView) {
        super(itemView);
        context=itemView.getContext();
        this.rootProgress = itemView.findViewById(R.id.root_progress_text);
        this.deleteButton=itemView.findViewById(R.id.deleteButton);
        this.progressBar=itemView.findViewById(R.id.progress_bar);

    }
    public Context getContext() {
        return context;
    }

}
