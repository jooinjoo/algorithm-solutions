import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n;
    static int[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        sb = new StringBuilder();

        dfs(0, 0, n);
        System.out.println(sb);
    }

    static void dfs(int r, int c, int size) {
        boolean flag = true;
        int base = map[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != base) flag = false;
            }
        }

        if (flag) {
            sb.append(base);
        } else {
            int half = size / 2;
            sb.append("(");
            dfs(r, c, half);
            dfs(r, c + half, half);
            dfs(r + half, c, half);
            dfs(r + half, c + half, half);
            sb.append(")");
        }
    }
}