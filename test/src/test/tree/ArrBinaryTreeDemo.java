package test.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

//编写tree实现顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //完成顺序存储二叉树的前序遍历
    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，无法遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左遍历
        if (2 * index + 1 < arr.length){
            preOrder(2*index+1);
        }
        //向右遍历
        if (2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }

    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
}
