package test.huffmanCode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//        System.out.println(getNodes(contentBytes));
//        List<Node> nodes = getNodes(contentBytes);
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        preOrder(huffmanTreeRoot);
//        getCodes(huffmanTreeRoot);
//        System.out.println(huffmanCodes);
//        byte[] huffmanCOdeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println(Arrays.toString(huffmanCOdeBytes));
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是："+Arrays.toString(huffmanCodesBytes));
        System.out.println("长度为："+huffmanCodesBytes.length);
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("还原后的结果是："+new String(sourceBytes));
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("节点为空无法遍历");
        } else {
            root.preOrder();
        }
    }

    /**
     * @param bytes 字节数组
     * @return 返回对应的Node节点
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转成一个Node对象 并加入nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过NodeList 创建对应的赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //根据赫夫曼树生成对应的赫夫曼编码存储在Map<Byte,String>中
    //规定向左为0，向右为1，定义一个StringBuilder储存每一个叶子节点的路径
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的node节点下所有叶子节点对应的赫夫曼编码放入到huffmanCodes中
     *
     * @param node          节点
     * @param code          路径：向左是0，向右是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                //是非叶子结点，向左递归,想右递归
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //是叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //为了调用方便，重写一下上面的方法
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //将字符串对应的byte数组，通过生成的赫夫曼编码表 返回一个赫夫曼编码压缩后的byte数组
    /**
     * @param bytes        原始字符串对应的原始byte数组
     * @param huffmanCodes 赫夫曼编码表
     * @return 返回经过赫夫曼编码表编码之后的byte数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用赫夫曼编码表 将buyes转成赫夫曼编码后的byte数组
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将stringBuilder转成byte数组
        //先统计返回byte数组的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建储存转换后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i+8>stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成byte放入到huffmanCodeBytes中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }
    //将前面的方法封装起来
    /**
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 经过赫夫曼压缩后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        //第一步创建byte-weight节点List
        List<Node> nodes = getNodes(bytes);
        //根据节点List创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据赫夫曼树得到赫夫曼编码表
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据赫夫曼编码表将原始bytes数组压缩
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return  huffmanCodeBytes;
    }

    //将bytes转成二进制的字符串
    /**
     *
     * @param flag 标识是否需要补高位 如果是true，标识需要 反之则不要
     * @param b 传入的byte
     * @return 传入的b对应的二进制字符串，注意这里是按照补码返回的
     */
    public static String byteToBitString(boolean flag,byte b){
        int temp = b;
        //如果是整数还需要补高位
        if (flag){
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }

    //根据赫夫曼编码表对赫夫曼编码后的byte数组转成常规的原始byte数组
    /**
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码后的byte数组
     * @return 原始的byte数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        StringBuilder stringBuilder = new StringBuilder();
        //将数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //根据赫夫曼编码表进行调换
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

}

//创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据的ASCII码
    int weight;//权值 表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;//从小到大排序
    }

    @Override
    public String toString() {
        return "Node [data=" + data + " weight=" + weight + "]";
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
