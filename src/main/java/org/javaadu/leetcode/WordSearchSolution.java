package org.javaadu.leetcode;

import java.util.Arrays;

public class WordSearchSolution {

    public static void main(String[] args) {
        //[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println("row:" + board.length);
        System.out.println("col:" + board[0].length);
        String word = "ABCCED";
        System.out.println(exist(board, word));

        char[][] board1 = {{'a', 'b'}};
        System.out.println("row:" + board1.length);
        System.out.println("col:" + board1[0].length);
        String word1 = "ba";
        System.out.println(exist(board1, word1));
    }

    static boolean wordExist = false;

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //从棋盘的每一个位置出发进行遍历
                backtracking(board, word, i, j, 0);
                if (wordExist) {
                    return true;
                }
            }
        }
        return false;
    }

    //i表示棋盘的行，j表示棋盘的列，这里表示的是当前遍历到的棋盘的位置
    //x，表示匹配到单词里的字母的索引位置
    public static void backtracking(char[][] board, String word, int i, int j, int x) {
        if (x == word.length() - 1) {
            wordExist = board[i][j] == word.charAt(x);
            return;
        }

        if (board[i][j] == word.charAt(x)) {
            char temp = board[i][j];
            board[i][j] = ' ';

            if (j - 1 >= 0 && board[i][j - 1] != ' ') {
                //往左递归
                backtracking(board, word, i, j - 1, x + 1);
                if (wordExist) {
                    //关键点：如果已经找到了，就无需尝试其他方向
                    return;
                }
            }

            if (i - 1 >= 0 && board[i - 1][j] != ' ') {
                //往上递归
                backtracking(board, word, i - 1, j, x + 1);
                if (wordExist) {
                    return;
                }
            }

            if (j + 1 < board[0].length && board[i][j + 1] != ' ') {
                //往右递归
                backtracking(board, word, i, j + 1, x + 1);
                if (wordExist) {
                    return;
                }
            }

            if (i + 1 < board.length && board[i + 1][j] != ' ') {
                //往下递归
                backtracking(board, word, i + 1, j, x + 1);
                if (wordExist) {
                    return;
                }
            }

            board[i][j] = temp;
        }
    }

}
