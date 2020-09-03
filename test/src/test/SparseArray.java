package test;

public class SparseArray {
    public static void main(String[] args) {
        // 原始创建二维数组
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //稀疏数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr[i][j] != 0){
                    sum ++;
                }
            }
        }
        int[][] chessArr2 = new int[sum + 1][3];
        chessArr2[0][0] = 11;
        chessArr2[0][1] = 11;
        chessArr2[0][2] = sum;
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr[i][j] != 0){
                    chessArr2[count][0] = i;
                    chessArr2[count][1] = j;
                    chessArr2[count][2] = chessArr[i][j];
                    count ++;
                }
            }
        }
        System.out.println("转化为稀疏数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //    激昂稀疏数组恢复成为二维数组
        int chessArr3[][] = new int[chessArr2[0][0]][chessArr2[0][1]];
        for (int i = 0; i < chessArr2[0][1]; i++) {
            for (int j = 0; j < chessArr2[0][2]; j++) {
                chessArr3[i][j] = 0;
            }
        }
        for (int i = 1; i < 4; i++) {
            chessArr3[chessArr2[i][0]][chessArr2[i][1]] = chessArr2[i][2];
        }
        System.out.println("稀疏数组转回二维数组：");
        for (int[] row : chessArr3) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
