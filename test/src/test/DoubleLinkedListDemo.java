package test;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "吴用", "智多星");
        HeroNode2 hero3 = new HeroNode2(3, "林冲", "豹子头");
        HeroNode2 hero4 = new HeroNode2(4, "李逵", "黑旋风");
        HeroNode2 hero8 = new HeroNode2(15, "李艳", "小李飞刀");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero8);
//        doubleLinkedList.list();

//        HeroNode2 herox = new HeroNode2(4, "李逵", "黑旋风3");
//        doubleLinkedList.update(herox);
//        doubleLinkedList.list();
        doubleLinkedList.del(2);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return this.head;
    }

    //    遍历
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                return;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //    添加节点到最后
    public void add(HeroNode2 heroNode) {
//        由于头结点不能动，因此需要一个辅助变量temp
        HeroNode2 temp = head;
//        遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
//            如果没有找到则temp后移
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //    修改节点
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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

    //    删除节点
    public void del(int no) {
        HeroNode2 temp = head.next;
        Boolean flag = false;
        if (temp.next == null) {
            System.out.println("链表为空，无法删除");
        }
        while (true) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            if (temp == null) {
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有找到");
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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
