package by.palto.domain.builder;

import by.palto.domain.entity.Disability;

public interface DisabilityBuilder {
    Disability build();

    DisabilityBuilder withDisabilityGroup(String disabilityGroup);

}
