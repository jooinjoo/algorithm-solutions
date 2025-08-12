# 백조의 호수

> https://www.acmicpc.net/problem/3197

## 문제 설명

- 문제

두 마리의 백조가 호수에서 살고 있었다. 그렇지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.

호수는 행이 R개, 열이 C개인 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.

호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다. 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.

아래에는 세 가지 예가 있다.

...XXXXXX..XX.XXX ....XXXX.......XX .....XX..........  
....XXXXXXXXX.XXX .....XXXX..X..... ......X..........  
...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X.....  
..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX....  
.XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX....  
XXXXXXX...XXXX... ..XXXX.....XX.... ....X............  
..XXXXX...XXX.... ....XX.....X..... .................  
....XXXXX.XXX.... .....XX....X..... .................  
처음 첫째 날 둘째 날  
백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.

며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성하시오.

- 입력

입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.

다음 R개의 줄에는 각각 길이 C의 문자열이 하나씩 주어진다. '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.

- 출력

첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.

## 접근 방법

두 백조가 현재 상태의 지도에서 만날 수 있는지를 체크하는 과정이 먼저 필요하다.  
만약 만날 수 없는 상태라면, 최종적으로 X를 만나는 좌표를 다음 백조 출발 위치라고 생각할 수 있다.   
왜냐하면 백조가 만나지 못해 종료되지 않으면, 물과 접해있는 호수가 녹아 해당 좌표들이 다음 시간에서 백조가 방문하지 않은 좌표이기 때문.  
따라서 BFS 탐색과 백조와 물 각각 두 개의 큐를 사용한 Flood Fill 알고리즘을 활용해 만날 수 있는 최단 시간에 탐색 종료.

## 문제 해결 과정

- 첫 번째 시도:
    - 매 시간마다 한 백조에서 다음 백조까지 갈 수 있는지 전체 맵을 BFS 탐색했으나 시간 초과.
        - 전체 지도가 1500^2 범위에, 약 100번 정도 레벨이 걸린다고 하면 2억 이상의 경우의 수라 성능 저하.
- 최종 해결 방법:
    - 물의 방문 처리 `visWater`와 백조의 방문 처리 `visSwan`을 따로 선언.
    - 또한 노드를 삽입하는 큐도 각각 2개씩 사용하며, Flood Fill 알고리즘을 활용하였다.
        - 물의 경우 `wq`에 해당 시간에서 `'.'`과 같이 이동 가능한 모든 지역을 삽입하고, `'X'`는 만나면 해당 지역에서 더이상 갈 수 없지만 녹는 지역이다.
            - 따라서 `wqTmp`에 만난 지역 중 `'X'` 값을 가지는 지역을 모두 삽입한 뒤, 다음 시간의 물이 해당 지역들을 탐색하도록 `wq = wqTmp` 초기화.
        - 백조의 경우도 위와 동일하다. 동서남북으로 방문하지 않은 지역을 갈 수 있으면 `sq`에 삽입하며 얼음을 만나면 다음 큐 `sqTmp`에 삽입.
            - 이 과정에서 다른 백조를 만나면 종료.
- 25.8.12. 다시 푼 방법:
    - 각 시간마다 "1) 백조가 만날 수 있는지 체크"하고 "2) 만날 수 없다면 물 이동하여 얼음 녹이기"가 진행된다.
    - 따라서 백조와 물 각각 큐를 두 개씩 사용하여, 현재 이동할 수 있는 영역에 사용되는 큐와 다음에 사용될 큐가 필요하다.
        - 백조의 경우 `move()` 메서드를 통해, 현재 백조 위치에서 이동할 수 있는 모든 영역을 이동하는 `sq`에서 탐색을 진행.
            - 중간에 다른 백조를 만나면 종료하며, 벽을 만나면 `stmp`에 임시로 저장해 다음 시간에 사용.
        - 물의 경우 `water()` 메서드를 통해, 현재 물 원소들의 위치에서 이동할 수 있는 모든 영역을 탐색
            - 이 과정에서 벽을 만나면 해당 영역들을 물로 바꿔주며 다음 큐인 `wtmp`에 저장하여 넣는다.
    - 굉장히 어려웠다. 시간이 빡빡할 땐 여러 큐를 복합적으로 사용하는 것을 생각해야 한다.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int R, C, x, y;
    static char[][] board;
    static Queue<Pos> sq, stmp, wq, wtmp;
    static boolean[][] svis, wvis;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        R = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        board = new char[R][C];
        sq = new LinkedList<>();
        wq = new LinkedList<>();
        svis = new boolean[R][C];
        wvis = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'L') {
                    x = i;
                    y = j;
                }
                if (board[i][j] == '.' || board[i][j] == 'L') {
                    wq.offer(new Pos(i, j));
                    wvis[i][j] = true;
                }
            }
        }

        svis[x][y] = true;
        sq.offer(new Pos(x, y));

        int ans = 0;
        while (true) {
            if (move()) break;
            water();
            ans++;
        }

        System.out.println(ans);
    }

    static void water() {
        wtmp = new LinkedList<>();

        while (!wq.isEmpty()) {
            Pos cur = wq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr == R || nc == C || wvis[nr][nc]) continue;
                if (board[nr][nc] == 'X') {
                    wvis[nr][nc] = true;
                    board[nr][nc] = '.';
                    wtmp.add(new Pos(nr, nc));
                }
            }
        }
        wq = wtmp;
    }

    static boolean move() {
        stmp = new LinkedList<>();

        while (!sq.isEmpty()) {
            Pos cur = sq.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr == R || nc == C || svis[nr][nc]) continue;
                svis[nr][nc] = true;
                if (board[nr][nc] == '.') {
                    sq.add(new Pos(nr, nc));
                } else if (board[nr][nc] == 'X') {
                    stmp.add(new Pos(nr, nc));
                } else if (board[nr][nc] == 'L') return true;
            }
        }
        sq = stmp;
        return false;
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