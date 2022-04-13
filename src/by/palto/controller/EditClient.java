package by.palto.controller;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.command.factory.CommandFactory;
import by.palto.domain.builder.impl.*;
import by.palto.domain.entity.Client;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

public class EditClient {

    //text
    @FXML
    public TextField addName;
    @FXML
    public TextField addSurname;
    @FXML
    public TextField addPatronymic;
    @FXML
    public TextField birthDateTxtField;
    @FXML
    public CheckBox addPensioner;
    @FXML
    public TextField addPassAuthority;
    @FXML
    public TextField passIssueDateTxtField;
    @FXML
    public TextField addBirthPlace;
    @FXML
    public TextField addPassSeries;
    @FXML
    public TextField addPassNumber;
    @FXML
    public TextField addPassPersonalNumber;
    @FXML
    public TextField addHomePhone;
    @FXML
    public TextField addMobilePhone;
    @FXML
    public ComboBox<String> addCityOfResidence;
    @FXML
    public TextField addResidenceAddress;
    @FXML
    public RadioButton addMale;
    @FXML
    public RadioButton addFemale;
    @FXML
    public TextField addEmail;
    @FXML
    public TextField addRegistrationAddress;
    @FXML
    public ComboBox<String> addMaritalStatus;
    @FXML
    public ComboBox<String> addCitizenship;
    @FXML
    public ComboBox<String> addDisability;
    @FXML
    public TextField addMonthIncome;

    //buttons
    @FXML
    public Button editingButton;


    //error labels
    @FXML
    public Label addNameErrorLabel;
    @FXML
    public Label addSurnameErrorLabel;
    @FXML
    public Label addPatronymicErrorLabel;
    @FXML
    public Label addRegistrationAddressErrorLabel;
    @FXML
    public Label addResidenceAddressErrorLabel1;
    @FXML
    public Label addEmailErrorLabel;
    @FXML
    public Label addMonthIncomeErrorLabel;

    private Client client;

    public void initialize(Client clientForEdit) {
        client = clientForEdit;

        System.out.println(client);
        hideErrorLabels();
        initLists();
        initFields(clientForEdit);
    }

    private void hideErrorLabels() {
        addNameErrorLabel.setVisible(false);
        addSurnameErrorLabel.setVisible(false);
        addPatronymicErrorLabel.setVisible(false);
        addRegistrationAddressErrorLabel.setVisible(false);
        addResidenceAddressErrorLabel1.setVisible(false);
        addEmailErrorLabel.setVisible(false);
        addMonthIncomeErrorLabel.setVisible(false);
    }

    private void initLists() {
        String[] cities = {"Минск", "Гродно", "Витебск", "Гомель", "Могилев", "Брест", "Новополоцк", "Полоцк", "Мозырь","Пинск", "Бобруйск"};
        String[] disabilityGroups = {"-", "1 группа", "2 группа", "3 группа", "4 группа"};
        String[] maritalStatuses = {"Женат/замужем", "Разведен(а)", "Не женат/Не замужем"};
        String[] citizenship = {"Республика Беларусь", "Российская Федерация", "Польша", "Германия", "Украина", "США", "Литва", "Латвия", "Чехия"};

        ObservableList<String> citiesList = FXCollections.observableList(Arrays.asList(cities));
        ObservableList<String> disabilityList = FXCollections.observableList(Arrays.asList(disabilityGroups));
        ObservableList<String> maritalStatusList = FXCollections.observableList(Arrays.asList(maritalStatuses));
        ObservableList<String> citizenshipList = FXCollections.observableList(Arrays.asList(citizenship));

        addCityOfResidence.setItems(citiesList);
        addDisability.setItems(disabilityList);
        addMaritalStatus.setItems(maritalStatusList);
        addCitizenship.setItems(citizenshipList);

    }

    private void initFields(Client client) {
        addName.setText(client.getName());
        addSurname.setText(client.getSurname());
        addPatronymic.setText(client.getPatronymic());
        birthDateTxtField.setText(client.getBirthDate().toString());
        addBirthPlace.setText(client.getBirthPlace());

        addPassSeries.setText(client.getPassport().getPassSeries());
        addPassNumber.setText(Long.toString(client.getPassport().getPassNumber()));
        addPassPersonalNumber.setText(client.getPassport().getPassPersonalNumber());
        addPassAuthority.setText(client.getPassport().getPassAuthority());
        passIssueDateTxtField.setText(client.getPassport().getPassIssueDate().toString());
        addRegistrationAddress.setText(client.getRegistrationAddress());

        addResidenceAddress.setText(client.getResidenceAddress());
        addMonthIncome.setText(Double.toString(client.getMonthIncome()));
        addEmail.setText(client.getEmail());

        addHomePhone.setText(Long.toString(client.getHomePhone()));
        addMobilePhone.setText(Long.toString(client.getMobilePhone()));

        addCitizenship.getSelectionModel().select(client.getCitizenship().getCitizenshipName());
        addMaritalStatus.getSelectionModel().select(client.getMaritalStatus().getMaritalStatusName());
        addCityOfResidence.getSelectionModel().select(client.getCityOfResidence().getResidenceCity());
        addDisability.getSelectionModel().select(client.getDisability().getDisabilityGroup());

        if ("женский".equals(client.getSex().getSexName())) {
            //   addMale.setSelected(false);
            addFemale.setSelected(true);
        } else {
            addMale.setSelected(true);
        }

        if (client.isPensioner()) {
            addPensioner.setSelected(true);
        }

    }


