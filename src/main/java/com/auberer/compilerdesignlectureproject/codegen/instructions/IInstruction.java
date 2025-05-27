package com.auberer.compilerdesignlectureproject.codegen.instructions;

import com.auberer.compilerdesignlectureproject.codegen.IDumpable;

public interface IInstruction extends IDumpable {
  void trace(StringBuilder sb);
}
