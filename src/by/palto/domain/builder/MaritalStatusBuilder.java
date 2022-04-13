package by.palto.domain.builder;

import by.palto.domain.entity.MaritalStatus;

public interface MaritalStatusBuilder {
    MaritalStatus build();

    MaritalStatusBuilder withMaritalStatusName(String maritalStatusName);

}
