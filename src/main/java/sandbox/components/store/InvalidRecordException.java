package sandbox.components.store;

import org.springframework.util.StringUtils;

public class InvalidRecordException extends Exception {

  public InvalidRecordException(String key, String value) {
    super("Invalid entry " + String.join(",",
      StringUtils.hasText(key) ? null : "Key must have text",
      value != null ? null : "Value must not be null"
    ));
  }
}
