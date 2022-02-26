package programmers;

import java.util.StringTokenizer;

import sun.security.util.Length;

public class NewID {

//	신규아이디 추천
	
	public static void main(String[] args) {

		Solution s = new Solution();
		System.out.println(s.solution("...!@BaT#*..y.abcdefghijklm"));

		System.out.println(s.solution("z-+.^."));
		System.out.println(s.solution("=.="));
		System.out.println(s.solution("123_.def"));
		System.out.println(s.solution("abcdefghijklmn.p"));

	}

//	카카오 서비스에 가입하는 유저들의 아이디를 생성하는 업무를 담당하게 되었습니다
//	 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때, 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.

//	아이디의 길이는 3자 이상 15자 이하여야 합니다.
//	아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
//	단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

//	7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고 규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천해 주려고 합니다.

//	1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
//	2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
//	3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
//	4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
//	5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
//	6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//	     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
//	7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.

//	신규 유저가 입력한 아이디를 나타내는 new_id가 매개변수로 주어질 때, "네오"가 설계한 7단계의 처리 과정을 거친 후의 추천 아이디를 return 하도록 solution 함수를 완성해 주세요.
//	new_id는 길이 1 이상 1,000 이하인 문자열입니다.
//	new_id는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성되어 있습니다.
//	new_id에 나타날 수 있는 특수문자는 -_.~!@#$%^&*()=+[{]}:?,<>/ 로 한정됩니다.

	static class Solution {
		public String solution(String new_id) {
			StringBuilder sb = new StringBuilder();

			String answer = new_id;

			// 1
			sb.append(answer.toLowerCase());

			// 2
			StringTokenizer st = new StringTokenizer(sb.toString(), "~|!|@|#|$|%|^|&|*|(|)|=|+|[|{|]|}|:|?|,|<|>|/");
			sb.setLength(0);
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken());
			}

			// 3
			for (int i = 0; i < sb.length(); i++) {
				if ((sb.charAt(i) == '.') && (i + 1 < sb.length()) && (sb.charAt(i + 1) == '.')) {
					sb.replace(i, i + 2, ".");
					i--;
				}
			}

			// 4
			if (sb.charAt(0) == '.')
				sb.deleteCharAt(0);
			if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '.')
				sb.deleteCharAt(sb.length() - 1);

			// 5
			if (sb.length() == 0)
				sb.append("a");
			System.out.println("5 "+sb);

			// 6
			if (sb.length() > 15)
				sb.delete(15, sb.length());
			if (sb.charAt(sb.length() - 1) == '.')
				sb.deleteCharAt(14);

			// 7
			while (sb.length() < 3) {
				sb.append(sb.charAt(sb.length() - 1));
			}

			answer = sb.toString();
			return answer;
		}
	}

	
//	class Solution {
//        public String solution(String new_id) {
//            String answer = new_id;
//
//            // 1
//            answer = answer.toLowerCase();
//
//            // 2
//            StringTokenizer st = new StringTokenizer(answer, "~|!|@|#|$|%|^|&|*|(|)|=|+|[|{|]|}|:|?|,|<|>|/");
//            StringBuilder sb = new StringBuilder();
//            while (st.hasMoreTokens()) {
//                sb.append(st.nextToken());
//            }
//            answer = sb.toString();
//
//            // 3
//            for (int i = 0; i < answer.length(); i++) {
//                if (answer.charAt(i) == '.') {
//                    if (i + 1 < answer.length())
//                        if (answer.charAt(i + 1) == '.')
//                            answer = answer.substring(0, i) + answer.substring(i-- + 1);
//                }
//            }
//
//            // 4
//            if (answer.charAt(0) == '.')
//                answer = answer.substring(1);
//            if (answer.length() != 0 && answer.charAt(answer.length() - 1) == '.')
//                answer = answer.substring(0, answer.length() - 1);
//
//            // 5
//            if (answer.length() == 0)
//                answer = "a";
//
//            // 6
//            if (answer.length() > 15)
//                answer = answer.substring(0, 15);
//            if (answer.charAt(answer.length()-1) == '.')
//                answer = answer.substring(0, 14);
//
//            // 7
//            while (answer.length() < 3) {
//                answer = answer + answer.charAt(answer.length() - 1);
//            }
//
//            return answer;
//        }
//    }
	
	//정규표현식
