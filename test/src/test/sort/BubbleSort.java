package test.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,20};
        //排序前
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);

        //输出查看最终结果
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length-1; i++) {
            flag = false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                System.out.println("在"+i+"循环中没有发生交换");
                break;
            }
        }
    }

}
