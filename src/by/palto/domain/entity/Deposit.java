package by.palto.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Deposit implements Serializable {
    private final int idDeposit;
    private final int idDepositType;
    private final int idCurrency;
    private final int idClient;
    private final LocalDate startDate;
    private final LocalDate closeDate;
    private final String depositAmount;

    public Deposit(int idDeposit, int idDepositType, int idCurrency, int idClient, LocalDate startDate, LocalDate closeDate, String depositAmount) {
        this.idDeposit = idDeposit;
        this.idDepositType = idDepositType;
        this.idCurrency = idCurrency;
        this.idClient = idClient;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.depositAmount = depositAmount;
    }

    public int getIdDeposit() {
        return idDeposit;
    }

    public int getIdDepositType() {
        return idDepositType;
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

    public String getDepositAmount() {
        return depositAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return idDeposit == deposit.idDeposit &&
                idDepositType == deposit.idDepositType &&
                idCurrency == deposit.idCurrency &&
                idClient == deposit.idClient &&
                Objects.equals(startDate, deposit.startDate) &&
                Objects.equals(closeDate, deposit.closeDate) &&
                Objects.equals(depositAmount, deposit.depositAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDeposit, idDepositType, idCurrency, idClient, startDate, closeDate, depositAmount);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "idDeposit=" + idDeposit +
                ", idDepositType=" + idDepositType +
                ", idCurrency=" + idCurrency +
                ", idClient=" + idClient +
                ", startDate=" + startDate +
                ", closeDate=" + closeDate +
                ", depositAmount='" + depositAmount + '\'' +
                '}';
    }
}
