package by.palto.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Disability implements Serializable {
    private final int idDisability;
    private final String disabilityGroup;

    public Disability(int idDisability, String disabilityGroup) {
        this.idDisability = idDisability;
        this.disabilityGroup = disabilityGroup;
    }

    public int getIdDisability() {
        return idDisability;
    }

    public String getDisabilityGroup() {
        return disabilityGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disability)) return false;
        Disability that = (Disability) o;
        return getIdDisability() == that.getIdDisability() &&
                Objects.equals(getDisabilityGroup(), that.getDisabilityGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdDisability(), getDisabilityGroup());
    }

    @Override
    public String toString() {
        return "Disability{" +
                "idDisability=" + idDisability +
                ", disabilityGroup='" + disabilityGroup + '\'' +
                '}';
    }
}
