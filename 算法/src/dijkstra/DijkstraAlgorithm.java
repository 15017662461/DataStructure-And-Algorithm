package dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连接
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dsj(6);
        graph.showDijkstra();
    }
}

class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertext vv;//已经访问的顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph(){
        for (int[] link:matrix
             ) {
            System.out.println(Arrays.toString(link));
        }
    }

    //显示结果
    public void showDijkstra(){
        vv.show();
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index 出发顶点对应的下标
     */
    public void dsj(int index){
        vv = new VisitedVertext(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index); //更新index顶点到周围顶点的距离
        }
    }

    //更新index下标顶点到周围点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            //len:出发顶点到index顶点的距离+从index顶点到j顶点的距离之和
            len = vv.getDis(index)+matrix[index][j];
            //如果j顶点没有被访问过，且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(j)&&len<vv.getDis(j)){
                vv.updatePre(j, index);
                vv.updateDis(j, len);
            }
        }
    }
}

//已访问顶点的集合
class VisitedVertext{
    public int[] already_arr;//记录各个顶点是否访问过，1表示访问过，0表示未访问过

    public int[] pre_visited;//每个下表对应的值为前一个顶点的下标

    public int[] dis;//记录触发顶点到其他所有顶点的距离

    /**
     * 构造函数
     * @param length 表示顶点的个数
     * @param index 出发顶点的下标
     */
    public VisitedVertext(int length,int index){
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //处理dis数组,先将全部设为65535，设置顶点被访问过,再将自己到自己设为0
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 判断下标为index的顶点是否被访问过
     * @param index 某一顶点的下标
     * @return 访问过则返回true 没访问过则返回false
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发定点到index顶点的距离
     * @param index 目标顶点的下标
     * @param len 距离
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index节点
     * @param pre 本次节点
     * @param index 前驱节点
     */
    public void updatePre(int pre,int index){
        this.pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index 目标顶点
     */
    public int getDis(int index){
        return dis[index];
    }

    //继续选择并返回新的访问顶点
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新 index 顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果，也就是将三个数组的情况输出
    public void show(){
        System.out.println("=============================");
        for (int i :already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i :pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i :dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int i:dis
             ) {
            if (i!=65535){
                System.out.print(vertex[count]+"("+i+")");
            }else{
                System.out.println("N");
            }
            count ++;
        }
        System.out.println();
    }
}
