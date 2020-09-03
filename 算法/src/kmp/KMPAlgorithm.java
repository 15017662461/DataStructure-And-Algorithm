package kmp;

import java.util.ArrayList;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));

    }

    /**
     *
     * @param s1 源字符串
     * @param s2 子串
     * @param next 部分匹配表，子串对应的部分匹配表
     * @return 返回第一个匹配到的位置 没有找到则返回-1
     */
    public static int kmpSearch(String s1,String s2,int[] next){
        for (int i = 0,j = 0; i < s1.length(); i++) {
            while (j > 0 && s1.charAt(i) != s2.charAt(j)){
                j = next[j-1];
            }
            if (s1.charAt(i) == s2.charAt(j)){
                j ++;
            }
            if (j == s2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;//字符串长度为0的话部分匹配值一定是0
        for (int i = 1,j=0; i < dest.length(); i++) {
            while (j> 0 && dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }
            if (dest.charAt(i)==dest.charAt(j)){
                j ++;
            }
            next[i] = j;
        }
        return next;
    }

    //该方法是我自己写的获取字符串（子串）的部分匹配值表的方法
    public static int[] test(String dest){
        int[] res = new int[dest.length()];
        String temp;
        ArrayList<String> s1 = new ArrayList<>();
        ArrayList<String> s2 = new ArrayList<>();
        int t;

        for (int i = 0; i < dest.length(); i++) {
            temp = dest.substring(0, i+1);
            s1.clear();
            s2.clear();
            t = 0;
            for (int j = 1; j <= i; j++) {
                s1.add(temp.substring(0,j));
            }
            for (int j = i; j > 0; j--) {
                s2.add(temp.substring(j));
            }
            for (int j = 0; j < s1.size(); j++) {
                for (int k = 0; k < s2.size(); k++) {
                    if (s1.get(j).equals(s2.get(k))&&s1.get(j).length()>t){
                        t = s1.get(j).length();
                    }
                }
            }
            res[i] = t;
        }
        return res;
    }
}
