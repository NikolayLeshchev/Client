package by.palto.domain.builder;

import by.palto.domain.entity.*;

import java.time.LocalDate;

public interface ClientBuilder {
    Client build();

    ClientBuilder withName(String name);
    ClientBuilder withSurname(String surname);
    ClientBuilder withPatronymic(String patronymic);
    ClientBuilder withBirthDate(LocalDate birthDate);
    ClientBuilder withSex(Sex sex);
    ClientBuilder withPassport(Passport passport);
    ClientBuilder withBirthPlace(String birthPlace);
    ClientBuilder withCityOfResidence(CityOfResidence cityOfResidence);
    ClientBuilder withResidenceAddress(String residenceAddress);
    ClientBuilder withHomePhone(long homePhone);
    ClientBuilder withMobilePhone(long mobilePhone);
    ClientBuilder withEmail(String email);
    ClientBuilder withRegistrationAddress(String registrationAddress);
    ClientBuilder withMaritalStatus(MaritalStatus maritalStatus);
    ClientBuilder withCitizenship(Citizenship citizenship);
    ClientBuilder withDisability(Disability disability);
    ClientBuilder withIsPensioner(boolean isPensioner);
    ClientBuilder withMonthIncome(double monthIncome);


}
