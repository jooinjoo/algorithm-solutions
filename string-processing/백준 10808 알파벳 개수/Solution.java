import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        cnt = new int[26];
        char[] chars = br.readLine().toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }

        for (int i : cnt) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}