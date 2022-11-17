package org.javaadu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NChessBoard {

    private static List<List<String>> res = new ArrayList<>();

    public static List<List<String>> solveNQueens(int n) {
        //初始化：定义和初始化二维数组
        char[][] chessBoard = new char[n][n];
        for (char[] c : chessBoard) {
            Arrays.fill(c, '.');
        }
        backing(chessBoard, n, 0);
        return res;
    }

    private static void backing(char[][] chessBoard, int n, int row) {
        if (row == n) {
            //非合法的解法，无法走到这一步
            res.add(charArray2StringList(chessBoard));
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chessBoard)) {
                //合法的才往下递归，不合法的在这里就去除掉了
                chessBoard[row][col] = 'Q';
                //递归判断下一行的
                backing(chessBoard, n, row + 1);
                //回溯到上一行，方便判断下一列的
                chessBoard[row][col] = '.';
            }
        }
    }

    /**
     * 工具类方法，将char二维数组转成String的一维数组
     *
     * @param chessBoard
     * @return
     */
    private static List<String> charArray2StringList(char[][] chessBoard) {
        List<String> res = new ArrayList<>();
        for (char[] c : chessBoard) {
            res.add(String.copyValueOf(c));
        }
        return res;
    }

    /**
     * 注意这里不需要判断同一行的，因为每次递归处理的时候都是不同行的，取数的时候不会同时取同一行的
     *
     * @param row
     * @param col
     * @param n
     * @param chessBoard
     * @return
     */
    private static boolean isValid(int row, int col, int n, char[][] chessBoard) {
        //判断同一列
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false;
            }
        }
        //判断左上角斜线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        //判断右上角斜线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}
