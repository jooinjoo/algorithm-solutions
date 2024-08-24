import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int h, w;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] words = br.readLine().split(" ");
        h = Integer.parseInt(words[0]);
        w = Integer.parseInt(words[1]);
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == 'c') map[i][j] = 0;
                else map[i][j] = -1;
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 0) {
                    for (int k = j + 1; k < w; k++) {
                        if (map[i][k] != -1) break;
                        map[i][k] = map[i][k - 1] + 1;
                    }
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}