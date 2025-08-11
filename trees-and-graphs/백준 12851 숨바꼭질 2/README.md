# 숨바꼭질 2

> https://www.acmicpc.net/problem/12851

## 문제 설명

- 문제

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의
위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고, 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.

- 입력

첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

- 출력

첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.

## 접근 방법

동생이 있는 위치까지 가는 최단 경로 문제이므로 BFS 탐색을 사용한다.  
수빈의 현재 위치에서 움직일 수 있는 세 좌표 각각에 대해 범위 내에 있는지, 그리고 방문한 적 있는지 처리하며 큐에 위치를 삽입.   
한편 동생까지 최단 경로가 여러 경우일 수 있기 때문에, 한번 방문한 적 있어도 같은 시간이 걸려서 도착했다면 큐에 삽입하는 것이 핵심.   
최대 10만 개의 인덱스를 탐색하므로 O(100,000)의 시간 복잡도.

## 문제 해결 과정

- 최종 해결 방법:
    - 1차원 배열 `int[] vis`에 수빈의 위치를 설정. 방문 처리를 위해 편의상 1 삽입.
        - 그 이전에 다음 노드에 대해 처음 방문하거나 같은 시간이 걸려서 방문하도록 만들기 위해, `Arrays.fill(vis, Integer.MAX_VALUE)`로 초기화.
            - 이후 다음 노드 탐색에서 `vis[cur] + 1 <= vis[nr]` 조건을 사용하기 위해 최대한 큰 수로 저장되어 있어야 한다.
    - 수빈과 동생의 위치가 최초로 같아지는 순간 현재 레벨이 최소 시간이므로, 더이상 다른 노드에서 큐에 다음 노드를 삽입하지 않도록 `flag = true` 설정.
        - 이미 해당 레벨에서 갈 수 있는 모든 노드는 큐에 삽입된 상태이기 때문.
    - 한편 반례에 대해 생각하지 않았는데 운 좋게 풀렸다. 예를 들어 수빈의 위치가 동생과 같거나 더 큰 위치에 해당하고 있을 때.
        - 같은 경우 `0 + "\n" + 1`을 출력해야 하고, 더 큰 경우 뒤로 1칸씩 이동하는 한가지 경우만 존재하므로 `n - k + "\n" + 1` 출력.
- 25.8.11. 다시 푼 방법:
    - 수빈은 +1, -1, *2 만큼 한번에 이동할 수 있는데 수빈이 동생과 같거나 큰 수에 있을 경우 뒤로가는 경우의 수 하나밖에 없어서 최초 차이만큼 뺀 것을 출력해주면 된다.
    - 한편 동생이 수빈보다 큰 수에 있을 경우, 세 가지 경우에 따라 전부 BFS 탐색을 통해 최대한 빠르게 만나는 방법을 찾아야 한다.
        - 수빈이 해당 인덱스에 도달하기까지의 거리를 `dp`에 최댓값으로 초기화한 뒤, 시작점을 `0`으로 두고 하나씩 증가시키며 동생 위치와 비교.
        - 한편 동생위치와 같은 위치가 큐에서 최초로 poll 되면, BFS 방식으로 접근했기 때문에 현재 큐에 넣어있는 원소들만 최솟값이 가능하다.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, k, ans, cnt;
    static int[] vis;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        k = Integer.parseInt(tok[1]);
        ans = 0;
        cnt = 0;
        vis = new int[100001];
        q = new LinkedList<>();

        if (n >= k) {
            System.out.println(n - k + "\n" + 1);
            return;
        }

        Arrays.fill(vis, Integer.MAX_VALUE);
        boolean flag = false;
        vis[n] = 1;
        q.offer(n);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == k) {
                flag = true;
                ans = vis[cur] - 1;
                cnt++;
            } else if (!flag) {
                int nr = cur + 1;
                if (nr >= 0 && nr <= 100000 && vis[cur] + 1 <= vis[nr]) {
                    vis[nr] = vis[cur] + 1;
                    q.offer(nr);
                }
                nr = cur - 1;
                if (nr >= 0 && nr <= 100000 && vis[cur] + 1 <= vis[nr]) {
                    vis[nr] = vis[cur] + 1;
                    q.offer(nr);
                }
                nr = cur * 2;
                if (nr >= 0 && nr <= 100000 && vis[cur] + 1 <= vis[nr]) {
                    vis[nr] = vis[cur] + 1;
                    q.offer(nr);
                }
            }
        }
        System.out.println(ans + "\n" + cnt);
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int N, K, cnt = 0;
    static int[] dp, d = {1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        K = Integer.parseInt(tok[1]);
        dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;

        if (N >= K) {
            System.out.println(N - K);
            System.out.println(1);
            System.exit(0);
        }

        Queue<Integer> que = new LinkedList<>();
        que.offer(N);
        boolean flag = false;
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == K) {
                flag = true;
                cnt++;
            }
            if (!flag) {
                for (int i = 0; i < 3; i++) {
                    int next = cur + d[i];
                    if (i == 2) next *= 2;
                    if (next < 0 || next >= dp.length) continue;
                    if (dp[cur] + 1 <= dp[next]) {
                        dp[next] = dp[cur] + 1;
                        que.offer(next);
                    }
                }
            }
        }

        System.out.println(dp[K]);
        System.out.println(cnt);
    }
}
```