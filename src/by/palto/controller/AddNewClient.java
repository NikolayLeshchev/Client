package by.palto.controller;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.command.factory.CommandFactory;
import by.palto.domain.builder.impl.*;
import by.palto.domain.entity.*;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;

public class AddNewClient {

    /////////////
    //BUTTONS
    /////////////
    @FXML
    public Button addingButton;
    @FXML
    public Button backButton;

    /////////////
    //LABELS
    /////////////
    @FXML
    public Label addNameErrorLabel;
    @FXML
    public Label addSurnameErrorLabel;
    @FXML
    public Label addPatronymicErrorLabel;
    @FXML
    public Label addBirthDateErrorLabel;
    @FXML
    public Label addPassSeriesErrorLabel;
    @FXML
    public Label addPassNumberErrorLabel;
    @FXML
    public Label addPassAuthorityErrorLabel;
    @FXML
    public Label addPassIssueDateErrorLabel;

    @FXML
    public Label addRegistrationAddressErrorLabel;
    @FXML
    public Label addBirthPlaceErrorLabel;
    @FXML
    public Label addResidenceAddressErrorLabel1;
    @FXML
    public Label addEmailErrorLabel;
    @FXML
    public Label addMonthIncomeErrorLabel;
    @FXML
    public Label addPassPersonalNumberErrorLabel;


    /////////////
    //COMBOBOX
    /////////////
    @FXML
    ComboBox<String> addMaritalStatus;
    @FXML
    ComboBox<String> addCityOfResidence;
    @FXML
    ComboBox<String> addDisability;
    @FXML
    ComboBox<String> addCitizenship;

    /////////////
    //TEXT FIELDS
    /////////////
    @FXML
    public TextField addName;
    @FXML
    public TextField addSurname;
    @FXML
    public TextField addPatronymic;
    @FXML
    public DatePicker addBirthDate;
    @FXML
    public CheckBox addPensioner;
    @FXML
    public TextField addPassSeries;
    @FXML
    public TextField addPassNumber;
    @FXML
    public TextField addPassAuthority;
    @FXML
    public DatePicker addPassIssueDate;
    @FXML
    public TextField addPassPersonalNumber;
    @FXML
    public TextField addBirthPlace;
    @FXML
    public TextField addResidenceAddress;
    @FXML
    public TextField addHomePhone;
    @FXML
    public TextField addMobilePhone;
    @FXML
    public RadioButton addMale;
    @FXML
    public RadioButton addFemale;
    @FXML
    public TextField addEmail;
    @FXML
    public TextField addRegistrationAddress;
    @FXML
    public TextField addMonthIncome;


    public void initialize() {

        initLists();
        hideLabels();

    }

    private void initLists() {
        String[] cities = {"Минск", "Гродно", "Витебск", "Гомель", "Могилев", "Брест"};
        String[] disabilityGroups = {"-", "1 группа", "2 группа", "3 группа", "4 группа"};
        String[] maritalStatuses = {"Женат/замужем", "Разведен(а)", "Не женат/не замужем"};
        String[] citizenship = {"Республика Беларусь", "Российская Федерация", "Польша", "Германия", "Украина"};

        ObservableList<String> citiesList = FXCollections.observableList(Arrays.asList(cities));
        ObservableList<String> disabilityList = FXCollections.observableList(Arrays.asList(disabilityGroups));
        ObservableList<String> maritalStatusList = FXCollections.observableList(Arrays.asList(maritalStatuses));
        ObservableList<String> citizenshipList = FXCollections.observableList(Arrays.asList(citizenship));

        addCityOfResidence.setItems(citiesList);
        addDisability.setItems(disabilityList);
        addMaritalStatus.setItems(maritalStatusList);
        addCitizenship.setItems(citizenshipList);

    }

