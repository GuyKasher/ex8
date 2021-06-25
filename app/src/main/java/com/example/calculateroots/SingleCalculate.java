package com.example.calculateroots;

import java.io.Serializable;
import java.util.UUID;

public class SingleCalculate implements Serializable {

    String id;
    String text;
    long inputNumber;

    public SingleCalculate(){
        this.id= UUID.randomUUID().toString();
        this.text="";
        this.inputNumber=0;
    }
    public SingleCalculate(String id,String text,long inputNumber){
        this.id=id;
        this.text=text;
        this.inputNumber=inputNumber;
    }

    public void setInputNumber(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getText() {
        return text;
    }

    public long getInputNumber() {
        return inputNumber;
    }
}
