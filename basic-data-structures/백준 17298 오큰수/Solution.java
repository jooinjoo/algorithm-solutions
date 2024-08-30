import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Solution {

    static int n;
    static int[] ret, arr;
    static Stack<Integer> stk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        ret = new int[n];
        Arrays.fill(ret, -1);
        arr = new int[n];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tok[i]);
        }
        stk = new Stack<>();

        stk.push(0);
        for (int i = 1; i < n; i++) {
            int cur = arr[i];
            while (!stk.isEmpty() && cur > arr[stk.peek()]) {
                ret[stk.pop()] = cur;
            }
            stk.push(i);
        }
        for (int i : ret) sb.append(i).append(" ");
        System.out.println(sb);
    }
}