package com.auberer.compilerdesignlectureproject.interpreter;

import com.auberer.compilerdesignlectureproject.codegen.Function;
import com.auberer.compilerdesignlectureproject.codegen.Module;
import com.auberer.compilerdesignlectureproject.codegen.instructions.Instruction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

public class InterpreterEnvironment {

  private final Module module;
  private final boolean doTracing;
  @Setter
  @Getter
  private ListIterator<Instruction> instructionIterator;
  private final Stack<ListIterator<Instruction>> callStack = new Stack<>();

  public InterpreterEnvironment(Module module, boolean doTracing) {
    this.module = module;
    this.doTracing = doTracing;
  }

  public void interpret() {
    // Search for the main function
    Function mainFunction = module.getFunction("main", new ArrayList<>());
    if (mainFunction == null)
      throw new RuntimeException("No main function found in module " + module.getName());

    // Call main function
    callFunction(null, mainFunction);

    // Main loop
    while (instructionIterator != null && instructionIterator.hasNext()) {
      Instruction instruction = instructionIterator.next();
      if (doTracing) {
        System.out.println(instruction);
      }
      instruction.run(this);
    }
  }

  public void callFunction(ListIterator<Instruction> returnIterator, Function function) {
    // Push the current instruction iterator to the call stack
    callStack.push(returnIterator);
    // Set the instruction iterator to the first instruction of the entry block
    instructionIterator = function.getEntryBlock().getInstructions().listIterator();
  }

  public void returnFromFunction() {
    // Check if there is a corresponding call for the return instruction
    if (callStack.isEmpty()) {
      throw new RuntimeException("Return instruction without corresponding call");
    }
    // Pop the call stack and set the instruction iterator to the instruction after the call
    // This logic allows for nested function calls
    instructionIterator = callStack.pop();
  }

}
