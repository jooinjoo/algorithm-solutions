import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N, ans = Integer.MAX_VALUE, cnt;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == 'H') map[i][j] = true;
            }
        }

        for (int cur = 0; cur < (1 << N); cur++) {
            flipRow(cur);

            cnt = countBack();
            ans = Math.min(ans, cnt);

            flipRow(cur);
        }
        System.out.println(ans);
    }

    static void flipRow(int row) {
        for (int i = 0; i < N; i++) {
            if ((row & (1 << i)) != 0) {
                for (int j = 0; j < N; j++) map[i][j] = !map[i][j];
            }
        }
    }

    static int countBack() {
        int ret = 0;
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            for (int j = 0; j < N; j++) {
                if (!map[j][i]) tmp++;
            }
            tmp = tmp > (N / 2) ? N - tmp : tmp;
            ret += tmp;
        }
        return ret;
    }
}