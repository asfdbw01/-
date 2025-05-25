/*
문제 요약 📘
- 초항 k인 **우박수열**을 생성하고,
- 우박수열을 좌표 (x, y) = (순서, 수열값)으로 표현하여 꺾은선 그래프를 그림
- 주어진 구간 [a, b]에 대해 x=a에서 x=n+b까지의 구간에 대해 **면적(정적분 결과)**을 구함
- 단, b는 음수이고 n은 우박수열이 1이 되기까지 걸리는 총 단계 수
- 만약 a > n + b면 유효하지 않은 구간이므로 결과는 -1.0

우박수열 생성 규칙 (Collatz 추측):
1. k가 짝수면 → k / 2
2. k가 홀수면 → k * 3 + 1
→ 1이 될 때까지 반복

면적 계산 방식 📐
- 인접한 두 좌표 (x, y1), (x+1, y2)의 사다리꼴 넓이 = (y1 + y2) / 2
- 꺾인 구간 개수는 (수열 길이 - 1)개

입력 🎯
- int k: 우박수열의 초항 (2 ≤ k ≤ 10,000)
- int[][] ranges: 정적분 구간 목록
    * 각 ranges[i]는 [a, b] (0 ≤ a < 200, -200 < b ≤ 0)

출력 🧾
- double[] : 각 구간의 정적분 결과 목록 (구간이 유효하지 않으면 -1.0)

예시 설명 💡
예) k = 5
수열: 5 → 16 → 8 → 4 → 2 → 1
좌표: (0,5), (1,16), ..., (5,1)
넓이: (5+16)/2, (16+8)/2, ... → 사다리꼴 5개

예) range [2, -3]
→ 수열 길이 = 5 (n = 5)
→ b = -3 → end = 2
→ [2,2] 는 시작 == 끝이므로 넓이 0.0

주의할 점 ⚠️
- 넓이 구간은 [a, n+b) 범위이므로 마지막 점 포함 X
- 정적분 결과는 double
- 유효하지 않은 구간은 -1.0으로 처리

*/


import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        List<int[]> collatz = new ArrayList<>();
        int idx = 0;
        collatz.add(new int[]{idx,k});
        while(k!=1){
            if(k%2==0)k/=2;
            else k = k*3+1;
            idx++;
            collatz.add(new int[]{idx,k});
        }
        // a~idx-b 까지의 넓이 
        for(int i=0;i<ranges.length;i++){
            double sum=0;
            //b가 a보다 작으면 -1
            if(idx+ranges[i][1]<ranges[i][0])sum=-1.0;
            //b가 인덱스 초과여도 -1
            else if(idx+ranges[i][1]>idx)sum=-1;
            else {
                for(int j=ranges[i][0];j<idx+ranges[i][1];j++){
                    int[] hight1 = collatz.get(j);
                    int[] hight2 = collatz.get(j+1);
                    sum += (hight1[1]+hight2[1])/2.0;
                }
            }
            answer[i] = sum;
        }
        return answer;
    }
}