    private void hideLabels() {
        addNameErrorLabel.setVisible(false);
        addSurnameErrorLabel.setVisible(false);
        addPatronymicErrorLabel.setVisible(false);

        addBirthDateErrorLabel.setVisible(false);

        addPassSeriesErrorLabel.setVisible(false);
        addPassNumberErrorLabel.setVisible(false);
        addPassAuthorityErrorLabel.setVisible(false);
        addPassIssueDateErrorLabel.setVisible(false);
        addPassPersonalNumberErrorLabel.setVisible(false);

        addRegistrationAddressErrorLabel.setVisible(false);
        addResidenceAddressErrorLabel1.setVisible(false);
        addBirthPlaceErrorLabel.setVisible(false);

        addEmailErrorLabel.setVisible(false);

        addMonthIncomeErrorLabel.setVisible(false);
    }

    public void addClientToTheDatabase(ActionEvent actionEvent) {
        boolean isAddingSuccessful = false;
        boolean isAllListSelected = true;

        Client client = readEnteredData();
        System.out.println(client);

        if (!isAllMaskFieldFilled(client)) {
            ShowAlert.showErrorAlert("Check your fields with masks!");
        }

        if (!isListSelected(addCityOfResidence.getValue(), addCityOfResidence.getPromptText())) {
            ShowAlert.showErrorAlert("Выберите город проживания!");
            isAllListSelected = false;
        } else if (!isListSelected(addMaritalStatus.getValue(), addMaritalStatus.getPromptText())) {
            ShowAlert.showErrorAlert("Выберите социальный статус!");
            isAllListSelected = false;
        } else if (!isListSelected(addDisability.getValue(), addDisability.getPromptText())) {
            ShowAlert.showErrorAlert("Выберите состояние инвалидности!");
            isAllListSelected = false;
        } else if (!isListSelected(addCitizenship.getValue(), addCitizenship.getPromptText())) {
            ShowAlert.showErrorAlert("Выберите гражданство!");
            isAllListSelected = false;
        }

        if (isAllMaskFieldFilled(client)) {

            Client returnedClient = passClientToTheServer(client);

            if (checkValidatedClient(returnedClient) && isAllListSelected) {
                ShowAlert.showSuccessfulAlert("Successful!");
                isAddingSuccessful = true;
            } else {
                ShowAlert.showErrorAlert("Some fields are wrong!");
            }

        }

        if (isAddingSuccessful) {

            Main.changeScene(Pages.MAIN_CLIENT_TABLE, 996, 540);
        }


    }


    private Client passClientToTheServer(Client client) {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.ADD_NEW_USER);
        Message message = new Message();
        message.add("addNewClient", client);
        Message response = command.execute(message);

        Client returnedClient = (Client) response.getByKey("validatedClient");

