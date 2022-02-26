package jiyoung.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class AntHouse {

	// G2 14725 개미굴

	static class Trie {
		class Node {
			Map<String, Node> child = new HashMap<>();
			boolean isTerminal;
		}

		Node root = new Node();
		StringBuilder text;

		public void insert(String[] food) {
			Node cur = root;
			int len = food.length;

			for (int i = 0; i < len; i++) {
				cur.child.putIfAbsent(food[i], new Node());
				cur = cur.child.get(food[i]);
			}
			cur.isTerminal = true;
		}

		public StringBuilder printTrie() {
			text = new StringBuilder();
			print(this.root, "", new StringBuilder(), -1);
			return text;
		}

		private void print(Node node, String food, StringBuilder sb, int depth) {
			Node cur = node;
			Map<String, Node> child = cur.child;

			if (node != root) {
				for (int i = 0; i < depth; i++) {
					sb.append("--");
				}
				sb.append(food).append("\n");
			}

			if (node.isTerminal) {
				text = sb;
			}

			Object[] keys = child.keySet().toArray();
			Arrays.sort(keys);
			for (Object key : keys) {
				String kk = key.toString();
				print(child.get(kk), kk, sb, depth + 1);
			}

		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		Trie trie = new Trie();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			String[] food = new String[k];
			for (int j = 0; j < k; j++) {
				food[j] = st.nextToken();
			}
			trie.insert(food);
		}

		System.out.println(trie.printTrie());

	}

}
