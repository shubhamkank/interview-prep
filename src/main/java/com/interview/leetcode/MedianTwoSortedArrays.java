package com.interview.leetcode;

public class MedianTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if(len1 + len2 == 0) {
            throw new IllegalArgumentException("Both arrays are empty");
        }

        int [] nums3 = new int[len1 + len2];

        int medianIndex = (len1 + len2)/2;
        boolean isEven = (len1 + len2) % 2 == 0;

        int i = 0, j = 0, k = 0;

        while(i < len1 && j < len2) {
            if(nums1[i] <= nums2[j]) {
                nums3[k] = nums1[i];
                i++;
            } else {
                nums3[k] = nums2[j];
                j++;
            }
            k++;
        }

        while(i < len1) {
            nums3[k] = nums1[i];
            i++;
            k++;
        }

        while(j < len2) {
            nums3[k] = nums2[j];
            j++;
            k++;
        }

        if(isEven) {
            return ((double)nums3[medianIndex - 1] + (double)nums3[medianIndex])/2;
        }
        return (double) nums3[medianIndex];
    }

    //Time complexity - O(log(min(n,m))
    public static double findMedianSortedArrays2(int [] a, int [] b) {
        if(a.length > b.length) {
            return findMedianSortedArrays2(b, a);
        }

        int n = a.length;
        int m = b.length;

        int low = 0;
        int high = n;

        while(low <= high) {
            int partitionA = (low + high)/2;
            int partitionB = (n + m + 1)/2 - partitionA;

            int maxLeftA = partitionA == 0 ? Integer.MIN_VALUE : a[partitionA - 1];
            int minRightA = partitionA == n ? Integer.MAX_VALUE : a[partitionA];

            int maxLeftB = partitionB == 0 ? Integer.MIN_VALUE : b[partitionB - 1];
            int minRightB = partitionB == m ? Integer.MAX_VALUE : b[partitionB];

            if(maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if((n + m) % 2 == 0) {
                    return ((double)Math.max(maxLeftA, maxLeftB) + (double)Math.min(minRightA, minRightB))/2;
                } else {
                    return (double)Math.max(maxLeftA, maxLeftB);
                }
            } else if(maxLeftA > minRightB) {
                high = partitionA - 1;
            } else {
                low = partitionA + 1;
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String [] args) {
        int [] nums1 = {1, 3};
        int [] nums2 = {2};

        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }
}
