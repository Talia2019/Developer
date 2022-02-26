package jiyoung.week2;

import java.util.LinkedList;
import java.util.Queue;

public class TruckAcrossingBridge {
//	트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 
//	모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 
//	다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 
//	다리는 weight 이하까지의 무게를 견딜 수 있습니다. 
//	단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.
//	예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 
//	무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.
//	따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.
//	solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 
//	다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 
//	이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.
//	bridge_length는 1 이상 10,000 이하입니다.
//	weight는 1 이상 10,000 이하입니다.
//	truck_weights의 길이는 1 이상 10,000 이하입니다.
//	모든 트럭의 무게는 1 이상 weight 이하입니다.

//	n초에 올라가면 -> n+다리길이에 내려옴
//	현재 다리위에 올라간 무게 저장
//	맨뒤 애가 몇초에 올라갔는지가 중요함

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		int b1 = 2;
		int b2 = 100;
		int w1 = 10;
		int w2 = 100;
		int[] t1 = { 7, 4, 5, 6 };
		int[] t2 = { 10, 2, 2 };
		int[] t3 = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		int[] t4 = {1,2,3,4,1,2,3,4};
		int[] t5 = {1,1,1,1,1,1,1,1,1,1};
		int[] t6 = {2,2,2,2,1,1,1,1,1};
		
		System.out.println(s.solution(5, 5, t6));

	}
	static class Solution {
		public int solution(int bridge_length, int weight, int[] truck_weights) {
			int answer = 1;
			int curWeight = 0;

			Queue<Integer> truck = new LinkedList<>();
			Queue<Integer> time = new LinkedList<>();

			for (int i = 0; i < truck_weights.length; i++) {
				curWeight += truck_weights[i]; // 일단올려
				truck.add(truck_weights[i]);
				
				// 다리에 여유가 없으면
				while (curWeight > weight || truck.size() > bridge_length) { // 새거 올릴수있을때까지 옛날거 뺌
					curWeight -= truck.poll(); // 옛날거빼고
					int t = time.poll() + bridge_length;
					if(answer <= t) 	//****이 조건없어서!! 앞에거 나가기 전에 이미 올린상태를 고려안했음*****
						answer = t;
				}
				
				time.add(answer++);
//				System.out.println(i + " truck" + truck.toString());
//				System.out.println(i + " time " + time.toString());
			}
			while (!time.isEmpty()) {
				answer = time.poll() + bridge_length;
			}
			return answer;
		}
	}
}
