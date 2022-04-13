package by.palto.domain.builder.impl;

import by.palto.domain.builder.DepositBuilder;
import by.palto.domain.entity.Deposit;

import java.time.LocalDate;

public class DepositBuilderImpl implements DepositBuilder {

    private  int idDeposit;
    private  int idDepositType;
    private  int idCurrency;
    private  int idClient;
    private  LocalDate startDate;
    private  LocalDate closeDate;
    private  String depositAmount;

    @Override
    public Deposit build() {
       Deposit deposit = new Deposit(idDeposit,idDepositType,idCurrency,idClient,startDate,closeDate,depositAmount);
       return deposit;
    }

    @Override
    public DepositBuilder withIdDeposit(int idDeposit) {
        this.idDeposit = idDeposit;
        return this;
    }

    @Override
    public DepositBuilder withIdDepositType(int idDepositType) {
        this.idDepositType = idDepositType;
        return this;
    }

    @Override
    public DepositBuilder withIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
        return this;
    }

    @Override
    public DepositBuilder withIdClient(int idClient) {
        this.idClient = idClient;
        return this;
    }

    @Override
    public DepositBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public DepositBuilder withCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    @Override
    public DepositBuilder withDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
        return this;
    }
}
