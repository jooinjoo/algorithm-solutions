import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static int N, ans;
    static int[] arr;
    static int[][] vals;
    static List<Integer> tmp;
    static List<String> ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        arr = new int[4];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(tok[i]);
        }
        vals = new int[N][5];
        for (int i = 0; i < N; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                vals[i][j] = Integer.parseInt(tok[j]);
            }
        }
        tmp = new ArrayList<>();
        ret = new ArrayList<>();

        dfs(0, 0, 0, 0, 0, 0);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            Collections.sort(ret);
            sb.append(ans).append("\n");
            sb.append(ret.get(0));
            System.out.println(sb.toString());
        }
    }

    static void dfs(int idx, int mp, int mf, int ms, int mv, int sum) {
        if (idx == N) {
            if (isValid(mp, mf, ms, mv) && ans >= sum) {
                if (ans > sum) {
                    ans = sum;
                    ret = new ArrayList<>();
                }
                StringBuilder sb = new StringBuilder();
                for (int i : tmp) sb.append(i + 1).append(" ");
                ret.add(sb.toString());
            }
            return;
        }

        tmp.add(idx);
        dfs(idx + 1, mp + vals[idx][0], mf + vals[idx][1], ms + vals[idx][2], mv + vals[idx][3], sum + vals[idx][4]);
        tmp.remove(tmp.size() - 1);
        dfs(idx + 1, mp, mf, ms, mv, sum);
    }

    static boolean isValid(int mp, int mf, int ms, int mv) {
        return mp >= arr[0] && mf >= arr[1] && ms >= arr[2] && mv >= arr[3];
    }
}