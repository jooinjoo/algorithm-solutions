import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n, s1, s2, pre, cnt1, cnt2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        s1 = s2 = 0;
        pre = 0;
        cnt1 = cnt2 = 0;
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            int w = Integer.parseInt(s[0]);
            s = s[1].split(":");
            int cur = 60 * Integer.parseInt(s[0]) + Integer.parseInt(s[1]);

            if (s1 != s2) {
                int tmp = cur - pre;
                if (s1 > s2) cnt1 += tmp;
                else cnt2 += tmp;
            }

            if (w == 1) s1++;
            else s2++;
            pre = cur;
        }

        if (s1 != s2) {
            int tmp = 2880 - pre;
            if (s1 > s2) cnt1 += tmp;
            else cnt2 += tmp;
        }
        System.out.printf("%02d:%02d\n", cnt1 / 60, cnt1 % 60);
        System.out.printf("%02d:%02d\n", cnt2 / 60, cnt2 % 60);
    }
}