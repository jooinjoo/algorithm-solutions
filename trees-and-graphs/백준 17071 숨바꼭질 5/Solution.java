import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, k, lev;
    static int[][] vis;
    static Queue<Integer> q;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        k = Integer.parseInt(tok[1]);
        lev = 1;
        vis = new int[2][500001];
        q = new LinkedList<>();
        flag = false;

        if (n == k) {
            System.out.println(0);
            return;
        }

        vis[0][n] = 1;
        q.offer(n);
        while (!q.isEmpty()) {
            k += lev;
            if (k > 500000) break;
            if (vis[lev % 2][k] > 0) {
                flag = true;
                break;
            }

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                int[] dr = {-1, 1, cur};
                for (int j = 0; j < 3; j++) {
                    int nr = cur + dr[j];
                    if (nr < 0 || nr > 500000 || vis[lev % 2][nr] != 0) continue;
                    vis[lev % 2][nr] = vis[(lev + 1) % 2][cur] + 1;
                    if (nr == k) {
                        flag = true;
                        break;
                    }
                    q.offer(nr);
                }
                if (flag) break;
            }
            if (flag) break;
            lev++;
        }
        System.out.println(flag ? lev : -1);
    }
}