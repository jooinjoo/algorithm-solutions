import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    static int sum = 0, tmp;
    static int[] arr, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        arr = new int[9];
        ans = new int[7];
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                tmp = sum;
                tmp -= arr[i] + arr[j];
                if (tmp == 100) {
                    int cnt = 0;
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        ans[cnt++] = arr[k];
                    }
                }
            }
        }
        Arrays.sort(ans);
        for (int a : ans) {
            sb.append(a).append("\n");
        }
        System.out.println(sb.toString());
    }
}