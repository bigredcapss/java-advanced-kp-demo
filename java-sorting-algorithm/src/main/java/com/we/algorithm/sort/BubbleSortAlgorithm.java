package com.we.algorithm.sort;

/**
 * 冒泡排序
 * @description
 * （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
 * （2）这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1 个位置。
 * （3）N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。
 * @author we
 * @date 2021-09-10 10:39
 **/
public class BubbleSortAlgorithm {
    public static void main(String[] args) {
        int[] a = {7,6,8,1,4,9};
        bubbleSort(a,5);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void bubbleSort(int[] a,int n){
        int i,j;
        // 表示n次排序过程~前一个元素的遍历过程
        for (i = 0; i < n ; i++) {
            // 后一个元素的遍历过程
            for (j = 1; j < n-i; j++){
                // 前面的数字大于后面的数字就交换
                if(a[j-1]>a[j]){
                    // 交换a[j-1]和a[j]
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

}
