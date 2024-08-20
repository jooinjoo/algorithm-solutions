import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n, m, j, lt, rt, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] words = br.readLine().split(" ");
        n = Integer.parseInt(words[0]);
        m = Integer.parseInt(words[1]);
        lt = 1;
        rt = m;
        j = Integer.parseInt(br.readLine());
        cnt = 0;

        for (int i = 0; i < j; i++) {
            int cur = Integer.parseInt(br.readLine());
            if (lt <= cur && cur <= rt) continue;

            int tmp = Math.min(Math.abs(lt - cur), Math.abs(rt - cur));
            if (cur < lt) {
                lt -= tmp;
                rt -= tmp;
            } else {
                lt += tmp;
                rt += tmp;
            }
            cnt += tmp;
        }
        System.out.println(cnt);
    }
}