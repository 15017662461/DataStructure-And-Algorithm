package test.binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历
        binarySortTree.infixOrder();
        //删除叶子节点
        //binarySortTree.delNode(1);
        System.out.println("删除节点后：");
        //删除只有一个节点的节点
        binarySortTree.delNode(1);
        //删除节点后中序遍历
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，无法遍历");
        }
    }

    //查找指定value值的节点
    public Node search(int value){
        if (root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找指定value值的节点的父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node 传入的节点（当做二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值并删除对应的节点
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        while (target.left!=null){
            target = target.left;
        }
        //这时target就是最小的节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        }else{
            Node targetNode = search(value);
            if (targetNode == null){
                return;
            }
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            Node parent = searchParent(value);
            if (targetNode.left == null && targetNode.right == null){
                //要删除的节点是叶子节点（也就是没有左子节点也没有右子节点）
                if (parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else if (parent.right!=null && parent.right.value == value){
                    parent.right = null;
                }
            }else if (targetNode.left!=null && targetNode.right!=null){
                //要删除的节点有两个子节点
                //找到右子树中最小的节点的值并删除，将值赋给targetNode
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else{
                //要删除的节点只有一个子节点（只有右子节点或者只有左子节点）
                if (targetNode.left!=null){
                    if (parent!=null){
                        //要删除的节点只有左子节点
                        if (parent.left.value == value){
                            //要删除的节点是父节点的左子节点
                            parent.left = targetNode.left;
                        }else{
                            //要删除的节点是父节点的右子节点
                            parent.right = targetNode.left;
                        }
                    }else{
                        root = targetNode.left;
                    }
                }else{
                    if (parent!=null){
                        //要删除的节点只有右子节点
                        if (parent.left.value == value){
                            //要删除的节点是父节点的左子节点
                            parent.left = targetNode.right;
                        }else{
                            //要删除的节点是父节点的右子节点
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

//节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //按照二叉排序树的方式添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找指定value的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找指定value的节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            }else{
                return null;
            }
        }
    }
}

