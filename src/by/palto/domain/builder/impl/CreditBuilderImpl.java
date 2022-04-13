package by.palto.domain.builder.impl;

import by.palto.domain.builder.CreditBuilder;
import by.palto.domain.entity.Credit;

import java.time.LocalDate;

public class CreditBuilderImpl implements CreditBuilder {
    private  int idCredit;
    private  int idCreditType;
    private  int idCurrency;
    private  int idClient;
    private  LocalDate startDate;
    private  LocalDate closeDate;
    private  String creditAmount;

    @Override
    public Credit build() {
        return new Credit(idCredit,idCreditType,idCurrency,idClient,startDate,closeDate,creditAmount);
    }

    @Override
    public CreditBuilder withIdCredit(int idCredit) {
        this.idCredit = idCredit;
        return this;
    }

    @Override
    public CreditBuilder withIdCreditType(int idCreditType) {
        this.idCreditType = idCreditType;
        return this;
    }

    @Override
    public CreditBuilder withIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
        return this;
    }

    @Override
    public CreditBuilder withIdClient(int idClient) {
        this.idClient = idClient;
        return this;
    }

    @Override
    public CreditBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public CreditBuilder withCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    @Override
    public CreditBuilder withCreditAmount(String amount) {
        this.creditAmount = amount;
        return this;
    }
}
