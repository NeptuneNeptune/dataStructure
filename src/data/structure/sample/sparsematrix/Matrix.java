package data.structure.sample.sparsematrix;

import java.util.LinkedList;

/**
 * 稀疏矩阵的转换与还原
 *
 * @author Neptune
 * @date 2020/6/17 17:08
 */
public class Matrix {
    /**
     * 传入一个稀疏矩阵，将矩阵转换为小规模矩阵
     *
     * @param matrix 稀疏矩阵
     * @param key    稀疏矩阵中非关键信息元素
     * @return Integer[][] 压缩矩阵
     */
    public static Integer[][] compress(Integer[][] matrix, Integer key) {
        // 必要的校验
        if (null == matrix) {
            throw new RuntimeException("矩阵不能为null");
        }
        if (null == key) {
            key = 0;
        }
        // 行数
        int rowlen = matrix.length;
        if (rowlen == 0) {
            return matrix;
        }
        // 列数
        int collen = matrix[0].length;
        // 保证每行的元素个数相同
        for (int i = 1; i < rowlen; i++) {
            if (matrix[i].length != collen) {
                throw new RuntimeException("请输入合法的稀疏矩阵");
            }
        }
        // 开始遍历矩阵并提取关键信息
        // 保存关键信息
        LinkedList<Integer[]> keyInfo = new LinkedList<>();
        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (!key.equals(matrix[i][j])) {
                    keyInfo.add(new Integer[]{i, j, matrix[i][j]});
                }
            }
        }
        // 生成压缩矩阵
        Integer[][] result = new Integer[keyInfo.size() + 1][3];
        result[0] = new Integer[]{rowlen, collen, keyInfo.size()};
        for (int i = 0; i < keyInfo.size(); i++) {
            result[i + 1] = keyInfo.get(i);
        }

        return result;
    }


    /**
     * 传入一个小规模矩阵和填充元素，将小规模矩阵还原为稀疏矩阵
     *
     * @param matrix 压缩矩阵
     * @param fill   填充元素
     * @return Integer[][] 稀疏矩阵
     */
    public static Integer[][] restore(Integer[][] matrix, Integer fill) {
        // 必要的校验
        if (null == matrix) {
            throw new RuntimeException("压缩矩阵不能为null");
        }
        if (null == fill) {
            fill = 0;
        }
        // 稀疏矩阵行数、列数、关键元素个数
        int rowlen = matrix[0][0];
        int collen = matrix[0][1];
        int count = matrix[0][2];
        // 校验压缩矩阵的正确性
        if (count != (matrix.length - 1)) {
            throw new RuntimeException("请输入正确的压缩矩阵");
        }
        for (int i = 1; i < matrix.length; i++) {
            Integer[] row = matrix[i];
            if (row[0] > rowlen - 1 || row[0] < 0 || row[1] > collen - 1 || row[1] < 0) {
                throw new RuntimeException("请输入正确的压缩矩阵");
            }
        }
        // 初始化稀疏矩阵
        Integer[][] result = new Integer[rowlen][collen];
        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                result[i][j] = fill;
            }
        }
        // 填充关键数据
        for (int i = 1; i < matrix.length; i++) {
            result[matrix[i][0]][matrix[i][1]] = matrix[i][2];
        }

        return result;
    }
}

class Test {
    /**
     * 将矩阵[
     * [0,0,0,0,0,0,0,0,0,0,0],
     * [0,3,0,0,0,0,7,0,0,0,0],
     * [0,0,0,6,0,0,0,0,0,9,0],
     * [5,0,0,0,0,0,0,0,0,0,0],
     * [0,0,0,0,0,8,0,0,0,0,0],
     * [0,0,0,0,0,0,0,0,0,0,0],
     * [0,0,0,0,0,0,0,0,0,0,9],
     * [0,0,0,0,0,0,0,0,0,0,0],
     * [5,0,0,0,0,0,0,0,0,0,9],
     * [0,0,0,0,0,0,0,0,0,0,0],
     * [0,0,0,6,0,0,0,6,0,0,0]
     * ]
     * 压缩为小规模矩阵，然后使用*元素替换原有的0元素，将稀疏矩阵还原
     */
    public static void main(String[] args) {
        Integer[][] source = new Integer[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 7, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 0, 0, 0, 0, 9, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0}
        };
        Integer[][] compressed = Matrix.compress(source, null);
        print(compressed);
        print(Matrix.restore(compressed, -1));
    }

    public static void print(Integer[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == matrix[i].length - 1) {
                    System.out.print(matrix[i][j]);
                } else {
                    System.out.print(matrix[i][j] + ",");
                }
            }
            if (i == matrix.length - 1) {
                System.out.println("]");
            } else {
                System.out.println("],");
            }
        }
        System.out.println("]");
    }
}
