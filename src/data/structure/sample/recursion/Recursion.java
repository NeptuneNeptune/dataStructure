package data.structure.sample.recursion;

/**
 * 递归算法求斐波拉契数列的第n个数
 *
 * @author Neptune
 * @date 2020/6/19 13:08
 */
public class Recursion {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            sb.append(compute(i)).append(", ");
        }
        System.out.println(sb.substring(0, sb.length() - 2));
    }

    /**
     * 求斐波拉契数列的第n个数
     * 第一个数和第二个数均为1，其余的数由递增公式得出
     * 递增公式为A(n) = A(n-1) + A(n-2)
     *
     * @param n 需要求得的第几个数
     * @return int 斐波拉契数列的第n个数
     */
    public static int compute(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return compute(n - 1) + compute(n - 2);
        }
    }
}
