# Reference

## Multi modules

When a project with multiple modules is created, note that locking dependencies with 
`gradle dependencies --write-locks` does not really work, since it only locks the root module 
dependencies.

Create a custom like this one to lock all submodules:

```groovy
tasks.register('resolveAndLockAll') {
  notCompatibleWithConfigurationCache("Filters configurations at execution time")
  doFirst {
    assert gradle.startParameter.writeDependencyLocks
  }
  doLast {
    configurations.findAll {
      // Add any custom filtering on the configurations to be resolved
      it.canBeResolved
    }.each { it.resolve() }
  }
}
```
