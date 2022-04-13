package by.palto.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class MaritalStatus implements Serializable {

    private int idMaritalStatus;
    private String maritalStatusName;


    public MaritalStatus(int idMaritalStatus, String maritalStatusName) {
        this.idMaritalStatus = idMaritalStatus;
        this.maritalStatusName = maritalStatusName;
    }

    public int getIdMaritalStatus() {
        return idMaritalStatus;
    }

    public void setIdMaritalStatus(int idMaritalStatus) {
        this.idMaritalStatus = idMaritalStatus;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaritalStatus)) return false;
        MaritalStatus that = (MaritalStatus) o;
        return getIdMaritalStatus() == that.getIdMaritalStatus() &&
                Objects.equals(getMaritalStatusName(), that.getMaritalStatusName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMaritalStatus(), getMaritalStatusName());
    }

    @Override
    public String toString() {
        return "MaritalStatus{" +
                "idMaritalStatus=" + idMaritalStatus +
                ", maritalStatusName='" + maritalStatusName + '\'' +
                '}';
    }
}
