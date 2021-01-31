package Week_01.homework;

/**
 * @program: algorithm024
 * @description: 两数之和
 * @author: xiaoboji
 * @create: 2021-02-01 00:53
 **/
public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    for(int i = 0; i < nums.length; i++){
      for (int j = i + 1; j < nums.length; j++){
        if(nums[i] + nums[j] == target){
          return new int[]{i, j};
        }
      }
    }
    return null;
  }
}
