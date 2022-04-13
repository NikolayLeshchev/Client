package by.palto.domain.builder.impl;

import by.palto.domain.builder.MaritalStatusBuilder;
import by.palto.domain.entity.MaritalStatus;

public class MaritalStatusBuilderImpl implements MaritalStatusBuilder {
    private int idMaritalStatus;
    private String maritalStatusName;

    public MaritalStatusBuilderImpl(int idMaritalStatus) {
        this.idMaritalStatus = idMaritalStatus;
    }

    @Override
    public MaritalStatus build() {
        MaritalStatus maritalStatus = new MaritalStatus(idMaritalStatus, maritalStatusName);
        return maritalStatus;
    }

    @Override
    public MaritalStatusBuilder withMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
        return this;
    }
}
