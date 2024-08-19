# 쿼드트리

> https://www.acmicpc.net/problem/1992

## 문제 설명

- 문제

흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다. 흰 점을 나타내는 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이
몰려있으면, 쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.

주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽
아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다

위 그림에서 왼쪽의 영상은 오른쪽의 배열과 같이 숫자로 주어지며, 이 영상을 쿼드 트리 구조를 이용하여 압축하면 "(0(0011)(0(0111)01)1)"로 표현된다. N ×N 크기의 영상이 주어질 때, 이 영상을
압축한 결과를 출력하는 프로그램을 작성하시오.

- 입력

첫째 줄에는 영상의 크기를 나타내는 숫자 N 이 주어진다. N 은 언제나 2의 제곱수로 주어지며, 1 ≤ N ≤ 64의 범위를 가진다. 두 번째 줄부터는 길이 N의 문자열이 N개 들어온다. 각 문자열은 0 또는 1의
숫자로 이루어져 있으며, 영상의 각 점들을 나타낸다.

- 출력

영상을 압축한 결과를 출력한다.

## 접근 방법

전체 배열 `int[n][n] map`을 4분할하여, 동일한 값을 가지는지 확인.  
이 과정에서 기준이 되는 값과 모두 같은지, 그렇지 않으면 분할 정복으로 재귀.  
N이 최대 64이므로, 시간 복잡도 O(N^2 * logN)으로 충분히 통과.

## 문제 해결 과정

- 최종 해결 방법:
    - 주어진 배열을 `int[n][n] map`에 2차원 배열로 입력.
    - `(r, c)`를 왼쪽 꼭지점으로 `size`만큼의 정사각형 내부 값이 모두 같은 값을 갖는지 확인하는 `dfs()` 함수 재귀.
        - 동일하다면 그대로 출력에 `(r, c)`의 값 `base` 출력.
        - 같지 않다면, 해당 범위를 다시 4분할하여 함수 재귀 시작.
            - 이 과정에서 4분할의 시작과 끝에 `(`, `)`를 출력해줘야 한다.
            - `size = 1`이 되면 무조건 `base`가 같은 값이므로 따로 기저 사례를 적지 않았다.
- 다른 해결 방법:
    - 기존 해결 방법과 논리는 동일하지만, `dfs()`를 String 타입을 반환하도록 변경.
    - `size = 1`인 경우의 종료 기저 사례를 구현하고, 반환 String 객체를 누적하는 방식으로 구현.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(dfs(0, 0, n));
    }

    static String dfs(int r, int c, int size) {
        int base = map[r][c];
        if (size == 1) return String.valueOf(base);

        boolean flag = true;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != base) flag = false;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (!flag) {
            int half = size / 2;
            sb.append("(");
            sb.append(dfs(r, c, half));
            sb.append(dfs(r, c + half, half));
            sb.append(dfs(r + half, c, half));
            sb.append(dfs(r + half, c + half, half));
            sb.append(")");
            return sb.toString();
        }
        return String.valueOf(base);
    }
}
```
