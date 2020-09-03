package test.search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        //使用二分查找法时的数组必须是有序的，这里的数组是从小到大排序
        int arr[] = {1,8,10,89,1000,1000,1234};
        System.out.println(binarySearch(arr,0,arr.length-1,3));
    }

    /**
     *
     * @param arr 需要查找的数组
     * @param left 左边起始索引
     * @param right 右边结束索引
     * @param findVal 需要查找的值
     * @return 找到就返回对应的下标，找不到则返回-1
     */
    public static ArrayList<Integer> binarySearch(int[] arr,int left,int right,int findVal){
        int mid = (left + right)/2;
        int midVal = arr[mid];
        if (left>=right){
            return new ArrayList<>();
        }
        if (findVal>midVal){
            return binarySearch(arr, mid+1, right, findVal);
        }else if(findVal<midVal){
            return binarySearch(arr, left, mid-1, findVal);
        }else{
            ArrayList<Integer> resIndexlist = new ArrayList<>();
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp --;
            }
            resIndexlist.add(mid);
            temp = mid + 1;
            while (true){
                if (temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp ++;
            }
            return resIndexlist;
        }
    }
}
