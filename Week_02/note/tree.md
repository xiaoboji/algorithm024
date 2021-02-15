树、二叉树、二叉搜索树的知识总结
---

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

- **树的遍历**
    * 前序遍历：跟-左-右
    * 中序遍历：左-跟-右
    * 后序遍历：左-右-跟

- **二叉树**

    * 完全二叉树是二叉树的一种特殊情况，满二叉树又是完全二叉树的一种特殊情况
    * 二叉树: 顾名思义，每个节点最多有两个“叉”，也就是两个子节点，分别是左子节点和右子节点。
    * 满二叉树: 编号 2 的二叉树中，叶子节点全都在最底层，除了叶子节点之外，每个节点都有左右两个子节点。
    * 完全二叉树: 叶子节点都在最底下两层，最后一层的叶子节点都靠左排列，并且除了最后一层，其他层的节点个数都要达到最大，这种二叉树叫做完全二叉树。
    * 二叉树的存储：
        + 基于指针的链式存储法：每个节点有三个字段，其中一个存储数据，另外两个是指向左右子节点的指针，大部分二叉树代码都是通过这种结构来实现的。
        + 基于数组的顺序存储法：如果节点 X 存储在数组中下标为 i 的位置，下标为 2 * i 的位置存储的就是左子节点，下标为 2 * i + 1 的位置存储的就是右子节点。反过来，下标为 i/2 的位置存储就是它的父节点。通过这种方式，我们只要知道根节点存储的位置（一般情况下，为了方便计算子节点，根节点会存储在下标为 1 的位置），这样就可以通过下标计算，把整棵树都串起来。如果某棵二叉树是一棵完全二叉树，那用数组存储无疑是最节省内存的一种方式。

- **二叉搜索树**

二叉查找树是二叉树中最常用的一种类型，也叫二叉搜索树。顾名思义，二叉查找树是为了实现快速查找而生的。二叉查找树要求，在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值。

1. 查找：

首先，我们看如何在二叉查找树中查找一个节点。我们先取根节点，如果它等于我们要查找的数据，那就返回。如果要查找的数据比根节点的值小，那就在左子树中递归查找；如果要查找的数据比根节点的值大，那就在右子树中递归查找。
```
  public Node find(int data) {
    Node p = tree;
    while (p != null) {
      if (data < p.data) p = p.left;
      else if (data > p.data) p = p.right;
      else return p;
    }
    return null;
  }

```
2. 插入

二叉查找树的插入过程有点类似查找操作。新插入的数据一般都是在叶子节点上，所以我们只需要从根节点开始，依次比较要插入的数据和节点的大小关系。

如果要插入的数据比节点的数据大，并且节点的右子树为空，就将新数据直接插到右子节点的位置；如果不为空，就再递归遍历右子树，查找插入位置。同理，如果要插入的数据比节点数值小，并且节点的左子树为空，就将新数据插入到左子节点的位置；如果不为空，就再递归遍历左子树，查找插入位置。

```
public void insert(int data) {
  if (tree == null) {
    tree = new Node(data);
    return;
  }

  Node p = tree;
  while (p != null) {
    if (data > p.data) {
      if (p.right == null) {
        p.right = new Node(data);
        return;
      }
      p = p.right;
    } else { // data < p.data
      if (p.left == null) {
        p.left = new Node(data);
        return;
      }
      p = p.left;
    }
  }
}
```
3. 删除
二叉查找树的查找、插入操作都比较简单易懂，但是它的删除操作就比较复杂了 。

第一种情况是，如果要删除的节点没有子节点，我们只需要直接将父节点中，指向要删除节点的指针置为 null。

第二种情况是，如果要删除的节点只有一个子节点（只有左子节点或者右子节点），我们只需要更新父节点中，指向要删除节点的指针，让它指向要删除节点的子节点就可以了。

第三种情况是，如果要删除的节点有两个子节点，这就比较复杂了。我们需要找到这个节点的右子树中的最小节点，把它替换到要删除的节点上。然后再删除掉这个最小节点，因为最小节点肯定没有左子节点（如果有左子结点，那就不是最小节点了），所以，我们可以应用上面两条规则来删除这个最小节点。

```

public void delete(int data) {
  Node p = tree; // p指向要删除的节点，初始化指向根节点
  Node pp = null; // pp记录的是p的父节点
  while (p != null && p.data != data) {
    pp = p;
    if (data > p.data) p = p.right;
    else p = p.left;
  }
  if (p == null) return; // 没有找到

  // 要删除的节点有两个子节点
  if (p.left != null && p.right != null) { // 查找右子树中最小节点
    Node minP = p.right;
    Node minPP = p; // minPP表示minP的父节点
    while (minP.left != null) {
      minPP = minP;
      minP = minP.left;
    }
    p.data = minP.data; // 将minP的数据替换到p中
    p = minP; // 下面就变成了删除minP了
    pp = minPP;
  }

  // 删除节点是叶子节点或者仅有一个子节点
  Node child; // p的子节点
  if (p.left != null) child = p.left;
  else if (p.right != null) child = p.right;
  else child = null;

  if (pp == null) tree = child; // 删除的是根节点
  else if (pp.left == p) pp.left = child;
  else pp.right = child;
}
```

- **时间复杂度分析**

插入：O(logN)<br>
删除：O(logN)<br>
查找：O(logN)<br>

- **红黑树**
    * 红黑树中的节点，一类被标记为黑色，一类被标记为红色。除此之外，一棵红黑树还需要满足这样几个要求：
    * 根节点是黑色的
    * 每个叶子节点都是黑色的空节点，也就是说，叶子节点不存储数据
    * 任何相邻的节点都不能同时为红色，也就是说，红色节点是被黑色节点隔开的
    * 每个节点，从改节点到达其可达节点的所有路径，都包含相同数目的黑色节点
   

- 参考链接
    * [二叉搜索树 Demo](https://visualgo.net/zh/bst?slide=1)
    * [数据结构预算法之美](https://time.geekbang.org/column/article/68334?utm_source=web&utm_medium=pinpaizhuanqu&utm_campaign=baidu&utm_term=pinpaizhuanqu&utm_content=0427)

