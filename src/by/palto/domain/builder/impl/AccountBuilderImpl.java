package by.palto.domain.builder.impl;

import by.palto.domain.builder.AccountBuilder;
import by.palto.domain.entity.Account;

public class AccountBuilderImpl implements AccountBuilder{
    private  String id_account;
    private  String id_chart_of_accounts;
    private  int num_contract;
    private  String sum;
    private  int type;


    @Override
    public Account build() {
        Account account = new Account(id_account,id_chart_of_accounts,num_contract,sum,type);
        return account;
    }

    @Override
    public AccountBuilder withIdAccount(String id_account) {
        this.id_account = id_account;
        return this;
    }

    @Override
    public AccountBuilder withIdChartOfAccounts(String id_chart_of_accounts) {
        this.id_chart_of_accounts = id_chart_of_accounts;
        return this;
    }

    @Override
    public AccountBuilder withNumContract(int num_contract) {
        this.num_contract = num_contract;
        return this;
    }

    @Override
    public AccountBuilder withSum(String sum) {
        this.sum = sum;
        return this;
    }

    @Override
    public AccountBuilder withType(int type) {
        this.type = type;
        return this;
    }
}
