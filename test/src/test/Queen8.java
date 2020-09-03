package test;

public class Queen8 {

    //定义一个max表示一共有多少个皇后
    int max = 8;
    //定义数组array表示皇后放置的位置
    int[] array = new int[max];
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
    }

    //将皇后摆放的位置打印输出
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    //当放置第n个皇后时，检测该皇后是否与前面已经放置的皇后形成冲突（在一条线上）
    /**
     *
     * @param n 表示放置的是第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                //在同一列上或者在一条斜线上
                return false;
            }
        }
        return true;
    }

    //放置皇后，从0开始递归
    private void check(int n){
        if (n == max){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后n，放到该行的第一列
            array[n] = i;
            if (judge(n)){//和之前放置的皇后不冲突
                //接着放n+1个皇后，开始递归
                check(n+1);
            }
        }
    }
}
