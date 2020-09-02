package com.kotlin.vip.datastructure.leetcode;

/**
 * Created by likaiyu on 2020/8/10.
 *
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
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
public class LC_36_ValidSudoku_有效的数独_200821 {
    private static final int l = 9;

    public static void main(String[] args) {
        char[][] nums = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] nums2 = {
                {'.','.','4','.','.','.','6','3','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','6','.','.','.','.'},
                {'4','.','3','.','.','.','.','.','1'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        
        boolean validSudoku = isValidSudoku(nums2);
        System.out.println(validSudoku);
    }


    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) return false;
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
