package Week_01.homework;

/**
 * @program: algorithm024
 * @description: 加一
 * @author: xiaoboji
 * @create: 2021-02-01 00:55
 **/
public class PlusOne {
  public int[] plusOne(int[] digits) {
    // 两种情况9和非9，非9直接返回，9的话需要进位
    for (int i = 0; i < digits.length; i++){
      if(digits[digits.length - 1 - i] != 9){
        digits[digits.length - 1 - i]++;
        return digits;
      } else {
        digits[digits.length - 1 - i] = 0;
      }
    }

    // 处理999的情况
    int[] result = new int[digits.length + 1];
    result[0] = 1;
    return result;
  }
}
