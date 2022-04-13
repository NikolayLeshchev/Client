package by.palto.controller;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.command.factory.CommandFactory;
import by.palto.domain.entity.Client;
import by.palto.message.Message;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
            launch(args);
    }

    public static void changeScene(String nextStagePath, int width, int height) {

        try {
            Parent root = FXMLLoader.load(Main.class.getResource(nextStagePath));
            Scene second = new Scene(root,width,height);
            primaryStage.setTitle("");
            primaryStage.setResizable(false);
            primaryStage.setScene(second);
        } catch (IOException ignore) {

        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
//        Main.primaryStage = primaryStage;
//        Parent root = FXMLLoader.load(getClass().getResource(Pages.MAIN_TABLE));
//        primaryStage.setTitle("Main table");
//        primaryStage.setScene(new Scene(root, 1500, 600));
//        primaryStage.show();


        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(Pages.MAIN_MENU));
        primaryStage.setTitle("Main table");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void exit() {
        System.exit(0);
    }

    public void showClientsTable(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Pages.MAIN_CLIENT_TABLE));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }

    public void addNewDeposit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Pages.ADD_DEPOSIT));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }

    public void finishTheDay(ActionEvent actionEvent) {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.END_OF_THE_DAY);
        Message message = new Message();
        Message response = command.execute(message);

        ShowAlert.showSuccessfulAlert("День прошел");

    }

    public void addNewCredit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Pages.ADD_CREDIT));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }
}
