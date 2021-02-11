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
V | compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)<br>计算指定键值
V | computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)<br>计算不存在的键值
V | computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)<br>计算存在的键值
boolean | containsKey(Object key)<br>如果map里包含这个key，则返回true
boolean | containsValue(Object value)<br>如果map里包含一个或多个key的value为这个值，则返回true
Set<Map.Entry<K,V>> | entrySet()<br>返回一个所有映射关系的Set
void | forEach(BiConsumer<? super K,? super V> action)<br>一种简单的循环方式
V | get(Object key)<br>获取值
V | getOrDefault(Object key, V defaultValue)<br>获取值，如果不存在就返回默认值
boolean | isEmpty()<br>是否为空，为空则返回ture
Set<K> | keySet()<br>返回包含所有key的一个set
V | merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)<br>新老值合并指定key+value
V | put(K key, V value)<br>写入指定的key值
void | putAll(Map<? extends K,? extends V> m)<br>将另一个map里的值全部写入，如果有重复的key则覆盖原值
V | putIfAbsent(K key, V value)<br>如果不存在则写入
V |	remove(Object key) <br>移除指定的key值
boolean | remove(Object key, Object value)<br>移除指定的key+value
V | replace(K key, V value)<br>替换指定的key值
boolean | replace(K key, V oldValue, V newValue)<br>替换指定的key+value值
void | replaceAll(BiFunction<? super K,? super V,? extends V> function)<br>替换所有的value值
int | size()<br>返回map的size
Collection<V> | values()<br>返回所有的值的一个collection
#### 三、HashMap Test实例
```java
/**
 * HashMap API Test
 *
 * @author jixiaobo
 */
public class HashMapTest {
  public static void main(String[] args){
    HashMap<String,String> hashMap = new HashMap<>(16);

    // 存入值(>>>>:{1=A, 2=B, 3=C})
    hashMap.put("1","A");
    hashMap.put("2","B");
    hashMap.put("3","C");
    // putIfAbsent 当key不存在时添加, key存在时不做任何操作
    hashMap.putIfAbsent("1","A-putIfAbsent");
    System.out.println(hashMap);

    // 返回元素个数(>>>>:3)
    int size = hashMap.size();
    System.out.println(size);

    // 获取值(>>>>:A)
    String value = hashMap.get("1");
    System.out.println(value);
    // 获取key值对应的value，当key不存在的时候，返回默认值(>>>>:default)
    String def = hashMap.getOrDefault("5", "default");
    System.out.println(def);

    // 是否包含key/value值,是否为空(>>>>:true false false)
    boolean bkey = hashMap.containsKey("1");
    boolean bvalue = hashMap.containsKey("A");
    boolean isempty = hashMap.isEmpty();
    System.out.println(bkey + " " + bvalue + " " + isempty);

    // putAll，讲一个具体的MAP中的映射关系全部复制到另一个map中，hashmap如果有相同的key，将会被覆盖(>>>>:{1=T1, 2=B, 3=C, 4=T})
    HashMap<String,String> temp = new HashMap<>(2);
    temp.put("4","T");
    temp.put("1","T1");
    hashMap.putAll(temp);
    System.out.println(hashMap);

    // remove-移除key值或者key(>>>>:{1=T1, 3=C, 4=T})
    hashMap.remove("2");
    // remove-只有完全匹配才会被移除，下面的key=3 value=A不存在所有不被移除1
    hashMap.remove("3","A");
    System.out.println(hashMap);

    // keySet-获取hashmap中的所有key(>>>>:1 3 4  )
    Set<String> keys = hashMap.keySet();
    for (String str :  keys) {
        System.out.print(str + " ");
    }
    System.out.println();

    // keySet-获取hashmap中的所有值(>>>>: T1 C T  )
    Collection<String> values = hashMap.values();
    for (String str :  values) {
      System.out.print(str + " ");
    }
    System.out.println();

    // replace-替换value的逻辑，(>>>>: {1=A-replace, 3=A-replaceAll, 4=A-replaceAll})
    hashMap.replace("1", "A+");
    // replaceAll-替换所有的值为100
    hashMap.replaceAll((k,v) -> v = "A-replaceAll");
    // replace-匹配上key+value的值之后才会被替换
    hashMap.replace("1","A-replaceAll","A-replace");
    System.out.println(hashMap);

    // entrySet-获取所有值(>>>>: 1=A-replace 3=A-replaceAll 4=A-replaceAll )
    Set<Entry<String, String>> mapEvtry = hashMap.entrySet();
    for (Entry<String,String> entry : mapEvtry ) {
      System.out.print(entry + " ");
    }
    System.out.println();

    // forEach-lambda表达式，更简单的for循环(>>>>: 1 A-replace3 A-replaceAll4 A-replaceAll)
    hashMap.forEach(
        (k, v) -> {
          System.out.print(k + " " + v);
        });
    System.out.println();

    // merge-合并value的旧值和新值(>>>>: {1=A-replacenew, 3=A-replaceAll, 4=A-replaceAll})
    hashMap.merge("1", "new", (oldValue, newValue) -> oldValue + newValue);
    System.out.println(hashMap);

    // 对不存在的key进行计算,如果是数值型 可以进行相应的数值计算(>>>>: {1=A-replacenew, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll})
    hashMap.computeIfAbsent("2", (key) -> "computeIfAbsent");
    System.out.println(hashMap);
    // 对存在的key进行计算,如果是数值型 可以进行相应的数值计算(>>>>:{1=A-replacenew computeIfAbsent, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll} )
    hashMap.computeIfPresent("1", (key, oldvalue) -> oldvalue + " computeIfAbsent" );
    System.out.println(hashMap);
    // 对指定key进行计算,不存在的时候添加，存在的时候修改(>>>>: {1=A-replacenew computeIfAbsent, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll, 6=null computeIfAbsent})
    hashMap.compute("6", (key, oldvalue) -> oldvalue + " computeIfAbsent" );
    System.out.println(hashMap);

    // clone，由当前的数据浅拷贝，key和value还是一份内存地址，不会被拷贝(>>>>: {1=A-replacenew computeIfAbsent, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll, 6=null computeIfAbsent})
    Object cloneMap = hashMap.clone();
    hashMap.put("1", "clone");
    hashMap.clear();
    System.out.println(cloneMap);

  }

}
```
#### 四、HashMap 关键源码解读
- 链表节点
```
 /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;//hash值
        final K key;//key值
        V value;//value值
        Node<K,V> next;//下一个节点

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            // 每一个节点的hash值，是将key值的hash和value值的hash异或后的值
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
```
hashmap的节点是使用node进行存储，使用的是一个单链表，单链表中每个节点的hash值是根据key和value的hashcode进行异或

