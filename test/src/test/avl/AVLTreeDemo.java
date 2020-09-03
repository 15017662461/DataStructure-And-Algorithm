package test.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
        int[] arr = {10,12,8,9,7,6};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历：");
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().left.height());

    }
}

//创建AVL数
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

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

        //当添加完一个节点后，如果右子树高度减去左子树的高度的值大于1，执行左旋转
        if (rightHeight()-leftHeight()>1){
            //如果右子树的左子树的高度大于它的右子树的高度
            if (right!=null&&right.leftHeight()>right.rightHeight()){
                right.rightRotate();
                leftRotate();//左旋转
            }else{
                leftRotate();//左旋转
            }
            return;
        }
        //当添加完一个节点后，如果左子树高度减去右子树的高度的值大于1，执行右旋转
        if (leftHeight()-rightHeight()>1){
            //如果左子树的右子树的高度大于它的左子树的高度
            if (left!=null&&left.rightHeight()>left.leftHeight()){
                //先对当前节点的左节点进行左旋转
                left.leftRotate();
                rightRotate();//右旋转
            }else{
                rightRotate();//右旋转
            }
            return;
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

    //返回指定节点的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    //左旋转
    public void leftRotate(){
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新的节点
        left = newNode;
    }

    //右旋转
    public void rightRotate(){
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的右子树设置为当前节点的右子树
        newNode.right = right;
        //把新的节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //把当前节点的值替换成左子节点的值
        value = left.value;
        //把当前节点的左子树设置成当前节点的左子树的左子树
        left = left.left;
        //把当前节点的右子树设置为新的节点
        right = newNode;
    }

}
