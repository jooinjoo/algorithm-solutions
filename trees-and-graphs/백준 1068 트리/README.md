# 트리

> https://www.acmicpc.net/problem/1068

## 문제 설명

- 문제

트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.

트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.

예를 들어, 다음과 같은 트리가 있다고 하자.

현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다. 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.

이제 리프 노드의 개수는 1개이다.

- 입력

첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째
줄에는 지울 노드의 번호가 주어진다.

- 출력

첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

## 접근 방법

노드 간의 연결 정보를 배열에 입력받아, 루트 노드부터 DFS 탐색.     
탐색 과정에서 제거한 노드는 방문하지 않고, 다음 노드에 연결된 노드가 없을 때 종료 및 카운팅.  
제거 노드가 루트 노드라면 바로 출력.  
N은 50 이하이며, 시간복잡도는 O(N).

## 문제 해결 과정

- 첫 번째 시도:
    - 노드 간의 연결 정보를 `boolean[][] map`에 입력받아, 제거 노드를 `map`에서 갱신한뒤 노드 0부터 탐색 시작.
        - `i`의 자식 노드가 `j` 노드라는 것을 `map[i][j] = true`로 표현.
            - 제거 노드를 처리하는 방법으로는 각각의 노드 번호마다 제거 노드 `del`과 연결되었다면 전부 `false` 처리.
        - 한편 루트 노드가 다를 수 있다는 사실을 망각하고, 항상 노드 0에서 시작해서 실패.
        - 또한 삭제 노드가 0 또는 루트 노드일 수도 있다는 생각도 하지 못했다.
- 최종 해결 방법:
    - 노드 간의 연결 정보를 보다 직관적으로 표현하기 위해, `ArrayList<Integer>[] adj`에 입력받아, 자식 노드를 리스트에 추가. 이 때 O(N).
    - 입력 과정에서 부모 노드 `p == -1`인 경우 `root`를 따로 설정.
        - 나머지는 현재 노드 `i`를 부모 노드의 자식 노드로 추가.
    - 제거 노드 `del == 0`일 경우 바로 0을 출력.
        - 그렇지 않으면 `dfs()` 메서드를 통해 리프 노드까지 탐색. 둘 씩 나뉘는 트리이므로 O(logN).
            - 연결된 자식 노드가 `del`과 같으면 통과.
            - 그렇지 않으면 더 깊이 탐색하며 최종적으로 다음 노드 개수 `cnt`가 없을 때 `return 1`
    - 종료 조건이나 예외 케이스를 좀 더 세심히 살펴야겠다.
- 25.8.9. 다시 푼 방법:
    - 노드 간의 연결 정보를 `ArrayList<ArrayList<Integer>> adj`에 입력하여, 특정 노드의 자식노드를 리스트에 추가하는 방식으로 구현하였다.
    - 노드의 부모 정보를 입력할 때, 루트 노드까지 찾는다.(0번 인덱스가 루트가 아닐 확률 존재)
        - 삭제하는 노드, `target` 인덱스를 모든 노드를 루프하여 자식 노드로 `target`을 가지고 있다면 삭제.
    - 모든 노드와 삭제하는 노드까지 입력한 후 루트 노드부터 DFS 탐색을 통해 다음 노드로 진행하며, 특정 노드의 자식 노드가 없다면 `cnt++`.
        - 한편 루트 노드와 제거 노드가 같은 경우 탐색할 트리 자체가 없어지므로 바로 `0` 출력.
    - 이전에 푼 방법에 비해 시간복잡도가 살짝 증가. 그 이유는 모든 노드를 직접 탐색하여, `target` 노드를 자식 노드로 갖고 있는지 검사했기 때문.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int N, root = -1, cnt = 0, target = -1;
    static ArrayList<ArrayList<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(tok[i]);
            if (parent == -1) {
                root = i;
                continue;
            }
            adj.get(parent).add(i);
        }

        target = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i == target) continue;
            ArrayList<Integer> cur = adj.get(i);
            for (int j = 0; j < cur.size(); j++) {
                if (cur.get(j) == target) cur.remove(j);
            }
        }

        if (root == target) {
            System.out.println(0);
            System.exit(0);
        }

        dfs(root);
        System.out.println(cnt);
    }

    static void dfs(int idx) {
        ArrayList<Integer> cur = adj.get(idx);
        if (cur.isEmpty()) {
            cnt++;
            return;
        }

        for (int next : cur) {
            dfs(next);
        }
    }
}
```