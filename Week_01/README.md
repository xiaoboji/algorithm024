本周学习了数组、链表、跳表、栈、队列五种数据结构，做了部分练习，整体还没进入节奏，投入时间较少。

---

### 一、学习笔记
[第一周学习笔记](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/note)

### 二、练习汇总
[leetcode-做题记录(建设中)](https://github.com/xiaoboji/j-leetcode)

### 三、本周作业
简单：
- [用 add first 或 add last 这套新的 API 改写 Deque 的代码](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
- [分析 Queue 和 Priority Queue 的源码](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework)
- 删除排序数组中的重复项（Facebook、字节跳动、微软在半年内面试中考过）
- 旋转数组（微软、亚马逊、PayPal 在半年内面试中考过）
- [合并两个有序链表（亚马逊、字节跳动在半年内面试常考）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework/MergeTwoLists.java)
- [合并两个有序数组（Facebook 在半年内面试常考）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework/MergeTwoArrays.java)
- [两数之和（亚马逊、字节跳动、谷歌、Facebook、苹果、微软在半年内面试中高频常考）](https://github.com/xiaoboji/algorithm024/blob/main/Week_01/homework/TwoSum.java)
- [移动零（Facebook、亚马逊、苹果在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_005_283_move_zeros)
- [加一（谷歌、字节跳动、Facebook 在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_002_66_plus_one)

中等：
- 设计循环双端队列（Facebook 在 1 年内面试中考过）

困难：
- 接雨水（亚马逊、字节跳动、高盛集团、Facebook 在半年内面试常考）

### 四、心得及小结
心得
- 五毒神掌5分钟限时贯彻不彻底，还是会和题目杠上，最后又是一地鸡毛
- 五毒神掌重复刷题贯彻不彻底，目前还是一遍的居多，需要按艾宾浩斯记忆法第0天/+1天/+3天/+7天/+30天
- 第一遍参考别人的代码，有时候直接抄过来，其实没有理解，抄完了其实没有做完，需要理解清除

本周小结
- Arrays.asList()不能直接作用于基本数据类型如int，asList接收的是一个泛型变长参数，而我们知道基本类型是不能泛型化的，就是说8种基本类型不能作为泛型参数，要想作为泛型参数就要使用其所对应的包装类。
- 源码实现种单链表实际用的比较少，一般都是使用双端链表

### 五、疑问及困惑

- 对于链表的指针没有理解透彻，时长犯晕 pre = pre.next、pre.next = pre.next.next？
- 很多题都有很多种解法，对于参考来说，我们需要看几种解法就够了？
