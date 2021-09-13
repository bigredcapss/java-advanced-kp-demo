package com.we.algorithm.sort;

/**
 * 二分查找
 * @description 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
 * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
 * @author we
 * @date 2021-09-09 11:25
 **/
public class BiSearchAlgorithm {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(biSearch(arr,6));

    }

    public static int biSearch(int[] array,int a){
        int lo = 0;
        int hi = array.length-1;
        int mid;
        while (lo<=hi){
            // 中间位置
            mid = (lo+hi)/2;

            if(array[mid]==a){
                return mid+1;
            }
            // 向右查找
            else if(array[mid]<a){
                lo = mid+1;
            }
            // 向左查找
            else{
                hi = mid-1;
            }
        }
        return -1;
    }

}
