package org.javaadu.leetcode;

public class Solution {

    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 3};
        int[] nums2 = new int[] {2};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i< nums1.length && j< nums2.length) {
            res[k] = Math.min(nums1[i], nums2[j]);
            if(nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
            k++;
        }
        while(i < nums1.length) {
            res[k] = nums1[i];
            k++;
            i++;
        }
        while(j < nums2.length) {
            res[k] = nums2[j];
            k++;
            j++;
        }

        if(k % 2 == 0) {
            return (double) (res[k/2 - 1] + res[k/2])/2;
            //长度是偶数
        } else{
            return res[k/2];
            //长度是偶数
        }
    }
}
