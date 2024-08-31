import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int n, m, dist;
    static int[][] map;
    static ArrayList<Pos> hList, cList;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        dist = Integer.MAX_VALUE;
        map = new int[n][n];
        hList = new ArrayList<>();
        cList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
                if (map[i][j] == 1) hList.add(new Pos(i, j));
                else if (map[i][j] == 2) cList.add(new Pos(i, j));
            }
        }

        res = new int[m];
        comb(0, 0);

        System.out.println(dist);
    }

    static void comb(int cnt, int idx) {
        if (cnt == m) {
            calc();
            return;
        }

        for (int i = idx; i < cList.size(); i++) {
            res[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    static void calc() {
        int ret = 0;
        for (Pos home : hList) {
            int tmp = Integer.MAX_VALUE;
            for (int idx : res) {
                Pos chic = cList.get(idx);
                tmp = Math.min(tmp, Math.abs(home.r - chic.r) + Math.abs(home.c - chic.c));
            }
            ret += tmp;
        }
        dist = Math.min(dist, ret);
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}