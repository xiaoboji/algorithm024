本周学习了哈希、树、堆、图

#### 一、学习笔记
- [哈希、映射、集合](https://github.com/xiaoboji/algorithm024/tree/main/Week_02/note/hash.md)
- [树、二叉树、二叉搜索树](https://github.com/xiaoboji/algorithm024/tree/main/Week_02/note/tree.md)
- [堆和堆排序](https://github.com/xiaoboji/algorithm024/tree/main/Week_02/note/heap.md)
- [图](https://github.com/xiaoboji/algorithm024/tree/main/Week_02/note/graph.md)

#### 二、本周作业

简单：
- [写一个关于HashMap的小总结](https://github.com/xiaoboji/algorithm024/tree/main/Week_02/homework/HashMap.md)
- [有效的字母异位词（亚马逊、Facebook、谷歌在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_019_242_valid_anagram)
- [两数之和（非常多）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_003_1_two_sum)
- [N叉树的前序遍历（亚马逊在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_024_589_n_ary_tree_preorder_traversal)
- [HeapSort自学](https://github.com/xiaoboji/algorithm024/blob/main/Week_02/note/heap.md)

中等：
- [字母异位词分组（亚马逊在半年内面试中常考）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_020_49_group_anagrams)
- [二叉树的中序遍历（亚马逊、字节跳动、微软在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_021_94_binary_tree_inorder_traversal)
- [二叉树的前序遍历（字节跳动、谷歌、腾讯在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_022_144_binary_tree_preorder_traversal)
- [N叉树的层序遍历（亚马逊在半年内面试中考过）](https://github.com/xiaoboji/j-leetcode/tree/main/java/src/main/java/com/xiaoboji/problems/no_025_429_n_ary_tree_level_traversal)
- [丑数（字节跳动在半年内面试中考过）]
- [前K个高频元素（亚马逊在半年内面试中常考）]

#### 三、训练场相关
- 哈希相关：
    * [找雪花]
- 树相关：
    * [安装路灯]
- 二叉搜索树相关：
    * [二叉搜索树的后序遍历序列]
- 堆相关：
    * [最火视频榜单]
- 图相关：
    * [手游上线]
#### 四、心得及小结

- 本周可以沉淀的代码
    * 二叉树遍历(递归)
    ```
    // 前序遍历核心代码
    public void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }
    // 中序遍历核心代码
    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
    // 后续遍历核心代码
    public void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }
    ```
    * 二叉树遍历(迭代)
    ```
    // 前序遍历核心代码
    while(root != null || !stack.isEmpty()){
        //go left down to the ground
        while(root != null){
          res.add(root.val);
          stack.push(root);
          root = root.left;
        }
        
        //if we reach to the leaf, go back to the parent right, and repeat the go left down.
        TreeNode cur = stack.pop();
        root = cur.right;
    }
    // 中序遍历核心代码
    while (root != null || !stack.isEmpty()) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        res.add(root.val);
        root = root.right;
    }
    // 后续遍历核心代码，前序遍历的一个逆序
    while(root != null || !stack.isEmpty()){
        while(root != null){
            res.add(root.val);
            stack.push(root);
            root = root.right;
        }

        TreeNode cur = stack.pop();
        root = cur.left;
    }
    Collections.reverse(res);
    ```  
- 本周学习心得


#### 五、疑问汇总

#### 六、练习汇总

[leetcode-做题记录](https://github.com/xiaoboji/j-leetcode)
