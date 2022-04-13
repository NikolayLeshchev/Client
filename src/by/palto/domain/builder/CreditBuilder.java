package by.palto.domain.builder;

import by.palto.domain.entity.Credit;

import java.time.LocalDate;

public interface CreditBuilder {

    Credit build();
    CreditBuilder withIdCredit(int idCredit);
    CreditBuilder withIdCreditType(int idCreditType);
    CreditBuilder withIdCurrency(int idCurrency);
    CreditBuilder withIdClient(int idClient);
    CreditBuilder withStartDate(LocalDate startDate);
    CreditBuilder withCloseDate(LocalDate closeDate);
    CreditBuilder withCreditAmount(String amount);
}
