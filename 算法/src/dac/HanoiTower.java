package dac;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    //汉诺塔的移动方法 使用分治算法
    //可以把盘子视为两部分，上面的若干个盘子和最下面的一个盘子
    //移动过程就是：1.将上面的若干个盘子从a移动到b
    //2.将最下面的盘子从a移动到c
    //3.将上面的若干个盘子从b移动到c
    public static void hanoiTower(int num,char a,char b,char c){
        if (num == 1){
            System.out.println("第1个盘从"+a+"到"+c);
        }else{
            hanoiTower(num-1, a, c, b);
            System.out.println("第"+num+"个盘从"+a+"到"+c);
            hanoiTower(num-1, b, a, c);
        }
    }
}
