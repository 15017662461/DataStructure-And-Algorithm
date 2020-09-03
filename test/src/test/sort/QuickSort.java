package test.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};

        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp;//临时变量作为交换使用
        //使用while循环使得比pivot小的全部放到左边，大的放到右边
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //交换完成后发现arr[l] == pivot 向前移一下
            if (arr[l] == pivot){
                r -= 1;
            }
            //交换完成后arr[r] == pivot 向后移一下
            if (arr[r] == pivot){
                l += 1;
            }
        }
        if (l == r){
            l += 1;
            r -= 1;
        }
        if (left<r){
             quickSort(arr, left, r);
        }
        if (right>l){
            quickSort(arr, l, right);
        }
    }
}
