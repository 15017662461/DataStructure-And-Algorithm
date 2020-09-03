package test.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibonacciSearch(arr, 1234));
    }

    //得到一个长度为20的斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    /**
     *
     * @param a 数组
     * @param key 需要查找的值
     * @return
     */
    public static int fibonacciSearch(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        int k = 0;//表示斐波那契数列分割数值的下标
        int mid = 0;
        int f[] = fib();//获取到斐波那契数列
        //获取分割数值的下标
        while (a.length > f[k] - 1){
            k ++;
        }
        //构造新的数组使得数组长度等于f【k】,不够的部分会用0填充
        int[] temp = Arrays.copyOf(a, f[k]-1);
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //使用while来循环处理找到key
        while (low<=high){
            mid = low+f[k-1]-1;
            if (key < temp[mid]){
                high = mid - 1;
                k --;
            }else if(key > temp[mid]){
                low = mid + 1;
                k -= 2;
            }else{
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
