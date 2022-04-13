package by.palto.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private final String id_account;
    private final String id_chart_of_accounts;
    private final int num_contract;
    private final String sum;
    private final int type;

    public Account(String id_account, String id_chart_of_accounts, int num_contract, String sum, int type) {
        this.id_account = id_account;
        this.id_chart_of_accounts = id_chart_of_accounts;
        this.num_contract = num_contract;
        this.sum = sum;
        this.type = type;
    }

    public String getId_account() {
        return id_account;
    }

    public String getId_chart_of_accounts() {
        return id_chart_of_accounts;
    }

    public int getNum_contract() {
        return num_contract;
    }

    public String getSum() {
        return sum;
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return type == account.type &&
                Objects.equals(id_account, account.id_account) &&
                Objects.equals(id_chart_of_accounts, account.id_chart_of_accounts) &&
                Objects.equals(num_contract, account.num_contract) &&
                Objects.equals(sum, account.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_account, id_chart_of_accounts, num_contract, sum, type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id_account='" + id_account + '\'' +
                ", id_chart_of_accounts='" + id_chart_of_accounts + '\'' +
                ", num_contract='" + num_contract + '\'' +
                ", sum='" + sum + '\'' +
                ", type=" + type +
                '}';
    }
}
