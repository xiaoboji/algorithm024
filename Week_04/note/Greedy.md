贪心算法知识总结
---

 - 概念

贪心算法（英语：greedy algorithm），又称贪婪算法，是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是最好或最优的算法。

贪心算法在有最优子结构的问题中尤为有效。最优子结构的意思是局部最优解能决定全局最优解。简单地说，问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。

- 经验技巧

贪心算法理论消化不了，直接练习最好。

贪心算法的最难的一块是如何将要解决的问题抽象成贪心算法模型，只要这一步搞定之后，贪心算法的编码一般都很简单。

实现该算法的过程：<br>
从问题的某一初始解出发；while 能朝给定总目标前进一步 do，求出可行解的一个解元素<br>
最后，由所有解元素组合成问题的一个可行解。<br>

- 经典应用
    * 霍夫曼编码（Huffman Coding）
    * Prim算法 
    * Kruskal 最小生成树算法
    * Dijkstra 单源最短路径算法

- 参考资料
    * [贪心算法wiki](https://zh.wikipedia.org/wiki/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95)
