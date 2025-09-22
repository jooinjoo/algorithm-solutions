# 동전 뒤집기

> https://www.acmicpc.net/problem/1285

## 문제 설명

- 문제

N2개의 동전이 N행 N열을 이루어 탁자 위에 놓여 있다. 그 중 일부는 앞면(H)이 위를 향하도록 놓여 있고, 나머지는 뒷면(T)이 위를 향하도록 놓여 있다. <그림 1>은 N이 3일 때의 예이다.

<그림 1>

이들 N2개의 동전에 대하여 임의의 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업을 수행할 수 있다. 예를 들어 <그림 1>의 상태에서 첫 번째 열에 놓인 동전을 모두 뒤집으면 <그림 2>와 같이
되고, <그림 2>의 상태에서 첫 번째 행에 놓인 동전을 모두 뒤집으면 <그림 3>과 같이 된다.

<그림 2>    <그림 3>

<그림 3>의 상태에서 뒷면이 위를 향하여 놓인 동전의 개수는 두 개이다. <그림 1>의 상태에서 이와 같이 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업을 계속 수행할 때 뒷면이 위를 향하도록 놓인
동전의 개수를 2개보다 작게 만들 수는 없다.

N2개의 동전들의 초기 상태가 주어질 때, 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업들을 수행하여 뒷면이 위를 향하는 동전 개수를 최소로 하려 한다. 이때의 최소 개수를 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 20이하의 자연수 N이 주어진다. 둘째 줄부터 N줄에 걸쳐 N개씩 동전들의 초기 상태가 주어진다. 각 줄에는 한 행에 놓인 N개의 동전의 상태가 왼쪽부터 차례대로 주어지는데, 앞면이 위를 향하도록 놓인
경우 H, 뒷면이 위를 향하도록 놓인 경우 T로 표시되며 이들 사이에 공백은 없다.

- 출력

첫째 줄에 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업들을 수행하여 뒷면이 위를 향하여 놓일 수 있는 동전의 최소 개수를 출력한다.

## 접근 방법

동전의 행/열은 뒤집거나 뒤집지 않거나, 두 가지 상태를 가지므로 해당 줄을 뒤집었는지 판단하기 위해 비트마스킹 사용.  
한편 한 줄에 최대 20인 정사각형 형태이므로, 2^20(행) * 2^20(열)의 경우의 수는 시간 초과.   
그렇지만 행만 뒤집은 상태에서 열은 해당 열의 뒷면 개수가 N의 절반보다 크면 해당 열을 뒤집은 상태로 뒷면 개수를 카운팅하면 2^20 경우의 수로 가능.   
이렇게 모든 경우의 수에 대해 브루트포스로 탐색하며, 뒷면의 개수를 최솟값 갱신하며 루프.

## 문제 해결 과정

- 첫 번째 시도:
    - 행과 열을 모두 체크한 2^40 경우의 수로 비트마스킹 시도. 메모리 초과 실패.
- 최종 해결 방법:
    - `boolean[][] map`에 초기 동전 상태를 입력. 위(H) = true, 뒤(T) = false.
    - 행 0~N-1까지 루프하며 뒤집거나, 뒤집지 않은 상태를 비트마스킹.
        - `flipRow(row)`를 통해 현재 상태(`cur`)에 해당하는 행의 값을 모두 뒤집어준다.
        - 모두 뒤집은 이후, `countBack()`을 통해 뒷면의 개수를 구한다.
            - 모든 값을 탐색하되, 열을 기준으로 먼저 루프하여 해당 열의 뒷면 값 `tmp`가 N의 절반보다 작으면 열 뒤집기.
            - 열을 뒤집으면 `tmp`는 `N - tmp`로 갱신됨.
        - 최솟값을 갱신한 뒤엔 뒤집은 행 원상 복구.
- 다른 해결 방법:
    - 동전의 상태인 `map`을 `int[] map`으로 선언하여, 하나의 행 상태를 10진수 값으로 표현.
        - 동전을 왼쪽의 열부터 시작하여, 해당 동전이 뒷면(T)이면 1로 비트 마스킹.
        - 예를 들어 "TTHT"인 경우, (1101) -> `map[i] = 1 + 2 + 8`로 `11`의 값을 갖게 됨.
    - 이후 `dfs(idx)`를 재귀하며, 해당 `idx`에 해당하는 행을 뒤집지 않거나, 뒤집은 상태로 DFS 탐색.
        - 행을 뒤집을 땐, 매 열마다 루프하며 값을 바꾸는 게 아니라 `map[idx] = ~map[idx]`로 간단히 가능.
    - 그리고 모든 행에 대한 뒤집기 탐색이 끝나면, (...001) 부터 (1...00)까지 해당 열을 기준으로, 각 행마다 뒤집어진 개수를 카운팅하며 최솟값에 갱신.
- 25.9.22. 다시 푼 방법:
    - 각 행 별로 뒤집은 동전의 상태를 비트마스킹 `int[] vals`에 표기.
        - 뒷면인 `T`인 경우에만 1로 표기하여, 10진수로 저장한다.
    - 이후 메서드 `dfs(idx)`를 재귀하여 각 행 별로 뒤집거나, 뒤집지 않거나 모든 경우를 탐색.
        - `idx`는 몇 번째 행을 의미한다. 해당 행을 뒤집지 않고 다음 행으로 넘어가거나, 뒤집고 넘어가는 경우 두 가지가 존재.
        - `idx`가 `N`과 같아지면 모든 행을 지난 것이므로 해당 동전 상태를 검사한다.
            - 이젠 각 열 별로 `T`인 경우가 몇 개인지 계산하여, 해당 열의 뒷면 개수가 앞면 개수보다 많은 경우 뒤집는다고 생각.
            - 이렇게 하면 이미 행은 모두 뒤집은 상태이고, 열을 뒤집거나 뒤집지 않거나는 최소의 뒷면 개수를 구해야하기 때문에 정해져있다.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N, ans = Integer.MAX_VALUE, cnt;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int val = 1;
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == 'T') map[i] |= val;
                val *= 2;
            }
        }

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int idx) {
        if (idx == N) {
            cnt = 0;
            for (int i = 1; i < (1 << N); i *= 2) {
                int tmp = 0;
                for (int j = 0; j < N; j++) {
                    if ((map[j] & i) != 0) tmp++;
                }
                cnt += Math.min(tmp, N - tmp);
            }
            ans = Math.min(ans, cnt);
            return;
        }

        dfs(idx + 1);
        map[idx] = ~map[idx];
        dfs(idx + 1);
    }
}
```

### 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N, ret;
    static int[] vals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ret = N * N;
        vals = new int[N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == 'T') {
                    vals[i] += (int) Math.pow(2, j);
                }
            }
        }

        dfs(0);
        System.out.println(ret);
    }

    static void dfs(int idx) {
        if (idx == N) {
            int cnt = 0;
            for (int i = 1; i < (1 << N); i *= 2) {
                int tmp = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & vals[j]) > 0) tmp++;
                }
                cnt += Math.min(tmp, N - tmp);
            }
            ret = Math.min(ret, cnt);
            return;
        }

        dfs(idx + 1);
        vals[idx] = ~vals[idx];
        dfs(idx + 1);
    }
}
```