package by.palto.domain.builder.impl;

import by.palto.domain.builder.CityOfResidenceBuilder;
import by.palto.domain.entity.CityOfResidence;

public class CityOfResidenceBuilderImpl implements CityOfResidenceBuilder {

    private int idCityOfResidence;
    private String residenceCity;

    public CityOfResidenceBuilderImpl(int idCityOfResidence) {
        this.idCityOfResidence = idCityOfResidence;
    }

    @Override
    public CityOfResidence build() {
        CityOfResidence cityOfResidence = new CityOfResidence(idCityOfResidence, residenceCity);
        return cityOfResidence;
    }

    @Override
    public CityOfResidenceBuilder withResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
        return this;
    }
}
