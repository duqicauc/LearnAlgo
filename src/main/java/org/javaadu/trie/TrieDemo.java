package org.javaadu.trie;

public class TrieDemo {
    private TrieNode root = new TrieNode('/'); // 存储无意义字符

    public void insert(String text) {
        TrieNode p = root;
        for (char c : text.toCharArray()) {
            //换算出在当前层的索引
            int index = c - 'a';

            if (p.children[index] == null) {
                //p所在的层次的孩子节点不存在，则新增一个子节点
                TrieNode newNode = new TrieNode(c);
                p.children[index] = newNode;
            }

            //往下一层
            p = p.children[index];
        }

        //加到最后一层后，要把叶子节点打标
        p.isEndingChar = true;
    }

    /**
     * 不能完全匹配，返回false
     * 能完全匹配，返回true
     *
     * @param pattern
     * @return
     */
    public boolean find(String pattern) {
        TrieNode p = root;
        for (char c : pattern.toCharArray()) {
            int index = c - 'a';
            if (p.children[index] == null) {
                //还没循环，就已经搜到叶子节点了
                return false;
            }
            p = p.children[index];
        }

        //循环搜索完了，看是否是完全匹配：
        //不能完全匹配，返回false
        //能完全匹配，返回true
        return p.isEndingChar;
    }

    public static void main(String[] args) {
        TrieDemo trieDemo = new TrieDemo();
        trieDemo.insert("策略");
        trieDemo.insert("测试");
        trieDemo.insert("哈哈");
        trieDemo.insert("is");

        System.out.println(trieDemo.find("测试123"));
    }
}


class TrieNode {
    char data;
    TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;

    public TrieNode(char data) {
        this.data = data;
    }
}
