package test.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//顶点集合
    private int[][] edges;//表示边的数目
    private int numOfEdges;//表示边的数目
    //定义一个不二数组，记录每个顶点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;//顶点的个数
        String Vertexs[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        //添加顶点
        for (String vertexValue:Vertexs) {
            graph.insertVertex(vertexValue);
        }
        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //显示邻接矩阵
        graph.showGraph();

        //深度遍历
        System.out.println("深度优先遍历：");
        graph.dfs();

        //广度遍历
        System.out.println("广度优先遍历：");
        graph.bfs();
    }

    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>();
    }

    //添加顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**添加边
     *
     * @param v1 顶点的下标 也就是说是第几个顶点
     * @param v2 顶点的下标 也就是说是第几个顶点
     * @param weight 边的权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges ++;
    }

    //返回顶点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //根据下标返回对应的顶点
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回连接两点之间的边的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.err.println(Arrays.toString(link));
        }
    }

    //根据传入的下标得到其第一个邻接顶点的下标,不存在则返回-1
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接顶点的下标获取下一个邻接顶点
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    //对一个顶点进行深度优先遍历
    private void dfs(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);//得到顶点的第一个邻接顶点的下标
        while (w != -1){
            if (!isVisited[w]){
                //如果没有被访问过
                dfs(isVisited, w);
            }else{
                //如果被访问过
                w = getNextNeighbor(i, w);
            }
        }
    }

    //对dfs进行重载，遍历所有顶点并进行dfs
    public void dfs(){
        isVisited = new boolean[5];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    //对一个顶点进行广度优先遍历
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列头顶点对应的下标
        int w;//邻接顶点
        //队列，记录顶点的访问顺序
        LinkedList queue = new LinkedList();
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = (Integer)queue.removeFirst();
            w = getFirstNeighbor(u);
            while ((w!=-1)){
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }else{
                    w = getNextNeighbor(u, w);
                }
            }
        }
    }

    //对所有顶点进行广度优先遍历
    public void bfs(){
        isVisited = new boolean[5];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }
}
