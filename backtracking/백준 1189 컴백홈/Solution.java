import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int R, C, K, ans;
    static char[][] map;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        R = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        K = Integer.parseInt(tok[2]);
        ans = 0;
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        vis = new boolean[R][C];

        if (K < R + C - 1) {
            System.out.println(0);
            return;
        }

        vis[R - 1][0] = true;
        dfs(R - 1, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int r, int c, int cnt) {
        if (cnt == K) {
            if (r == 0 && c == C - 1) ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (map[nr][nc] == 'T' || vis[nr][nc]) continue;
            vis[nr][nc] = true;
            dfs(nr, nc, cnt + 1);
            vis[nr][nc] = false;
        }
    }
}