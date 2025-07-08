// N개의 스티커가 원형으로 연결되어 있다.
// 스티커를 뜯어내면 양쪽 인접한 스티커는 사용할 수 없다.
// 스티커에 적힌 숫자의 합이 최대가 되도록 몇 장을 뜯어내자.
// 단, 원형이기 때문에 첫 번째와 마지막 스티커도 서로 인접한 것으로 본다.
//
// 🔷 제한사항
// - sticker 배열의 길이 N: 1 이상 100,000 이하
// - sticker[i]: 1 이상 100 이하
//
// 🔷 풀이 아이디어
// - 스티커가 원형이므로, 첫 번째 스티커를 뜯는 경우와 뜯지 않는 경우 두 가지로 나누어 풀어야 한다.
//   1) 첫 번째를 선택하고 마지막은 선택하지 않는 경우
//   2) 첫 번째를 선택하지 않고 마지막을 선택할 수도 있는 경우
// - 두 경우를 각각 DP로 계산해 더 큰 값을 선택한다.
//
// 🔷 입출력 예
// [14, 6, 5, 11, 3, 9, 2, 10] → 36
// [1, 3, 2, 5, 4] → 8

class Solution {
    public int solution(int[] sticker) {
        int n = sticker.length;
        if (n == 1) return sticker[0];

        return Math.max(
            maxSticker(sticker, 0, n - 2),
            maxSticker(sticker, 1, n - 1)
        );
    }

    private int maxSticker(int[] sticker, int start, int end) {
        int n = end - start + 1;
        int[] dp = new int[n];
        dp[0] = sticker[start];
        if (n > 1) dp[1] = Math.max(sticker[start], sticker[start+1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[start+i]);
        }

        return dp[n-1];
    }
}
