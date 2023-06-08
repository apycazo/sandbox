package sandbox.components.store;

import org.springframework.util.StringUtils;

public record StoreRecord(String key, String value) {

  boolean isValid() {
    return StringUtils.hasText(key) && value != null;
  }
}