        return returnedClient;
    }

    private Client readEnteredData() {
        ClientBuilderImpl clientBuilder = new ClientBuilderImpl(0);
        PassportBuilderImpl passportBuilder = new PassportBuilderImpl();
        CityOfResidenceBuilderImpl cityOfResidenceBuilder = new CityOfResidenceBuilderImpl(0);
        MaritalStatusBuilderImpl maritalStatusBuilder = new MaritalStatusBuilderImpl(0);
        DisabilityBuilderImpl disabilityBuilder = new DisabilityBuilderImpl(0);
        CitizenshipBuilderImpl citizenshipBuilder = new CitizenshipBuilderImpl(0);
        SexBuilderImpl sexBuilder = new SexBuilderImpl(0);

        if("".equals(addPassNumber.getText().replaceAll("[_)-]", ""))) {
            passportBuilder.withPassNumber(-1);
        } else {
            passportBuilder.withPassNumber(Long.parseLong(addPassNumber.getText().replaceAll("[_)-]", "")));
        }


        passportBuilder.
                withPassSeries(addPassSeries.getText().toUpperCase().replaceAll("[_)-]", "")).
             //   withPassNumber(Long.parseLong(addPassNumber.getText().replaceAll("[_)-]", ""))).
                withPassAuthority(addPassAuthority.getText()).
                withPassIssueDate(addPassIssueDate.getValue()).
                withPassPersonalNumber(addPassPersonalNumber.getText().replaceAll("[_)-]", ""));


        cityOfResidenceBuilder.withResidenceCity(addCityOfResidence.getValue());
        maritalStatusBuilder.withMaritalStatusName(addMaritalStatus.getValue());
        disabilityBuilder.withDisabilityGroup(addDisability.getValue());
        citizenshipBuilder.withCitizenshipName(addCitizenship.getValue());
        sexBuilder.withSexName(addFemale.isSelected() ? "Женский" : "Мужской");


        if (!addMonthIncome.getText().matches("[0-9]+.[0-9]*")) {
            ShowAlert.showErrorAlert("Check your month income!!");
            clientBuilder.withMonthIncome(-1);
            addMonthIncomeErrorLabel.setVisible(true);
        } else {
            clientBuilder.withMonthIncome(Double.parseDouble(addMonthIncome.getText()));
        }

        if (!addEmail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            //           ShowAlert.showErrorAlert("Check your email!!");
            clientBuilder.withEmail("");
            addEmailErrorLabel.setVisible(true);
        } else {
            clientBuilder.withEmail(addEmail.getText());
        }



        clientBuilder.
                withName(addName.getText()).
                withSurname(addSurname.getText()).
                withPatronymic(addPatronymic.getText()).
                withBirthDate(addBirthDate.getValue()).
                withPassport(passportBuilder.build()).
                withBirthPlace(addBirthPlace.getText()).
                withResidenceAddress(addResidenceAddress.getText()).
                withHomePhone(Long.parseLong(addHomePhone.getText().substring(3, 15).replaceAll("[_)-]", ""))).
                withMobilePhone(Long.parseLong(addMobilePhone.getText().substring(5, 17).replaceAll("[_)-]", ""))).

                //    withEmail(addEmail.getText()).
                        withRegistrationAddress(addRegistrationAddress.getText()).
                withCityOfResidence(cityOfResidenceBuilder.build()).
                withMaritalStatus(maritalStatusBuilder.build()).
                withDisability(disabilityBuilder.build()).
                withCitizenship(citizenshipBuilder.build()).
                withSex(sexBuilder.build()).
                withIsPensioner(addPensioner.isSelected());


        System.out.println("client  " + clientBuilder.build());

        return clientBuilder.build();

    }

    public void returnBack(ActionEvent actionEvent) {
        Main.changeScene(Pages.MAIN_CLIENT_TABLE, 996, 540);
    }

    private boolean checkValidatedClient(Client validatedClient) {
        hideLabels();

        boolean isRight = true;

        if (validatedClient.getPassport().getPassNumber() == -1) {
            ShowAlert.showErrorAlert("Клиент с таким номером паспорта уже есть!");
            addPassNumberErrorLabel.setVisible(true);
            isRight = false;
        } else if ("".equals(validatedClient.getPassport().getPassPersonalNumber())) {
            ShowAlert.showErrorAlert("Клиент с таким номером паспорта уже есть!");
            addPassPersonalNumberErrorLabel.setVisible(true);
            isRight = false;
        } else {

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

            if (validatedClient.getBirthDate() == null) {
                addBirthDateErrorLabel.setVisible(true);
                isRight = false;
            }

            if ("".equals(validatedClient.getPassport().getPassAuthority())) {
                addPassAuthorityErrorLabel.setVisible(true);
                isRight = false;
            }

            if (validatedClient.getPassport().getPassIssueDate() == null) {
                addPassIssueDateErrorLabel.setVisible(true);
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

            if ("".equals(validatedClient.getBirthPlace())) {
                addBirthPlaceErrorLabel.setVisible(true);
                isRight = false;
            }

            if (validatedClient.getMonthIncome() == -1) {
                addMonthIncomeErrorLabel.setVisible(true);
                isRight = false;
            }

            if ("".equals(validatedClient.getEmail())) {
                addEmailErrorLabel.setVisible(true);
                isRight = false;
            }
        }


        return isRight;
    }


    private boolean isAllMaskFieldFilled(Client client) {

        if (client.getPassport().getPassSeries().length() == 2 && client.getPassport().getPassNumber() >= 1000000) {
            if (client.getPassport().getPassPersonalNumber().length() == 14) {
                if (client.getMobilePhone() >= 100000000 && client.getHomePhone() >= 100000000) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isListSelected(String selectedValue, String defValue) {
        if (selectedValue.equals(defValue)) {
            return false;
        } else {
            return true;
        }
    }
}
