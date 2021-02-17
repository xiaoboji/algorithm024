数组的知识总结
---
  
> 经典的定义:数组（Array）是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。数组支持随机访问，根据下标随机访问的时间复杂度为 O(1) 

中间有三个核心概念
- 线程表

线性表就是数据排成像一条线一样的结构。每个线性表上的数据最多只有前和后两个方向。其实除了数组，链表、队列、栈等也是线性表结构。

与之相对应的是非线性表，非线性表中，数据之间并不是简单的前后关系,往往具有多前或者多后。比如二叉树、堆、图等。
- 连续的内存空间

即使整体内存足够，但是数据不连续，也无法初始化需要大小的数组。

- 相同类型的数据

java中即使现在有了泛型，在泛型擦除之后，也还是具体的相同的数据类型。

小结：

正是有了上述三个特点，才使得数组有了杀手锏一样的特性，随机访问。但是有利有弊，连续的内存空间和相同的数据类型，也使得数据的删除插入等操作低效，因为了保证连续性，就要移动大量的数据。

时间复杂度分析:

操作     | 时间复杂度
-------- | -----
插入  | O(N)
删除  | O(N)
修改  | O(1)
查询  | O(1)