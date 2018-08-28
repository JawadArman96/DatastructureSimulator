package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Scanner;

public class RadixSort{

    public Controller controller;

    Label[] labels = new Label[10];
    Label[] indexlabels = new Label[10];
    Label[] countlabels = new Label[10];
    Label[] sclabels = new Label[10];
    Label[] digits = new Label[10];
    Label[] sortedlabels = new Label[10];
    public Label sc;
    public Label dig;
    public Label srt;
    public int[] numbers = new int [10];
    public int[] unsorted = new int[10];
    public int[] counts= new int [10];
    public int[] sumcounts= new int [10];
    public int[] numbers2 = new int [10];
    public int[] sorted = new int[10];
    public int digitflag=1;
    public int max;
    public int maxlen;
    public int sortflag=0;

    public void start(Controller conn) throws Exception{
        //Stage primaryStage=conn.main.primaryStage;
        AnchorPane anch=conn.main.pane;
        for(int i=0;i<10;i++){
            labels[i]=new Label("");
            labels[i].setLayoutY(400);
            labels[i].setLayoutX(100+50*i);
            labels[i].setTextFill(Color.BLACK);
            anch.getChildren().add(labels[i]);
        }
        Label ind = new Label("");
        ind.setLayoutX(70);
        ind.setLayoutY(500);
        ind.setTextFill(Color.BROWN);
        ind.setText("INDEX");
        anch.getChildren().add(ind);
        for(int i=0;i<10;i++){
            indexlabels[i] = new Label("");
            indexlabels[i].setLayoutY(500);
            indexlabels[i].setLayoutX(120 + i * 50);
            indexlabels[i].setTextFill(Color.BLUE);
            indexlabels[i].setText(i + "");
            anch.getChildren().add(indexlabels[i]);
        }
        dig = new Label("");
        dig.setLayoutX(70);
        dig.setLayoutY(450);
        dig.setTextFill(Color.BROWN);
        anch.getChildren().add(dig);
        for(int i=0 ;i<10;i++){
            digits[i]=new Label("");
            digits[i].setLayoutY(450);
            digits[i].setLayoutX(120 + i * 50);
            digits[i].setTextFill(Color.BLUE);
            anch.getChildren().add(digits[i]);
        }
        sc= new Label("");
        sc.setLayoutX(70);
        sc.setLayoutY(600);
        sc.setTextFill(Color.BROWN);
        //sc.setText("SUMS");
        anch.getChildren().add(sc);
        for(int i=0;i<10;i++){
            sclabels[i] = new Label("");
            sclabels[i].setLayoutY(600);
            sclabels[i].setLayoutX(120 + i * 50);
            sclabels[i].setTextFill(Color.BLUE);
            anch.getChildren().add(sclabels[i]);
        }
        Label c = new Label("");
        c.setLayoutX(57);
        c.setLayoutY(550);
        c.setTextFill(Color.BROWN);
        c.setText("COUNTS");
        anch.getChildren().add(c);
        for(int i=0;i<10;i++){
            countlabels[i] = new Label("");
            countlabels[i].setLayoutY(550);
            countlabels[i].setLayoutX(120 + i * 50);
            countlabels[i].setTextFill(Color.BLUE);
            countlabels[i].setText("0");
            anch.getChildren().add(countlabels[i]);
        }
        srt= new Label("");
        srt.setLayoutX(65);
        srt.setLayoutY(650);
        srt.setTextFill(Color.BROWN);
        anch.getChildren().add(srt);
        for(int i=0;i<10;i++){
            sortedlabels[i] = new Label("");
            sortedlabels[i].setLayoutY(650);
            sortedlabels[i].setLayoutX(120 + i * 50);
            sortedlabels[i].setTextFill(Color.BLUE);
            anch.getChildren().add(sortedlabels[i]);
        }

     /*
        primaryStage.setTitle("Radix sort");
        primaryStage.setScene(new Scene(anch, 1000, 750));
        primaryStage.show();
        */
    }

    void calculations(int index){
        max=numbers[0];
        for(int m=0;m<=index;m++){
            //System.out.println(numbers[m]);
            if(numbers[m]>max){
                max=numbers[m];
            }
        }
        System.out.println("max is " + max);
        maxlen=0;
        int copy=max;
        while(copy!=0){
            copy=copy/10;
            maxlen++;
        }
        System.out.println("max len is " + maxlen);
        for(int m=0;m<=index;m++){
            copy=numbers[m];
            int len=0;
            while (copy != 0) {
                copy=copy/10;
                len++;
            }
            while(len<maxlen){
                String s= labels[m].getText();
                labels[m].setText("0"+s);
                len++;
            }
        }
        for(int j=0;j<10;j++){
            counts[j]=0;
            sumcounts[j]=0;
        }

    }
    void radix(int index,int final_index){
        for(int i=0;i<10;i++){
            labels[i].setTextFill(Color.BLACK);
        }
        labels[index].setTextFill(Color.RED);
        int copy2=numbers[index];
        int m=1;
        while(m<digitflag){
            copy2=copy2/10;
            m++;
        }
        int mod=copy2%10;
        unsorted[index]=mod;
        digits[index].setText(mod+"");
        counts[mod]++;
        countlabels[mod].setText(counts[mod]+"");
    }
    void sumup(int index){
        sc.setText("SUMS");
        srt.setText("sorted");
        for(int i=0;i<10;i++){
            for(int j=0;j<=i;j++){
                sumcounts[i]=sumcounts[i]+counts[j];
            }
            sclabels[i].setText(sumcounts[i]+"");
            labels[i].setTextFill(Color.BLACK);
            sortedlabels[i].setText("");
        }
    }
    void digitsort(int index){
        int n= sumcounts[unsorted[index]];
        n--;
        sorted[n]=unsorted[index];
        sortedlabels[n].setText(sorted[n]+"");
        sumcounts[unsorted[index]]--;
        sclabels[unsorted[index]].setText(sumcounts[unsorted[index]] + "");
        numbers2[n]=numbers[index];
    }
    void reset(int index){
        for(int i=0;i<=index;i++){
            numbers[i]=numbers2[i];
            labels[i].setText(numbers[i]+"");
        }
        sc.setText("");
        //srt.setText("");
        dig.setText("");
        digitflag++;

        for(int i=0;i<10;i++){
            countlabels[i].setText("0");
            sclabels[i].setText("");
            digits[i].setText("");
            counts[i]=-1;
            sumcounts[i]=-1;
            unsorted[i]=-1;
            sorted[i]=-1;
        }
        if(digitflag>maxlen) sortflag=1;
    }




}
