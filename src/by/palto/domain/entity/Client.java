package by.palto.domain.entity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/* 1. полное дублирование кода.  делать билдеры
 *  2. 1 конструктор на класс*/

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;


//    private void writeObject(ObjectOutputStream c) throws Exception {
//        c.defaultWriteObject();
//    }
//
//    private void readObject(ObjectInputStream c) throws Exception {
//        c.defaultReadObject();
//    }

    private final int id_client;

    private final String name;
    private final String surname;
    private final String patronymic;

    private final LocalDate birthDate;
    //
    private final Sex sex;
    //
    private final Passport passport;
    //
    private final String birthPlace;
    //
    private final CityOfResidence cityOfResidence;
    //
    private final String residenceAddress;

    private final long homePhone;
    private final long mobilePhone;
    private final String email;


    private final String registrationAddress;
    //
    private final MaritalStatus maritalStatus;
    //
//
    private final Citizenship citizenship;
    //
    //
    private final Disability disability;
    //

    private final boolean isPensioner;
    private final double monthIncome;


    public Client(int id_client, String name, String surname, String patronymic, LocalDate birthDate, Sex sex,
                  Passport passport, String birthPlace, CityOfResidence cityOfResidence, String residenceAddress,
                  long homePhone, long mobilePhone, String email, String registrationAddress, MaritalStatus maritalStatus,
                  Citizenship citizenship, Disability disability, boolean isPensioner, double monthIncome) {
        this.id_client = id_client;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.passport = passport;
        this.birthPlace = birthPlace;
        this.cityOfResidence = cityOfResidence;
        this.residenceAddress = residenceAddress;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.registrationAddress = registrationAddress;
        this.maritalStatus = maritalStatus;
        this.citizenship = citizenship;
        this.disability = disability;
        this.isPensioner = isPensioner;
        this.monthIncome = monthIncome;
    }

    public int getId_client() {
        return id_client;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Passport getPassport() {
        return passport;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public CityOfResidence getCityOfResidence() {
        return cityOfResidence;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public long getHomePhone() {
        return homePhone;
    }

    public long getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public Disability getDisability() {
        return disability;
    }

    public boolean isPensioner() {
        return isPensioner;
    }

    public double getMonthIncome() {
        return monthIncome;
    }

    //SEXNAME
    public String getSexName() {
        return this.getSex().getSexName();
    }

    //PASSPORT
    public String getPassSeries() {
        return this.getPassport().getPassSeries();
    }

    public Long getPassNumber() {
        return this.getPassport().getPassNumber();
    }

    public String getPassAuthority() {
        return this.getPassport().getPassAuthority();
    }

    public LocalDate getPassIssueDate() {
        return this.getPassport().getPassIssueDate();
    }

    public String getPassPersonalNumber() {
        return this.getPassport().getPassPersonalNumber();
    }

    //CITY OF RESIDENCE
    public String getCityOfResidenceName() {
        return this.getCityOfResidence().getResidenceCity();
    }
    // MARITAL STATUS

    public String getMaritalStatusName() {
        return this.getMaritalStatus().getMaritalStatusName();
    }

    // CITIZENSHIP

    public String getCitizenshipName() {
        return this.getCitizenship().getCitizenshipName();
    }

    //DISABILITY
    public String getDisabilityName() {
        return this.getDisability().getDisabilityGroup();
    }

    public String getPensioner() {
        if(isPensioner){
            return "Да";
        } else {
            return "Нет";
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", passport=" + passport +
                ", birthPlace='" + birthPlace + '\'' +
                ", cityOfResidence=" + cityOfResidence +
                ", residenceAddress='" + residenceAddress + '\'' +
                ", homePhone=" + homePhone +
                ", mobilePhone=" + mobilePhone +
                ", email='" + email + '\'' +
                ", registrationAddress='" + registrationAddress + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", citizenship=" + citizenship +
                ", disability=" + disability +
                ", isPensioner=" + isPensioner +
                ", monthIncome=" + monthIncome +
                '}';
    }


}
