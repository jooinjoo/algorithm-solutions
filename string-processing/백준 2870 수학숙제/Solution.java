import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    static int n;
    static String tmp;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        while (n-- > 0) {
            String s = br.readLine();
            tmp = "";

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    tmp += c;
                } else if (!tmp.isEmpty()) add();
            }
            if (!tmp.isEmpty()) add();
        }

        list.sort((o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        list.forEach(o -> sb.append(o).append("\n"));
        System.out.println(sb);
    }

    static void add() {
        while (tmp.length() > 1 && tmp.charAt(0) == '0') {
            tmp = tmp.substring(1);
        }
        list.add(tmp);
        tmp = "";
    }
}
