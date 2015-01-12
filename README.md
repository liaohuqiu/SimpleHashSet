### maven Central

```xml
<dependency>
    <groupId>in.srain.cube</groupId>
    <artifactId>simple-hashset</artifactId>
    <type>jar</type>
    <version>1.0.1</version>
</dependency>
```

gradle:

```
compile 'in.srain.cube:simple-hashset:1.0.1'
```

### Save 25% memory

In fact, `java.util.HashSet` is just a wrapper of `java.util.HashMap`, so its retained heap size is O(`n` * `entry_size`).

The `java.util.HashMap` is using separate chaining to process collision, `n` is the sum of the count of element and the count of free slots remain in array.

The `entry_size` is the shadow heap size of the `jave.util.HashMap.Entry`.

The definition of `java.util.HashMap.Entry`:

```
static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    Entry<K,V> next;
    int hash;
}
```

So the shadow heap size is:

*  32bit JVM:  8 + 4 * 4 = 24 bytes
*  64bit JVM `-UseCompressedOops`: 16 + 8 * 4 = 48 bytes.
*  64bit JVM `+UseCompressedOops`: 12 + 4 * 4 + 4(padding) = 32 bytes.
*  Davlik:    12 + 4 * 4 + 4(padding) = 32 bytes.

In fact `V value` is usless for `HashSet`, we can implement a HashSet use the following `SimpleHashSetEntry`:

```
private static class SimpleHashSetEntry<T> {

    private int mHash;
    private T mKey;
    private SimpleHashSetEntry<T> mNext;
}
```

The retained heap size is:

*  32bit JVM:  8 + 4 * 3 + (padding) = 24 bytes
*  64bit JVM `-UseCompressedOops`: 16 + 8 * 3 = 40 bytes. (8 bytes saved, 16.66%)
*  64bit JVM `+UseCompressedOops`: 12 + 4 * 3 = 24 bytes. (8 bytes saved, 25%)
*  Davlik:    12 + 4 * 3 = 24 bytes (8 bytes saved, 25%).
