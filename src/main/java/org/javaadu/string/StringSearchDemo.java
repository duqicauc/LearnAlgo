package org.javaadu.string;

import java.util.List;

/**
 * <a href="https://oi-wiki.org/string/match/">字符串匹配</a>
 */
public class StringSearchDemo {

    public static void main(String[] args) {
        String text = "aabaabaaf";
        String pattern = "aabaaf";
        boolean res = bfSearch(text, pattern);
        System.out.println(res);
    }

    /**
     * 利用BF算法求解pattern是否在text中出现过
     *
     * @param text    文本串
     * @param pattern 模式串
     * @return pattern在text中出现，则返回true，否则返回false
     */
    public static boolean bfSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int i, j;
        for (i = 0; i < (n - m + 1); i++) {
            for (j = 0; j < m; j++) {
                int posInText = i + j;
                if (text.charAt(posInText) != pattern.charAt(j)) {
                    //这里只会跳出第一层循环
                    break;
                }
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }
}
