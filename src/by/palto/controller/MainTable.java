package by.palto.controller;


import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.command.factory.CommandFactory;
import by.palto.domain.entity.Client;

import by.palto.message.Message;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainTable {
    @FXML
    public TableView list_tv_clients;
    @FXML
    private TableColumn<Client, String> columnName;
    @FXML
    public TableColumn<Client, String> columnSurname;
    @FXML
    public TableColumn<Client, String> columnPatronymic;
    @FXML
    public TableColumn<Client, String> columnBirthDate;
    @FXML
    public TableColumn<Client, String> columnSex;
    @FXML
    public TableColumn<Client, String> columnPassSeries;
    @FXML
    public TableColumn<Client, String> columnPassNumber;
    @FXML
    public TableColumn<Client, String> columnPassAuthority;
    @FXML
    public TableColumn<Client, String> columnPassIssueDate;
    @FXML
    public TableColumn<Client, String> columnPassPersonalNumber;
    @FXML
    public TableColumn<Client, String> columnBirthPlace;
    @FXML
    public TableColumn<Client, String> columnCityOfResidence;
    @FXML
    public TableColumn<Client, String> columnResidenceAddress;
    @FXML
    public TableColumn<Client, String> columnHomePhone;
    @FXML
    public TableColumn<Client, String> columnMobilePhone;
    @FXML
    public TableColumn<Client, String> columnEmail;
    @FXML
    public TableColumn<Client, String> columnRegistrationAddress;
    @FXML
    public TableColumn<Client, String> columnMaritalStatus;
    @FXML
    public TableColumn<Client, String> columnCitizenship;
    @FXML
    public TableColumn<Client, String> columnDisability;
    @FXML
    public TableColumn<Client, String> columnPensioner;
    @FXML
    public TableColumn<Client, String> columnMonthIncome;


    public void initialize() {
        initColumns();
        initClientsList();
    }

    private void initClientsList() {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ALL_CLIENTS);
        Message message = new Message();
        Message response = command.execute(message);
        ObservableList<Client> clients = (ObservableList<Client>) response.getByKey("clients");
        list_tv_clients.setItems(clients);
    }

    private void initColumns() {
        // то что в ковычках этого будет искать геттер в классе
        columnName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
        columnPatronymic.setCellValueFactory(new PropertyValueFactory<Client, String>("patronymic"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<Client, String>("birthDate"));
        columnSex.setCellValueFactory(new PropertyValueFactory<Client, String>("sexName"));
        columnPassSeries.setCellValueFactory(new PropertyValueFactory<Client, String>("passSeries"));
        columnPassNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("passNumber"));
        columnPassAuthority.setCellValueFactory(new PropertyValueFactory<Client, String>("passAuthority"));
        columnPassIssueDate.setCellValueFactory(new PropertyValueFactory<Client, String>("passIssueDate"));
        columnPassPersonalNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("passPersonalNumber"));
        columnBirthPlace.setCellValueFactory(new PropertyValueFactory<Client, String>("birthPlace"));
        columnCityOfResidence.setCellValueFactory(new PropertyValueFactory<Client, String>("cityOfResidenceName"));
        columnResidenceAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("residenceAddress"));
        columnHomePhone.setCellValueFactory(new PropertyValueFactory<Client, String>("homePhone"));
        columnMobilePhone.setCellValueFactory(new PropertyValueFactory<Client, String>("mobilePhone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        columnRegistrationAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("registrationAddress"));
        columnMaritalStatus.setCellValueFactory(new PropertyValueFactory<Client, String>("maritalStatusName"));
        columnCitizenship.setCellValueFactory(new PropertyValueFactory<Client, String>("citizenshipName"));
        columnDisability.setCellValueFactory(new PropertyValueFactory<Client, String>("disabilityName"));
        columnPensioner.setCellValueFactory(new PropertyValueFactory<Client, String>("pensioner"));
        columnMonthIncome.setCellValueFactory(new PropertyValueFactory<Client, String>("monthIncome"));

    }



    public void actionAddingButtonPressed(ActionEvent actionEvent) {
        Main.changeScene(Pages.ADD_NEW_CLIENT, 800, 650);
    }

    public void actionEditingButtonPressed(ActionEvent actionEvent) throws IOException, InterruptedException {
        Client clientForEdit = (Client) list_tv_clients.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Pages.EDIT_CLIENT));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(p));

        EditClient editClient = loader.getController();

        editClient.initialize(clientForEdit);
   //     stage.show();
        stage.showAndWait();


        Main.changeScene(Pages.MAIN_CLIENT_TABLE,996,540);
    }

    public void actionDeletingButtonPressed(ActionEvent actionEvent) {

        Client clientForDelete = (Client) list_tv_clients.getSelectionModel().getSelectedItem();

        Command command = CommandFactory.getInstance().getCommand(CommandEnum.DELETE_CLIENT);
        Message message = new Message();

        message.add("deleteClient", clientForDelete);
        Message response = command.execute(message);

//        initClientsList();
        Main.changeScene(Pages.MAIN_CLIENT_TABLE, 996, 540);
    }
}
