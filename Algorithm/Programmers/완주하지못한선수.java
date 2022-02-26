package jiyoung.week1;

import java.util.HashMap;
import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		단 한명만 빼고 마라톤을 완주
//		마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 
//		완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 
//		완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
		
//		마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
//		completion의 길이는 participant의 길이보다 1 작습니다.
//		참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
//		참가자 중에는 동명이인이 있을 수 있습니다.
		
//		Scanner sc = new Scanner(System.in);
		
		//문자열자르기
//		String input = sc.nextLine();
//		String[] names = input.split("\", \"");
//		names[0] = names[0].substring(2);
//		String last = names[names.length-1];
//		names[names.length-1] = last.substring(0,last.indexOf("\""));
		
//		for(String s : names) {
//			System.out.println("'"+s+"'");
//		}

//		배열로준다고............문제똑바로읽기;
		
		///String[] hashMap = new String
		
		//해쉬로 풀라니까..
		
		String[] participant1 = {"leo", "kiki", "eden"};
		String[] completion1 = {"eden", "kiki"};
		String[] participant2 = {"mislav", "stanko", "mislav", "ana"};
		String[] completion2 = {"stanko", "ana", "mislav"};
		
		
		System.out.println(solution(participant2, completion2));
		
	}

    public static String solution(String[] participant, String[] completion) {
    	
        String answer = "";
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>(100000);
		
		for(String name : completion) {
			if(!hashMap.containsKey(name)) {	
				hashMap.put(name, 1);	
			}
			else {	//동명이인
				hashMap.put(name, hashMap.get(name) + 1);
			}
		}
		for(String name : participant) {
			if(!hashMap.containsKey(name)) {	//없는사람
				answer = name;
				break;
			}
			else {
				int num = hashMap.get(name);
				
				if(num == 0){	//동명이인 있을경우 마지막 동명이인
					answer = name;
					break;
				}
				else {
					hashMap.put(name, --num);
				}
			}
		}
        return answer;
    }
}
