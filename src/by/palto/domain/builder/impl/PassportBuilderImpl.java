package by.palto.domain.builder.impl;

import by.palto.domain.builder.PassportBuilder;
import by.palto.domain.entity.Passport;

import java.time.LocalDate;

public class PassportBuilderImpl implements PassportBuilder {

    private String passSeries;
    private long passNumber;
    private String passAuthority;
    private LocalDate passIssueDate;
    private String passPersonalNumber;

    @Override
    public Passport build() {
        Passport passport = new Passport(passSeries,passNumber,passAuthority,passIssueDate,passPersonalNumber);

        return passport;
    }

    @Override
    public PassportBuilder withPassSeries(String passSeries) {
        this.passSeries = passSeries;
        return this;
    }

    @Override
    public PassportBuilder withPassNumber(long passNumber) {
        this.passNumber = passNumber;
        return this;
    }

    @Override
    public PassportBuilder withPassAuthority(String passAuthority) {
        this.passAuthority = passAuthority;
        return this;
    }

    @Override
    public PassportBuilder withPassIssueDate(LocalDate passIssueDate) {
        this.passIssueDate = passIssueDate;
        return this;
    }

    @Override
    public PassportBuilder withPassPersonalNumber(String passPersonalNumber) {
        this.passPersonalNumber = passPersonalNumber;
        return this;
    }
}
