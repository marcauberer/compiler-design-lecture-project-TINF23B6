package com.auberer.compilerdesignlectureproject.sema;

import lombok.Data;

@Data
public class Type {
  private SuperType superType;
  private String subType;

  public Type(SuperType superType) {
    this.superType = superType;
  }

  public Type(SuperType superType, String subType) {
    this.superType = superType;
    this.subType = subType;
  }

  public boolean is(SuperType superType) {
    return this.superType == superType;
  }

  public boolean isOneOf(SuperType... superTypes) {
    for (SuperType superType : superTypes) {
      if (this.superType == superType)
        return true;
    }
    return false;
  }

  public String toString() {
    return superType.toString();
  }

  public String toLLVMIRTypeString() {
    return switch(superType) {
      case TYPE_INT -> "i32";
      case TYPE_DOUBLE -> "double";
      case TYPE_STRING -> "ptr";
      case TYPE_BOOL -> "i1";
      default -> throw new RuntimeException("Unknown serialization for type " + superType);
    };
  }

  public int toAlignmentString() {
    return switch(superType) {
      case TYPE_INT -> 4;
      case TYPE_DOUBLE, TYPE_STRING -> 8;
      case TYPE_BOOL -> 1;
      default -> throw new RuntimeException("Unknown alignment for type " + superType);
    };
  }
}
