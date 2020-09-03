package test;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {99, -2, 33, 23, 6, 0};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp;
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                System.out.println(i);
                break;
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }

    //插入排序
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int val = arr[i];
            while (j >= 0 && val < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = val;
        }
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                }
                arr[j] = temp;
            }
        }
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(l + r) / 2];
        int temp;
        while (l < r){
            while (arr[l] < pivot){
                l ++;
            }
            while (arr[r] > pivot){
                r --;
            }
            if (l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot){
                r --;
            }
            if (arr[r] == pivot){
                l ++;
            }
        }
        if (r == l){
            l = l+1;
            r = r-1;
        }
        if (left<r){
            quickSort(arr, left, r);
        }
        if (right > l){
            quickSort(arr, l, right);
        }

    }
}
