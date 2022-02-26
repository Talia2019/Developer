import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_D2_1984_중간평균값구하기 {

	//정렬하고 가운데 값들 더해서 평균구하기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		int[] numbers = new int[10];
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 10; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(numbers);
			int sum = 0;
			for (int i = 1; i < 9; i++) {
				sum+=numbers[i];
			}
			double avg = (double)sum/8;
			sb.append("#").append(tc).append(" ").append(Math.round(avg)).append("\n");
		}
		System.out.println(sb);
	}

}
