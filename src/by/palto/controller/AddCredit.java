package by.palto.controller;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.command.factory.CommandFactory;
import by.palto.domain.builder.AccountBuilder;
import by.palto.domain.builder.CreditBuilder;
import by.palto.domain.builder.impl.AccountBuilderImpl;
import by.palto.domain.builder.impl.CreditBuilderImpl;
import by.palto.domain.entity.Account;
import by.palto.domain.entity.Client;
import by.palto.domain.entity.Credit;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;

public class AddCredit {
    public ComboBox<String> cmbBoxCreditType;
    public ComboBox<String> cmbBoxCurrency;
    public ComboBox<String> cmbBoxClients;
    public ComboBox<String> cmbBoxCharts;

    public TextField txtFieldPercent;
    public TextField txtFieldCreditAmount;
    public TextField txtFieldMainChart;
    public TextField txtFieldPercentChart;
    public TextField creditNumber;

    public DatePicker dtPickerStart;
    public DatePicker dtPickerEnd;

    private String chartId;

    public void initialize() {
        initLists();

        String random = random();
        creditNumber.setText(random);

        dtPickerEnd.setDisable(true);
    }

    private void initLists() {
        initClientList();
        initCurrencyList();
        initChartsList();
        initCreditTypesList();

    }

    private void initCreditTypesList() {
        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ALL_CREDIT_TYPES_NAMES);
        Message message = new Message();
        Message response = command.execute(message);
        ObservableList<String> creditTypes = (ObservableList<String>) response.getByKey("creditTypes");

        cmbBoxCreditType.setItems(creditTypes);
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
        String creditType = cmbBoxCreditType.getSelectionModel().getSelectedItem();

        Command command = CommandFactory.getInstance().getCommand(CommandEnum.GET_PERCENT_BY_CREDIT_TYPE);
        Message message = new Message();
        message.add("creditType", creditType);
        Message response = command.execute(message);
        percent = (String) response.getByKey("percent");

        txtFieldPercent.setText(percent);
    }

    public void addNewCredit(ActionEvent actionEvent) {
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

        command = CommandFactory.getInstance().getCommand(CommandEnum.GET_ID_CREDIT_TYPE_BY_NAME);
        message = new Message();
        message.add("creditType", cmbBoxCreditType.getSelectionModel().getSelectedItem());
        response = command.execute(message);
        int creditTypeId = (int) response.getByKey("creditTypeId");

        CreditBuilder creditBuilder = new CreditBuilderImpl();
        String creditAmount = txtFieldCreditAmount.getText();

        if(Double.parseDouble(creditAmount) > 100000.0) {
            creditAmount = String.valueOf(100000);
        }

        Credit credit = creditBuilder.
                withIdCredit(Integer.parseInt((creditNumber.getText()))).
                withIdClient(clientId).
                withStartDate(dtPickerStart.getValue()).
                withCloseDate(dtPickerEnd.getValue()).
                withCreditAmount(creditAmount).
                withIdCurrency(currencyId).
                withIdCreditType(creditTypeId).
                build();

        AccountBuilder accountBuilder = new AccountBuilderImpl();

        Account mainAccount = accountBuilder.
                withIdAccount(txtFieldMainChart.getText()).
                withIdChartOfAccounts(chartId).
                withNumContract(Integer.parseInt(creditNumber.getText())).
                withType(1). //for main
                withSum("0").
                build();

        Account percentAccount = accountBuilder.
                withIdAccount(txtFieldPercentChart.getText()).
                withIdChartOfAccounts(chartId).
                withNumContract(Integer.parseInt(creditNumber.getText())).
                withType(0). // for percent
                withSum("0").
                build();


        command = CommandFactory.getInstance().getCommand(CommandEnum.ADD_NEW_CREDIT_CONTRACT);
        message = new Message();
        message.add("credit", credit);
        message.add("mainAccount",mainAccount);
        message.add("percentAccount",percentAccount);
        response = command.execute(message);

        ShowAlert.showSuccessfulAlert("Добавлено");
    }

    public void createCharts(ActionEvent actionEvent) {
        initChartNumbers();
    }

    private void initChartNumbers() {
        String bankAccountNumber = createABankAccountNumber();
        txtFieldMainChart.setText(bankAccountNumber);
        bankAccountNumber = createABankAccountNumber();
        txtFieldPercentChart.setText(bankAccountNumber);
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

    public String random() {

        int random_number;
        int a = 100000000;  // "от"
        int b = 999999999; //  "до"

        random_number = a + (int) (Math.random() * b);

        return "" + random_number;
    }

    public void changeStartDate(ActionEvent actionEvent) {
        if (dtPickerStart.getValue().compareTo(ChronoLocalDate.from(LocalDateTime.now())) < 0){
            ShowAlert.showErrorAlert("Дата!");
        }
        dtPickerEnd.setDisable(false);
    }

    public void changeEndDate(ActionEvent actionEvent) {
        if (dtPickerEnd.getValue().compareTo(ChronoLocalDate.from(dtPickerStart.getValue())) < 0){
            ShowAlert.showErrorAlert("Дата!");
        }
    }
}
