package test.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println(Arrays.toString(arr));
    }
    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left<right){
            int mid = (left+right)/2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid+1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 两个有序数组合并的方法
     * @param arr   排序的数列原型
     * @param left  左边数列的起始索引
     * @param mid   中间索引
     * @param right 右边数列的结束索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左边起始索引
        int j = mid + 1;//右边序列的初始索引
        int t = 0;//temp的索引

        //先把左右两边各自有序的数列按照规则填充的temp中
        //直到左右两边的有序序列有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //吧有剩余数据的一边的数据全部依次填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将temp数组元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft<=right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
