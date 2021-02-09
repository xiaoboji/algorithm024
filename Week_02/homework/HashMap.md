HashMap的学习笔记, 总结一下，顺便学学英语

---
#### 一、HashMap简介
> public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable

继承了AbstractMap抽象类，实现了Map,Cloneable,Serializable接口

> Hash table based implementation of the Map interface. This implementation provides all of the optional map operations, and permits null values and the null key. (The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls.) This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time.

基于Hash表实现的MAP接口，实现了所有可选的的map操作，允许null值和null键.(HashMap类似于hashtable，不同之处在于hashmap是线程不安全的，并且允许空值)，hashmap不保证顺序，具体点，不保证随时间推移，内部顺序保持不变。

> This implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the elements properly among the buckets. Iteration over collection views requires time proportional to the "capacity" of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.

如果hash函数保证了元素均匀的分散，HashMap的常用操作(get和put)可以达到常数时间的性能，对集合进行迭代操作所需的时间，和HashMap实例的容量(桶的数量)以及大小(键值对数)成比例。所以，如果对迭代性能要求比较高的话，初始容量不要太高，装载因子不要太低。

> An instance of HashMap has two parameters that affect its performance: initial capacity and load factor. The capacity is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created. The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.

一个hashMap的实例，有两个重要的参数影响它的性能，初始容量和负载因子。这个容量就是hash算法中桶的数量，初始容量简而言之就是刚开始创建的时候的初始容量。这个负载因子就是当hash表的容满的时候，去创建hash表的一个度量值。当hash表里的元素个数超过了当前容量*负载因子，这个hash表就会重hash(初始的hash结构会被重构)，hash表会扩充到原来的大致两倍的大小。

> As a general rule, the default load factor (.75) offers a good tradeoff between time and space costs. Higher values decrease the space overhead but increase the lookup cost (reflected in most of the operations of the HashMap class, including get and put). The expected number of entries in the map and its load factor should be taken into account when setting its initial capacity, so as to minimize the number of rehash operations. If the initial capacity is greater than the maximum number of entries divided by the load factor, no rehash operations will ever occur.

常规来说，默认的负载因子(0.75)能兼顾时间和空间的开销。负载因子越大会减少空间的消耗，但是会增加查找的消耗(影响包括get和put在内的大部分HashMap的操作)。设置这个值要综合考虑表里的数据量和负载因子，以到达最少rehash的操作次数。如果初始容量太大，将永远不会rehash(这样浪费了空间)。

> If many mappings are to be stored in a HashMap instance, creating it with a sufficiently large capacity will allow the mappings to be stored more efficiently than letting it perform automatic rehashing as needed to grow the table. Note that using many keys with the same hashCode() is a sure way to slow down performance of any hash table. To ameliorate impact, when keys are Comparable, this class may use comparison order among keys to help break ties.

如果一个HashMap的实例需要存储很多映射关系，创建一个足够大的可以从初它的容量，比让它不停的rehash要更有效一些。很多key都有相同的hashcode会降低检索的性能。为了改善这个影响，当这些
键值hashcode一样的时候，这个类需要比较这些减值以解决问题。

> Note that this implementation is not synchronized. If multiple threads access a hash map concurrently, and at least one of the threads modifies the map structurally, it must be synchronized externally. (A structural modification is any operation that adds or deletes one or more mappings; merely changing the value associated with a key that an instance already contains is not a structural modification.) This is typically accomplished by synchronizing on some object that naturally encapsulates the map. If no such object exists, the map should be "wrapped" using the Collections.synchronizedMap method. This is best done at creation time, to prevent accidental unsynchronized access to the map:

需要注意的是，hashmap不是线程安全的，在多线程的条件下，有多个线程结构化修改这个map的值，那它必须在外部进行同步。(结构化修改是指，添加或者删除一个映射的相关操作，只是改变一一下值不是一个结构化修改)。通常使用同步这个对象来实现对hashmap不同步的支持。如果不存在这样的类，需要使用Collections.synchronizedMap方法，为了防止有不同步的情况出现，最好创建hashmap的时候就这么干。

> Map m = Collections.synchronizedMap(new HashMap(...));

> The iterators returned by all of this class's "collection view methods" are fail-fast: if the map is structurally modified at any time after the iterator is created, in any way except through the iterator's own remove method, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.

HashMap的所有集合视图方法返回的迭代器都满足fail-fast机制，这个迭代器创建之后，map在任何时候被修改，只能通过迭代器励的remove方法，除此之外，会抛出一个ConcurrentModificationExceprion方。因此，面对这种并发修改的场景，迭代器快速的返回失败，而不是将来会莫名其妙的爆出一个不确定的行为或者错误。

> Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification. Fail-fast iterators throw ConcurrentModificationException on a best-effort basis. Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators should be used only to detect bugs.

需要注意的是，fail-fast机制不能够保证没有问题，通常来说，也不可能完全保证对于一个不同步的去做非同步的并发修改，fail-fast迭代器尽最大努力的去抛出ConcurrentModificaitonException。因此，写程序去依赖这个异常是不对的，这个fail-fast机制应该仅仅用来解决bug。

#### 二、HashMap API详解
返回类型   | 方法和描述
-------- | -----
void | clear()<br>移除map里的所有数据
Object | clone()<br>返回一个hashmap的浅拷贝，具体的key值和values值不会被克隆
V | compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)<br>
V | computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)<br>
V | computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)<br>
boolean | containsKey(Object key)<br>如果map里包含这个key，则返回true
boolean | containsValue(Object value)<br>如果map里包含一个或多个key的value为这个值，则返回true
Set<Map.Entry<K,V>> | entrySet()<br>
void | forEach(BiConsumer<? super K,? super V> action)<br>
V | get(Object key)<br>
V | getOrDefault(Object key, V defaultValue)<br>
boolean | isEmpty()<br>
Set<K> | keySet()<br>
V | merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)<br>
V | put(K key, V value)<br>
void | putAll(Map<? extends K,? extends V> m)<br>
V | putIfAbsent(K key, V value)<br>
V |	remove(Object key) <br>
boolean | remove(Object key, Object value)<br>
V | replace(K key, V value)<br>
boolean | replace(K key, V oldValue, V newValue)<br>
void | replaceAll(BiFunction<? super K,? super V,? extends V> function)<br>
int | size()<br>
Collection<V> | values()<br>
#### 三、HashMap Test实例

#### 四、HashMap 关键源码解读

#### 五、HashMap 小结



