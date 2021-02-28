二分查找知识总结
---
- 二分查找的前提
    * 目标单数单调性(单调递增或者递减)
    * 存在上下界(bounded)
    * 能够通过索引/下标访问(index accessible),简单点说就是数组。
    
- 代码模板

三个容易出错的地方：循环退出条件、mid 的取值，low 和 high 的更新。

```
  // 循环实现 
  public int binarySearch(int[] array, int target) 
    {    
      int left = 0, right = array.length - 1, mid;
      while (left <= right) {
        mid = (right - left) / 2 + left;
        if (array[mid] == target) {
          return mid;        
        } else if (array[mid] > target) {
          right = mid - 1;        
        } else {
          left = mid + 1;        
        }    
      }
      return -1;
    }
```

```

// 二分查找的递归实现
  public int bsearch(int[] a, int n, int val) {
    return bsearchInternally(a, 0, n - 1, val);
  }
    
  private int bsearchInternally(int[] a, int low, int high, int value) {
    if (low > high) return -1;
    
    int mid =  low + ((high - low) >> 1);
    if (a[mid] == value) {
      return mid;
    } else if (a[mid] < value) {
      return bsearchInternally(a, mid+1, high, value);
    } else {
      return bsearchInternally(a, low, mid-1, value);
    }
  }
```
- 经验总结
    * 二分查找依赖的是顺序表结构，简单点说就是数组
    * 二分查找针对的是有序数据
    * 数据量太小不适合二分查找，顺序遍历就够了
    * 数据量太大也不适合二分查找，要用数组，就要用大量的连续空间

- 典型应用
    * 1000 万个整数数据(内存限制是 100MB，每个数据大小是 8 字节)，快速判断某个整数是否出现在这 1000 万数据中。
    * 快速定位IP对应的省份地址
    * 查找第一个值等于给定值的元素
    * 查找最后一个值等于给定值的元素
    * 查找第一个大于等于给定值的元素
    * 查找最后一个小于等于给定值的元素
    
- 参考链接
    *  [Fast InvSqrt() 扩展阅读](https://www.beyond3d.com/content/articles/8/)