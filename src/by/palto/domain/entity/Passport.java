package by.palto.domain.entity;


import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Passport implements Serializable {

    private final String passSeries;
    private final long passNumber;
    private final String passAuthority;
    private final LocalDate passIssueDate;
    private final String passPersonalNumber;

    public Passport(String passSeries, long passNumber, String passAuthority, LocalDate passIssueDate, String passPersonalNumber) {
        this.passSeries = passSeries;
        this.passNumber = passNumber;
        this.passAuthority = passAuthority;
        this.passIssueDate = passIssueDate;
        this.passPersonalNumber = passPersonalNumber;
    }


    public Passport(ResultSet resultSet) throws SQLException {
        passSeries = resultSet.getString("passport_series");
        passNumber = resultSet.getLong("passport_number");
        passAuthority = resultSet.getString("passport_authority");
        passIssueDate = resultSet.getDate("passport_issue_date").toLocalDate();
        passPersonalNumber = resultSet.getString("passport_personal_number");
    }

    public String getPassSeries() {
        return passSeries;
    }

    public long getPassNumber() {
        return passNumber;
    }

    public String getPassAuthority() {
        return passAuthority;
    }

    public LocalDate getPassIssueDate() {
        return passIssueDate;
    }

    public String getPassPersonalNumber() {
        return passPersonalNumber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passSeries='" + passSeries + '\'' +
                ", passNumber=" + passNumber +
                ", passAuthority='" + passAuthority + '\'' +
                ", passIssueDate='" + passIssueDate + '\'' +
                ", passPersonalNumber='" + passPersonalNumber + '\'' +
                '}';
    }
}
