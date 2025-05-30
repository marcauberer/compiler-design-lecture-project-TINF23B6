package com.auberer.compilerdesignlectureproject.util;

public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.err.println("[" + t.getName() + "] " + e);
    // Print frames without line number
    for (StackTraceElement el : e.getStackTrace())
      System.err.println("\tat " + el.getClassName() + "." + el.getMethodName() + "(" + el.getFileName() + ")");
  }
}
