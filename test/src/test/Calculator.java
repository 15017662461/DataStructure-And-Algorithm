package test;

public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";
        //创建两个栈，一个是数字栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描的index
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//用于保存每次扫描得到的结果
        String keepNum = "";//用于处理多位数
        //while循环扫描表达式
        while (true){
            //依次得到表达式每一个字符
            ch = expression.substring(index, index+1).charAt(0);
            //判断是否是符号
            if(operStack.isOper(ch)){
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //若符号的优先级小于等于栈内的优先级，则先计算栈内的数字，再入栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        //若符号有限级大于栈内的，则直接入栈
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
            }else{//如果是数字判断是否是多位数
                keepNum += ch;
                if(index == expression.length() -1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index = index + 1;
            if (index >= expression.length()){
                break;
            }
        }
        while (true){
            //如果符号栈为空，则已经计算完毕，数栈中剩余一个数字，该数字就是最终的结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println("表达式的最后结果为"+numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public int peek(){
        return stack[top];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        int t = top;
        while (true) {
            if (top == -1) {
                top = t;
                return;
            }
            System.out.println("第" + top + "个数据为：" + stack[top]);
            top--;
        }
    }

    //判断运算符的优先级，优先级使用数字表示，数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '-') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}
