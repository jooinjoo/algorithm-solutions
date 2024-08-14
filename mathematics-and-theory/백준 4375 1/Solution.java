import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = "";
        int n;
        while ((s = br.readLine()) != null) {
            n = Integer.parseInt(s);
            long tmp = 1;
            int cnt = 1;
            while (true) {
                if (tmp % n == 0) {
                    sb.append(cnt).append("\n");
                    break;
                }
                tmp = (tmp * 10) % n + 1;
                cnt++;
            }
        }
        System.out.println(sb);
    }
}