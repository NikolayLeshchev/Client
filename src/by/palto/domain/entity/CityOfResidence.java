package by.palto.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class CityOfResidence  implements Serializable {

    private final int idCityOfResidence;
    private final String residenceCity;


    public CityOfResidence(int idCityOfResidence, String residenceCity) {
        this.idCityOfResidence = idCityOfResidence;
        this.residenceCity = residenceCity;
    }

    public int getIdCityOfResidence() {
        return idCityOfResidence;
    }

    public String getResidenceCity() {
        return residenceCity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityOfResidence)) return false;
        CityOfResidence that = (CityOfResidence) o;
        return getIdCityOfResidence() == that.getIdCityOfResidence() &&
                Objects.equals(getResidenceCity(), that.getResidenceCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCityOfResidence(), getResidenceCity());
    }


    @Override
    public String toString() {
        return "CityOfResidence{" +
                "idCityOfResidence=" + idCityOfResidence +
                ", residenceCity='" + residenceCity + '\'' +
                '}';
    }
}