//	class Solution {
//	    public String solution(String new_id) {
//	        String answer = "";
//	        String temp = new_id.toLowerCase();
//
//	        temp = temp.replaceAll("[^-_.a-z0-9]","");
//	        System.out.println(temp);
//	        temp = temp.replaceAll("[.]{2,}",".");
//	        temp = temp.replaceAll("^[.]|[.]$","");
//	        System.out.println(temp.length());
//	        if(temp.equals(""))
//	            temp+="a";
//	        if(temp.length() >=16){
//	            temp = temp.substring(0,15);
//	            temp=temp.replaceAll("^[.]|[.]$","");
//	        }
//	        if(temp.length()<=2)
//	            while(temp.length()<3)
//	                temp+=temp.charAt(temp.length()-1);
//
//	        answer=temp;
//	        return answer;
//	    }
//	}

}

// String으로 풀었을때
//테스트 1 〉	통과 (0.10ms, 73.6MB)
//테스트 2 〉	통과 (11.27ms, 72MB)
//테스트 3 〉	통과 (10.45ms, 72.8MB)
//테스트 4 〉	통과 (1.90ms, 71.1MB)
//테스트 5 〉	통과 (1.40ms, 67.7MB)
//테스트 6 〉	통과 (0.07ms, 57.4MB)
//테스트 7 〉	통과 (0.08ms, 61MB)
//테스트 8 〉	통과 (1.64ms, 73.2MB)
//테스트 9 〉	통과 (14.35ms, 57.9MB)
//테스트 10 〉	통과 (0.10ms, 73.5MB)
//테스트 11 〉	통과 (0.10ms, 58.7MB)
//테스트 12 〉	통과 (1.39ms, 59.2MB)
//테스트 13 〉	통과 (15.08ms, 75.9MB)
//테스트 14 〉	통과 (1.30ms, 72.8MB)
//테스트 15 〉	통과 (1.44ms, 70.8MB)
//테스트 16 〉	통과 (1.95ms, 75.2MB)
//테스트 17 〉	통과 (1.83ms, 72.2MB)
//테스트 18 〉	통과 (1.77ms, 76.2MB)
//테스트 19 〉	통과 (2.76ms, 70.6MB)
//테스트 20 〉	통과 (2.16ms, 58.8MB)
//테스트 21 〉	통과 (2.98ms, 67.2MB)
//테스트 22 〉	통과 (11.38ms, 61.6MB)
//테스트 23 〉	통과 (10.22ms, 72.8MB)
//테스트 24 〉	통과 (0.32ms, 60.2MB)
//테스트 25 〉	통과 (2.81ms, 73.1MB)
//테스트 26 〉	통과 (3.32ms, 72MB)

//StringBuilder로 풀었을때
//테스트 1 〉	통과 (2.50ms, 73.9MB)
//테스트 2 〉	통과 (2.16ms, 72.3MB)
//테스트 3 〉	통과 (2.35ms, 58.8MB)
//테스트 4 〉	통과 (2.19ms, 59.8MB)
//테스트 5 〉	통과 (3.04ms, 73.2MB)
//테스트 6 〉	통과 (3.04ms, 69.2MB)
//테스트 7 〉	통과 (2.06ms, 59.4MB)
//테스트 8 〉	통과 (2.13ms, 58.8MB)
//테스트 9 〉	통과 (3.33ms, 60.9MB)
//테스트 10 〉	통과 (2.17ms, 69.3MB)
//테스트 11 〉	통과 (2.93ms, 68.8MB)
//테스트 12 〉	통과 (2.22ms, 59.5MB)
//테스트 13 〉	통과 (2.37ms, 61.4MB)
//테스트 14 〉	통과 (2.08ms, 59.7MB)
//테스트 15 〉	통과 (2.43ms, 61.4MB)
//테스트 16 〉	통과 (2.66ms, 72MB)
//테스트 17 〉	통과 (2.81ms, 73.2MB)
//테스트 18 〉	통과 (2.93ms, 73.7MB)
//테스트 19 〉	통과 (3.56ms, 59.9MB)
//테스트 20 〉	통과 (2.63ms, 73.8MB)
//테스트 21 〉	통과 (3.22ms, 73.9MB)
//테스트 22 〉	통과 (2.65ms, 61.3MB)
//테스트 23 〉	통과 (3.26ms, 69.9MB)
//테스트 24 〉	통과 (2.62ms, 70MB)
//테스트 25 〉	통과 (8.60ms, 74.8MB)
//테스트 26 〉	통과 (3.69ms, 76.2MB)
