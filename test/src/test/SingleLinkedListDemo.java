package test;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "吴用", "智多星");
        HeroNode hero3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode hero4 = new HeroNode(4, "李逵", "黑旋风");
        HeroNode hero8 = new HeroNode(15, "李艳", "小李飞刀");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNull(hero4);
        singleLinkedList.addNull(hero1);
        singleLinkedList.addNull(hero3);
        singleLinkedList.addNull(hero8);
        singleLinkedList.addNull(hero2);
        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHead()));
        System.out.println();
//        System.out.println(findListIndexNode(singleLinkedList.getHead(),2));
//        reverseLinkedList(singleLinkedList).list();
        printFromLast(singleLinkedList);
    }

    /**
     * 返回链表倒数第k个节点
     *
     * @param head  链表的头结点
     * @param index 倒数第index个
     * @return
     */
    public static HeroNode findListIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = 1;
        HeroNode temp = head.next;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            size++;
        }
        int rIndex = size - index + 1;
        int count = 1;
        HeroNode cur = head.next;
        while (true) {
            if (count == rIndex) {
                break;
            }
            cur = cur.next;
            count++;
        }
        return cur;
    }

    /**
     * 获取单链表节点的个数
     *
     * @param head 链表的头结点
     * @return 返回的是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (true) {
            length++;
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        return length;
    }

    /**
     * 将链表进行翻转
     *
     * @param list 需要翻转的链表
     * @return 翻转后的链表
     */
    public static SingleLinkedList reverseLinkedList(SingleLinkedList list) {
        SingleLinkedList result = new SingleLinkedList();
//        先找到原来的链表长度
        int size = 0;
        HeroNode temp = list.getHead();
        while (true) {
            if (temp.next == null) {
                break;
            }
            size++;
            temp = temp.next;
        }
        for (int i = 0; i < size; i++) {
            int s = 0;
            HeroNode tm = list.getHead();
            while (true) {
                if (tm.next == null) {
                    break;
                }
                s++;
                tm = tm.next;
            }
            HeroNode cur = list.getHead();
            for (int j = 0; j < s; j++) {
                cur = cur.next;
            }
            result.addNull(cur);
            list.delete(cur.no);
        }
        return result;
    }

    /**
     * 从后往前输出
     *
     * @param list
     */
    public static void printFromLast(SingleLinkedList list) {
        int size = 0;
        HeroNode temp = list.getHead();
        while (true) {
            if (temp.next == null) {
                break;
            }
            size++;
            temp = temp.next;
        }

        for (int i = 0; i < size; i++) {
            int s = 0;
            HeroNode tm = list.getHead();
            while (true) {
                if (tm.next == null) {
                    break;
                }
                s++;
                tm = tm.next;
            }
            HeroNode cur = list.getHead();
            for (int j = 0; j < s; j++) {
                cur = cur.next;
            }
            System.out.println(cur);
            list.delete(cur.no);
        }

    }

}

//定义SIngleLinkedList 管理英雄
class SingleLinkedList {
    //    先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //    添加节点到单向链表(排序版)
    public void add(HeroNode heroNode) {
//        由于头结点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
        HeroNode tn;
//        遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            if (temp.next.no == heroNode.no) {
                System.out.println("\"" + heroNode + "\"" + "的no已存在，无法添加");
                break;
            }
            if (temp.next.no > heroNode.no) {
                tn = temp.next;
                temp.next = heroNode;
                heroNode.next = tn;
                break;
            }
//            如果没有找到则temp后移
            temp = temp.next;
        }
    }

    public void addNull(HeroNode heroNode) {
//        由于头结点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
//        遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
//                到达链表的最后
                break;
            }
            if (temp.next.no == no) {
//                该节点的下一个节点就是要删除的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到");
        }
    }

    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
//                链表已经到达最后了
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("没有找到该编号的节点");
        }
    }

    //    显示链表
    public void list() {
//        判断链表为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

