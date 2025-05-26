package com.auberer.compilerdesignlectureproject.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class CustomJSONPrettyPrinter extends DefaultPrettyPrinter {

  public CustomJSONPrettyPrinter() {
    super();

    // Use 2-space indentation and system line feeds
    this.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    this.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
  }

  @Override
  public DefaultPrettyPrinter createInstance() {
    return new CustomJSONPrettyPrinter();
  }

  @Override
  public void writeObjectFieldValueSeparator(JsonGenerator g) {
    try {
      g.writeRaw(": ");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

