package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Controller {
    Main main;
    @FXML
    Button clearAll;
    @FXML
    Button go;
    @FXML
    Button enter;
    @FXML
    TextField item;
    @FXML
    Button backfromselection;
    @FXML
    public void backfromseclect(ActionEvent event) throws Exception {
        main.inputStage.close();
        Stage primaryStage=new Stage();
        main.showInputPage(primaryStage);
    }
    @FXML
    public void clearall(ActionEvent event){
        item.setText("");
        for(int i=0;i<main.inputlength;i++) main.labels[i].setText("");
        main.inputlength=0;
    }
    @FXML
    public void inputAction(ActionEvent event){
        String a=item.getText();
        Integer i=new Integer(a);
        int element=i.intValue();
        main.addElement(element);
        item.setText("");
    }
    public void gotoAction(ActionEvent event) throws IOException {
        if(isbackfromsort==1) main.showSelection(this,1);
        else {
            isbackfromsort=0;
            main.showSelection(this,0);
        }
    }
    @FXML
    Button sorting;
    @FXML
    Button searching;
    public void sortselected(ActionEvent event) throws IOException {
        main.sortSelected(this);
    }
    public void searchselected(ActionEvent event){

    }
    int isbackfromsort=0;
    @FXML
    Button bubble;
    @FXML
    Button quick;
    @FXML
    Button radixsort;
    @FXML
    Button back;
    public void backfromsort(ActionEvent event) throws IOException {
        isbackfromsort=1;
        gotoAction(event);
    }
    public void startQuickSort(ActionEvent event) throws InterruptedException {

            for(int i=0;i<main.inputlength;i++) main.arr[i]=main.arra[i];
            main.length=main.inputlength;
            System.out.println("");
            main.createLabelQuick(this);
            QuickSort ob=new QuickSort(this);
            main.timer(this);
    }

    public void setBubble(BubbleSort mai){
        bubblesort=mai;
    }
    BubbleSort bubblesort;
    public  int i=0;
    public  int flag=0;
    public  int a=0,b=1;
    public long lastframe=0;
    void updatestate(long now) throws InterruptedException {
        double p=(now-lastframe)/100;
        if(p>=300000.0){
            flag=1;
            if(bubblesort.sortflag==0) {
                bubblesort.sort(i - 1, a, b,this);
                if (b == i - 1) {
                    a++;
                    b = a+1;
                    return;
                } else {
                    b++;
                    return;
                }
            }
        }
        else {

        }
    }
    @FXML

    public void startBubbleSort(ActionEvent event) throws Exception {
        bubblesort=new BubbleSort();
        for(int j=0;j<main.inputlength;j++) {
            if(i<10 && flag==0) {
                bubblesort.numbers[i++] =main.arra[j];
                System.out.println(bubblesort.numbers[i-1]);
                bubblesort.addElement(main);
            }
            else{
                System.out.println("Cant insert");
                flag=1;
            }
        }
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    updatestate(now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
   // radix sort
    int callRadix=0;
    public  RadixSort mai;
    public  int radixi=0;
    public  int radixflag=0;
    public  int radixsecondflag=0;
    public  int radixa=0,radixb=1;
    public  int radixpass=0;
    public  int radixpass2;
    public int radixlastframe=0;
    void setRadix(RadixSort main){
        this.mai=main;
    }
    int radixba1=0;

    void radixsortstarter()throws Exception{

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(radixba1==0) try {
                    radixba1=updateState(now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                else if(radixba1==2) try {
                    radixba1=updateState(now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public int updateState(long now) throws InterruptedException {
        double p=(now-radixlastframe)/10000000.0;
        if(p<1500.0) return 0;
        if(radixflag==0){
            mai.calculations(radixi-1);
            radixflag=1;
        }

        if(mai.sortflag==1) return 1;
        if(mai.sortflag==0) { //sortflag will be 1 when sorting for all the digits will be done

            if (radixpass < radixi) { //counts....needs pausing
                mai.dig.setText("digit" + mai.digitflag);
                mai.radix(radixpass, radixi);
                radixpass++;
            }

            if (radixpass == radixi && radixsecondflag == 0) { //generates sum array after finishing counting.
                mai.sumup(radixi - 1);
                radixsecondflag = 1;
                radixpass2 = radixi - 1;
            }

            if (radixsecondflag == 1 && radixpass2 >= 0) {//needs pausing
                mai.digitsort(radixpass2);
                radixpass2--;
            }
            if (radixpass2 < 0) { //sets up sorting for the next digit
                mai.reset(radixi - 1);
                mai.calculations(radixi - 1);
                radixpass=0;
                radixsecondflag=0;
                radixpass2=radixi-1;
            }
        }
        Thread.sleep(2000);
        return 2;
    }
    @FXML
    void radixsortstarts (ActionEvent event) throws Exception{
        if(callRadix==0) {
            mai=new RadixSort();
            mai.start(this);
            callRadix=1;
        }
        for(int x=0;x<main.inputlength;x++) {
            if (radixi < 7 && radixflag == 0) {
                mai.numbers[radixi++] = main.arra[x];
            } else {
                System.out.println("Cant insert");
                radixflag = 1;
            }
        }
        radixsortstarter();

    }


    public void setMain(Main aa){
        main=aa;
    }
}
