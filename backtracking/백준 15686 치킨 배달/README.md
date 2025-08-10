# 치킨 배달

> https://www.acmicpc.net/problem/15686

## 문제 설명

- 문제

크기가 N×N인 도시가 있다. 도시는 1×1크기의 칸으로 나누어져 있다. 도시의 각 칸은 빈 칸, 치킨집, 집 중 하나이다. 도시의 칸은 (r, c)와 같은 형태로 나타내고, r행 c열 또는 위에서부터 r번째 칸,
왼쪽에서부터 c번째 칸을 의미한다. r과 c는 1부터 시작한다.

이 도시에 사는 사람들은 치킨을 매우 좋아한다. 따라서, 사람들은 "치킨 거리"라는 말을 주로 사용한다. 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 즉, 치킨 거리는 집을 기준으로 정해지며, 각각의 집은
치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.

임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.

예를 들어, 아래와 같은 지도를 갖는 도시를 살펴보자.

0은 빈 칸, 1은 집, 2는 치킨집이다.

(2, 1)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |2-1| + |1-2| = 2, (5, 5)에 있는 치킨집과의 거리는 |2-5| + |1-5| = 7이다. 따라서, (2, 1)에 있는 집의 치킨
거리는 2이다.

(5, 4)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |5-1| + |4-2| = 6, (5, 5)에 있는 치킨집과의 거리는 |5-5| + |4-5| = 1이다. 따라서, (5, 4)에 있는 집의 치킨
거리는 1이다.

이 도시에 있는 치킨집은 모두 같은 프랜차이즈이다. 프렌차이즈 본사에서는 수익을 증가시키기 위해 일부 치킨집을 폐업시키려고 한다. 오랜 연구 끝에 이 도시에서 가장 수익을 많이 낼 수 있는 치킨집의 개수는 최대
M개라는 사실을 알아내었다.

도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 어떻게 고르면, 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성하시오.

## 접근 방법

최대 13개의 치킨집 중 M개의 치킨집을 남겨, 각각의 모든 집이 가지는 치킨 거리의 합이 최소가 되는 값을 구하는 문제.  
N과 M이 각각 최대 50과 13으로 크지 않아, 최댓값 기준으로 모든 치킨집 조합에 따른 도시의 치킨 거리를 구해도 시간 복잡도를 충분히 통과한다.  
직접 계산해보면 13C6 * 2N = 1716 * 100 = 171600 가지 경우의 수.

## 문제 해결 과정

- 최종 해결 방법:
    - 도시의 지도를 입력할 때 `map[i][j]`의 값에 따라 각각 집 리스트 `ArrayList<Pos> hList`와 치킨집 리스트 `ArrayList<Pos> cList`에 입력.
    - 그리고 치킨집 리스트에서 M개의 치킨집 조합을 뽑아 해당 인덱스를 `int[] res`에 삽입.
        - 이 과정에서 `comb()` 메서드를 재귀하며 `res`에 M개의 치킨집 조합을 넣었을 때, `calc()`를 통해 모든 집과의 해당 치킨 거리를 계산.
        - `tmp`는 해당 조합에 대한 치킨집 거리의 최솟값을 의미하고, `tmp = Integer.MAX_VALUE` 초기화.
            - 각각의 치킨집 조합에 따른 `tmp`를 모두 더해 현재 조합의 도시의 치킨 거리를 `ret`에 저장.
            - 직전까지의 도시의 치킨 거리 `dist`와 `ret`를 비교하며 최솟값을 갱신한다.
- 25.8.11. 다시 푼 방법:
    - 치킨집을 최대 M개 뽑는 조합을 구성한 뒤, 각 조합마다 가지는 도시의 치킨 거리를 비교하며 최솟값을 찾는 방식을 사용.
    - M을 입력받고, 1부터 M까지 치킨집을 뽑는 경우의 수를 나눴다.
        - 문제에서 최대 M개의 치킨집을 놔둘 때 이익이 난다고 이해하여, 1~M까지 전부 시도했는데, 생각해보니 최댓값인 M만 시도해도 될 것 같다.
        - 어차피 모든 더 많은 치킨집에서 더 적은 수의 치킨집 조합은 항상 이미 가지기 때문에.
    - 조합을 만드는 메서드에서 시간이 좀 걸렸는데, 빠르게 사용할 수 있도록 아예 외우도록 하자.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int N, M, min = Integer.MAX_VALUE;
    static int[][] board;
    static ArrayList<Pos> house;
    static ArrayList<Pos> chic;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        M = Integer.parseInt(tok[1]);
        board = new int[N][N];
        house = new ArrayList<>();
        chic = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(tok[j]);
                if (board[i][j] == 1) house.add(new Pos(i, j));
                else if (board[i][j] == 2) chic.add(new Pos(i, j));
            }
        }

        for (int i = 1; i <= M; i++) {
            tmp = new int[i];
            comb(i, 0, 0);
        }

        System.out.println(min);
    }

    static void comb(int size, int idx, int cnt) {
        if (cnt == size) {
            int sum = calc();
            min = Math.min(min, sum);
            return;
        }

        for (int i = idx; i < chic.size(); i++) {
            tmp[cnt] = i;
            comb(size, i + 1, cnt + 1);
        }
    }

    static int calc() {
        int ret = 0;
        for (Pos hou : house) {
            int sum = Integer.MAX_VALUE;
            for (int t : tmp) {
                Pos chi = chic.get(t);
                int dist = Math.abs(hou.r - chi.r) + Math.abs(hou.c - chi.c);
                sum = Math.min(sum, dist);
            }
            ret += sum;
        }
        return ret;
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
```