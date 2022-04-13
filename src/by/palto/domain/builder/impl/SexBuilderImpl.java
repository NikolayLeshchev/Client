package by.palto.domain.builder.impl;

import by.palto.domain.builder.SexBuilder;
import by.palto.domain.entity.Sex;

public class SexBuilderImpl implements SexBuilder {

    private int idSex;
    private String sexName;

    public SexBuilderImpl(int idSex) {
        this.idSex = idSex;
    }

    @Override
    public Sex build() {
        Sex sex = new Sex(idSex,sexName);
        return sex;
    }

    @Override
    public SexBuilder withSexName(String sexName) {
        this.sexName = sexName;
        return this;
    }
}