- put()函数
```
  /**
     * Implements Map.put and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value//如果onlyifAbsent为true，不会覆盖相同key的值value
     * @param evict if false, the table is in creation mode.// 如果evict是false，
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        // tab存储当前的hash桶，p作为临时链表节点
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // table 被延迟到插入新数据时再进行初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 如果桶中不包含键值对节点引用，则将新键值对节点的引用存入桶中即可
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        // 发生了hash冲突
        else {
            Node<K,V> e; K k;
            // 如果键的值以及节点 hash 等于链表中的第一个键值对节点时，则将 e 指向该键值对
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 如果hash值相同，节点又是通过红黑树存储的，则使用红黑树存储新节点
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            // 如果不需要覆盖，则插入一个普通的链表节点
            else {
                // 对链表进行遍历，并统计链表长度
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        // 链表中不包含要插入的键值对节点时，则将该节点接在链表的最后
                        p.next = newNode(hash, key, value, null);
                        // 如果节点的链表数量>=8，则转换为红黑树，以增加查询效率
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 条件为 true，表示当前链表包含要插入的键值对，终止遍历
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // 判断要插入的键值对是否存在 HashMap 中
            if (e != null) { // existing mapping for key
                // onlyIfAbsent 表示是否仅在 oldValue 为 null 的情况下更新键值对的值
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        // 修改modCount
        ++modCount;
        // 键值对数量超过阈值时，则进行扩容
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

- 查询具体的key值
```
    public V get(Object key) {
        Node<K,V> e;
        // 根据key值获得hash值，根据hash值找具体的值
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
    // 根据hash值和key找到目标节点ndoe
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        // 如果hash表不为空，根据hash值计算出index(核心在此-->first = tab[(n - 1) & hash])
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            // 如果index处只有一个值，即没有hash冲突，则直接返回这个值
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            // 如果index处有多个值
            if ((e = first.next) != null) {
                // index的节点是一个树节点，则调用红黑树进行查找
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                // 否则的话就使用链表逐个查找
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```
- 扩容函数

```
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    // 如果 table 不为空，表明已经初始化过了
    if (oldCap > 0) {
        // 当 table 容量超过容量最大值，则不再扩容
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        } 
        // 按旧容量和阈值的2倍计算新容量和阈值的大小
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    } else if (oldThr > 0) // initial capacity was placed in threshold
        /*
         * 初始化时，将 threshold 的值赋值给 newCap，
         * HashMap 使用 threshold 变量暂时保存 initialCapacity 参数的值
         */ 
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        /*
         * 调用无参构造方法时，桶数组容量为默认容量，
         * 阈值为默认容量与默认负载因子乘积
         */
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    
    // newThr 为 0 时，按阈值计算公式进行计算
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    // 创建新的桶数组，桶数组的初始化也是在这里完成的
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        // 如果旧的桶数组不为空，则遍历桶数组，并将键值对映射到新的桶数组中
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    // 重新映射时，需要对红黑树进行拆分
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    // 遍历链表，并将链表节点按原顺序进行分组
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    // 将分组后的链表映射到新桶中
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}

```
#### 五、HashMap 小结
- hashmap的作用
    * HashMap 底层基于散列算法实现
    * HashMap 允许 null 键和 null 值
    * HashMap 则使用了拉链式的散列算法，并在 JDK 1.8 中引入了红黑树优化过长的链表。
    * HashMap 并不保证键值对的顺序，这意味着在进行某些操作后，键值对的顺序可能会发生变化
    * HashMap 是非线程安全类，在多线程环境下可能会存在问题
- putval()
    * 当桶数组 table 为空时，通过扩容的方式初始化 table
    * 查找要插入的键值对是否已经存在，存在的话根据条件判断是否用新值替换旧值
    * 如果不存在，则将键值对链入链表中，并根据链表长度决定是否将链表转为红黑树
    * 判断键值对数量是否大于阈值，大于的话则进行扩容操作
- get()
    * 先定位键值对所在的桶的位置
    * 然后再对链表或红黑树进行查找
- resize()
    * HashMap桶数组的长度均是2的幂，阈值大小为桶数组长度与负载因子的乘积
    * 当HashMap中的键值对数量超过阈值时，进行扩容
    * 扩容之后，要重新计算键值对的位置，并把它们移动到合适的位置上去
    
#### 六、参考链接
- [HashMap 源码详细分析(JDK1.8)](https://segmentfault.com/a/1190000012926722)
- [HashMap API文档](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)