package Week_01.homework;

import Week_01.common.ListNode;

/**
 * @program: algorithm024
 * @description: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author: xiaoboji
 * @create: 2021-01-31 23:50
 **/
public class MergeTwoLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // 一些边界判断
    if(l1 == null){
      return l2;
    }
    if(l2 == null){
      return l1;
    }

    // 链表技巧，使用头结点
    ListNode pre = new ListNode(-1);
    ListNode head = pre;

    // 逐个比较，添加到pre链表去
    while(l1.next != null && l2.next != null){
      if(l1.val < l2.val){
        pre.next = l1;
        l1 = l1.next;
      } else {
        pre.next = l2;
        l2 = l2.next;
      }
      pre = pre.next;

    }
    // 循环结束l1或者l2有尾巴
    pre.next = (l1==null? l2:l1);
    return head.next;
  }
}
