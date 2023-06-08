package sandbox.components.store;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sandbox.components.shared.Outcome;

import java.util.Collection;

@RestController
@RequestMapping("store")
public class KeyValueController {

  private final KeyValueService service;

  public KeyValueController(KeyValueService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<StoreRecord> findAll() {
    return service.getAllValues();
  }

  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Outcome<Integer> deleteAll() {
    return Outcome.success(service.deleteAll());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody StoreRecord record) throws InvalidRecordException {
    service.save(record);
  }

  @GetMapping(value = "{key}", produces = MediaType.APPLICATION_JSON_VALUE)
  public StoreRecord findByKey(@PathVariable("key") String key) {
    return service
      .getByKey(key)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("{key}")
  public Outcome<Boolean> deleteByKey(@PathVariable("key") String key) {
    return Outcome.success(service.deleteByKey(key));
  }
}
