package Week_01.homework;

/**
 * @program: algorithm024
 * @description: 移动零
 * @author: xiaoboji
 * @create: 2021-02-01 00:54
 **/
public class MoveZeros {
  // 快慢指针，类似于快排思想
  public void moveZeroes(int[] nums) {
    if(nums.length == 0){
      return;
    }
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if(nums[i] != 0){
        int temp = -1;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j++] = temp;
      }
    }
  }


}
