package sandbox.components.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class KeyValueService {

  private final Map<String, StoreRecord> map = new ConcurrentHashMap<>();

  public void save(StoreRecord record) throws InvalidRecordException {
    if (record != null && record.isValid()) {
      map.put(record.key(), record);
    } else if (record != null) {
      throw new InvalidRecordException(record.key(), record.value());
    } else {
      throw new InvalidRecordException(null, null);
    }
  }

  public void save(String key, String value) throws InvalidRecordException {
    if (StringUtils.hasText(key) && StringUtils.hasText(value)) {
      map.put(key, new StoreRecord(key, value));
    } else {
      throw new InvalidRecordException(key, value);
    }
  }

  public Optional<StoreRecord> getByKey(String key) {
    return Optional.ofNullable(map.get(key));
  }

  public Collection<StoreRecord> getAllValues() {
    return map.values();
  }

  public boolean deleteByKey(String key) {
    if (StringUtils.hasText(key)) {
      return map.remove(key) != null;
    } else {
      return false;
    }
  }

  public int deleteAll() {
    int count = map.size();
    map.clear();
    return count;
  }
}
