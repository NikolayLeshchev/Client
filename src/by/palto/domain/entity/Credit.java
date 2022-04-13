package by.palto.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Credit implements Serializable {
    private final int idCredit;
    private final int idCreditType;
    private final int idCurrency;
    private final int idClient;
    private final LocalDate startDate;
    private final LocalDate closeDate;
    private final String creditAmount;

    public Credit(int idCredit, int idCreditType, int idCurrency, int idClient, LocalDate startDate, LocalDate closeDate, String creditAmount) {
        this.idCredit = idCredit;
        this.idCreditType = idCreditType;
        this.idCurrency = idCurrency;
        this.idClient = idClient;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.creditAmount = creditAmount;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public int getIdCreditType() {
        return idCreditType;
    }

    public int getIdCurrency() {
        return idCurrency;
    }

    public int getIdClient() {
        return idClient;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return idCredit == credit.idCredit &&
                idCreditType == credit.idCreditType &&
                idCurrency == credit.idCurrency &&
                idClient == credit.idClient &&
                Objects.equals(startDate, credit.startDate) &&
                Objects.equals(closeDate, credit.closeDate) &&
                Objects.equals(creditAmount, credit.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCredit, idCreditType, idCurrency, idClient, startDate, closeDate, creditAmount);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "idCredit=" + idCredit +
                ", idCreditType=" + idCreditType +
                ", idCurrency=" + idCurrency +
                ", idClient=" + idClient +
                ", startDate=" + startDate +
                ", closeDate=" + closeDate +
                ", creditAmount='" + creditAmount + '\'' +
                '}';
    }
}
