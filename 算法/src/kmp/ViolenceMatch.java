package kmp;

public class ViolenceMatch {
    //暴力匹配算法
    public static void main(String[] args) {
        System.out.println(violenceMatch("硅鬼谷 尚硅谷你上柜 尚硅谷你上柜你上柜你好", "尚硅谷"));
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
