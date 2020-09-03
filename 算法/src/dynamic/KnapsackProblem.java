package dynamic;

public class KnapsackProblem {
    //动态规划解决01背包（物品不能重复放置）问题
    public static void main(String[] args) {
        int[] w = {1,4,3};//记录物品的重量
        int[] val = {1500,3000,2000};//记录物品的价值
        int m = 4;//背包的最大容量
        int n = val.length;//物品的个数

        //创建二维数组,表示在前i个物品在容量为j+1的背包的最大价值
        int[][] v = new int[n+1][m+1];
        //记录商品放入的情况
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列为0（为了后面方便书写代码）
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//也不处理第一列
                if (w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if (v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        path[i][j] = 1;
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //打印二维数组v
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < m+1; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        //path中最后一个出现1的位置就是最优解
        int i = path.length-1;
        int j = path[0].length-1;
        while (i>0&&j>0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i-1];
            }
            i --;
        }

    }
}
