import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Queue<Long> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			long input = Long.parseLong(br.readLine());
			if (input == 0) {
				if (pq.size() == 0)
					sb.append("0").append("\n");
				else
					sb.append(pq.poll()).append("\n");
			} else
				pq.add(input);
		}
		System.out.println(sb);
	}

}
