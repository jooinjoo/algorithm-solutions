# 완전 이진 트리

> https://www.acmicpc.net/problem/9934

## 문제 설명

- 문제

상근이는 슬로베니아의 도시 Donji Andrijevci를 여행하고 있다. 이 도시의 도로는 깊이가 K인 완전 이진 트리를 이루고 있다. 깊이가 K인 완전 이진 트리는 총 2K-1개의 노드로 이루어져 있다. (아래
그림) 각 노드에는 그 곳에 위치한 빌딩의 번호가 붙여져 있다. 또, 가장 마지막 레벨을 제외한 모든 집은 왼쪽 자식과 오른쪽 자식을 갖는다.

깊이가 2와 3인 완전 이진 트리

상근이는 도시에 있는 모든 빌딩에 들어갔고, 들어간 순서대로 번호를 종이에 적어 놓았다. 한국으로 돌아온 상근이는 도시가 어떻게 생겼는지 그림을 그려보려고 하였으나, 정확하게 기억이 나지 않아 실패했다. 하지만,
어떤 순서로 도시를 방문했는지 기억해냈다.

가장 처음에 상근이는 트리의 루트에 있는 빌딩 앞에 서있다.
현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동한다.
현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 현재 노드에 있는 빌딩을 들어가고 종이에 번호를 적는다.
현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동한다.
현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동한다.
왼쪽 그림에 나와있는 마을이라면, 상근이는 2-1-3 순서대로 빌딩을 들어갔을 것이고, 오른쪽 그림의 경우에는 1-6-4-3-5-2-7 순서로 들어갔을 것이다. 상근이가 종이에 적은 순서가 모두 주어졌을 때, 각
레벨에 있는 빌딩의 번호를 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 K (1 ≤ K ≤ 10)가 주어진다.

둘째 줄에는 상근이가 방문한 빌딩의 번호가 들어간 순서대로 주어진다. 모든 빌딩의 번호는 중복되지 않으며, 구간 [1,2K)에 포함된다.

- 출력

총 K개의 줄에 걸쳐서 정답을 출력한다. i번째 줄에는 레벨이 i인 빌딩의 번호를 출력한다. 출력은 왼쪽에서부터 오른쪽 순서대로 출력한다.

## 접근 방법

이진 트리에서 중위 순회를 적용해 트리와 노드를 배열의 형태로 출력하는 문제.  
각 노드를 인덱스로 보고, 자식 노드는 `i * 2`, `i * 2 + 1`로 간주하여 트리 구조를 인접 리스트로 설정.  
중위 순회를 통해 방문한 순서대로 각 노드의 정보를 따로 `dp[]` 배열에 저장.

## 문제 해결 과정

- 최종 해결 방법:
    - 트리의 높이 `k`가 주어지면, 노드의 개수는 `2^k - 1`로 정의.
        - 이를 바탕으로 `adj[]` 라는 인접 리스트 배열을 만들어 각 노드의 자식들을 트리 구조에 맞게 연결.
    - DFS 알고리즘을 활용해 중위 순회 방식으로 트리를 탐색.
        - `cur` 노드를 방문하면 왼쪽 자식 노드를 먼저 탐색하고, 그 후에 현재 노드를 처리하여 `dp[]`에 기록한 후 오른쪽 자식을 탐색.
        - 이때, `cnt` 변수를 통해 몇 번째 노드를 방문했는지 순서를 추적.
    - 각 트리 레벨이 끝날 때마다 줄바꿈을 적용하여 출력.
- 다른 해결 방법:
    - 이진 트리를 배열로 표현하고, 트리의 중간 값 `mid`를 기준으로 재귀적으로 트리를 분할해 탐색.
    - 트리의 높이 `k`가 주어지면, 노드의 총 개수는 `n = 2^k - 1`.
        - 이 트리를 배열로 표현하기 위해 배열의 중간 값을 뽑아내어 트리의 각 레벨에 배치.
    - 중간 값을 기준으로 트리를 재귀적으로 분할해 탐색.
        - `s`와 `e`는 배열의 시작과 끝. 중간 값 `mid = (s + e) / 2`는 트리의 현재 레벨에 추가.
        - 중간 값 왼쪽 `(s ~ mid - 1)`과 오른쪽 `(mid + 1 ~ e)`을 각각 재귀적으로 탐색하여 다음 레벨로 내려간다.
        - 재귀가 멈추는 조건은 `s == e`, 즉 구간이 하나의 노드만 남았을 때. 이때 해당 노드를 현재 레벨에 추가.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int k, n;
    static ArrayList<Integer>[] ret;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(br.readLine());
        n = (int) (Math.pow(2, k) - 1);
        ret = new ArrayList[k + 1];
        for (int i = 0; i <= k; i++) {
            ret[i] = new ArrayList<>();
        }
        arr = new int[n];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tok[i]);
        }

        dfs(0, n - 1, 1);

        for (int i = 1; i <= k; i++) {
            for (int j : ret[i]) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int s, int e, int lev) {
        if (s > e) return;
        if (s == e) {
            ret[lev].add(arr[s]);
            return;
        }

        int mid = (s + e) / 2;
        ret[lev].add(arr[mid]);
        dfs(s, mid - 1, lev + 1);
        dfs(mid + 1, e, lev + 1);
    }
}
```