package jiyoung.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringSet {

	// S3 14425 문자열집합

	// 이건 소문자만 들어올걸 알아서 배열로 구현.. 원래는 hashmap 쓰는게 일반적
	// 이문제는 trie안쓰고 그냥 hash로 푸는게 더 빠른듯!!
	static class Trie {
		Node root;
		static final int ALPHABET_SIZE = 26;

		public Trie() {
			this.root = new Node();
			this.root.val = ' ';
		}

		private class Node {
			Node[] child = new Node[ALPHABET_SIZE]; // 문자를 인덱스화하여 저장
			boolean isTerminal = false; // 문자 완성이 되는 노드인가?
			int childNum = 0; // 현 노드에 연결된 문자열의 수
			char val; // 현 노드의 값
		}

		public void insert(String str) {
			int len = str.length();
			Node cur = this.root; // 루트부터 시작해서 내려감
			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				int num = c - 'a';

				if (cur.child[num] == null) { // 연결단어로 처음나오는 단어
					cur.child[num] = new Node();
					cur.child[num].val = c;
					cur.childNum++;
				}

				cur = cur.child[num]; // 다음노드로
			}
			cur.isTerminal = true;
		}

		public boolean find(String str) {
			int len = str.length();
			Node cur = this.root;

			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				int num = c - 'a';

				if (cur.child[num] == null)
					return false;
				cur = cur.child[num];
			}
			// 문자열이 있으면서 문자열의 마지막이라면 true반환
			return cur != null && cur.isTerminal;
		}

		// 저장된 단어 내역 출력
		public void printTrie() {
			printTrie(this.root, 0, new StringBuilder());
		}

		public void printTrie(Node node, int idx, StringBuilder sb) {
			Node cur = node;
			Node[] child = cur.child;

			// 루트노드엔 저장된게 없어서
			if (!cur.equals(this.root))
				sb.append((char) (idx + 'a'));

			// 완성된거면 프린트
			if (cur.isTerminal)
				System.out.println(sb);

			for (int i = 0; i < ALPHABET_SIZE; i++) {
				if (cur.child[i] == null)
					continue;
				printTrie(child[i], i, sb);
			}
		}

		// 삭제..
		public void delete(String str) {
			delete(this.root, str, 0);
		}

		public void delete(Node cur, String str, int idx) {
			int len = str.length();

			// 자식이 없는데 문자가 남아있으면?
			// 끝까지갔는데 해당노드가 terminal이 아니라면?
			// 없는단어임
			if ((cur.childNum == 0 && idx != len) || (idx == len && !cur.isTerminal))
				return;

			// 끝까지 왔음
			if (idx == len) {
				cur.isTerminal = false;
				if (cur.childNum == 0)
					cur = null;
			} else {
				char c = str.charAt(idx);
				int num = c - 'a';

				delete(cur.child[num], str, idx + 1);
				// 자식노드 하나 줄어듬
				if (cur.child[num] == null)
					cur.childNum--;
				// 현재 노드 자식이 없고, 마지막도 아니면 삭제
				if (cur.childNum == 0 && !cur.isTerminal)
					cur = null;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Trie trie = new Trie();
		for (int i = 0; i < n; i++) {
			trie.insert(br.readLine());
		}

		int cnt = 0;
		for (int i = 0; i < m; i++) {
			if (trie.find(br.readLine()))
				cnt++;
		}
		System.out.println(cnt);
	}

}
