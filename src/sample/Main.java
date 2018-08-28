package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage inputStage;
    int arra[]=new int [100];
    Label labels[]=new Label[10];
    int inputlength=0;
    AnchorPane pane=new AnchorPane();

    Stage primaryStage;
    //    Thread t;
    public int top=0;
    public int length=0;
    public int arraySize=100;
    public AnchorPane root;
    public  int arr[] =new int [arraySize];
  //  Label[] labels=new Label[10];
    int ind;
    int start=0,end=length-1;
    int item;
    int result=0;
    Controller control;
    long lastframe=0;
    int  frameconut=0;
    double p;
    int shit[][]=new int [100][15];
    int len=0;
    public void createLabelQuick(Controller c){
      //  Label label[]=new Label[10];
        for(int i=0;i<c.main.inputlength;i++){
            labels[i]=new Label(""+c.main.arra[i]);
            labels[i].setFont(new Font("Times new Roman",15));
            labels[i].setTextFill(Color.BLACK);
            labels[i].setLayoutX(100+i*50);
            labels[i].setLayoutY(250);
            c.main.pane.getChildren().add(labels[i]);
        }
    }
    public void timer(Controller control){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(updateGameState(now,control)==-1) {
                    this.stop();
                    System.out.println("Sorted");
                }
            }
        }.start();
    }
    public  int updateGameState(long now,Controller co){
        double p=(now-lastframe)/100000;
        if(p>=15000.0) {
            lastframe=now;
            int l=co.main.shit[frameconut][co.main.length];
            int m=co.main.shit[frameconut][co.main.length+1];
            int n=co.main.shit[frameconut][co.main.length+2];
            for(int i=0;i<co.main.length;i++) {
                co.main.labels[i].setText(co.main.shit[frameconut][i]+"");
                if(m!=i && l!=i && n!=i)co.main.labels[i].setTextFill(Color.BLUE);
            }
            co.main.labels[co.main.shit[frameconut][co.main.length]].setTextFill(Color.RED);
            if(m!=-1) co.main.labels[m].setTextFill(Color.BROWN);
            if(n!=-1) co.main.labels[n].setTextFill(Color.BROWN);
            System.out.println("This: "+frameconut);
            frameconut++;
        }
        if(frameconut>=len) {
            if(co.main.length==1) {
                co.main.labels[0].setText(co.main.arra[0]+"");
            }
            co.main.labels[co.main.shit[frameconut-1][co.main.length]].setTextFill(Color.BLUE);
            return -1;
        }
        else return 0;
    }

    public void showInputPage(Stage primaryStage) throws IOException {
        this.primaryStage.close();
        inputlength=0;
        AnchorPane pane=new AnchorPane();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent parent=loader.load();
        Controller c=loader.getController();
        c.setMain(this);
        pane.getChildren().add(parent);
        primaryStage.setTitle("User Input");
        primaryStage.setScene(new Scene(pane, 600,600));
        primaryStage.show();
        this.primaryStage=primaryStage;
        this.pane=pane;
        inputStage=primaryStage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        inputlength=0;
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent parent=loader.load();
        Controller c=loader.getController();
        c.setMain(this);
        pane.getChildren().add(parent);
        primaryStage.setTitle("User Input");
        primaryStage.setScene(new Scene(pane, 600,600));
        primaryStage.show();
        this.primaryStage=primaryStage;
        inputStage=primaryStage;
    }
    public void initializeinput(){
        length=0;
        inputlength=0;
    }
    public void addElement(int item){
        labels[inputlength]=new Label(item + "");
        labels[inputlength].setFont(new Font("Times new Roman",15));
        labels[inputlength].setLayoutY(200);
        labels[inputlength].setLayoutX((inputlength*50)+100);
        labels[inputlength].setTextFill(Color.BLACK);
        arra[inputlength]=item;
        pane.getChildren().add(labels[inputlength++]);

    }
    public void showSelection(Controller co,int isbackfromsort) throws IOException {
        if(isbackfromsort==1) co.main.primaryStage.close();
        AnchorPane pane=new AnchorPane();
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("selection.fxml"));
        Parent parent=loader.load();
        Controller c=loader.getController();
        c.setMain(this);
        pane.getChildren().add(parent);
        primaryStage.setTitle("User Selection");
        primaryStage.setScene(new Scene(pane, 600,600));
        primaryStage.show();
        this.primaryStage=primaryStage;
        this.pane=pane;
    }
    public void searchSelected(){

    }
    public void sortSelected(Controller co) throws IOException {
        co.main.primaryStage.close();
        AnchorPane pane=new AnchorPane();
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("sortpage.fxml"));
        Parent parent=loader.load();
        Controller c=loader.getController();
        c.setMain(this);
        pane.getChildren().add(parent);
        primaryStage.setTitle("Sort Simulation");
        primaryStage.setScene(new Scene(pane, 1200,1200));
        primaryStage.show();
        this.primaryStage=primaryStage;
        this.pane=pane;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
