package jiyoung.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakingWord {

	// S1 1148 단어만들기

	// 각 단어를 필수로 포함하여 만들수 있는 경우
	// 각 단어마다 어떤 글자를 필요로 하는지 : 20만*9
	// 특정 글자를 반드시 포함하여 만들 수 있는 글자 개수 찾기 : 9 * 20만*9

	// 각 사전의 단어마다 어떤 글자를 몇개 필요로하는지 저장 - 해쉬맵
	// 내가 가진 단어로 각 사전의 단어를 만들수있는지 체크한 후 만들 수 있으면 각 글자가 포함된경우 ++

	// ㅠㅠ좀오래걸림 1초정도.. 200초대로 걸리는 코드들도 있었음
	public static List<String> dictionary = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String word = br.readLine();
		while (!word.equals("-")) {
			dictionary.add(word);
			word = br.readLine();
		}

		Map<Character, Integer> dictionaryCnt[] = new HashMap[dictionary.size()];
		int num;
		char cur;

		// 각 사전의 단어를 저장
		for (int i = 0; i < dictionary.size(); i++) {
			dictionaryCnt[i] = new HashMap<>();
			String str = dictionary.get(i);
			for (int j = 0; j < str.length(); j++) {
				cur = str.charAt(j);
				num = dictionaryCnt[i].getOrDefault(cur, 0);
				dictionaryCnt[i].put(cur, num + 1);
			}
		}

		// 각 사전의 단어마다 어떤 글자를 몇개 필요로하는지 저장 - 해쉬맵
		word = br.readLine();
		int[] cnt = new int[26];
		boolean[] visited = new boolean[26];
		Map<Character, Integer> hash = new HashMap<Character, Integer>();
		while (!word.equals("#")) {
			Arrays.fill(cnt, 0);
			Arrays.fill(visited, false);
			hash.clear();
			for (int i = 0; i < 9; i++) {
				cur = word.charAt(i);
				num = hash.getOrDefault(cur, 0); // 없으면0으로줌
				hash.put(cur, num + 1);
				visited[cur - 'A'] = true;
			}

			// 만들수 있는 단어인지 구분한다음
			// 특정 단어가 포함되는가?

			check: for (Map<Character, Integer> map : dictionaryCnt) {
				for (Character ch : map.keySet()) {
					if (map.get(ch) > hash.getOrDefault(ch, 0)) { // 만들수 없는 단어임
						continue check;
					}
				}
				// 여기 오면 만들 수 있는 단어
				for (Character ch : map.keySet()) {
					cnt[ch - 'A']++;
				}
			}

			int max = 0;
			int min = dictionary.size();
			for (int i = 0; i < 26; i++) {
				if (visited[i]) {
					if (cnt[i] < min)
						min = cnt[i];
					if (cnt[i] > max)
						max = cnt[i];
				}
			}

			// 최소갯수를 가진애들
			for (int i = 0; i < 26; i++) {
				if (visited[i] && cnt[i] == min)
					sb.append((char) (i + 'A'));
			}
			sb.append(" ").append(min).append(" ");

			// 최대갯수를 가진애들
			for (int i = 0; i < 26; i++) {
				if (visited[i] && cnt[i] == max)
					sb.append((char) (i + 'A'));
			}
			sb.append(" ").append(max).append("\n");

			word = br.readLine();
		}
		System.out.println(sb);
	}

}

//단어는 최소 4글자 이상이어야 하며, 한 글자당 최대 1번만 사용할 수 있다. 따라서 10글자 이상의 단어는 만들 수 없다. 또한, 표의 정중앙에 있는 글자는 반드시 사용해야 한다.
//어떤 글자를 놓아야 퍼즐이 가장 쉬워지는지(즉, 만들 수 있는 단어의 개수가 가장 많음), 또 어떤 글자를 놓아야 퍼즐이 가장 어려워지는지(즉, 만들 수 있는 단어의 개수가 가장 적음)를 알려주려고 한다.
//이 단어들을 모두 담고 있는 사전과 퍼즐판에 배치할 9개의 문자가 주어졌을 때, 문제를 푸는 프로그램을 작성하시오.

//입력의 처음에는 사전을 이루는 최대 20만 개의 단어가 주어진다. 각 단어는 4~9글자의 영어 대문자로 이루어져 있으며, 한 줄에 하나씩 주어진다. 또한 사전순으로 정렬되어 있다. 사전 입력의 끝에는 한 줄에 걸쳐 '-' 한 글자가 주어진다.
//그 다음부터 여러 개의 퍼즐판이 주어진다. 각 퍼즐판은 9개의 영어 대문자로 이루어져 있으며, 한 줄에 하나씩 주어진다. 입력의 맨 끝에는 한 줄에 걸쳐 '#' 한 글자가 주어진다.

//각 퍼즐판마다 정답을 예제 형식에 맞게 한 줄에 하나씩 출력한다.
//각 문제마다 정답의 개수를 가장 적게 하기 위해 정중앙에 놓아야 할 문자들과 그때의 정답 개수, 정답의 개수를 가장 많게 하기 위해 정중앙에 놓아야 할 문자들과 그 때의 정답 개수를 공백으로 구분하여 출력한다.
//한 개 이상의 문자가 답을 만족할 경우 문자들을 알파벳순으로 정렬하여 출력하며, 중복된 문자는 출력하지 않는다. 첫 번째 예제 출력에서 보듯이 I나 L은 여러 번 등장하지만 한 번씩만 출력하였다.
