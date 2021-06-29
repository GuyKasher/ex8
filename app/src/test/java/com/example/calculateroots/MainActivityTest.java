package com.example.calculateroots;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.InstrumentationRegistry;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.UUID;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MainActivityTest extends TestCase {

    private ActivityController<MainActivity> activityController;
    private  SingleCalculateDataBaseImpl mockDataBase;

    @Before
    public void setup(){
        mockDataBase = Mockito.mock(SingleCalculateDataBaseImpl.class);
        // when asking the `mockDataBase` to get the current items, return an empty list
        Mockito.when(mockDataBase.getCurrentItems())
                .thenReturn(null);

        activityController = Robolectric.buildActivity(MainActivity.class);

        // let the activity use our `mockDataBase` as the TodoItemsDataBase
        MainActivity activityUnderTest = activityController.get();
        activityUnderTest.dataBase = mockDataBase;
    }


    @Test
    public void when_addingItem_then_ListOfItemShouldHaveThisItem() {
        // setup
        Context context = InstrumentationRegistry.getTargetContext();

        SingleCalculateDataBaseImpl dataBaseTest = new SingleCalculateDataBaseImpl(context);
        Assert.assertEquals(0, dataBaseTest.getCurrentItems().size());
        dataBaseTest.addItem(new SingleCalculate());
        Assert.assertEquals(1, dataBaseTest.getCurrentItems().size());


    }
    @Test
    public void when_addingAndDeleteItem_then_ListOfItemShouldHaveTheCorrectItem() {
        // setup
        Context context = InstrumentationRegistry.getTargetContext();

        SingleCalculateDataBaseImpl dataBaseTest = new SingleCalculateDataBaseImpl(context);
        Assert.assertEquals(0, dataBaseTest.getCurrentItems().size());
        dataBaseTest.addItem(new SingleCalculate());
        Assert.assertEquals(1, dataBaseTest.getCurrentItems().size());
        dataBaseTest.deleteItem(dataBaseTest.getCurrentItems().get(0));
        Assert.assertEquals(0, dataBaseTest.getCurrentItems().size());


    }

    @Test
    public void when_addingFewNumbersWithDifferentConstructors_then_ListOfItemShouldHaveTheCorrectNumberOfItems() {
        // setup
        Context context = InstrumentationRegistry.getTargetContext();

        SingleCalculateDataBaseImpl dataBaseTest = new SingleCalculateDataBaseImpl(context);
        Assert.assertEquals(0, dataBaseTest.getCurrentItems().size());
        dataBaseTest.addItem(new SingleCalculate());
        Assert.assertEquals(1, dataBaseTest.getCurrentItems().size());
        dataBaseTest.addItem(new SingleCalculate(5555555));
        Assert.assertEquals(2, dataBaseTest.getCurrentItems().size());
        dataBaseTest.addItem(new SingleCalculate(UUID.randomUUID().toString(),"hello",55,0,3,1,1));
//    public SingleCalculate(String id,String text,long inputNumber, long currentNumberInCalculation, int progress,long root1, long root2){

        Assert.assertEquals(3, dataBaseTest.getCurrentItems().size());



    }


}
