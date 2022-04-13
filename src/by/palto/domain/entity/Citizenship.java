package by.palto.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Citizenship implements Serializable {
    private final int idCitizenship;
    private final String citizenshipName;

    public Citizenship(int idCitizenship, String citizenshipName) {
        this.idCitizenship = idCitizenship;
        this.citizenshipName = citizenshipName;
    }

    public int getIdCitizenship() {
        return idCitizenship;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizenship)) return false;
        Citizenship that = (Citizenship) o;
        return getIdCitizenship() == that.getIdCitizenship() &&
                Objects.equals(getCitizenshipName(), that.getCitizenshipName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCitizenship(), getCitizenshipName());
    }

    @Override
    public String toString() {
        return "Citizenship{" +
                "idCitizenship=" + idCitizenship +
                ", citizenshipName='" + citizenshipName + '\'' +
                '}';
    }
}
