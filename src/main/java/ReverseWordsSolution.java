public class ReverseWordsSolution {
    public static void main(String[] args) {
        System.out.println(reverseWords("  hello   world  "));
    }

    public static String reverseWords(String s) {
        //删除字符串前后的空格，删除单词中间的多余空格
        StringBuilder sb = removeExtraSpace(s);
        System.out.println(sb);
        //翻转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        //翻转每个单词
        reverseWord(sb);
        return sb.toString();
    }

    /**
     * 双指针方式去除多余空格
     *
     * @param s
     * @return
     */
    public static StringBuilder removeExtraSpace(String s) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++; //去除字符串前面的空格
        while (s.charAt(end) == ' ') end--;     //去除字符串后面的空格
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                //如果当前的字符是一个字母，或者是第一个空格，则保留
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    /**
     * 翻转字符串中指定位置之间的字符
     *
     * @param sb
     * @param start
     * @param end
     */
    public static void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    public static void reverseWord(StringBuilder sb) {
        int start = 0;  //单词的第一个字符
        int end = 1;    //单词的最后一个字符
        int len = sb.length();
        while (start < len) {
            while (end < len && sb.charAt(end) != ' ') {
                //找到第一个单词
                end++;
            }
            //翻转这个单词
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }
}
