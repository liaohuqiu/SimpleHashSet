[![Build Status](https://travis-ci.org/liaohuqiu/SimpleHashSet.svg)](https://travis-ci.org/liaohuqiu/SimpleHashSet)

### maven 中央库

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

### 最多节省 25% 内存

> Java 内存大小: http://www.liaohuqiu.net/cn/posts/caculate-object-size-in-java/

> Davlik 内存大小: http://www.liaohuqiu.net/posts/android-object-size-dalvik/

`java.util.HashSet` 仅仅是简单包装了 `java.util.HashMap`, 内存占用为 O(`n` * `entry_size`).

`java.util.HashMap` 采用链接法解决冲突, `n` 是元素总数和数组中可用槽之和。

`entry_size` 是 `jave.util.HashMap.Entry` 对象在内存中的大小。

`java.util.HashMap.Entry`的定义如下:

```
static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    Entry<K,V> next;
    int hash;
}
```

在不同平台，内存占用为:

*  32bit JVM:  8 + 4 * 4 = 24 bytes
*  64bit JVM `-UseCompressedOops`: 16 + 8 * 4 = 48 bytes.
*  64bit JVM `+UseCompressedOops`: 12 + 4 * 4 + 4(padding) = 32 bytes.
*  Davlik:    12 + 4 * 4 + 4(padding) = 32 bytes.

对于`HashSet`来说，`V value` 这个字段是没用的，如果我们采用以下的`SimpleHashSetEntry`来实现 `SimpleHashSet`:

```
private static class SimpleHashSetEntry<T> {

    private int mHash;
    private T mKey;
    private SimpleHashSetEntry<T> mNext;
}
```

占用的内存大小为:

*  32bit JVM:  8 + 4 * 3 + (padding) = 24 bytes
*  64bit JVM `-UseCompressedOops`: 16 + 8 * 3 = 40 bytes. (8 bytes saved, 16.66%)
*  64bit JVM `+UseCompressedOops`: 12 + 4 * 3 = 24 bytes. (8 bytes saved, 25%)
*  Davlik:    12 + 4 * 3 = 24 bytes (8 bytes saved, 25%).

除了了32位的JVM上，都能节省可观的内存。


### License

Apache 2
