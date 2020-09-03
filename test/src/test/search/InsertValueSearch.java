package test.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(insertValueSearch(arr, 0, arr.length-1, 100));
    }

    /**
     *
     * @param arr 查找的数组
     * @param left 左边起始索引
     * @param right 右边结束索引
     * @return 返回查找的值的索引，没有找到就返回-1
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if (left>=right || findVal > arr[right] || findVal < arr[left]){
            return -1;
        }
        int mid = left + (right - left) * (findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){
            return insertValueSearch(arr, mid+1, right, findVal);
        }else if (findVal < midVal){
            return insertValueSearch(arr, left, mid-1,findVal);
        }else{
            return mid;
        }
    }
}
