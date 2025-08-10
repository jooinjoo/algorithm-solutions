# 효율적인 해킹

> https://www.acmicpc.net/problem/1325

## 문제 설명

- 문제

해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다. 김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.

이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.

이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.

- 입력

첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다. 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를
신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.

- 출력

첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.

## 접근 방법

컴퓨터 간의 관계를 노드로 보고, 인접리스트에 트리 형태로 연결.  
BFS를 활용하여 시작 노드에서부터 가능한 모든 경로를 탐색하며 카운팅.  
카운팅한 값이 가장 많은 노드를 오름차순 출력.  
시간복잡도 O(N * M), 약 10억으로 간신히 통과.

## 문제 해결 과정

- 첫 번째 시도:
    - DFS를 활용해 시작 노드부터 가능한 모든 경로 탐색하여 카운팅했으나 시간 초과.
        - 방문 처리까지 하면서 통과한 코드와 동일한 논리를 수행했으나, 테스트 케이스가 너무나도 빡빡한 관계로 시간 초과한 듯하다.
- 최종 해결 방법:
    - `ArrayList<Integer>[] adj`에 노드간의 관계를 입력.
        - 컴퓨터 A가 B를 신뢰한다는 것은, B가 A의 부모 노드인 것을 의미. 따라서 `adj[b].add(a)`로 단방향 연결.
    - 각 노드별로 시작하는 BFS를 수행하며, 해당 노드부터 해킹할 수 있는 모든 컴퓨터를 카운팅. O(N) 반복.
        - 큐에 방문 처리된 노드를 꺼냈을 때, 연결된 자식 노드가 있다면 자식 노드 큐에 삽입 반복. 이 과정에서 `ret++` 카운팅.
        - BFS는 O(V + E) 시간복잡도를 지니므로, 크기가 더 큰 M이 10만이라 O(M).
    - 모든 노드 탐색 이후, 노드 별로 `int[] dp`에 저장된 해당 노드와 자식 노드들, 즉 해킹 가능한 모든 컴퓨터의 수가 제일 많은 인덱스만 출력.
    - 한편 제출 시간이 9576ms, 거의 10초에 다다를만큼 테스트 케이스가 빡빡해서, 동일 논리지만 DFS는 실패하고 BFS는 성공.
        - 만약 논리는 맞는 것 같은데, 시간 초과가 뜬다면 한번쯤 DFS - BFS 전환을 시도해보자.
- 25.8.10 다시 푼 방법:
    - 각 노드의 해킹가능한 수를 저장하는 `int[] cnt`를 01로 초기화.
    - 1~N까지 노드마다 BFS 탐색을 통해 해킹가능한 노드 수를 `cnt`에 저장.
        - 이 과정에서 최종적으로 저장되는 값을 `int max`와 비교하며 최댓값 갱신.
    - 1~N까지 루프하며 `max`와 같은 값을 가진 `cnt[i]`가 나오면 `i`를 출력.
    - `boolean[] vis`와 같이 각 노드의 탐색마다 방문처리를 새로 해주는 것이 필요하다.
        - 예를 들어, 순환 구조인 사이클이 주어진다면 단순 dp를 통해서는 이미 방문한 노드를 다시 방문하는 것이 가능하다.
        - 방문처리가 필요한 경우 귀찮더라도 따로 방문처리 배열을 선언하는 것이 실수를 줄일 수 있는 것 같다.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int N, M, max = -1;
    static ArrayList<Integer>[] adj;
    static int[] cnt;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        M = Integer.parseInt(tok[1]);

        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            tok = br.readLine().split(" ");
            int A = Integer.parseInt(tok[0]);
            int B = Integer.parseInt(tok[1]);
            adj[B].add(A);
        }

        cnt = new int[N + 1];
        Arrays.fill(cnt, -1);
        for (int i = 1; i < N + 1; i++) {
            vis = new boolean[N + 1];
            cnt[i] = bfs(i);
        }

        for (int i = 1; i < N + 1; i++) {
            if (cnt[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }

    static int bfs(int idx) {
        int ret = 1;
        Queue<Integer> que = new LinkedList<>();
        que.offer(idx);
        vis[idx] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int next : adj[cur]) {
                if (vis[next]) continue;
                que.offer(next);
                vis[next] = true;
                ret++;
            }
        }

        max = Math.max(max, ret);
        return cnt[idx] = ret;
    }
}
```