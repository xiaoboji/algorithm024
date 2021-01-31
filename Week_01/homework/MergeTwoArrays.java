package Week_01.homework;

/**
 * @program: algorithm024
 * @description: 合并两个有序数组
 * @author: xiaoboji
 * @create: 2021-02-01 00:32
 **/
public class MergeTwoArrays {

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    while(n>0 && m>0){
      if(nums1[m-1] > nums2[n-1]){
        nums1[n+m-1] = nums1[m-1];
        m--;
      }else{
        nums1[n+m-1] = nums2[n-1];
        n--;
      }
    }
    for(int i = 0;i < n;i++){
      nums1[i] = nums2[i];
    }
  }
}
