package by.palto.controller;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.command.factory.CommandFactory;

import by.palto.domain.builder.AccountBuilder;
import by.palto.domain.builder.DepositBuilder;
import by.palto.domain.builder.impl.AccountBuilderImpl;
import by.palto.domain.builder.impl.DepositBuilderImpl;
import by.palto.domain.entity.Account;
import by.palto.domain.entity.Client;
import by.palto.domain.entity.Deposit;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Arrays;

public class AddDeposit {
    public TextField depositNumber;
    public TextField txtFieldPercent;
    public TextField txtFieldDepositAmount;
    public TextField txtFieldMainChart;
    public TextField txtFieldPercentChart;

    public ComboBox<String> cmbBoxDepositType;
    public ComboBox<String> cmbBoxCurrency;
    public ComboBox<String> cmbBoxClients;
    public ComboBox<String> cmbBoxCharts;

    public DatePicker dtPickerStart;
    public DatePicker dtPickerEnd;

    private String chartId;

    public void initialize() {
        initLists();

        String random = random();
        depositNumber.setText(random);
    }

    private void initLists() {
        initClientList();
        initCurrencyList();
        initChartsList();
        initDepositTypesList();
    }

    private void initChartNumbers() throws SQLException, ClassNotFoundException {
        String bankAccountNumber = createABankAccountNumber();
        txtFieldMainChart.setText(bankAccountNumber);
        bankAccountNumber = createABankAccountNumber();
        txtFieldPercentChart.setText(bankAccountNumber);
    }

    private void initDepositTypesList() {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ALL_DEPOSIT_TYPES_NAMES);
        Message message = new Message();
        Message response = command.execute(message);
        ObservableList<String> depositTypes = (ObservableList<String>) response.getByKey("depositTypes");

        cmbBoxDepositType.setItems(depositTypes);
    }

    private void initChartsList() {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ALL_CHARTS_NAMES);
        Message message = new Message();
        Message response = command.execute(message);
        ObservableList<String> charts = (ObservableList<String>) response.getByKey("charts");

        cmbBoxCharts.setItems(charts);
    }

    private void initCurrencyList() {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ALL_CURRENCY_NAMES);
        Message message = new Message();
        Message response = command.execute(message);
        ObservableList<String> currencies = (ObservableList<String>) response.getByKey("currency");

        cmbBoxCurrency.setItems(currencies);
    }

    public void initClientList() {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ALL_CLIENTS);
        Message message = new Message();
        Message response = command.execute(message);
        ObservableList<Client> clientList = (ObservableList<Client>) response.getByKey("clients");
        String[] clientNumbers = new String[clientList.size()];

        for (int i = 0; i < clientNumbers.length; i++) {
            clientNumbers[i] = clientList.get(i).getPassPersonalNumber();
        }

        ObservableList<String> clientNumberList = FXCollections.observableList(Arrays.asList(clientNumbers));
        cmbBoxClients.setItems(clientNumberList);
    }


    public void changePercent(ActionEvent actionEvent) {
        String percent;
        String depositType = cmbBoxDepositType.getSelectionModel().getSelectedItem();

        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_PERCENT_BY_DEPOSIT_TYPE);
        Message message = new Message();
        message.add("depositType", depositType);
        Message response = command.execute(message);
        percent = (String) response.getByKey("percent");

        txtFieldPercent.setText(percent);
    }

    public void addNewDeposit(ActionEvent actionEvent) {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ID_CLIENT_BY_PERS_NUMB);
        Message message = new Message();
        message.add("personalNumber", cmbBoxClients.getSelectionModel().getSelectedItem());
        Message response = command.execute(message);
        int clientId = (int) response.getByKey("idClient");

        command = CommandFactory.getInstance().getCommand(CommandEnum.GET_CURRENCY_ID_BY_NAME);
        message = new Message();
        message.add("currencyName", cmbBoxCurrency.getSelectionModel().getSelectedItem());
        response = command.execute(message);
        int currencyId = (int) response.getByKey("currencyId");

        command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ID_DEPOSIT_TYPE_BY_NAME);
        message = new Message();
        message.add("depositType", cmbBoxDepositType.getSelectionModel().getSelectedItem());
        response = command.execute(message);
        int depositTypeId = (int) response.getByKey("depositTypeId");

        DepositBuilder depositBuilder = new DepositBuilderImpl();
        String depositAmount = txtFieldDepositAmount.getText();

        if(Double.parseDouble(depositAmount) > 100000.0){
            depositAmount = String.valueOf(100000);
        }

        Deposit deposit = depositBuilder.
                withIdDeposit(Integer.parseInt((depositNumber.getText()))).
                withIdClient(clientId).
                withStartDate(dtPickerStart.getValue()).
                withCloseDate(dtPickerEnd.getValue()).
                withDepositAmount(depositAmount).
                withIdCurrency(currencyId).
                withIdDepositType(depositTypeId).
                build();

        AccountBuilder accountBuilder = new AccountBuilderImpl();

        Account mainAccount = accountBuilder.
                withIdAccount(txtFieldMainChart.getText()).
                withIdChartOfAccounts(chartId).
                withNumContract(Integer.parseInt(depositNumber.getText())).
                withType(1). //for main
                withSum("0").
                build();

        Account percentAccount = accountBuilder.
                withIdAccount(txtFieldPercentChart.getText()).
                withIdChartOfAccounts(chartId).
                withNumContract(Integer.parseInt(depositNumber.getText())).
                withType(0). // for percent
                withSum("0").
                build();


        command = CommandFactory.getInstance().getCommand(CommandEnum.ADD_NEW_DEPOSIT_CONTRACT);
        message = new Message();
        message.add("deposit", deposit);
        message.add("mainAccount",mainAccount);
        message.add("percentAccount",percentAccount);
        response = command.execute(message);

        ShowAlert.showSuccessfulAlert("Добавлено!");
    }

    public void createCharts(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        initChartNumbers();
    }

    public String random() {

        int random_number;
        int a = 100000000;  // "от"
        int b = 999999999; //  "до"

        random_number = a + (int) (Math.random() * b);

        return "" + random_number;
    }

    public String createABankAccountNumber() {
        String bankAccountNumber = "";

        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ID_CHART_BY_NAME);
        Message message = new Message();
        message.add("chartName", cmbBoxCharts.getSelectionModel().getSelectedItem());
        Message response = command.execute(message);
        chartId = (String) response.getByKey("chartID");
        System.out.println(chartId);
        bankAccountNumber = chartId + random();

        return bankAccountNumber;
    }
}
