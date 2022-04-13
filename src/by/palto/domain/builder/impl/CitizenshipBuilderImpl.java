package by.palto.domain.builder.impl;

import by.palto.domain.builder.CitizenshipBuilder;
import by.palto.domain.entity.Citizenship;

public class CitizenshipBuilderImpl implements CitizenshipBuilder {

    private int idCitizenship;
    private String citizenshipName;

    public CitizenshipBuilderImpl(int idCitizenship) {
        this.idCitizenship = idCitizenship;
    }

    @Override
    public Citizenship build() {
        Citizenship citizenship = new Citizenship(idCitizenship,citizenshipName);
        return citizenship;
    }

    @Override
    public CitizenshipBuilder withCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
        return this;
    }
}
