package com.auberer.compilerdesignlectureproject.sema;

import com.auberer.compilerdesignlectureproject.ast.ASTEntryNode;
import com.auberer.compilerdesignlectureproject.ast.ASTFunctionDefNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionTable {

    private int pointer = 0;
    private List<FunctionTableEntry> entries = new ArrayList<>();
    FunctionTable(){
    }

    public void createEntry(ASTFunctionDefNode node){
        String identifier = node.getIdentifier();
        if(!entryExistsByIdentifier(identifier)){
            entries.add(new FunctionTableEntry(identifier));
            //Set Pointer to new Entry
            pointer = entries.size() - 1;
        }else{
            throw new RuntimeException("Entry with identifier " + identifier + " already exists");
        }
    }

    private Boolean entryExistsByIdentifier(String identifier){
        if(entries.isEmpty()){ return false; }
        for(FunctionTableEntry entry : entries){
            if (entry.getFunctionIdentifier().equals(identifier)){
                return true;
            }
        }
        return false;
    }

    public FunctionTableEntry getActiveEntry(){
        return entries.get(pointer);
    }

    public void setCurrentReturnType(Type returnType){
        getActiveEntry().setReturnType(returnType);
    }

    @Override
    public String toString() {
        String out = "FunctionTable:" + "\n";
        for(FunctionTableEntry entry : entries){
            out = out+entry.toString()+ "\n";
        }
        return out;
    }

    //ToDo Justus: Rewrite this ugly quick and dirty solution
    public void incDoubleParamCount(){
        entries.get(pointer).incrementAmountDoubleParams();
    }

    public void incStringParamCount(){
        entries.get(pointer).incrementAmountStringParams();
    }

    public void incBooleanParamCount(){
        entries.get(pointer).incrementAmountBooleanParams();
    }

    public void incIntParamCount(){
        entries.get(pointer).incrementAmountIntParams();
    }

    public void incrementAmountIntParamsDefaults(){
        entries.get(pointer).incrementAmountIntParamsDefaults();
    }

    public void incrementAmountDoubleParamsDefaults(){
        entries.get(pointer).incrementAmountDoubleParamsDefaults();
    }

    public void incrementAmountBooleanParamsDefaults(){
        entries.get(pointer).incrementAmountBooleanParamsDefaults();
    }

    public void incrementAmountStringParamsDefaults(){
        entries.get(pointer).incrementAmountStringParamsDefaults();
    }
}
