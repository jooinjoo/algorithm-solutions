# 다이어트

> https://www.acmicpc.net/problem/19942

## 문제 설명

- 문제

식재료 N개 중에서 몇 개를 선택해서 이들의 영양분(단백질, 탄수화물, 지방, 비타민)이 일정 이상이 되어야 한다. 아래 표에 제시된 6가지의 식재료 중에서 몇 개를 선택해서 이들의 영양분의 각각 합이 최소 100,
70, 90, 10가 되도록 하는 경우를 생각해보자. 이 경우 모든 재료를 선택하면 쉽게 해결되지만, 우리는 조건을 만족시키면서도 비용이 최소가 되는 선택을 하려고 한다.

재료 단백질 지방 탄수화물 비타민 가격  
1 30 55 10 8 100  
2 60 10 10 2 70  
3 10 80 50 0 50  
4 40 30 30 8 60  
5 60 10 70 2 120  
6 20 70 50 4 40  
예를 들어, 식재료 1, 3, 5를 선택하면 영양분은 100, 145, 130, 10으로 조건을 만족하지만 가격은 270이 된다. 대신 2, 3, 4를 선택하면 영양분의 합은 110, 130, 90, 10, 비용은
180이 되므로, 앞의 방법보다는 더 나은 선택이 된다.

입력으로 식재료 표가 주어졌을 때, 최저 영양소 기준을 만족하는 최소 비용의 식재료 집합을 찾아야 한다.

- 입력

첫 줄에 식재료의 개수
$N$이 주어진다.

다음 줄에는 단백질, 지방, 탄수화물, 비타민의 최소 영양성분을 나타내는 정수
$mp$,
$mf$,
$ms$,
$mv$가 주어진다.

이어지는
$N$개의 각 줄에는
$i$번째 식재료의 단백질, 지방, 탄수화물, 비타민과 가격이 5개의 정수
$p_i$,
$f_i$,
$s_i$,
$v_i$,
$c_i$와 같이 주어진다. 식재료의 번호는 1부터 시작한다.

- 출력

첫 번째 줄에 최소 비용을 출력하고, 두 번째 줄에 조건을 만족하는 최소 비용 식재료의 번호를 공백으로 구분해 오름차순으로 한 줄에 출력한다. 같은 비용의 집합이 하나 이상이면 사전 순으로 가장 빠른 것을 출력한다.

조건을 만족하는 답이 없다면 -1을 출력하고, 둘째 줄에 아무것도 출력하지 않는다.

## 접근 방법

해당 식재료를 선택하거나 선택하지 않거나, 두 가지로 나뉘어 모든 식재료에 대한 조합을 완전 탐색.  
식재료 개수가 15로 최대일 때, 경우의 수는 2^15이므로 충분히 시간 복잡도는 통과.  
최소 비용의 집합이 하나 이상인 경우 최종적으로 사전 순으로 정렬하여 가장 앞의 값을 리턴.

## 문제 해결 과정

- 첫 번째 시도:
    - 예외 케이스: 모든 비용이 0일 때, 예를 들어 (1 2 3)이 아닌 (1)을 반환해야 하는데, 이 케이스를 고려하지 못해 99%에서 실패.
- 최종 해결 방법:
    - `dfs()` 메서드를 통해 식재료 조합을 선택하며 재귀.
        - 현재 `idx`의 식재료를 선택하거나, 선택하지 않으면서 원상 복귀까지 고려. 일종의 방문 처리.
        - 이 과정에서 `tmp`에 임시 조합을 담으면서 `idx`가 모든 식재료를 지나면 최소 영양성분을 채우면서 최소 비용 이하인지 검증.
            - 만약 최소 비용보다 작으면 기존의 조합 `ret`를 초기화하고 새로 갱신.
            - 최소 비용과 같으면 현재 조합을 기존 조합에 추가.
    - 최소 비용이 같은 경우의 모든 케이스를 리스트에 담아 오름차순 정렬.(사전 순) 그리고 출력.
- 다른 해결 방법:
    - 비트마스킹을 통해 식재료의 조합을 구할 수 있도록 시도하였다.
        - 해당 식재료를 선택하거나, 선택하지 않거나 두 가지 경우의 수인 경우 비트마스킹이 방문 처리에 훨씬 유리한 것 같다.
        - 유의할 점으로는 현재 조합 상태인 `int cur`이 10진수로 표현된 값이므로 `Integer.toBinaryString(cur)`을 통해 변환하여 방문처리를 확인하는 것이 중요.
- 25.9.14. 다시 푼 방법:
    - 비트마스킹을 통해 조합할 수 있는 모든 조합을 탐색한다.
        - 각 조합마다 최소 식재료를 만족하는지, 검사한 뒤 만족하면 총 식재료가 이전까지 최소 식재료보다 적은지 검사.
        - 같으면 새로운 조합에 추가, 더 적으면 `sum`을 최솟값으로 갱신하며 조합에 추가.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    static int N, cost, ans;
    static int[] arr, tmp;
    static int[][] vals;
    static List<String> ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = -1;
        ans = Integer.MAX_VALUE;
        arr = new int[4];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(tok[i]);
        }
        vals = new int[N][5];
        for (int i = 0; i < N; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                vals[i][j] = Integer.parseInt(tok[j]);
            }
        }
        ret = new ArrayList<>();

        for (int cur = 0; cur < (1 << N); cur++) {
            cost = 0;
            tmp = new int[4];
            for (int j = 0; j < N; j++) {
                if ((cur & (1 << j)) != 0) {
                    calc(j);
                }
            }
            if (isValid() && ans >= cost) {
                if (ans > cost) ret = new ArrayList<>();
                ans = cost;
                StringBuilder sb = new StringBuilder();
                String bit = new StringBuilder(Integer.toBinaryString(cur)).reverse().toString();
                for (int j = 0; j < bit.length(); j++) {
                    if (bit.charAt(j) == '1') sb.append(j + 1).append(" ");
                }
                ret.add(sb.toString());
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            Collections.sort(ret);
            System.out.println(ans + "\n" + ret.get(0));
        }
    }

    static void calc(int idx) {
        for (int i = 0; i < 4; i++) {
            tmp[i] += vals[idx][i];
        }
        cost += vals[idx][4];
    }

    static boolean isValid() {
        for (int i = 0; i < 4; i++) {
            if (arr[i] > tmp[i]) return false;
        }
        return true;
    }
}
```

### 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    static int N, sum = Integer.MAX_VALUE;
    static int[] goal, tmp;
    static int[][] vals;
    static ArrayList<String> ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        goal = new int[4];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            goal[i] = Integer.parseInt(tok[i]);
        }
        vals = new int[N][5];
        for (int i = 0; i < N; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                vals[i][j] = Integer.parseInt(tok[j]);
            }
        }
        ret = new ArrayList<>();

        // 각 모든 경우의 수 조합 구한 뒤
        for (int i = 1; i < (1 << N); i++) {
            tmp = new int[5];
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    for (int k = 0; k < tmp.length; k++) {
                        tmp[k] += vals[j][k];
                    }
                }
            }

            if (check() && tmp[4] <= sum) {
                if (tmp[4] < sum) ret = new ArrayList<>();
                sum = tmp[4];
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) > 0) {
                        s.append(j + 1).append(" ");
                    }
                }
                ret.add(s.toString());
            }
        }

        Collections.sort(ret);

        if (sum == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            sb.append(sum).append("\n").append(ret.get(0));
            System.out.println(sb.toString());
        }
    }

    static boolean check() {
        for (int i = 0; i < 4; i++) {
            if (tmp[i] < goal[i]) return false;
        }
        return true;
    }
}
```