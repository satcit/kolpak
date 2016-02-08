package ru.satcit.kolpak.view;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * User: Aleksei
 * Date: 26.01.16 22:09
 */
public class MultipleDateEditor extends PropertyEditorSupport {
  public final static String DEFAULT_OUTPUT_FORMAT = "dd/MM/yyyy";
  public final static String[] DEFAULT_INPUT_FORMATS = {
      "dd/MM/yyyy HH:mm:ss",
      "dd-MM-yyyy HH:mm:ss",
      "dd/MM/yy HH:mm:ss",
      "dd-MM-yy HH:mm:ss",
      "dd/MM/yyyy",
      "dd-MM-yyyy",
      "dd/MM/yy",
      "dd-MM-yy",
      "yyyy"
  };

  private String outputFormat;
  private String[] inputFormats;
  private boolean allowEmpty;

  public MultipleDateEditor() {
    outputFormat = MultipleDateEditor.DEFAULT_OUTPUT_FORMAT;
    inputFormats = MultipleDateEditor.DEFAULT_INPUT_FORMATS;
    allowEmpty = false;
  }

  public MultipleDateEditor(String outputFormat, String[] inputFormats) {
    this.outputFormat = outputFormat;
    this.inputFormats = inputFormats;
    allowEmpty = false;
  }

  public MultipleDateEditor(String outputFormat, String[] inputFormats,
                            boolean allowEmpty) {
    this.outputFormat = outputFormat;
    this.inputFormats = inputFormats;
    this.allowEmpty = allowEmpty;
  }

  @Override
  public String getAsText() {
    if (allowEmpty && getValue() == null) {
      return "";
    }

    return DateFormatUtils.format((Date) getValue(), outputFormat);
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    try {
      if (StringUtils.hasText(text)) {
        setValue(DateUtils.parseDate(text, inputFormats));
      } else {
        if (allowEmpty) {
          setValue(null);
        } else {
          throw new IllegalArgumentException(
              "The text specified for parsing is null");
        }
      }
    } catch (ParseException ex) {
      throw new IllegalArgumentException("Could not parse text ["
          + text + "] into any available date input formats", ex);
    }
  }

  public boolean isAllowEmpty() {
    return allowEmpty;
  }

  public void setAllowEmpty(boolean allowEmpty) {
    this.allowEmpty = allowEmpty;
  }

  public String[] getInputFormats() {
    return inputFormats;
  }

  public void setInputFormats(String[] inputFormats) {
    this.inputFormats = inputFormats;
  }

  public String getOutputFormat() {
    return outputFormat;
  }

  public void setOutputFormat(String outputFormat) {
    this.outputFormat = outputFormat;
  }
}
