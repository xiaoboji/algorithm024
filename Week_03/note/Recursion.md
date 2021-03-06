递归知识总结
---
- 递归需要满足的三个条件

    * 一个问题的解可以分解为几个子问题的解
    * 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
    * 存在递归终止条件

递归是一种非常高效、简洁的编码技巧。只要是满足“三个条件”的问题就可以通过递归代码来解决。

递归代码虽然简洁高效，但是，递归代码也有很多弊端。比如，堆栈溢出、重复计算、函数调用耗时多、空间复杂度高等，所以，在编写递归代码的时候，一定要控制好这些副作用。

- 几个关键点
    * 编写递归代码的关键是，只要遇到递归，我们就把它抽象成一个递推公式，不用想一层层的调用关系，不要试图用人脑去分解递归的每个步骤。
    * 函数调用会使用栈来保存临时变量。每调用一个函数，都会将临时变量封装为栈帧压入内存栈，等函数执行完成返回时，才出栈。系统栈或者虚拟机栈空间一般都不大。如果递归求解的数据规模很大，调用层次很深，一直压入栈，就会有堆栈溢出的风险。
    * 不要人肉进行递归
    * 找最近重复子问题
    * 数学归纳法的思维
- Java代码模板
```
public void recur(int level, int param) {
  //terminator
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // process current logic
  process(level, param);
  
  // drill down
  recur(level: level + 1, newParam);
  
  // restore current status
}
```
    