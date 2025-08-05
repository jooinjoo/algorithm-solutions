# 비밀번호 발음하기

> https://www.acmicpc.net/problem/4659

## 문제 설명

- 문제

좋은 패스워드를 만드는것은 어려운 일이다. 대부분의 사용자들은 buddy처럼 발음하기 좋고 기억하기 쉬운 패스워드를 원하나, 이런 패스워드들은 보안의 문제가 발생한다. 어떤 사이트들은 xvtpzyo 같은 비밀번호를
무작위로 부여해 주기도 하지만, 사용자들은 이를 외우는데 어려움을 느끼고 심지어는 포스트잇에 적어 컴퓨터에 붙여놓는다. 가장 이상적인 해결법은 '발음이 가능한' 패스워드를 만드는 것으로 적당히 외우기 쉬우면서도
안전하게 계정을 지킬 수 있다.

회사 FnordCom은 그런 패스워드 생성기를 만들려고 계획중이다. 당신은 그 회사 품질 관리 부서의 직원으로 생성기를 테스트해보고 생성되는 패스워드의 품질을 평가하여야 한다. 높은 품질을 가진 비밀번호의 조건은
다음과 같다.

모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
이 규칙은 완벽하지 않다;우리에게 친숙하거나 발음이 쉬운 단어 중에서도 품질이 낮게 평가되는 경우가 많이 있다.

- 입력

입력은 여러개의 테스트 케이스로 이루어져 있다.

각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 테스트할 패스워드가 주어진다.

마지막 테스트 케이스는 end이며, 패스워드는 한글자 이상 20글자 이하의 문자열이다. 또한 패스워드는 대문자를 포함하지 않는다.

- 출력

각 테스트 케이스를 '예제 출력'의 형태에 기반하여 품질을 평가하여라.

## 접근 방법

각 테스트마다 받은 문자열을 조작하여, 3가지 조건에 모두 부합하는지 확인.  
단 하나라도 모음을 포함하고 있으면, 1번째 조건 통과.  
문자열의 처음부터 끝까지, 3개씩 묶었을 때 모두 같은 경우가 없으면, 2번째 조건 통과.  
문자열의 처음부터 끝까지, 2개씩 묶었을 때 서로 같지 않거나, "ee", "oo"인 경우 3번째 조건 통과.  
입력의 수를 N, 문자열의 길이는 최대 20이므로, 시간 복잡도 O(N).

## 문제 해결 과정

- 최종 해결 방법:
    - 알파벳 26개를 `boolean[] alpha`에 담아 모음만 `true` 설정.
    - 각 입력 문자열에 대해 조건에 부합하는지 판단하는 메서드 `isValid()` 실행.
        - `indexOf()`를 활용해 모음 알파벳이 해당 문자열에 있는지 판단.
        - 문자열을 3개씩 끊어 루프하며, 모두 같은 자음 또는 모음 종류인지 `alpha[idx]`의 값으로 판단.
        - 문자열을 2개씩 끊어 루프하며, 서로 같을 때 "ee", "oo"인지 판단.
    - 이후 `isValid()`의 리턴 값에 따라 출력.
- 다른 해결 방법:
    - 입력 문자열을 하나의 인덱스마다 루프하며, 3가지 조건을 갱신하며 진행.
    - 1번째 조건 `chk1`은 한번만 만족되면 통과이기 때문에 `false`, 2번째와 3번째 조건 `chk2`, `chk3`은 한번만 만족되지 못해도 실패이기 때문에 `true` 초기화.
        - 1번째 조건은 현재 `c`가 모음인지 판단.
        - 2번째 조건은 연속 모음 개수 `vCnt`와 연속 자음 개수`cCnt`를 갱신한다.
            - 만약 둘 중 하나라도 연속으로 `3`이 되는 순간 실패.
        - 3번째 조건은 직전 알파벳 `pre`와 비교하며 판단. 통과했다면 `pre` 갱신.
    - 이후 모든 조건이 `true`일 때만 좋은 패스워드.
- 25.8.5. 다시 푼 방법:
    - 입력 문자열의 알파벳을 하나씩 루프하며 현재 알파벳 `cur`이 모음/자음 인지, 연속된 모음/자음 인지, 직전 알파벳과 같은지를 검사한다.
    - 루프가 끝나면 각 세 가지 조건이 모두 참일 때만 좋은 패스워드, 그렇지 않으면 나쁜 패스워드를 출력.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static String cur;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        cur = "";
        while (!(cur = br.readLine()).equals("end")) {
            boolean chk1, chk2, chk3;
            chk1 = false;
            chk2 = chk3 = true;
            char pre = 0;
            int vCnt = 0, cCnt = 0;

            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    chk1 = true;
                    vCnt++;
                    cCnt = 0;
                } else {
                    cCnt++;
                    vCnt = 0;
                }
                if (vCnt == 3 || cCnt == 3) chk2 = false;
                if (c == pre && c != 'e' && c != 'o') chk3 = false;
                pre = c;
            }

            if (chk1 && chk2 && chk3) {
                sb.append("<").append(cur).append("> is acceptable.\n");
            } else {
                sb.append("<").append(cur).append("> is not acceptable.\n");
            }
        }
        System.out.println(sb);
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = "";
        while (!(input = br.readLine()).equals("end")) {
            boolean chk1, chk2, chk3;
            chk1 = false;
            chk2 = chk3 = true;
            int vCnt = 0, cCnt = 0;
            char pre = 0;

            for (char cur : input.toCharArray()) {
                if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
                    chk1 = true;
                    vCnt++;
                    cCnt = 0;
                } else {
                    cCnt++;
                    vCnt = 0;
                }

                if (vCnt == 3 || cCnt == 3) {
                    chk2 = false;
                }

                if (pre == cur && cur != 'e' && cur != 'o') {
                    chk3 = false;
                }
                pre = cur;
            }

            if (chk1 && chk2 && chk3) {
                sb.append("<").append(input).append("> is acceptable.\n");
            } else {
                sb.append("<").append(input).append("> is not acceptable.\n");
            }
        }
        System.out.println(sb.toString());
    }
}
```