package jiyoung.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CoordinateCompression {

//	18870 S2 좌표압축
	
//	수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.
//	Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표의 개수와 같아야 한다.
//	X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.
	
//	첫째 줄에 N이 주어진다.
//	둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.
	
//	첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.

	
//	************
//	풀었었는데 기억이안나네 ㅎㅎ...
	
//	Sol.ver1
//	정렬시키고 인덱스값을 출력?
//	중복값제거를 위해 treeSet사용
//	빨리 찾기 위해 hashMap에 값을 키로, 인덱스를 값으로 넣어줌
	
//	예전코드는 중복값을 없애기 위해 treeSet안쓰고 그냥 map에서 해당 키가 없을때만 넣었음
//	************
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		List<Integer> coordinates = new ArrayList<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			coordinates.add(Integer.parseInt(st.nextToken()));
		}
		
		TreeSet<Integer> sortedTree = new TreeSet<>(coordinates);	//중복제거용
		List<Integer> sortedCoordinates = new ArrayList<>(sortedTree); 
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < sortedCoordinates.size(); i++) {
			map.put(sortedCoordinates.get(i), i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < coordinates.size(); i++) {
			sb.append(map.get(coordinates.get(i))).append(" ");
		}
		System.out.println(sb);
		
	}

}
