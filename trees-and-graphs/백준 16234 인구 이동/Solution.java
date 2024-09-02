import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, l, r, cnt;
    static boolean flag;
    static int[][] map;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        l = Integer.parseInt(tok[1]);
        r = Integer.parseInt(tok[2]);
        cnt = 0;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
            }
        }

        while (true) {
            flag = false;
            vis = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!vis[i][j]) bfs(i, j);
                }
            }

            if (!flag) break;
            cnt++;
        }
        System.out.println(cnt);
    }

    static void bfs(int row, int col) {
        Queue<Pos> q = new LinkedList<>();
        vis[row][col] = true;
        q.offer(new Pos(row, col));
        ArrayList<Pos> open = new ArrayList<>();
        open.add(new Pos(row, col));
        int tmp = map[row][col];

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n || vis[nr][nc]) continue;
                int diff = Math.abs(map[nr][nc] - map[cur.r][cur.c]);
                if (diff < l || r < diff) continue;
                vis[nr][nc] = true;
                flag = true;
                open.add(new Pos(nr, nc));
                tmp += map[nr][nc];
                q.offer(new Pos(nr, nc));
            }
        }

        if (open.size() > 1) {
            for (Pos pos : open) {
                map[pos.r][pos.c] = tmp / open.size();
            }
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}