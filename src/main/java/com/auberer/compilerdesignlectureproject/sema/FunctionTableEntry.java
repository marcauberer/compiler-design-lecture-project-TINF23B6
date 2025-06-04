package com.auberer.compilerdesignlectureproject.sema;

import lombok.Getter;
import lombok.Setter;
import java.util.EnumMap;

@Getter
@Setter
public class FunctionTableEntry {
    private String functionIdentifier;
    private Type returnType;
//    private int amountIntParams;
//    private int amountDoubleParams;
//    private int amountStringParams;
//    private int amountBooleanParams;
//    private int amountIntParamsDefaults;
//    private int amountDoubleParamsDefaults;
//    private int amountStringParamsDefaults;
//    private int amountBooleanParamsDefaults;

    private final EnumMap<SuperType, Integer> amountParams = new EnumMap<>(SuperType.class);
    private final EnumMap<SuperType, Integer> amountParamsDefaults = new EnumMap<>(SuperType.class);

    public FunctionTableEntry(String functionIdentifier) {
        this.functionIdentifier = functionIdentifier;
    }

    @Override
    public String toString() {
        return "FunctionTableEntry [functionIdentifier=" + functionIdentifier + ", returnType="
                + returnType + ", amountParams=" + amountParams + ", amountParamsDefaults="
                + amountParamsDefaults + "]";
    }

    public void validateArgs(EnumMap<SuperType, Integer> providedArgs) {
        for (SuperType type : SuperType.values()) {
            int max = amountParams.getOrDefault(type, 0);
            int min = max - amountParamsDefaults.getOrDefault(type, 0);
            if (providedArgs.getOrDefault(type, 0) < min || providedArgs.getOrDefault(type, 0) > max) {
                throw new RuntimeException("wrong number of argument Type provided: " + type );
            }
        }
    }

    public void incrementAmountParams(SuperType superType ) {
        amountParams.merge(superType, 1, Integer::sum);
    }

    public void incrementParamsDefaults(SuperType superType) {
        amountParamsDefaults.merge(superType, 1, Integer::sum);
    }
}