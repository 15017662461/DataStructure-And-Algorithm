import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        int[][] vertices = new int[9][3]; //保存9个顶点的xyz坐标
        int[][] triangles = new int[3][3]; //保存三角形的三个顶点序号
        vertices[0] = new int[]{5, 0, 10};
        vertices[1] = new int[]{3, 2, 8};
        vertices[2] = new int[]{1, 5, 8};
        vertices[3] = new int[]{4, 7, 7};
        vertices[4] = new int[]{9, 2, 0};
        vertices[5] = new int[]{3, 6, 9};
        vertices[6] = new int[]{7, 0, 5};
        vertices[7] = new int[]{9, 0, 4};
        vertices[8] = new int[]{6, 3, 0};
        triangles[0] = new int[]{0,5,1};
        triangles[1] = new int[]{2,4,7};
        triangles[2] = new int[]{3,6,8};
        ArrayList res = getLines(vertices, triangles);
        System.out.println(res);
    }
    public static ArrayList getLines(int[][] vertices, int[][] triangles){
        ArrayList<String> res = new ArrayList<>();
        for (int[] points:triangles) {
            for (int i = 0; i < points.length; i++) {
                if (i == 2){
                    res.add("边("+points[i]+"->"+points[0]+"):("+ (vertices[points[0]][0]-vertices[points[i]][0])+","+(vertices[points[0]][1]-vertices[points[i]][1])+","+(vertices[points[0]][2]-vertices[points[i]][2])+")");
                }else{
                    res.add("边("+points[i]+"->"+points[i+1]+"):("+ (vertices[points[i+1]][0]-vertices[points[i]][0])+","+(vertices[points[i+1]][1]-vertices[points[i]][1])+","+(vertices[points[i+1]][2]-vertices[points[i]][2])+")");
                }
            }
        }
        return res;
    }
}
