package jiyoung.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Prefix {

	// S3 14426 접두사찾기

	static class Trie {
		class Node {
			Map<Character, Node> child = new HashMap<>();
			boolean isTerminal = false;
		}

		Node root;

		public Trie() {
			root = new Node();
		}

		public void insert(String str) {
			int len = str.length();
			Node cur = root;

			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				cur.child.putIfAbsent(c, new Node());
				cur = cur.child.get(c);
			}
			cur.isTerminal = true;
		}

		public boolean find(String str) {
			int len = str.length();
			Node cur = root;

			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				if (cur.child.getOrDefault(c, null) == null)
					return false;
				cur = cur.child.get(c);
			}
			return true;
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
