package by.palto.domain.builder;

import by.palto.domain.entity.Account;

public interface AccountBuilder {

    AccountBuilder withIdAccount(String id_account);
    AccountBuilder withIdChartOfAccounts(String id_chart_of_accounts);
    AccountBuilder withNumContract(int num_contract);
    AccountBuilder withSum(String sum);
    AccountBuilder withType(int type);

    Account build();
}
