import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int k;
    static long max, min;
    static String[] oper;
    static boolean[] vis;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        max = 0;
        min = 9999999999L;
        oper = br.readLine().split(" ");
        vis = new boolean[10];
        tmp = new int[k + 1];

        perm(0);
        System.out.println(string(max));
        System.out.println(string(min));
    }

    static void perm(int idx) {
        if (idx == k + 1) {
            boolean flag = true;
            for (int i = 0; i < oper.length; i++) {
                if (!calc(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                long cur = 0;
                for (int i : tmp) cur = cur * 10 + i;
                max = Math.max(max, cur);
                min = Math.min(min, cur);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            tmp[idx] = i;
            perm(idx + 1);
            vis[i] = false;
        }
    }

    static boolean calc(int idx) {
        if (oper[idx].equals("<")) return tmp[idx] < tmp[idx + 1];
        else return tmp[idx] > tmp[idx + 1];
    }

    static String string(Long num) {
        StringBuilder sb = new StringBuilder("0000000000");
        String s = sb.append(num).toString();
        return s.substring(s.length() - (k + 1));
    }
}