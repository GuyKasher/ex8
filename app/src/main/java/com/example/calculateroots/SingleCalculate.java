package com.example.calculateroots;

import java.io.Serializable;
import java.util.UUID;

public class SingleCalculate implements Serializable {

    String id;
    String text;

    public SingleCalculate(){
        this.id= UUID.randomUUID().toString();
        this.text="";
    }

    public void setText(String text) {
        this.text = text;
    }
}
