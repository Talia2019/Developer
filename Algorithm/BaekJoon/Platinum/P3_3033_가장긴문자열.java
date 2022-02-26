package jiyoung.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestString {

	// P3 3033 가장긴문자열

	// 보이어무어나 kmp 등은 skip배열이나 pi배열을 각 문자열마다 따로 만들어줘야함(이전 배열을 재활용이 불가능..)
	// 라빈카프는 해시를 설정할때 맨처음을빼고 맨뒤를 더하므로 중복이 그나마 적을거라 생각했음 (재활용이 가능해짐)

	// 리스트에 해시들을 다 저장하고(hash, 나온개수) 나온 개수가 2이상이면 검사해보도록 했으나 시간초과

	// 이분탐색을 해야 풀리는 문제인듯.. 다른 코드 참고ㅜ

	public static char[] text;
	public static int cnt, tLen, len;
	public static List<Long> hashList;
	public static Map<Long, List<Integer>> hashMap; // 인덱스들
	public static Set<Long> set;
	public static long power = 1;
	public static final int MOD = 100000007;
	public static final int BASE = 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int l = Integer.parseInt(br.readLine());
		text = br.readLine().toCharArray();
		hashMap = new HashMap<>();

		int top = 0, bot = l;
		while (top + 1 < bot) {
			int mid = (top + bot) / 2;
			power = 1;
			long hash = 0;
			boolean isAnswer = false;

			answer: for (int i = 0; i + mid < l; i++) {
				if (i == 0) {
					for (int j = mid - 1; j >= 0; j--) {
						hash = Mod(hash + text[j] * power);
						if (j > 0)
							power = Mod(power << 1);
					}
				} else {
					hash = Mod(2 * (hash - text[i - 1] * power) + text[i + mid - 1]);
				}

				// 충돌
				List<Integer> tmpList = hashMap.getOrDefault(hash, new ArrayList<>());
				if (tmpList.size() > 0) {
					for (Integer tl : tmpList) {
						boolean flag = true;
						for (int j = 0; j < mid; j++) {
							if (text[i + j] != text[tl + j]) {
								flag = false;
								break;
							}
						}

						if (flag) {
							isAnswer = true;
							break answer;
						}
					}
				}
				tmpList.add(i);
				hashMap.put(hash, tmpList);
			}
			if (isAnswer)
				top = mid;
			else
				bot = mid;
		}
		System.out.println(top);

//		tLen = text.length;
//		hashList = new LinkedList<Long>();
//		set = new HashSet<>();
//
//		len = tLen;
//		boolean flag = false;
//		while (len > 0) {
//			makeHash(len);
//			if (check()) {
//				flag = true;
//				System.out.println(len);
//				break;
//			}
//			len--;
//		}
//
//		if (!flag)
//			System.out.println(0);

	}

	public static long Mod(long n) {
		if (n >= 0)
			return n % MOD;
		else
			return ((-n / MOD + 1) * MOD + n) % MOD;
	}

	// 각 해시에서 마지막 단어 빼고 2로 나눔
	// 마지막 추가
	public static void makeHash(int len) {
		hashMap.clear();

		int num = hashList.size();
		long tmp;
		for (int i = 0; i < num; i++) {
			tmp = hashList.get(i);
			tmp -= text[len + i];
			tmp /= 2;
			hashList.set(i, tmp);
			List<Integer> hm = hashMap.getOrDefault(tmp, new ArrayList<Integer>());
			hm.add(i);
			hashMap.put(tmp, hm);
		}

		long hash = 0;
		if (len == tLen) {
			// 맨처음거
			for (int i = tLen - 1; i >= 0; i--) {
				hash = (hash + (text[i] * power)) % MOD;
				if (i > 0)
					power = (power % MOD * BASE) % MOD;
			}
		} else {
			power /= 2;
			hash = hashList.get(hashList.size() - 1);
			hash = BASE * hash % MOD - BASE * text[hashList.size() - 1] * power % MOD + text[tLen - 1];
			hash += MOD;
			hash %= MOD;
		}
		List<Integer> hm = hashMap.getOrDefault(hash, new ArrayList<Integer>());
		hm.add(hashList.size());
		hashMap.put(hash, hm);
		hashList.add(hash);
	}

	public static boolean check() {
		cnt = 0;

		for (Long l : hashMap.keySet()) {
			List<Integer> hm = hashMap.get(l);
			if (hm.size() >= 2) {
				// 해시중복인 경우 고려
				for (int i = 0; i < hm.size(); i++) {
					int ii = hm.get(i);
					for (int j = i + 1; j < hm.size(); j++) {
						int jj = hm.get(j);
						boolean flag = true;
						for (int k = 0; k < len; k++) {
							if (text[ii + k] != text[jj + k]) {
								flag = false;
								break;
							}
						}
						if (flag) {
							return true;
						}
					}
				}
			}
		}
		if (cnt >= 2)
			return true;

		return false;
	}

	public static void rabinKarpOrigin(char[] pattern) {
		long tHash = 0, pHash = 0, power = 1;
		int pLen = pattern.length;
		int cnt = 0;

		for (int i = 0; i < tLen - pLen; i++) {
			if (i == 0) {
				// 첫파트 해시 , 패턴 해시
				for (int j = pLen - 1; j >= 0; j--) {
					tHash += (text[j] * power);
					pHash += (pattern[j] * power);
					if (j > 0)
						power *= 2;
				}
			} else {
				tHash = 2 * (tHash - (text[i - 1] * power)) + text[pLen - 1 + i];
			}

			if (tHash == pHash) {
				boolean flag = true;
				for (int j = 0; j < pLen; j++) {
					if (text[i + j] != pattern[j]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					System.out.println(i + 1);
				}
			}

		}
	}

}
