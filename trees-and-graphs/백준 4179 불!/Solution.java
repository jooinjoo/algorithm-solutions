import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int r, c;
    static char[][] map;
    static int[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        r = Integer.parseInt(tok[0]);
        c = Integer.parseInt(tok[1]);
        Queue<Pos> jq = new LinkedList<>();
        Queue<Pos> fq = new LinkedList<>();
        map = new char[r][c];
        vis = new int[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') {
                    jq.offer(new Pos(i, j));
                    vis[i][j] = 1;
                    map[i][j] = '.';
                } else if (map[i][j] == 'F') {
                    fq.offer(new Pos(i, j));
                }
            }
        }

        while (!jq.isEmpty()) {
            int fSize = fq.size();
            for (int i = 0; i < fSize; i++) {
                Pos f = fq.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = f.row + dr[j];
                    int nc = f.col + dc[j];
                    if (nr < 0 || nc < 0 || nr >= r || nc >= c || map[nr][nc] != '.') continue;
                    map[nr][nc] = 'F';
                    fq.offer(new Pos(nr, nc));
                }
            }

            int jSize = jq.size();
            for (int i = 0; i < jSize; i++) {
                Pos cur = jq.poll();
                if (cur.row == 0 || cur.col == 0 || cur.row == r - 1 || cur.col == c - 1) {
                    System.out.println(vis[cur.row][cur.col]);
                    return;
                }
                for (int j = 0; j < 4; j++) {
                    int nr = cur.row + dr[j];
                    int nc = cur.col + dc[j];
                    if (nr < 0 || nc < 0 || nr >= r || nc >= c || map[nr][nc] != '.' || vis[nr][nc] != 0) continue;
                    vis[nr][nc] = vis[cur.row][cur.col] + 1;
                    jq.offer(new Pos(nr, nc));
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}