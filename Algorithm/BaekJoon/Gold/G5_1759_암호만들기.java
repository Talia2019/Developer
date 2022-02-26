import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1759_암호만들기 {

//	G5 1759 암호만들기

//	암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다.
//	암호는 오름차순 (abc)
//	새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다.
//	C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.

//	첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15) 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.
//	각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.

//	정렬하고 C개중 L개 뽑는 조합
//	모음, 자음수 세서 기준 미달이면 탈락

	public static char[] chars;
	public static int l, c;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		chars = new char[c];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			chars[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(chars);
		
		combination(0, 0, "", 0, 0);
		System.out.println(sb);

	}

	public static void combination(int cnt, int start, String code, int cons, int vows) {	//con : 자음,  vow : 모음
		if (cnt == l) {
			if(cons>=2 && vows>=1)
				sb.append(code).append("\n");
		}
fo:		for (int i = start; i < c; i++) {
			switch(chars[i]) {
			case 'i':
			case 'e':
			case 'a':
			case 'o':
			case 'u':
				combination(cnt + 1, i + 1, code + chars[i], cons, vows+1);
				continue fo;
			}
			combination(cnt + 1, i + 1, code + chars[i], cons+1, vows);
		}
	}

}
