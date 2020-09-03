package test;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //1来表示墙
        //上下设为1，模拟墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右设为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置两个挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        //开始找路
        setWay(map, 1, 1);
        System.out.println("找路后：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    //使用递归回溯来个小球找路
    /**
     * 将起点使用ij表示，终点设置为map[6][5]
     * 当点为0表示该点没有走过；为1表示是墙；为2表示该点可以走；点为3表示该点走过但走不通
     * 测试路径的顺序：下->右->上->左，如果该点走不通，在回溯
     * @param map 表示地图
     * @param i 从哪个位置开始
     * @param j 从哪个位置开始
     * @return 如果找到通路则返回true
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){
            return true;
        }else{
            if (map[i][j] == 0){
                //该点还没有走过
                //按照规定的测试顺序：下->右->上->左 走
                map[i][j] = 2;//假设该点可以走通
                if (setWay(map, i+1,j)){//向下走
                    return true;
                }else if(setWay(map, i,j+1)){//向右走
                    return true;
                }else if(setWay(map, i-1,j)){//向上走
                    return true;
                }else if(setWay(map, i,j-1)){//向下走
                    return true;
                }else{//该点是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
