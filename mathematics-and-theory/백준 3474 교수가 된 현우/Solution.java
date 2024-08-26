import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int cnt = 0;
            int tmp = 5;
            while (tmp <= n) {
                cnt += n / tmp;
                tmp *= 5;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}