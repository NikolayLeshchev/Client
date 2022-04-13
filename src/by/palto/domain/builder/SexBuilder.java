package by.palto.domain.builder;

import by.palto.domain.entity.Sex;

public interface SexBuilder {

    Sex build();

    SexBuilder withSexName(String sexName);



}
