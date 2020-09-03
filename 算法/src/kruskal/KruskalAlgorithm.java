package kruskal;

public class KruskalAlgorithm {
    private int edgeNum;//边的条数
    private char[] vertexts;
    private int [][] matrix;
    //表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexts = {'A','B','C','D','E','F','G'};
        int matrix[][] = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}};
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexts, matrix);
        kruskalAlgorithm.print();
        kruskalAlgorithm.kruskal();

    }

    public KruskalAlgorithm(char[] vertexts,int[][] matrix){
        int vlen = vertexts.length;
        //初始化顶点
        this.vertexts = new char[vlen];
        for (int i = 0; i < vertexts.length; i++) {
            this.vertexts[i] = vertexts[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //计算边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (this.matrix[i][j]!=INF){
                    edgeNum ++;
                }
            }
        }
    }

    public void print(){
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexts.length; i++) {
            for (int j = 0; j < vertexts.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if (edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值（比如A、B等）
     * @return 返回顶点对应的下标，如果找不到返回-1
     */
    private int getPosistion(char ch){
        for (int i = 0; i < vertexts.length; i++) {
            if (vertexts[i] == ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，并放到EData数组中，后续需要遍历该数组
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexts.length; i++) {
            for (int j = i+1; j < vertexts.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexts[i],vertexts[j] ,matrix[i][j] );
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的重点，用于判断两个顶点的终点是否相同
     * @param ends 记录各个顶点对应的终点的数组，ends数组是在遍历过程中逐步形成的
     * @param i 传入的顶点的对应的下标
     * @return
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    public void kruskal(){
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存最小生成树中的每个顶点在最小生成树中的终点
        EData[] rets = new EData[edgeNum];
        //获取图中 所有边的杰哥，一共有12条边
        EData[] edges = getEdges();
        //按照边的权值进行排序
        sortEdges(edges);
        //将边添加到最小生成树中时 判断是否形成回路，如果形成则不加入rets，没形成则加入rets
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点
            int p1 = getPosistion(edges[i].start);
            //获取到第i条边的第二个顶点
            int p2 = getPosistion(edges[i].end);

            //获取p1点在已有的最小生成树中对应的终点
            int m = getEnd(ends, p1);
            //获取p2点在已有的最小生成树中对应的终点
            int n = getEnd(ends, p2);
            //判断是否构成回路
            if (m != n){
                //不构成回路
                ends[m] = n;//设置m在已有最小生成树中的终点
                rets[index++] = edges[i];//有一条边加入到rets数组
            }
        }
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
}

class EData{
    char start;//边的终点
    char end;//边的另一个点
    int weight;//权值
    //构造器
    public EData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                ">=" + weight +
                '}';
    }
}
