import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static String cur;
    static boolean[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        cur = "";
        alpha = new boolean[26];
        alpha['a' - 'a'] = true;
        alpha['e' - 'a'] = true;
        alpha['i' - 'a'] = true;
        alpha['o' - 'a'] = true;
        alpha['u' - 'a'] = true;

        while (!(cur = br.readLine()).equals("end")) {
            if (isValid()) {
                sb.append("<").append(cur).append("> is acceptable.\n");
            } else {
                sb.append("<").append(cur).append("> is not acceptable.\n");
            }
        }
        System.out.println(sb);
    }

    static boolean isValid() {
        if (cur.indexOf('a') < 0 && cur.indexOf('e') < 0 && cur.indexOf('i') < 0 &&
                cur.indexOf('o') < 0 && cur.indexOf('u') < 0) return false;

        if (cur.length() >= 3) {
            for (int i = 0; i < cur.length() - 2; i++) {
                if (alpha[cur.charAt(i) - 'a'] == alpha[cur.charAt(i + 1) - 'a'] &&
                        alpha[cur.charAt(i + 1) - 'a'] == alpha[cur.charAt(i + 2) - 'a']) return false;
            }
        }

        if (cur.length() >= 2) {
            for (int i = 0; i < cur.length() - 1; i++) {
                if (cur.charAt(i) == cur.charAt(i + 1)) {
                    if (cur.charAt(i) == 'e' || cur.charAt(i) =='o') continue;
                    return false;
                }
            }
        }
        return true;
    }
}