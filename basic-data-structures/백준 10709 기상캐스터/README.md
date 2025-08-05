# 기상캐스터

> https://www.acmicpc.net/problem/10709

## 문제 설명

- 문제

JOI시는 남북방향이 H 킬로미터, 동서방향이 W 킬로미터인 직사각형 모양이다. JOI시는 가로와 세로의 길이가 1킬로미터인 H × W 개의 작은 구역들로 나뉘어 있다. 북쪽으로부터 i 번째, 서쪽으로부터 j 번째에
있는 구역을 (i, j) 로 표시한다.

각 구역의 하늘에는 구름이 있을 수도, 없을 수도 있다. 모든 구름은 1분이 지날 때마다 1킬로미터씩 동쪽으로 이동한다. 오늘은 날씨가 정말 좋기 때문에 JOI시의 외부에서 구름이 이동해 오는 경우는 없다.

지금 각 구역의 하늘에 구름이 있는지 없는지를 알고 있다. 기상청에서 일하고 있는 여러분은 각 구역에 대해서 지금부터 몇 분뒤 처음으로 하늘에 구름이 오는지를 예측하는 일을 맡았다.

각 구역에 대해서 지금부터 몇 분뒤 처음으로 하늘에 구름이 오는지를 구하여라.

- 입력

입력은 1 + H 행으로 주어진다.

첫 번째 행에는 정수 H, W (1 ≦ H ≦ 100, 1 ≦ W ≦ 100) 가 공백을 사이에 주고 주어진다. 이것은 JOI시가 H × W 개의 작은 구역으로 나뉘어 있다는 것을 의미한다.

이어진 H 개의 행의 i번째 행 (1 ≦ i ≦ H) 에는 W문자의 문자열이 주어진다. W 개의 문자 중 j번째 문자 (1 ≦ j ≦ W) 는, 구역 (i, j) 에 지금 구름이 떠 있는지 아닌지를 나타낸다. 구름이
있는 경우에는 영어 소문자 'c' 가, 구름이 없는 경우에는 문자 '.' 가 주어진다.

- 출력

출력은 H 행으로, 각 행에는 공백으로 구분된 W 개의 정수를 출력한다. 출력의 i 번째 행 j 번째 정수 (1 ≦ i ≦ H, 1 ≦ j ≦ W) 는, 지금부터 몇 분후에 처음으로 구역 (i, j) 에 구름이
뜨는지를 표시한다. 단, 처음부터 구역 (i, j) 에 구름이 떠 있었던 경우에는 0을, 몇 분이 지나도 구름이 뜨지 않을 경우에는 -1을 출력한다.

## 접근 방법

전체 배열 `int[h][w]`에서 행 별로 구름이 존재하는지 확인.  
구름을 만나면 우측으로, 다시 구름을 만나기 전까지 값을 갱신하며 채우기.

## 문제 해결 과정

- 최종 해결 방법:
    - 전체 `map`을 행 -> 열 순으로 탐색하며 구름이 지나간 적 없는 경우에만 값을 갱신.
    - `map`을 입력받을 때, `(i, j)`가 구름이면 `0`, 그렇지 않으면 `-1`을 입력.
    - `map`을 탐색할 땐, 구름이 우측으로만 이동하므로 행마다 열을 작은 값에서 큰 값으로 이동하며 값을 검토.
        - 만약 한 번이라도 `0`을 만나면 이후로는 무조건 구름이 지나가므로 직전 값 +1로 갱신.
        - 이 과정에서 다른 구름이 존재했던 `0`을 만나거나, `w` 범위에 다다르면 종료.
            - 기존에 탐색했던 열 값으로 돌아가 다시 재탐색.
- 25.8.5. 다시 푼 방법:
    - 전체 초기 값 `board`를 기준으로 `ret`를 구성. 구름이 있으면 0, 없으면 -1로 초기화.
    - 이후 각 행 -> 열 순으로 루프하며 해당 행에서 한 번이라도 구름을 만난 적 있는지 `flag`로 검사.
        - 만난 적 있고 해당 값이 -1이면 이후 값들은 무조건 직전 값 +1.
        - 만약 다른 구름이 존재하는, 0을 또 만나면 넘어가기.
    - 이전에 풀었던 방식으로 구름을 만나면, 그 이후 값들을 또다시 검사하는 방식이 반복을 줄일 수 있어 더 좋은 듯 하다.


## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int H, W;
    static char[][] board;
    static int[][] ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        H = Integer.parseInt(tok[0]);
        W = Integer.parseInt(tok[1]);
        board = new char[H][W];
        ret = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                ret[i][j] = -1;
            }
        }
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'c') ret[i][j] = 0;
            }
        }

        for (int i = 0; i < H; i++) {
            boolean flag = false;
            for (int j = 0; j < W; j++) {
                if (ret[i][j] == 0) {
                    flag = true;
                } else if (ret[i][j] == -1) {
                    if (flag) ret[i][j] = ret[i][j - 1] + 1;
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(ret[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
```