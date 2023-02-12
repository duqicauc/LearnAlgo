package org.javaadu.string;

import java.util.List;

/**
 * <a href="https://oi-wiki.org/string/match/">字符串匹配</a>
 * <a href = "https://www.bilibili.com/video/BV1AY4y157yL/?spm_id_from=333.337.search-card.all.click&vd_source=33b7a49ff9ee993637d7533b74ed12a5">
 * 最浅显易懂的KMP算法
 * </a>
 */
public class StringSearchDemo {

    public static void main(String[] args) {
        String text = "aabaabaaf";
        String pattern = "aabaaf";
        boolean bfRes = bfSearch(text, pattern);
        System.out.println("bfRes:" + bfRes);

        boolean kmpRes = kmpSearch(text, pattern);
        System.out.println("kmpRes:" + kmpRes);
    }

    /**
     * 利用BF算法求解pattern是否在text中出现过
     * 时间复杂度：O(n * m)
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

    /**
     * 利用KMP算法求解pattern是否在text中出现过
     *
     * @param text    文本串
     * @param pattern 模式串
     * @return pattern在text中出现，则返回true，否则返回false
     */
    public static boolean kmpSearch(String text, String pattern) {
        //初始化next数组
        int[] next = buildNext(pattern);
        //text中的指针
        int i = 0;
        //pattern中的指针
        int j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                //字符匹配，则两个指针同时后移
                i++;
                j++;
            } else if (j > 0) {
                //字符失配，则利用next数组避免i指针的回退
                j = next[j - 1];
            } else {
                //pattern中的第一个字符就失配了
                i++;
            }

            if (j == pattern.length()) {
                //搜索成功
                return true;
            }
        }
        return false;
    }

    /**
     * KMP算法的核心就在于：尽量利用之前已经匹配的结果，实现对主串的一次遍历
     * next[i]代表在pattern中的第i个字符在失配的时候可以跳过的字符个数
     * <p>
     * 求解next数组，就是求解pattern中，各个子串的最长的相同前后缀的长度
     * <p>
     * 以模式串aabaaf为例，什么是前缀和后缀？
     * 前缀：只包含首字母，不包含尾字母的所有子串
     * 后缀：只包含尾字母，不包含首字母的所有子串
     * <p>
     * 所有前缀子串：
     * a
     * aa
     * aab
     * aaba
     * aabaa
     * 所有后缀子串：
     * f
     * af
     * aaf
     * baaf
     * abaaf
     * 则aabaaf子串可跳过的长度，next[5] = 0
     * <p>
     * 再看aabaa这个子串
     * 所有的前缀子串：
     * a
     * aa
     * aab
     * aaba
     * 所有的后缀子串：
     * a
     * aa
     * baa
     * abaa
     * 则aabaa子串可跳过的长度，next[4] = 2
     *
     * @param pattern 模式串
     * @return 计算好的next数组
     */
    private static int[] buildNext(String pattern) {
        int[] next = new int[pattern.length()];
        //当前最长相等前后缀子串的长度
        int prefixLen = 0;
        //对于第pattern的1个字符，不存在比它还短的子串，也就不需要跳过，因此这里从pattern的第2个字符开始计算
        next[0] = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(prefixLen) == pattern.charAt(i)) {
                prefixLen++;
                next[i] = prefixLen;

                //处理下一个i的字符
                i++;
            } else {
                //这里prefixLen之前的都是已经计算完的，可以利用，看是否存在更短的最长相等前后缀
                prefixLen = next[prefixLen - 1];
                if (prefixLen == 0) {
                    //如果没有合适的，则设置为0
                    next[i] = 0;
                    //处理下一个i的字符
                    i++;
                }
                //递推处理，这时候i是不变的
            }
        }
        return next;
    }
}
