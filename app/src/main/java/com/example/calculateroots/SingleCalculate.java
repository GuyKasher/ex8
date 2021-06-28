package com.example.calculateroots;

import java.io.Serializable;
import java.util.UUID;

public class SingleCalculate implements Serializable {

    String id;
    String text;
    long inputNumber;
    long currentNumberInCalculation;
    int progress;
    long root1;
    long root2;
    public SingleCalculate(long inputNumber){
        this.inputNumber=inputNumber;
        this.currentNumberInCalculation=0;
        this.progress=0;
        this.text="Calculating roots for "+(inputNumber);
        this.id=" ";
        this.root1=0;
        this.root2=0;
    }

    public SingleCalculate(String id,String text,long inputNumber, long currentNumberInCalculation, int progress,long root1, long root2){
        this.id=id;
        this.text=text;
        this.inputNumber=inputNumber;
        this.currentNumberInCalculation=currentNumberInCalculation;
        this.progress=progress;
        this.root1=root1;
        this.root2=root2;
    }

    public SingleCalculate() {

    }

    public String itemToString() {
        return (this.id) + "#" + this.text + "#" + this.inputNumber
                +"#"+this.currentNumberInCalculation+"#"+this.progress+"#"+this.root1+"#"+this.root2;
    }

    public SingleCalculate stringToSingleCalculate(String itemString) {
        try {
            String[] split = itemString.split("#");
            String id = (split[0]);
            String text = split[1];
            long inputNumber=Long.parseLong(split[2]);
            long currentNumber=Long.parseLong(split[3]);
            int progress=Integer.parseInt(split[4]);
            long root1=Long.parseLong(split[5]);
            long root2=Long.parseLong(split[6]);
            return new SingleCalculate(id,text,inputNumber,currentNumber,progress,root1,root2);

        } catch (Exception e) {
            return null;
        }
    }

    public void setText(String text) {
        this.text = text;
    }

}
