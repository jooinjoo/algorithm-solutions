# 빈도 정렬

> https://www.acmicpc.net/problem/2910

## 문제 설명

- 문제

위대한 해커 창영이는 모든 암호를 깨는 방법을 발견했다. 그 방법은 빈도를 조사하는 것이다.

창영이는 말할 수 없는 방법을 이용해서 현우가 강산이에게 보내는 메시지를 획득했다. 이 메시지는 숫자 N개로 이루어진 수열이고, 숫자는 모두 C보다 작거나 같다. 창영이는 이 숫자를 자주 등장하는 빈도순대로
정렬하려고 한다.

만약, 수열의 두 수 X와 Y가 있을 때, X가 Y보다 수열에서 많이 등장하는 경우에는 X가 Y보다 앞에 있어야 한다. 만약, 등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.

이렇게 정렬하는 방법을 빈도 정렬이라고 한다.

수열이 주어졌을 때, 빈도 정렬을 하는 프로그램을 작성하시오.

- 입력

첫째 줄에 메시지의 길이 N과 C가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ C ≤ 1,000,000,000)

둘째 줄에 메시지 수열이 주어진다.

- 출력

첫째 줄에 입력으로 주어진 수열을 빈도 정렬한 다음 출력한다.

## 접근 방법

수열의 원소를 키, 그리고 등장 횟수와 최초 등장 인덱스를 각각 밸류로 맵에 입력.  
리스트에 키 집합을 넣고 1순위는 등장 횟수, 2순위는 최초 등장 인덱스를 기준으로 정렬.

## 문제 해결 과정

- 첫 번째 시도:
    - 수열의 원소를 리스트에 최초 등장 마다 한번씩 담고, `int[c] cnt` 배열을 통해 해당 원소 카운팅.
    - 이미 등장 순서대로 정렬한 리스트에서, 원소 X의 등장 횟수 `cnt[x]`의 값이 큰 순서로 정렬 시도.
        - `cnt[x]`의 값이 더 클 때만 자리를 바꿔, 같을 때는 이미 등장 순서로 정렬된 상태.
        - 하지만 `c`의 값이 10억 이하의 자연수라 너무 큰 배열이 선언되어, 메모리 초과.
- 최종 해결 방법:
    - 수열에 존재하는 원소만을 카운팅하는 것이 훨씬 효율적이라고 생각.
        - `map`을 통해 <원소, 등장 횟수>를 담고, `mapIdx`를 통해 <원소, 최초 등장 인덱스>를 담음.
    - `list`에 수열의 모든 원소를 한 번씩 입력하기 위해 `map.keySet()` 사용.
        - 리스트를 정렬할 때, 기본적으로 등장 횟수의 내림차순.
            - 만약 횟수가 같은 경우에는 최초 등장 인덱스의 오름차순 정렬.
- 25.7.30. 다시 푼 방법:
    - 첫 시도에서 배열을 선언하여 원소를 카운팅하고, 순서를 넣었더니 메모리 초과.
        - 입력되는 수들이 연속적이지 않고 멀리 떨어질 수 있으니 배열보단 맵이 더 적합.
    - `Map<Integer, Integer> cnt`라는 특정 원소 등장 횟수를 저장하는 맵과 `Map<Integer, Integer> seq`라는 특정 원소 최초 등장 순서를 저장하는 맵 선언.
        - 커스텀 정렬을 통해 원소 출력 순서를 결정.
        - 기본 정렬 순서는 `cnt`의 값이 큰 순서로, 그 다음 정렬 순서는 `seq`의 값이 작은 순서로 정렬.
    - 입력되는 수의 크기나 범위에 따라 자료 구조를 잘 선택해보자.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    static int N, C;
    static HashMap<Integer, Integer> cnt;
    static HashMap<Integer, Integer> seq;
    static ArrayList<Integer> vals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        cnt = new HashMap<>();
        seq = new HashMap<>();
        vals = new ArrayList<>();
        tok = br.readLine().split(" ");
        int idx = 1;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(tok[i]);

            cnt.put(cur, cnt.getOrDefault(cur, 0) + 1);
            if (!seq.containsKey(cur)) {
                seq.put(cur, idx++);
            }
            vals.add(cur);
        }

        vals.sort((o1, o2) -> {
            if (cnt.get(o1) == cnt.get(o2)) {
                return seq.get(o1) - seq.get(o2);
            }
            return cnt.get(o2) - cnt.get(o1);
        });

        for (int i : vals) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
```