import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n, m, h, ans;
    static int[][] map;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        h = Integer.parseInt(tok[2]);
        ans = -1;
        map = new int[h + 1][n + 1];
        for (int i = 0; i < m; i++) {
            tok = br.readLine().split(" ");
            int a = Integer.parseInt(tok[0]);
            int b = Integer.parseInt(tok[1]);
            map[a][b] = 1;
            map[a][b + 1] = 2;
        }
        flag = false;

        for (int i = 0; i <= 3; i++) {
            comb(0, i);
            if (flag) break;
        }
        System.out.println(flag ? ans : -1);
    }

    static void comb(int cnt, int size) {
        if (flag) return;
        if (cnt == size) {
            if (check()) {
                flag = true;
                ans = size;
            }
            return;
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n - 1; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    comb(cnt + 1, size);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int r = 1, c = i;
            for (int j = 1; j <= h; j++) {
                if (map[r][c] == 1) c++;
                else if (map[r][c] == 2) c--;
                r++;
            }
            if (i != c) return false;
        }
        return true;
    }
}