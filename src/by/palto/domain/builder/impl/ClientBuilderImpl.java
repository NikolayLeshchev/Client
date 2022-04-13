package by.palto.domain.builder.impl;

import by.palto.domain.builder.ClientBuilder;
import by.palto.domain.entity.*;

import java.time.LocalDate;

public class ClientBuilderImpl implements ClientBuilder {

    private int id_client;

    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private Sex sex;
    private Passport passport;
    private String birthPlace;
    private CityOfResidence cityOfResidence;
    private String residenceAddress;
    private long homePhone;
    private long mobilePhone;
    private String email;
    private String registrationAddress;
    private MaritalStatus maritalStatus;
    private Citizenship citizenship;
    private Disability disability;
    private boolean isPensioner;
    private double monthIncome;



    @Override
    public Client build() {
        Client client = new Client(id_client,name,surname,patronymic,birthDate,sex,passport,birthPlace,cityOfResidence,
                residenceAddress,homePhone,mobilePhone,email,registrationAddress,maritalStatus,citizenship,disability,
                isPensioner,monthIncome);

        return client;
    }

    public ClientBuilderImpl(int id_client) {
        this.id_client = id_client;
    }


    @Override
    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ClientBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public ClientBuilder withPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    @Override
    public ClientBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public ClientBuilder withSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public ClientBuilder withPassport(Passport passport) {
        this.passport = passport;
        return this;
    }

    @Override
    public ClientBuilder withBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    @Override
    public ClientBuilder withCityOfResidence(CityOfResidence cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
        return this;
    }

    @Override
    public ClientBuilder withResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
        return this;
    }

    @Override
    public ClientBuilder withHomePhone(long homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    @Override
    public ClientBuilder withMobilePhone(long mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    @Override
    public ClientBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public ClientBuilder withRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
        return this;
    }

    @Override
    public ClientBuilder withMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    @Override
    public ClientBuilder withCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    @Override
    public ClientBuilder withDisability(Disability disability) {
        this.disability = disability;
        return this;
    }

    @Override
    public ClientBuilder withIsPensioner(boolean isPensioner) {

        this.isPensioner = isPensioner;
        return this;
    }

    @Override
    public ClientBuilder withMonthIncome(double monthIncome) {
        this.monthIncome = monthIncome;
        return this;
    }
}
