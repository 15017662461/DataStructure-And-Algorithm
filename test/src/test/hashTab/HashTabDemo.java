package test.hashTab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建hash表
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner =new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//雇员类
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList{
    //头指针，指向第一个Emp，因此我们这个链表指向第一个Emp
    private Emp head;

    //添加雇员到链表,先假设添加雇员时，id自增长，也就是将雇员加到链表的最后
    public void add(Emp emp){
        if (head == null){//添加的是第一个雇员
            head = emp;
            return;
        }
        //如果不是最后一个，找到最后一个，添加到最后
        Emp curEmp = head;
        while (true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }
    //遍历链表
    public void list(int no){
        if (head == null){
            System.out.println("第"+(no+1)+"链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"链表的信息为：");
        Emp curEmp = head;
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            System.out.println();
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
    }

    //根据id查找雇员，查找到则返回EMp，找不到返回null
    public Emp findEmpById(int id){
        if (head == null){
            return null;
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}

//创建hashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedArray;
    private int size;

    //构造器初始化链表数组
    public HashTab(int size){
        this.size = size;
        empLinkedArray = new EmpLinkedList[size];
        //初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员，根据员工id判断应该添加到哪一条链表
    public void add(Emp emp){
        int empLinkedListNo = hashFun(emp.id);
        empLinkedArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedArray[i].list(i);
        }
    }

    //根据输入的id查找
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp =  empLinkedArray[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中找到 雇员 id= %d\n",empLinkedListNo+1,id);
        }else{
            System.out.println("没有找到对应的雇员");
        }
    }

    //编写散列函数，使用简单的取模法
    public int hashFun(int id){
        return id % size;
    }
}
