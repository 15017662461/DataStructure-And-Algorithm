package test.tree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //    创建赫夫曼树
    /**
     * @param arr 需要创建成赫夫曼树的数组
     * @return 创建完成到的赫夫曼树的根节点
     */
    public static Node createHuffmanTree(int arr[]) {
        //第一步，为了操作方便，遍历arr数组，将每个arr元素构建成一个Node并放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //循环处理过程
        while (nodes.size() > 1) {
            //排序 从小到大排序
            Collections.sort(nodes);
            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从nodes删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);

    }

    //前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树无法遍历");
        }
    }
}

//创建节点类
//为了让Node 对象持续排序实现Collection集合排序
class Node implements Comparable<Node> {
    int value; //权值
    Node left; //指向左子节点
    Node right; //指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int compareTo(Node o) {
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
