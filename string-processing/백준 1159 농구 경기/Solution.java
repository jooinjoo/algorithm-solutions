import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        cnt = new int[26];
        for (int i = 0; i < N; i++) {
            cnt[br.readLine().charAt(0) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (cnt[i] >= 5) sb.append((char) (97 + i));
        }
        System.out.println(sb.toString().isEmpty() ? "PREDAJA" : sb.toString());
    }
}