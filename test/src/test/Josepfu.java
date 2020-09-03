package test;

public class Josepfu {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.showBoy();

        circleLinkedList.countBoy(1, 2, 5);
    }
}

class CircleLinkedList{
    //创建一个first节点。当前先不设置编号
    private Boy first = new Boy(-1);

    //添加节点的方法
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums的值要大于1");
            return;
        }
        Boy curBoy = null;//帮助构建环形链表
        //for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1){
                //第一个小孩节点
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                return;
            }
            curBoy = curBoy.getNext();
        }
    }

    // 根据用户输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个节点开始计数
     * @param countNum 表示每次计数的数
     * @param nums  表示最初有几个小孩，用于简单的核算
     */
    public void countBoy(int startNo,int countNum,int nums){
        if (first ==null || startNo < 1 || startNo > nums){
            System.out.println("参数有误");
            return;
        }
        Boy helper = first;
        //将helper调整至最后一个节点，也就是下一个节点是first的位置
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //将first移动到startNo位置，helper移动到first上一个节点
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时，将first和helper同时移动 countNum - 1次
        //循环操作直到只有一个节点
        while (true){
            if (helper == first){
                System.out.println("最后留在圈中的小孩编号："+helper.getNo());
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点出圈
            System.out.println("出圈的小孩编号："+first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
    }

}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
