package com.kotlin.vip.datastructure.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by likaiyu on 2020/9/16.
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 */
public class LC_51_NQueens_N皇后_200916 {

    private static List<List<String>> res;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        List<List<String>> lists = solveNQueens(4);
        System.out.println(lists);
        System.out.println((System.currentTimeMillis() - l) / 1000);
    }

    public static List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        if (n == 0) return res;
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        backTrackII(board, 0);
        return res;
    }

    /**
     * 得到所有结果
     * @param board
     * @param row
     */
    public static void backTrack(char[][] board, int row) {
        if (row == board.length) {
            List<String> list = new ArrayList<>();
            for (char[] chars : board) {
                list.add(String.valueOf(chars));
            }
            res.add(list);
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            //做选择
            board[row][col] = 'Q';
            //进入下一行决策
            backTrack(board, row + 1);
            //撤销选择
            board[row][col] = '.';
        }
    }

    /**
     * 只返回一个结果
     * @param board
     * @param row
     * @return
     */
    public static boolean backTrackII(char[][] board, int row) {
        if (row == board.length) {
            List<String> list = new ArrayList<>();
            for (char[] chars : board) {
                list.add(String.valueOf(chars));
            }
            res.add(list);
            return true;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            //做选择
            board[row][col] = 'Q';
            //进入下一行决策
            if(backTrackII(board, row + 1))
                return true;
            //撤销选择
            board[row][col] = '.';
        }
        return false;
    }

    public static boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        //检查列是否有皇后冲突
        for (char[] chars : board) {
            if (chars[col] == 'Q') {
                return false;
            }
        }
        //检查右上方是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        //检查左上方是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }


}
