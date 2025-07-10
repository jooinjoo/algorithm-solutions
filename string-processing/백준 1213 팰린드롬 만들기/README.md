# 팰린드롬 만들기

> https://www.acmicpc.net/problem/1213

## 문제 설명

- 문제

임한수와 임문빈은 서로 사랑하는 사이이다.

임한수는 세상에서 팰린드롬인 문자열을 너무 좋아하기 때문에, 둘의 백일을 기념해서 임문빈은 팰린드롬을 선물해주려고 한다.

임문빈은 임한수의 영어 이름으로 팰린드롬을 만들려고 하는데, 임한수의 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만들려고 한다.

임문빈을 도와 임한수의 영어 이름을 팰린드롬으로 바꾸는 프로그램을 작성하시오.

- 입력

첫째 줄에 임한수의 영어 이름이 있다. 알파벳 대문자로만 된 최대 50글자이다.

- 출력

첫째 줄에 문제의 정답을 출력한다. 만약 불가능할 때는 "I'm Sorry Hansoo"를 출력한다. 정답이 여러 개일 경우에는 사전순으로 앞서는 것을 출력한다.

## 접근 방법

입력 문자열의 오름차순 정렬.  
정렬된 문자열을 둘 씩 묶어 검사하여 팰린드롬의 절반인 좌측만 저장.  
팰린드롬 성립하면, 출력할 때 + reverse() 조합.

## 문제 해결 과정

- 첫 번째 시도:
    - 전체 입력 문자열이 짝수인지 홀수인지에 따라 나뉜 뒤, 앞에서부터 둘 씩 문자열이 같은지 검사. 두 문자가 같으면 두 개의 스택에 각각 하나식 삽입.
    - 짝수이면, 항상 한 쌍의 문자열이 같아야만 했고, 홀수이면, 한 번까지는 다른 해당 문자열을 `mid`에 저장한 뒤 넘겨 똑같이 진행.
        - 한편 홀수인 경우 문자열의 인덱스를 기본적으로 2씩 올리다가도, 다른 경우 1만큼 올리는 과정에서 if 구문이 복잡해짐.
        - 또한 `AAAABBCCD` 이런 식으로 앞에서 배열의 끝에서 하나의 값만 남은 경우에 대한 처리까지 생각하다보니, if절 흐름을 잘못 설정하여 실패.
- 최종 해결 방법:
    - 우선 팰린드롬의 성질을 이용해, 최종 문자열을 반으로 나눴을 때 한쪽만 저장하면 된다는 사실을 깨닫고, 스택 대신 StringBuilder 사용.
    - 한편 문자열 길이가 홀수 또는 짝수인 것과는 상관없이, 현재 인덱스와 다음 인덱스가 존재하고 둘이 같다면, 일단 출력에 넣는 것은 동일하다.
        - 그리고 이전에 `mid` 문자열을 사용하지 않았다면 저장한 뒤 넘기고, 이미 한 번 사용했다면 팰린드롬 불가.
- 다른 해결 방법:
    - 알파벳 A~Z까지 사용된 횟수를 카운팅한 뒤, 알파벳의 내림차순으로 출력의 가운데부터 채워나가기.
    - 만약 현재 알파벳을 카운팅한 값이 홀수라면, `mid`에 해당 값을 넣고 카운팅을 하나 줄인 뒤, 나머지의 절반만큼 현재 출력의 양쪽에 삽입.
        - 예를 들어, 현재 알파벳이 `C`이고 `cnt['C' - 'A'] = 5`, 지금까지 출력이 `DEED`인 경우
        - `mid = 'C'`, `cnt['C' - 'A'] = 4`, 이후에 2번씩 양쪽에 `C`를 삽입하여 출력은 `CCDEEDCC`가 된다.
    - 그리고 위의 풀이와 같이 `mid` 값이 두 번 설정되면 팰린드롬 불가, 성립한다면 `mid` 값이 있을 때만 중간에 삽입하여 출력.
- 25.7.10. 다시 푼 방법:
    - 전체 문자열을 오름차순으로 정렬한 뒤, 전체 길이가 짝수인 경우와 홀수인 경우로 나누어 풀이.
        - 짝수인 경우 한 번이라도 두개씩 비교했을 때 서로 다르면 안되고, 홀수인 경우 두개씩 비교했을 때 한 번까지는 서로 달라도 된다.
            - 만약 서로 다른 경우가 생기면 해당 값은 가운데에 삽입.
    - 문제의 정답은 맞췄지만, 코드의 중복이 있어 좋지 못하다. 더 직관적인 방법으로는 알파벳 별로 카운팅한 값을 배열에 넣고 알파벳 `'Z'`부터 역으로 해당 카운팅 값이 존재하면 좌우로 채워넣는 방식이
      있다.
    - 팰린드롬이 되지 않는 경우는 전체 `input`길이에 상관없이 홀수 알파벳이 2개 이상일 때이다. 따라서 카운팅 배열 값이 홀수이면 해당 값을 `mid`에 넣고 하나 줄인 뒤 양쪽에 삽입 반복.
    - 문제를 푼 것은 좋았으나 좀 더 직관적인 방법을 찾도록 노력해보자.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        int[] cnt = new int[26];
        for (int i = 0; i < input.length(); i++) {
            cnt[input.charAt(i) - 'A']++;
        }
        int flag = 0;
        char mid = 0;

        for (int i = cnt.length - 1; i >= 0; i--) {
            char cur = (char) (i + 'A');
            if (cnt[i] % 2 == 1) {
                flag++;
                if (flag > 1) {
                    System.out.println("I'm Sorry Hansoo");
                    return;
                }
                mid = cur;
                cnt[i]--;
            }
            for (int j = 0; j < cnt[i] / 2; j++) {
                sb.insert(0, cur);
                sb.append(cur);
            }
        }

        if (mid > 0) sb.insert(sb.length() / 2, mid);
        System.out.println(sb);
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();
        boolean flag = true;

        Arrays.sort(input);
        String mid = "";
        if (input.length % 2 == 0) {
            for (int i = 0; i < input.length; i += 2) {
                if (input[i] != input[i + 1]) {
                    flag = false;
                    break;
                }
                sb.append(input[i]);
            }
        } else {
            for (int i = 0; i < input.length; i += 2) {
                if (i == input.length - 1) {
                    if (mid.isEmpty()) {
                        mid = String.valueOf(input[i]);
                    } else {
                        flag = false;
                    }
                } else {
                    if (input[i] == input[i + 1]) {
                        sb.append(input[i]);
                    } else {
                        if (mid.isEmpty()) {
                            mid = String.valueOf(input[i]);
                            i--;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }

        if (flag) {
            String lt = sb.toString();
            String rt = sb.reverse().toString();
            System.out.println(lt + mid + rt);
        } else {
            System.out.println("I'm Sorry Hansoo");
        }
    }
}
```