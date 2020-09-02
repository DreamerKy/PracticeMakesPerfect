package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/21.
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
 *
 * 空白格用 '.' 表示。
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 */

public class LC_37_SudokuSolver_解数独_200821 {

    public static void main(String[] args) {
        char[][] nums = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        solveSudoku(nums);
    }

    public static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        boolean solve = solve(board);
        System.out.println(solve);
    }

    private static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }

                        /*board[i][j] = c;
                        if(isValidSudoku(board)){
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }else{
                            board[i][j] = '.';
                        }*/

                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断该位置放置1-9是否合法
     * @param board
     * @param row
     * @param col
     * @param c
     * @return
     */
    private static boolean isValid(char[][] board, int row, int col, char c) {
        //board[row][col] 位置添加个元素，得判断横向纵向是否合法
        for (int i = 0; i < 9; i++) {
            //判断第row行0-9位置非'.'元素
            if (board[row][i] != '.' && board[row][i] == c) return false;
            //判断第col列0-9位置非'.'元素
            if (board[i][col] != '.' && board[i][col] == c) return false;
            //判断 3x3 宫内元素是否合法
            //i/3是为了3个换一行。i%3是为了同一行的三个。比如i=0,1,2就能得到第一个九宫格的第一行的三个位置
            char boxChar = board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3];
            if (boxChar != '.' && boxChar == c) return false;
        }
        return true;
    }

    /**
     * 判断整体二维数组是否合法，效率差
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) return false;
        int l = 9;
        boolean[][] rows = new boolean[l][l];
        boolean[][] cols = new boolean[l][l];
        boolean[][] boxes = new boolean[l][l];

        for (int r = 0; r < l; r++) {
            for (int c = 0; c < l; c++) {
                if (board[r][c] != '.') {
                    //取出当前位置的数并转化成 row、col和box的相应位置
                    int value = board[r][c] - '1';
                    //判断当前[][]元素位置所在的包,是当前的位置，不是那个tem值所在的包
                    int boxIndex = (r / 3) * 3 + c / 3;
                    //标记row、col和box的相应位置已经访问过了
                    //比如上面nums2中第5列有两个5，
                    //访问第一个5时访问到 rows[3][4]、cols[3][4]、boxes[4][4] 为true
                    //访问第二个5时访问到 rows[6][4]、cols[3][4]、boxes[7][4] 发现 cols[3][4] 已经为true，所以返回false
                    if (rows[r][value] || cols[c][value] || boxes[boxIndex][value]) {
                        return false;
                    }

                    rows[r][value] = true;
                    cols[c][value] = true;
                    boxes[boxIndex][value] = true;
                }
            }
        }

        return true;
    }
}
