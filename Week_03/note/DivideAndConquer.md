分治知识总结
---
- 分治是一种特殊的递归，归根到底就是找重复性
    * 最优重复性：动态规划
    * 最近重复性：分治、回溯等
    
- 和递归非要说有什么差别的话，可以理解为拆分完之后，有个合并的过程

- 代码模板
```
private static int divide_conquer(Problem, problem){
    // 到达最小问题，叶子结点
    if (problem == null) {
        int res = process_last_result();
        return res;    
    }
    // 处当前逻辑，如何把这个大问题拆分成小问题
    subProlems = split_prolem(problem);
    // 下探一层，解决更细节的问题
    res0 = divide_conquer(subProblems[0]);
    res1 = divide_conquer(subProblems[1]);
    // 对结果数据进行合并
    result = process_result(res0, res1);
    // 返回结果
    return result;
}
```

