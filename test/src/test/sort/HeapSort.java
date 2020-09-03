package test.sort;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {
        Random r = new Random();
        int arr[] = new int[1000];
        for (int j = 0; j < 1000; j++) {
            arr[j] = r.nextInt(1000);
        }
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //堆排序
    public static void heapSort(int[] arr) {
        int temp = 0;
        int length = arr.length;
        //最后一个非叶子结点在数组的序号为arr.length-1
        //从最后一个非叶子结点开始循环调整将最大的移到最上面
        for (int i = length/2-1; i >= 0; i--) {
            adjustHeap(arr, i, length);
        }
        for (int i = arr.length-1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            length --;
            for (int j = length/2-1; j >= 0; j--) {
                adjustHeap(arr, j, length);
            }
        }
    }

    //将给定的节点调整为大于等于其子节点
    /**
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中的索引
     * @param length 表示对多少个元素进行调整，length会逐渐变小
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//取出当前最后一个非叶子结点的值，保存
        //k是i节点的左子节点，判断左子节点与右子节点、当前i节点大小，最大的放在i节点
        int k = 2 * i +1;
        if (k+1<length && arr[k+1]>arr[k]){
            k = k+1;
        }
        if (arr[k]>temp){
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }

}
