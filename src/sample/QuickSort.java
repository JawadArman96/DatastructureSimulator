package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class QuickSort {
    Thread t;
    Controller control;
    long lastFrame=0;
    long frameCount=0;
    QuickSort(Controller c) throws InterruptedException {
        control=c;
        quicksort(control.main.arr,0,control.main.length-1);

    }

    public void quicksort(int [] a,int l,int h) throws InterruptedException {
        if(l<h){
            int p=partition(a,l,h);
            int x;
            for (x =0;x< control.main.length;x++) control.main.shit[control.main.len][x]=control.main.arr[x];
            control.main.shit[control.main.len][x]=p;
            control.main.shit[control.main.len][x+1]=-1;
            control.main.shit[control.main.len][x+2]=-1;
            control.main.len++;
            quicksort(a, l, p - 1);
            quicksort(a,p+1,h);
        }
    }
    //11 23 33 22 24 14
    //5
    public int partition(int [] a,int l,int h) throws InterruptedException {
        int x=a[h];
        int y;
        int j,i=l-1;
        int p;
        Label la;

        for( j=l;j<=h-1;j++){
            if(a[j]<=x) {
                i += 1;
                p = a[i];
                a[i] = a[j];
                a[j] = p;
                for (y =0;y< control.main.length;y++) control.main.shit[control.main.len][y]=control.main.arr[y];
                control.main.shit[control.main.len][y]=h;
                control.main.shit[control.main.len][y+1]=i+1;
                control.main.shit[control.main.len][y+2]=j;
                control.main.len++;
            }
            else {
                for (y =0;y< control.main.length;y++) control.main.shit[control.main.len][y]=control.main.arr[y];
                control.main.shit[control.main.len][y]=h;
                control.main.shit[control.main.len][y+1]=i+1;
                control.main.shit[control.main.len][y+2]=j;
                control.main.len++;

            }
        }
        p=a[i+1];
        a[i+1]=a[h];
        a[h]=p;
        return i+1;
    }
}
