import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, k, ans, cnt;
    static int[] vis;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        k = Integer.parseInt(tok[1]);
        ans = 0;
        cnt = 0;
        vis = new int[100001];
        q = new LinkedList<>();

        Arrays.fill(vis, Integer.MAX_VALUE);
        boolean flag = false;
        vis[n] = 1;
        q.offer(n);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == k) {
                flag = true;
                ans = vis[cur] - 1;
                cnt++;
            } else if (!flag) {
                int nr = cur + 1;
                if (nr >= 0 && nr <= 100000 && vis[cur] + 1 <= vis[nr]) {
                    vis[nr] = vis[cur] + 1;
                    q.offer(nr);
                }
                nr = cur - 1;
                if (nr >= 0 && nr <= 100000 && vis[cur] + 1 <= vis[nr]) {
                    vis[nr] = vis[cur] + 1;
                    q.offer(nr);
                }
                nr = cur * 2;
                if (nr >= 0 && nr <= 100000 && vis[cur] + 1 <= vis[nr]) {
                    vis[nr] = vis[cur] + 1;
                    q.offer(nr);
                }
            }
        }
        System.out.println(ans + "\n" + cnt);
    }
}