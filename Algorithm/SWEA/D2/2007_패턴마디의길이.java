import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_D2_2007_패턴마디의길이 {

//	패턴에서 반복되는 부분을 마디라고 부른다. 문자열을 입력 받아 마디의 길이를 출력하는 프로그램을 작성하라.
//	각 문자열의 길이는 30이다. 마디의 최대 길이는 10이다.

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		String str;
		for (int tc = 1; tc <= t; tc++) {
			str = br.readLine();
			int idx = 0;
			int cnt = 0;
			while (true) {
				idx = str.indexOf(str.charAt(0), idx + 1);	//다음위치 찾음
				boolean flag = true;
				cnt = 0;
				for (int i = 0; i < idx; i++) {
					if (str.charAt(i) != str.charAt(idx + i)) {
						flag = false;
					}
					cnt++;
				}
				if (flag)
					break;
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}

}
