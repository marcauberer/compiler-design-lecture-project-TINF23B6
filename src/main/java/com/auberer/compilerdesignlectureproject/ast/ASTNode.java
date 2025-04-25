package com.auberer.compilerdesignlectureproject.ast;

import com.auberer.compilerdesignlectureproject.reader.CodeLoc;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public abstract class ASTNode implements IVisitable {

  public void addChild(ASTNode child) {
    children.add(child);
    child.setParent(this);
  }

  public <T> ArrayList<T> getChildren(Class<T> targetClass) {
    return children.stream()
        .filter(targetClass::isInstance)
        .map(targetClass::cast)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  public <T> T getChild(Class<T> targetClass, int idx) {
    List<T> children = getChildren(targetClass);
    return children.isEmpty() ? null : children.get(idx);
  }

  @ToString.Exclude
  ASTNode parent;
  ArrayList<ASTNode> children = new ArrayList<>();
  CodeLoc codeLoc;
}
