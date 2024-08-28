import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int n, m, cnt, pre, lev;
    static int[][] map;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        map = new int[n][m];
        cnt = 0;
        for (int i = 0; i < n; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
                if (map[i][j] == 1) cnt++;
            }
        }
        lev = 0;
        pre = cnt;

        while (true) {
            lev++;
            vis = new boolean[n][m];
            vis[0][0] = true;
            Queue<Pos> q = new ArrayDeque<>();
            q.offer(new Pos(0, 0));

            while (!q.isEmpty()) {
                Pos cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= m || vis[nr][nc]) continue;
                    vis[nr][nc] = true;
                    if (map[nr][nc] == 1) {
                        map[nr][nc] = 0;
                        cnt--;
                    } else {
                        q.offer(new Pos(nr, nc));
                    }
                }
            }
            if (cnt == 0) break;
            pre = cnt;
        }
        sb.append(lev).append("\n").append(pre);
        System.out.println(sb);
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}