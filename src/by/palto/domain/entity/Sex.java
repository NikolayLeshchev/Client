package by.palto.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Sex implements Serializable {

    private final int idSex;
    private final String sexName;

    public Sex(int idSex, String sexName) {
        this.idSex = idSex;
        this.sexName = sexName;
    }

    public int getIdSex() {
        return idSex;
    }

    public String getSexName() {
        return sexName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sex)) return false;
        Sex sex = (Sex) o;
        return getIdSex() == sex.getIdSex() &&
                Objects.equals(getSexName(), sex.getSexName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdSex(), getSexName());
    }

    @Override
    public String toString() {
        return "Sex{" +
                "idSex=" + idSex +
                ", sexName='" + sexName + '\'' +
                '}';
    }
}
