package com.auberer.compilerdesignlectureproject.sema;

import lombok.Getter;
import lombok.Setter;
;

@Getter
@Setter
public class FunctionTableEntry {
    private String functionIdentifier;
    private Type returnType;
    private int amountIntParams;
    private int amountDoubleParams;
    private int amountStringParams;
    private int amountBooleanParams;
    private int amountIntParamsDefaults;
    private int amountDoubleParamsDefaults;
    private int amountStringParamsDefaults;
    private int amountBooleanParamsDefaults;


    public FunctionTableEntry(String functionIdentifier) {
        this.functionIdentifier = functionIdentifier;
    }

    @Override
    public String toString() {
        return "FunctionTableEntry{" +
                "functionIdentifier=" + functionIdentifier + " | " +
                "returnType=" + returnType +
                " | amountIntParams=" + amountIntParams +
                " | amountDoubleParams=" + amountDoubleParams +
                " | amountStringParams=" + amountStringParams +
                " | amountBooleanParams=" + amountBooleanParams +
                '}';
    }


    //ToDo Justus: Rewrite this ugly quick and dirty solution
    public void incrementAmountIntParams() {
        amountIntParams++;
    }

    public void incrementAmountDoubleParams() {
        amountDoubleParams++;
    }

    public void incrementAmountStringParams() {
        amountStringParams++;
    }

    public void incrementAmountBooleanParams() {
        amountBooleanParams++;
    }

    public void incrementAmountIntParamsDefaults() {
        amountIntParamsDefaults++;
    }

    public void incrementAmountDoubleParamsDefaults() {
        amountDoubleParamsDefaults++;
    }

    public void incrementAmountStringParamsDefaults() {
        amountStringParamsDefaults++;
    }

    public void incrementAmountBooleanParamsDefaults() {
        amountBooleanParamsDefaults++;
    }
}