    public void updateClientInTheDatabase(ActionEvent actionEvent) {

        Client client = readEnteredData();
        System.out.println(client);

        Client returnedClient = passClientToTheServer(client);

        if (checkValidatedClient(returnedClient) ) {
            ShowAlert.showSuccessfulAlert("Successful!");
        } else {
            ShowAlert.showErrorAlert("Some fields are wrong!");
        }

    }

    private Client passClientToTheServer(Client client) {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.UPDATE_CLIENT);
        Message message = new Message();
        message.add("updateClient", client);
        Message response = command.execute(message);

        Client returnedClient = (Client) response.getByKey("validatedUpdateClient");

        return returnedClient;
    }

    private Client readEnteredData() {
        ClientBuilderImpl clientBuilder = new ClientBuilderImpl(client.getId_client());

        CityOfResidenceBuilderImpl cityOfResidenceBuilder = new CityOfResidenceBuilderImpl(0);
        MaritalStatusBuilderImpl maritalStatusBuilder = new MaritalStatusBuilderImpl(0);
        DisabilityBuilderImpl disabilityBuilder = new DisabilityBuilderImpl(0);
        CitizenshipBuilderImpl citizenshipBuilder = new CitizenshipBuilderImpl(0);
        SexBuilderImpl sexBuilder = new SexBuilderImpl(0);


        cityOfResidenceBuilder.withResidenceCity(addCityOfResidence.getValue());
        maritalStatusBuilder.withMaritalStatusName(addMaritalStatus.getValue());
        disabilityBuilder.withDisabilityGroup(addDisability.getValue());
        citizenshipBuilder.withCitizenshipName(addCitizenship.getValue());
        sexBuilder.withSexName(addFemale.isSelected() ? "Женский" : "Мужской");

        if (!addMonthIncome.getText().matches("[0-9]+.[0-9]*")) {
            ShowAlert.showErrorAlert("Check your month income!!");
            clientBuilder.withMonthIncome(-1);
            addMonthIncomeErrorLabel.setVisible(true);
        }  else {
            clientBuilder.withMonthIncome(Double.parseDouble(addMonthIncome.getText()));
        }

        if (!addEmail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            //           ShowAlert.showErrorAlert("Check your email!!");
            clientBuilder.withEmail("");
            addEmailErrorLabel.setVisible(true);
        }  else {
            clientBuilder.withEmail(addEmail.getText());
        }

        clientBuilder.
                withName(addName.getText()).
                withSurname(addSurname.getText()).
                withPatronymic(addPatronymic.getText()).

                withBirthDate(client.getBirthDate()).
                withPassport(client.getPassport()).

                withBirthPlace(client.getBirthPlace()).
                withResidenceAddress(addResidenceAddress.getText()).

                withEmail(addEmail.getText()).
              //  withMonthIncome(Double.parseDouble(addMonthIncome.getText())).
                withRegistrationAddress(addRegistrationAddress.getText()).

                withCityOfResidence(cityOfResidenceBuilder.build()).
                withMaritalStatus(maritalStatusBuilder.build()).
                withDisability(disabilityBuilder.build()).
                withCitizenship(citizenshipBuilder.build()).
                withSex(sexBuilder.build()).
                withIsPensioner(addPensioner.isSelected());

        if(addHomePhone.getText().charAt(0) == '+') {
            clientBuilder.withHomePhone(Long.parseLong(addHomePhone.getText().substring(3, 15).replaceAll("[_)-]", "")));
        } else {
            clientBuilder.withHomePhone(client.getHomePhone());
        }

        if(addMobilePhone.getText().charAt(0) == '+') {
            clientBuilder.withMobilePhone(Long.parseLong(addMobilePhone.getText().substring(3, 15).replaceAll("[_)-]", "")));
        } else {
            clientBuilder.withMobilePhone(client.getMobilePhone());
        }

        System.out.println("client  " + clientBuilder.build());

        return clientBuilder.build();

    }

    private boolean checkValidatedClient(Client validatedClient) {
        hideErrorLabels();

        boolean isRight = true;

        if ("".equals(validatedClient.getName())) {
            addNameErrorLabel.setVisible(true);
            isRight = false;
        }

        if ("".equals(validatedClient.getSurname())) {
            addSurnameErrorLabel.setVisible(true);
            isRight = false;
        }

        if ("".equals(validatedClient.getPatronymic())) {
            addPatronymicErrorLabel.setVisible(true);
            isRight = false;
        }


        if ("".equals(validatedClient.getRegistrationAddress())) {
            addRegistrationAddressErrorLabel.setVisible(true);
            isRight = false;
        }

        if ("".equals(validatedClient.getResidenceAddress())) {
            addResidenceAddressErrorLabel1.setVisible(true);
            isRight = false;
        }

        if(validatedClient.getMonthIncome() == -1) {
            addMonthIncomeErrorLabel.setVisible(true);
            isRight = false;
        }

        if("".equals(validatedClient.getEmail())) {
            addEmailErrorLabel.setVisible(true);
            isRight = false;
        }

        return isRight;
    }


}
