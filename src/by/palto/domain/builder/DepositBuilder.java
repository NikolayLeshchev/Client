package by.palto.domain.builder;

import by.palto.domain.entity.Deposit;

import java.time.LocalDate;

public interface DepositBuilder {
    Deposit build();
    DepositBuilder withIdDeposit(int idDeposit);
    DepositBuilder withIdDepositType(int idDepositType);
    DepositBuilder withIdCurrency(int idCurrency);
    DepositBuilder withIdClient(int idClient);
    DepositBuilder withStartDate(LocalDate startDate);
    DepositBuilder withCloseDate(LocalDate closeDate);
    DepositBuilder withDepositAmount(String depositAmount);

}
