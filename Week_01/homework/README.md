- [用 add first 或 add last 这套新的 API 改写 Deque 的代码](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
```java
public class DequeNewApiDemo {
    public static void main(String[] args){
        // linkedList implements deque
        // Old api
        Queue<Integer> deque = new LinkedList<>();
        deque.add(10000);
        deque.add(20000);
        deque.add(30000);
        System.out.println("Deque:" + deque);

        // new api
        LinkedList<String> ll = new LinkedList<String>();
        // Adding elements to the linked list
        ll.add("A");
        ll.add("B");
        ll.addLast("C");
        ll.addFirst("D");
        ll.add(2, "E");

        System.out.println(ll);

        ll.remove("B");
        ll.remove(3);
        ll.removeFirst();
        ll.removeLast();

        System.out.println(ll);
    }
}
```
this program output:
```
Deque:[10000, 20000, 30000]<br>
[D, A, E, B, C]<br>
[A]<br>
```
- [分析 Queue 和 Priority Queue 的源码](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
    * 继承关系
   
        |-Iterable(Interface)<br>
        |----Collection(Interface)<br>
        |-------Queue(Interface)<br>
        |----------Deque(Interface)<br>
        |-------------LinkedList(Class)<br>
        |-------------ArrayList(Class)<br>
        |----------PriorityQueue(Class)<br>
        
    * Queue接口，相关方法如下所示
        + add(E e)：将指定的元素插入到此队列中如果当前没有可用空间，则抛出IllegalStateException
        + element：检索，但不删除，这个队列的头。
        + offer(E e)：如果在不违反容量限制的情况下立即执行，则将指定的元素插入到此队列中。
        + peek：检索但不删除此队列的头，如果此队列为空，则返回 null
        + poll：检索并删除此队列的头，如果此队列为空，则返回 null
        + remove：检索并删除此队列的头
    
    * PriorityQueue
        + 简介
        
        优先队列。优先队列的作用是能保证每次取出的元素都是队列中权值最小的。这里牵涉到了大小关系，元素大小的评判可以通过元素本身的自然顺序，也可以通过构造时传入的比较器.
        
        Java中PriorityQueue实现了Queue接口，不允许放入null元素；其通过堆实现，具体说是通过完全二叉树（complete binary tree）实现的小顶堆（任意一个非叶子节点的权值，都不大于其左右子节点的权值），也就意味着可以通过数组来作为PriorityQueue的底层实现。
        
        + 关键点
        
        由于插入元素有优先级，所有有一个比较器的东西。其中的插入和弹出都做了自己的特有的处理，关键入口函数是siftUp和siftDown，这两个函数里面进行一些优先级的比较
        
        + 方法列表
            + add：添加元素到队列中，队列满了会抛异常，其中调用offer函数进行插入
            + offer：插入元素到队列中，空就抛异常，原本没有元素就直接插入，有就调用siftUp函数进行插入
            + peek：直接返回头部元素
            + poll：获取并移除头部元素，siftDown函数返回数据
            + remove：其中调用了siftDown函数进行一些处理，移除头部元素
        + 参考链接
            + [深入理解Java PriorityQueue](https://www.cnblogs.com/CarpenterLee/p/5488070.html)  
            + [Java8 PriorityQueue 源码阅读](https://blog.csdn.net/codejas/article/details/85144502)  
            + [Queue Interface In Java](https://www.geeksforgeeks.org/queue-interface-java/)
        
- 删除排序数组中的重复项（Facebook、字节跳动、微软在半年内面试中考过）
- 旋转数组（微软、亚马逊、PayPal 在半年内面试中考过）
- [合并两个有序链表（亚马逊、字节跳动在半年内面试常考）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
- [合并两个有序数组（Facebook 在半年内面试常考）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
- [两数之和（亚马逊、字节跳动、谷歌、Facebook、苹果、微软在半年内面试中高频常考）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
- [移动零（Facebook、亚马逊、苹果在半年内面试中考过）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
- [加一（谷歌、字节跳动、Facebook 在半年内面试中考过）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
