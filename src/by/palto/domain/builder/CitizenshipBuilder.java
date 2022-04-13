package by.palto.domain.builder;

import by.palto.domain.entity.Citizenship;

public interface CitizenshipBuilder {
    Citizenship build();

    CitizenshipBuilder withCitizenshipName(String citizenshipName);
}
