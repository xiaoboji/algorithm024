package Week_01.homework;

/**
 * @program: algorithm024
 * @description: 加一
 * @author: xiaoboji
 * @create: 2021-02-01 00:55
 **/
public class PlusOne {
  public int[] plusOne(int[] digits) {
    //找到重复单元 +1 或者进一位
    for (int i = digits.length -1 ; i >= 0; i--){
      if(digits[i] < 9 ){
        digits[i] = digits[i] + 1;
      } else {
        digits[i] = 0;
      }

      if(digits[i] != 0){
        return digits;
      }
    }
    //处理999这种特殊情况
    if(digits[0] == 0){
      digits = new int[digits.length + 1];
      digits[0] = 1;
    }
    return digits;
  }
}
