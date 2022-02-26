package jiyoung.week4;

import java.util.HashSet;

public class FindPrimeNumber {

//	소수찾기 L2

//	한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
//	각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
//	numbers는 길이 1 이상 7 이하인 문자열입니다.
//	numbers는 0~9까지 숫자만으로 이루어져 있습니다.
//	"013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

//	*********
//	Sol.ver1
//	2부터 9999999까지의 배수를 배열에 true로 저장.. -> false로 초기화 되기에 false의 값이 소수
//	주어진 조각들의 순열을 구하고, 각각이 소수인지?
//	테스트 1 〉	통과 (117.83ms, 92.9MB)
//	테스트 2 〉	통과 (96.74ms, 95.9MB)
//	테스트 3 〉	통과 (100.96ms, 81.5MB)
//	테스트 4 〉	통과 (105.39ms, 93MB)
//	테스트 5 〉	통과 (120.65ms, 96.4MB)
//	테스트 6 〉	통과 (112.99ms, 92MB)
//	테스트 7 〉	통과 (97.94ms, 77.9MB)
//	테스트 8 〉	통과 (112.19ms, 93.7MB)
//	테스트 9 〉	통과 (103.51ms, 95.3MB)
//	테스트 10 〉	통과 (107.07ms, 91.6MB)
//	테스트 11 〉	통과 (116.73ms, 92.3MB)
//	테스트 12 〉	통과 (118.44ms, 92.3MB)
//	느린거같은뎀..
	
//	sqrt썼더니 순열 구하는데 시간이 절반쯤 줄어든거같다
//	테스트 1 〉	통과 (62.66ms, 95.5MB)
//	테스트 2 〉	통과 (68.65ms, 77.3MB)
//	테스트 3 〉	통과 (58.78ms, 79.4MB)
//	테스트 4 〉	통과 (78.97ms, 96.5MB)
//	테스트 5 〉	통과 (81.16ms, 93.2MB)
//	테스트 6 〉	통과 (69.29ms, 97MB)
//	테스트 7 〉	통과 (73.44ms, 93.5MB)
//	테스트 8 〉	통과 (61.68ms, 93.6MB)
//	테스트 9 〉	통과 (58.39ms, 81MB)
//	테스트 10 〉	통과 (58.24ms, 94.4MB)
//	테스트 11 〉	통과 (62.26ms, 78.9MB)
//	테스트 12 〉	통과 (55.92ms, 98.8MB)
//	
//	*********

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution("17");
		s.solution("011");
	}

}

class Solution {

	public static int n, totalCnt;
	public static int[] numberList;
	public static boolean[] isSelected;
	public static boolean[] isNotPrime;
	public static boolean[] isCounted;

	public int solution(String numbers) {

		n = numbers.length();
		numberList = new int[numbers.length()];
		isSelected = new boolean[numbers.length()];
		isCounted = new boolean[10000001];
		for (int i = 0; i < numbers.length(); i++) {
			numberList[i] = numbers.charAt(i) - '0';
		}
//		System.out.println(Arrays.toString(numberList));

		isNotPrime = new boolean[10000001];
		isNotPrime[0] = isNotPrime[1] = true;
		
		// 소수찾기 false로 저장된게 소수
		for (int i = 2; i < Math.sqrt(10000000); i++) {
			while (true) {
				if (!isNotPrime[i])
					break;
				i++;
			} // 다음소수 찾음
			int num = i + i;
			while (num < 10000000) {
				isNotPrime[num] = true;
				num += i;
			}
		}
		for (int i = 1; i <= numbers.length(); i++) {
			n = i;
			permutation(0, 0);
		}

		return totalCnt;
	}

	public void permutation(int cnt, int number) {
		if (cnt == n) {
//			System.out.println("순열 - 구한수 : "+number);
			if (!isCounted[number]) {
				isCounted[number] = true;
				if (!isNotPrime[number]) {
//					System.out.println("소수이다!");
					totalCnt++;
				}
			}
		}
		for (int i = 0; i < numberList.length; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			permutation(cnt + 1, number * 10 + numberList[i]);
			isSelected[i] = false;
		}
	}
	
	//순열 참고 코드 
	public void permutation(String prefix, String str, HashSet<Integer> set) {
        int n = str.length();
        if(!prefix.equals("")) set.add(Integer.valueOf(prefix));
        for (int i = 0; i < n; i++)
          permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), set);
//        나를 추가하고, 날뺀걸 넘김

    }

}
