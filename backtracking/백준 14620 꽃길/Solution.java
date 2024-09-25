import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n, ans;
    static int[][] map, tmp;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = 3000;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] tok = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
            }
        }
        tmp = new int[3][2];

        comb(1, 1, 0);
        System.out.println(ans);
    }

    static void comb(int r, int c, int cnt) {
        if (cnt == 3) {
            calc();
            return;
        }

        for (int i = r; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i < 1 || i > n - 2 || j > n - 2) continue;
                tmp[cnt][0] = i;
                tmp[cnt][1] = j;
                comb(i, j + 1, cnt + 1);
            }
        }
    }

    static void calc() {
        boolean[][] vis = new boolean[n][n];
        int ret = 0;
        for (int i = 0; i < 3; i++) {
            int[] cur = tmp[i];
            if (vis[cur[0]][cur[1]]) return;
            vis[cur[0]][cur[1]] = true;
            ret += map[cur[0]][cur[1]];
            for (int j = 0; j < 4; j++) {
                int nr = cur[0] + dr[j];
                int nc = cur[1] + dc[j];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || vis[nr][nc]) return;
                vis[nr][nc] = true;
                ret += map[nr][nc];
            }
        }
        ans = Math.min(ans, ret);
    }
}