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

    public Type getTypeByIdentifier(String identifier){
        FunctionTableEntry entrie = null;
        for(FunctionTableEntry entry : entries){
            if (entry.getFunctionIdentifier().equals(identifier)){
                entrie = entry;
            }
        }
        if(entrie == null){ return null; }
        return entrie.getReturnType();
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

    //ToDo Justus: badly engineered but quick and dirty sollution
    public int getPointerByIdentifier(String identifier){
        for(int i = 0; i < entries.size(); i++){
            if (entries.get(i).getFunctionIdentifier().equals(identifier)){
                return i;
            }
        }
        throw new RuntimeException("No entry with identifier " + identifier + " exists");
    }

    public void setPointer(int pointer){
        this.pointer = pointer;
    }

    public void resetPointer(){
        this.pointer = entries.size() - 1;
    }


    //ToDo Justus: Rewrite this ugly quick and dirty solution
    public void incrementParamCount(SuperType superType){
        entries.get(pointer).incrementAmountParams(superType);
    }

    public void incrementParamsDefaults(SuperType superType){
        entries.get(pointer).incrementParamsDefaults(superType);
    }


}
