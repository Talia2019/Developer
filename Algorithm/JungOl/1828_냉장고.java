import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
//	N개의 화학 물질 C1, C2, …, Cn이 있다. 
//	이들 각각은 보관되어야 할 온도가 각기 다른데, 각 Ci마다 최저 보관 온도 xi와 최고 보관 온도 yi가 정해져 있다. 
//	즉 Ci는 온도 xi이상, yi이하의 온도에서 보관되어야만 안전하다.
//	이 화학 물질들을 모두 보관하기 위해서는 여러 대의 냉장고가 필요한데 가능하면 적은 수의 냉장고를 사용하고 싶다. 
//	이를 해결하는 프로그램을 작성하시오.
	
//	첫줄에 화학물질의 수 N이 입력된다. N의 범위는 1이상 100 이하이다. 
//	두 번째 줄부터 N+1줄까지 최저보관온도와 최고보관온도가 입력된다. 
//	보관온도는 -270° ~ 10000°이며, 각 냉장고는 임의의 정해진 온도를 일정하게 유지할 수 있고, 냉장고는 아주 크다고 가정한다.
//	첫줄에 최소로 필요한 냉장고의 대수를 출력한다.
	
//	xi를 기준으로 정렬
//	냉장고는 xi보다 커야하고 yi보다 작아야함
//	더낮은 xi와 더 큰yi를 기준으로 사용
//	이 기준인 y-x가 0보다 작은경우 새로운 냉장고
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] chemical = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			chemical[i][0] = Integer.parseInt(st.nextToken());
			chemical[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(chemical, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
// 		for (int[] is : chemical) {
// 			System.out.println(Arrays.toString(is));
// 		}
		
		int cnt = 1;
		int low = chemical[0][0], high = chemical[0][1];
		for (int i = 1; i < chemical.length; i++) {
			if(chemical[i][0] > low) {
				low = chemical[i][0];
			}
			if(chemical[i][1]<high) {
				high = chemical[i][1];
			}
			if(high-low<0) {
				cnt++;
				low = chemical[i][0];
				high = chemical[i][1];
			}
		}
		System.out.println(cnt);
	}

}
