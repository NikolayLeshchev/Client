package by.palto.domain.builder.impl;

import by.palto.domain.builder.DisabilityBuilder;
import by.palto.domain.entity.Disability;

public class DisabilityBuilderImpl implements DisabilityBuilder {
    private int idDisability;
    private String disabilityGroup;

    public DisabilityBuilderImpl(int idDisability) {
        this.idDisability = idDisability;
    }

    @Override
    public Disability build() {
        Disability disability = new Disability(idDisability,disabilityGroup);
        return disability;
    }

    @Override
    public DisabilityBuilder withDisabilityGroup(String disabilityGroup) {
        this.disabilityGroup = disabilityGroup;
        return this;
    }
}
