package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(parseSuffixExpressionList);
        System.out.println(calculate(parseSuffixExpressionList));

        /*
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路：
        //先将表达式放倒ArrayList中
        //将ArrayList传递给一个方法，遍历完成计算
        int res = calculate(getListString(suffixExpression));
        System.out.println("结果是" + res);
        */

    }

    //将中缀表达式转成List
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;//相当于一个指针用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历到一个字符，就放入到c
        do {
            //如果c不是数字，直接加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //如果是数字，则需要考虑多位数拼接
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i ++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将中缀表达式组成的ArrayList转换成后缀表达式形式
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item:ls) {
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                //如果是左括号，直接放入s1
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，则将左右括号之间的符号全部压入s2中，并消除对应的左右括号
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                //是运算符号
                //若当前item的运算符优先级大于栈顶的运算符，直接压入s1
                //若当前item的运算符优先级小于等于栈顶的运算符，将栈顶的运算符取出并压入s2中，再次比较与栈顶的优先级
                //若栈顶为空或者是"("则直接压入s1
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将表达式放到ArrayList的方法
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split
        ) {
            list.add(ele);
        }
        return list;
    }

    // 计算后缀表达式
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        int res;
        for (String item : ls) {
            if (item.matches("\\d+")) {
                //是数字，直接入栈
                stack.push(item);
            } else {
                //是运算符，弹出两个数字并运算然后在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符号错误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
