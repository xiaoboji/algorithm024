本周学习了哈希、树、堆、图

### 一、哈希、映射、集合

- **hash table**

哈希表，也叫散列表，是根据关键码值而直接进行访问的数据结构。它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫做散列函数(hash function),存放记录的数据叫做哈希表(或散列表)

- **工程实践**
    * 电话号码簿
    * 用户信息表
    * 缓存(LRU Cache)
    * 键值对存储(Redis)
    * 安全加密(MD5,SHA,DES,AES算法等)
    * 唯一标识(哈希算法可以对大数据做信息摘要，通过一个较短的二进制编码来表示很大的数据)
    * 数据校验(校验数据的完整性和正确性)
    * 负载均衡(一个会话粘滞（session sticky）的负载均衡算法,即在一次会话中的所有请求都路由到同一个服务器上)
    * 分布式存储(利用一致性哈希算法，可以解决缓存等分布式系统的扩容、缩容导致数据大量搬移的难题)
    * 数据分片(处理的海量数据进行分片，多机分布式处理，可以突破单机资源的限制)

- **一个好的hash函数**
    * 从哈希值不能反向推导出原始数据（所以哈希算法也叫单向哈希算法）
    * 对输入数据非常敏感，哪怕原始数据只修改了一个bit，最后得到的哈希值也大不相同
    * 散列冲突的概率要很小，对于不同的原始数据，哈希值相同的概率很小
    * 哈希算法的执行效率要尽量的高效，针对较长的文本，能快速的计算出哈希值
    
一个比较好的hash函数的例子就是MD5算法
```
MD5("今天我来讲哈希算法") = bb4767201ad42c74e650c1b6c03d78fa
MD5("jiajia") = cd611a31ea969b908932d44d126d195b
```
- **一致性hash算法**

假设我们有 k 个机器，数据的哈希值的范围是[0, MAX]。我们将整个范围划分成 m 个小区间（m 远大于 k），每个机器负责 m/k 个小区间。

当有新机器加入的时候，我们就将某几个小区间的数据，从原来的机器中搬移到新的机器中。这样，既不用全部重新哈希、搬移数据，也保持了各个机器上数据数量的均衡。

- **时间空间复杂度分析**

平均复杂度:查询O(1);插入O(1);删除O(1);

最坏复杂度:查询O(n);插入O(n);删除O(n);

- **map和set**
    * map(key-value对，key值不重复)
        + new HashMap()
        + map.set(key,value)
        + map.get(key)
        + map.has(key)
        + map.size()
        + map.clear()

    * set(不重复元素的集合)
        + new HashSet()
        + set.add(value)
        + set.delete(value)
        + set.has(value)
        
### 二、树、二叉树、二叉搜索树
- **树**

树和图都是二维的数据结构，二者的区别，是图有环，树没有环，树可以解决在单链表中查询过慢的问题。

Linked List是特殊化的Tree，Tree是特殊化的Graph

树的定义
```java
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

节点的高度(Height) = 节点到叶子节点的最长路径(边数)<br>
节点的深度深度（Depth）= 根节点到这个节点所经历的边的个数<br>
节点的层树（Level) = 节点的深度 + 1<br>
树的高度 = 根节点的高速<br>

![Alt](https://github.com/xiaoboji/algorithm024/tree/main/Week_02/pic/50f89510ad1f7570791dd12f4e9adeb4.jpg)

- **树的遍历**
    * 前序遍历：跟-左-右
    * 中序遍历：左-跟-右
    * 后序遍历：左-右-跟
- **二叉树**

- **二叉搜索树**



### 三、堆、二叉堆


### 四、图


### 参考链接


