package test.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大的位数
        int maxLength = (max + "").length();
        //定义一个二维数组表示十个桶
        int[][] bucket = new int[10][arr.length];
        //定义一个一维数组来记录每一个桶所放置的数字的数量
        int[] bucketElementCounts = new int[10];

        for (int k = 0,n = 1; k < maxLength; k++,n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                //取出位数，第一次是个位，第二次是十位.....
                int digitOfElement = arr[i] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            //遍历每一个桶，并将每一个桶中的数据放入原数组
            for (int i = 0; i < bucketElementCounts.length; i++) {
                //如果桶中有数据，放入原数组
                if (bucketElementCounts[i] != 0) {
                    //循环遍历该桶，将数据放入
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index] = bucket[i][j];
                        index++;
                    }
                }
                //处理完毕后，需要将每个桶的数量清零
                bucketElementCounts[i] = 0;
            }
        }
    }
}

