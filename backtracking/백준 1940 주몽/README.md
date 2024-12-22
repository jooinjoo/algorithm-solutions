# 주몽

> https://www.acmicpc.net/problem/1940

## 문제 설명

- 문제

주몽은 철기군을 양성하기 위한 프로젝트에 나섰다. 그래서 야철대장을 통해 철기군이 입을 갑옷을 만들게 하였다. 야철대장은 주몽의 명에 따르기 위하여 연구에 착수하던 중 아래와 같은 사실을 발견하게 되었다.

갑옷을 만드는 재료들은 각각 고유한 번호를 가지고 있다. 갑옷은 두 개의 재료로 만드는데 두 재료의 고유한 번호를 합쳐서 M(1 ≤ M ≤ 10,000,000)이 되면 갑옷이 만들어 지게 된다. 야철대장은 자신이
만들고 있는 재료를 가지고 갑옷을 몇 개나 만들 수 있는지 궁금해졌다. 이러한 궁금증을 풀어 주기 위하여 N(1 ≤ N ≤ 15,000) 개의 재료와 M이 주어졌을 때 몇 개의 갑옷을 만들 수 있는지를 구하는
프로그램을 작성하시오.

- 입력

첫째 줄에는 재료의 개수 N(1 ≤ N ≤ 15,000)이 주어진다. 그리고 두 번째 줄에는 갑옷을 만드는데 필요한 수 M(1 ≤ M ≤ 10,000,000) 주어진다. 그리고 마지막으로 셋째 줄에는 N개의
재료들이 가진 고유한 번호들이 공백을 사이에 두고 주어진다. 고유한 번호는 100,000보다 작거나 같은 자연수이다.

- 출력

첫째 줄에 갑옷을 만들 수 있는 개수를 출력한다.

## 접근 방법

모든 재료를 Set에 삽입.  
Set에서 하나씩 뽑아, 더했을 때 M을 만들 수 있는 재료가 Set에 있는지 탐색.

## 문제 해결 과정

- 첫 번째 시도:
    - 주어진 재료에서 2개씩 뽑는 모든 조합을 통해, M을 만들 수 있는 경우 카운팅하는 것을 목표.
        - `comb(int idx, ArrayList<Integer> list)`의 조합 메서드를 구현했다. 현재 `idx`부터 리스트에 인덱스를 삽입한 뒤, 1씩 증가하며 가능한 모든 조합 탐색.
        - 하지만 재귀 호출의 깊이와 수많은 리스트 객체의 크기 변동이 메모리 사용량에 영향을 미쳐, 너무 커진 리스트 데이터 양으로 메모리 초과.
- 최종 해결 방법:
    - 2개만 뽑으면 되기 때문에, 모든 재료를 집합에 넣고 한 재료를 뽑을 때마다 뽑은 재료에 더해 M을 만들 수 있는 재료가 있는지 탐색.
        - 해당 값이 있는지 없는지만 판단하는 `contains()`는 O(1)로 시간복잡도에도 유리.
        - 다만 모든 조합을 재료를 한번씩 탐색하므로, 가능한 조합은 2번씩 중복되어 마지막에 2로 나눈 값을 출력하는 것에 유의.
- 다른 해결 방법:
    - 2개 조합이기 때문에, 재귀 메서드보단 2중 for문을 사용.
        - 조합의 총 개수가 3개까지는 쉽게 다중 for문을 사용하는 것을 생각.
        - 또한 시간복잡도의 단축을 위해, 가능한 종료 조건도 적어주면 좋다.
- 24.12.22. 다시 푼 방법:
  - 이전 최종 해결 방법과 동일하게, 각 재료마다 루프하며 Map을 활용해 가능한 조합이 있는지 확인하여 풀이.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int cnt = 0;
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (m > 200000) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == m) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
```

## 다시 푼 코드

```java
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        for (int i : set) {
            if (set.contains(m - i)) cnt++;
        }
        System.out.println(cnt / 2);
    }
}
```