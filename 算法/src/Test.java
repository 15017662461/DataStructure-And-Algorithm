import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {5, 6, 8, 11, 20, 33, -2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr, int length, int i) {
        int k = 2 * i + 1;
        int temp;
        if (k + 1 < length && arr[k + 1] > arr[k]) {
            k = k + 1;
        }
        if (arr[k] > arr[i]) {
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }

    public static void heapSort(int[] arr){
        int length = arr.length;
        int temp;
        for (int i = length/2-1; i >= 0; i--) {
            heapSort(arr, length, i);
        }
        for (int i = arr.length-1; i >0 ; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            length --;
            for (int j = length/2-1; j >= 0; j--) {
                heapSort(arr, length, j);
            }
        }
    }
}
