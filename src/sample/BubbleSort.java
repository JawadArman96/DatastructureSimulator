package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Controller;

public class BubbleSort  {

    Main main;
    public Controller controller;
    Label[] labels = new Label[10];
    public int[] numbers = new int [10];
    public int sortflag=0;
    public int length=0;
    BubbleSort(){}
    public void addElement(Main main) throws Exception{
        labels[length]=new Label(""+main.arra[length]);
        labels[length].setLayoutX(700+ length*50);
        labels[length].setLayoutY(125);
        labels[length].setTextFill(Color.BLACK);
        main.pane.getChildren().add(labels[length]);
        length++;
    }

    void sort(int index,int k1,int k2,Controller conn) throws InterruptedException {
        //Syste
        int temp;
        if(conn.main.inputlength==1) {
            sortflag=1;
            return;
        }
        if(k1==index-1 && k2== index){
            if(numbers[k2]<numbers[k1]){
                temp=numbers[k1];
                numbers[k1]=numbers[k2];
                numbers[k2]=temp;
                labels[k1].setText(numbers[k1] + "");
                labels[k2].setText(numbers[k2] + "");
            }
            sortflag=1;
            for(int m=0;m<=index;m++){
                labels[m].setLayoutY(125);
                labels[m].setTextFill(Color.BLACK);
            }
            Thread.sleep(2000);
            return;
        }
        for(int m=0;m<=index;m++){
            labels[m].setLayoutY(125);
            labels[m].setTextFill(Color.BLACK);
        }

        labels[k1].setLayoutY(400);
        labels[k2].setLayoutY(400);
        labels[k1].setTextFill(Color.RED);
        labels[k2].setTextFill(Color.RED);

        if(numbers[k2]<numbers[k1]){
            temp=numbers[k1];
            numbers[k1]=numbers[k2];
            numbers[k2]=temp;
            labels[k1].setText(numbers[k1] + "");
            labels[k2].setText(numbers[k2] + "");

        }
        Thread.sleep(2000);

    }




}
