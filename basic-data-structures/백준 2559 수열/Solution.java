import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N, K, ans = Integer.MIN_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        K = Integer.parseInt(tok[1]);
        arr = new int[N];
        tok = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tok[i]);
        }

        int tmp = 0;
        for (int i = 0; i < K; i++) {
            tmp += arr[i];
        }
        ans = tmp;
        for (int i = K; i < N; i++) {
            tmp += arr[i] - arr[i - K];
            ans = Math.max(ans, tmp);
        }
        System.out.println(ans);
    }
}