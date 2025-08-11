# 숨바꼭질 4

> https://www.acmicpc.net/problem/13913

## 문제 설명

- 문제

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의
위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

- 입력

첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

- 출력

첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.

## 접근 방법

수빈이 동생까지 갈 수 있는 최단 경로를 구하는 문제이므로 BFS 탐색을 통해 N에서 K까지 이동한다.  
"백준 12851 숨바꼭질 2" 문제와 달리 최단 경로 한 케이스만 찾으면 되고, 그 경로를 기록하기 위해 큐에 객체를 담을 때 인덱스와 경로까지 담는다.  
이론상 시간 복잡도는 최대 탐색 범위 O(100,000)이라 통과는 했지만, 실제로는 경로 문자열을 생성하고 붙이는 과정에서 성능 저하 발생.

## 문제 해결 과정

- 첫 번째 시도:
    - N이 K보다 커 쉽게 답을 찾고 종료할 수 있는 초기 조건이나, 다음 노드 방문 조건으로 `vis[nr] == vis[cur.idx] + 1`까지 포함해 시간 초과.
        - 수빈이 동생보다 큰 인덱스에 있는 경우 (-1) 반복 이동만 존재하므로 조기 종료 가능.
        - "백준 12851 숨바꼭질 2" 문제에서는 단순 최단 거리의 가짓수만 체크했기에, 같은 시간 내에 노드 재방문이 가능했다.
            - 하지만 큐에 삽입하는 `Pos` 객체에 `String route`를 연속해서 덧붙여 생성하기 때문에 시간 부하가 커진다.
- 최종 해결 방법:
    - `if (n >= k)`의 조건인 경우 조기 종료 출력.
    - 그렇지 않다면 큐에 현재 인덱스와 지금까지의 노드 경로를 담은 `Pos` 객체를 큐에 삽입하며 BFS 탐색.
        - `cur.idx == k`인 경우 탐색 종료 및 출력.
        - 다음 노드의 방문 가능성을 검증할 때, 최단 경로 한 케이스만 찾으면 되므로 `vis[nr] == 0`를 통해 같은 레벨의 재방문 가짓수를 제거.
            - 최초 방문이 가능하다면, 다음 인덱스와 현재 인덱스의 경로를 더해주며 큐에 삽입. `new Pos(nr, cur.route + " " + nr)`.
- 다른 해결 방법:
    - 기존 정답 도출에는 문제가 없었고 실제 점의 범위도 언급이 없지만, 모든 경우의 수를 생각해서 `vis`의 길이를 10만이 아닌 20만 정도로 잡아준다.
        - 예를 들어 `n = 60000`, `k = 100000`인 경우, 6만에서 12만으로 이동 후 K로 이동하는 것이 더 빠르다.
        - 나중에 극단적인 케이스의 문제를 만날 수도 있으므로 대비.
    - 다른 해결 방법의 핵심은 긴 문자열의 작업과 반복을 막기 위해 경로를 추적할 수 있는 `int[] pre` 배열을 사용하는 것.
        - 현재 String 객체에 문자열을 더했는데, 자바 특성 상 String 객체는 문자열을 더할 때마다 기존 객체 작업이 아닌 새로운 객체를 생성.
        - 또한 수많은 반복으로 길어진 객체 자체가 연산에 부담.
        - `pre` 배열은 해당 인덱스의 값이, 직전 인덱스의 위치를 의미함. 따라서 큐에 다음 노드를 삽입할 때마다 `pre[next] = cur` 갱신.
    - 그리고 BFS 탐색이 끝난 후에, K에서 N까지 역으로 추적하기 위해 `pre[k]`부터 `pre[n]`을 만날 때까지 루프.
        - 이 과정에서 역으로 추적한 경로를 `List<Integer> vals`에 담는다.
        - 루프가 끝나면 남은 인덱스 N을 담고, `Collections.reverse(vals)` 를 통해 다시 순서를 정방향으로 조정.
- 25.8.11. 다시 푼 방법:
    - 이전과 다르게 단 하나의 최단거리와 지난 경로를 구하면 된다.
    - 따라서 `int[] dp`를 시작점을 1로 설정해, 최초 방문하는 곳만 방문할 수 있게 처리하며 `K`를 만날 경우에 종료한다.
        - 이 과정에서 큐에 삽입할 때, `int[] pre`라는 배열을 따로 선언해 다음 인덱스로 이동할 때, 해당 인덱스는 어떤 인덱스에서 왔는지 저장해주는 배열에 값을 저장.
        - 이를 통해 역으로 K에서부터 추적할 수 있다.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n, k, ans;
    static int[] vis, pre;
    static Queue<Integer> q;
    static List<Integer> vals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        k = Integer.parseInt(tok[1]);
        ans = 0;
        vis = new int[200001];
        pre = new int[200001];
        q = new LinkedList<>();
        vals = new ArrayList<>();

        if (n >= k) {
            sb.append(n - k).append("\n");
            for (int i = n; i >= k; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            return;
        }

        vis[n] = 1;
        q.offer(n);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == k) {
                ans = vis[cur] - 1;
                break;
            }

            int[] dr = {1, -1, cur};
            for (int i = 0; i < 3; i++) {
                int next = cur + dr[i];
                if (next < 0 || next >= vis.length || vis[next] != 0) continue;
                vis[next] = vis[cur] + 1;
                pre[next] = cur;
                q.offer(next);
            }
        }

        for (int i = k; i != n; i = pre[i]) {
            vals.add(i);
        }
        vals.add(n);
        Collections.reverse(vals);
        sb.append(vis[k] - 1).append("\n");
        for (int v : vals) sb.append(v).append(" ");
        System.out.println(sb.toString());
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    static int N, K, cnt = 0;
    static int[] dp, pre;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        K = Integer.parseInt(tok[1]);
        dp = new int[100001];
        pre = new int[100001];

        if (N >= K) {
            sb.append(N - K).append("\n");
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }

        dp[N] = 1;
        Queue<Integer> que = new LinkedList<>();
        que.offer(N);
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == K) {
                break;
            }
            int[] d = {1, -1, cur};
            for (int i = 0; i < 3; i++) {
                int next = cur + d[i];
                if (next < 0 || next >= dp.length || dp[next] != 0) continue;
                dp[next] = dp[cur] + 1;
                pre[next] = cur;
                que.offer(next);
            }
        }
        dp[K]--;

        sb.append(dp[K]).append("\n");
        Stack<Integer> stk = new Stack<>();
        stk.push(K);
        int tmp = pre[K];
        for (int i = 0; i < dp[K]; i++) {
            stk.push(tmp);
            tmp = pre[tmp];
        }
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }
}
```