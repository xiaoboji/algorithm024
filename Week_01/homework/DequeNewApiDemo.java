package Week_01.homework;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: algorithm024
 * @description: 用 add first 或 add last 这套新的 API 改写 Deque 的代码
 * @author: xiaoboji
 * @create: 2021-01-31 22:36
 **/
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
