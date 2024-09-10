import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int r, c, lev, sr, sc;
    static char[][] map;
    static boolean[][] visWater, visSwan;
    static Queue<Pos> wq, wqTmp, sq, sqTmp;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        r = Integer.parseInt(tok[0]);
        c = Integer.parseInt(tok[1]);
        lev = 0;
        sr = 0;
        sc = 0;
        map = new char[r][c];
        visWater = new boolean[r][c];
        visSwan = new boolean[r][c];
        wq = new LinkedList<>();
        sq = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'L') {
                    sr = i;
                    sc = j;
                }
                if (map[i][j] == '.' || map[i][j] == 'L') {
                    visWater[i][j] = true;
                    wq.add(new Pos(i, j));
                }
            }
        }

        sq.offer(new Pos(sr, sc));
        visSwan[sr][sc] = true;
        while (true) {
            if (moveSwan()) break;
            moveWater();
            lev++;
        }
        System.out.println(lev);
    }

    static boolean moveSwan() {
        sqTmp = new LinkedList<>();
        while (!sq.isEmpty()) {
            Pos cur = sq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nr >= r || nc < 0 || nc >= c || visSwan[nr][nc]) continue;
                visSwan[nr][nc] = true;
                if (map[nr][nc] == '.') sq.add(new Pos(nr, nc));
                else if (map[nr][nc] == 'X') sqTmp.add(new Pos(nr, nc));
                else if (map[nr][nc] == 'L') return true;
            }
        }
        sq = sqTmp;
        return false;
    }

    static void moveWater() {
        wqTmp = new LinkedList<>();
        while (!wq.isEmpty()) {
            Pos cur = wq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nr >= r || nc < 0 || nc >= c || visWater[nr][nc]) continue;
                if (map[nr][nc] == 'X') {
                    visWater[nr][nc] = true;
                    wqTmp.add(new Pos(nr, nc));
                    map[nr][nc] = '.';
                }
            }
        }
        wq = wqTmp;
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}