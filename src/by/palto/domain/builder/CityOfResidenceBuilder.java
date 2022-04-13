package by.palto.domain.builder;

import by.palto.domain.entity.CityOfResidence;

public interface CityOfResidenceBuilder {
    CityOfResidence build();


    CityOfResidenceBuilder withResidenceCity(String residenceCity);
}
