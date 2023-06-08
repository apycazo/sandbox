package sandbox.components.shared;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Outcome<T> {

  @Builder.Default
  private boolean success = true;
  @Builder.Default
  private long timestamp = Instant.now().toEpochMilli();
  private T content;

  public static <T> Outcome<T> success(T content) {
    return Outcome.<T>builder().success(true).content(content).build();
  }

  public static <T> Outcome<T> failure(T content) {
    return Outcome.<T>builder().success(false).content(content).build();
  }
}
