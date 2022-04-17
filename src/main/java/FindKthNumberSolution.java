public class FindKthNumberSolution {

    public static void main(String[] args) {
        System.out.println(findKthNumber(20, 22));
    }

    public static int findKthNumber(int n, int k) {
        int cur = 1;
        k -= 1;

        while (k > 0) {
            int nodes = getNodes(n, cur);
            if (k < nodes) {
                k -= 1;
                cur *= 10; //往下走
            } else {
                k = k - nodes;
                cur++; //往右走
            }
        }
        return cur;
    }

    public static int getNodes(int n, int cur) {
        int next = cur + 1;
        int totalNodes = 0;
        while (cur <= n) {
            totalNodes += Math.min(next - cur, n - cur + 1);
            cur *= 10;
            next *= 10;
        }
        return totalNodes;
    }

}
