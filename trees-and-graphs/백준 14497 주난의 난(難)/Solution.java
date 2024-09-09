import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, m, x1, y1, x2, y2, lev;
    static char[][] map;
    static boolean[][] vis;
    static Queue<Pos> q;
    static boolean flag;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        map = new char[n][m];
        vis = new boolean[n][m];
        tok = br.readLine().split(" ");
        x1 = Integer.parseInt(tok[0]) - 1;
        y1 = Integer.parseInt(tok[1]) - 1;
        x2 = Integer.parseInt(tok[2]) - 1;
        y2 = Integer.parseInt(tok[3]) - 1;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        q = new LinkedList<>();
        lev = 0;
        flag = false;

        while (true) {
            lev++;
            vis = new boolean[n][m];
            vis[x1][y1] = true;
            q.offer(new Pos(x1, y1));
            while (!q.isEmpty()) {
                Pos cur = q.poll();
                if (cur.r == x2 && cur.c == y2) {
                    flag = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || vis[nr][nc]) continue;
                    vis[nr][nc] = true;
                    if (map[nr][nc] == '1') {
                        map[nr][nc] = '0';
                    } else {
                        q.offer(new Pos(nr, nc));
                    }
                }
            }
            if (flag) break;
        }
        System.out.println(lev);
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}