import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int A, B, C, ans;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        A = Integer.parseInt(tok[0]);
        B = Integer.parseInt(tok[1]);
        C = Integer.parseInt(tok[2]);
        ans = 0;
        cnt = new int[101];
        for (int i = 0; i < 3; i++) {
            tok = br.readLine().split(" ");
            int a = Integer.parseInt(tok[0]);
            int b = Integer.parseInt(tok[1]);
            for (int j = a; j < b; j++) {
                cnt[j]++;
            }
        }

        ans = A * count(1) + B * 2 * count(2) + C * 3 * count(3);
        System.out.println(ans);
    }

    static int count(int num) {
        int ret = 0;
        for (int i : cnt) {
            if (i == num) ret++;
        }
        return ret;
    }
}