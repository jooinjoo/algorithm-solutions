import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        int cur = 665;
        while (true) {
            cur++;
            if (String.valueOf(cur).contains("666")) cnt++;
            if (n == cnt) break;
        }
        System.out.println(cur);
    }
}