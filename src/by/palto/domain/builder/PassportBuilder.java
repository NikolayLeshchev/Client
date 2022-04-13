package by.palto.domain.builder;

import by.palto.domain.entity.Passport;

import java.time.LocalDate;

public interface PassportBuilder {
    Passport build();

    PassportBuilder withPassSeries(String passSeries);
    PassportBuilder withPassNumber(long passNumber);
    PassportBuilder withPassAuthority(String passAuthority);
    PassportBuilder withPassIssueDate(LocalDate passIssueDate);
    PassportBuilder withPassPersonalNumber(String passPersonalNumber);


}
