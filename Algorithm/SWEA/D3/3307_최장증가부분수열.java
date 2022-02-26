package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3307_D3_최장증가부분수열 {

	// D3 3307 최장증가부분수열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		int[] arr;

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			arr = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (min > arr[i])
					min = arr[i];
			}

			int[] lis = new int[n];
			Arrays.fill(lis, min);
			int lastIdx = 0;
			lis[0] = arr[0];
			// binarysearch : 해당 key를 찾으면 그 위치를 리턴하고, 그렇지 않으면 insertion point-1를 리턴
			for (int i = 1; i < n; i++) {
				if (arr[i] > lis[lastIdx]) {
					lis[++lastIdx] = arr[i];
				} else {
					int idx = Arrays.binarySearch(lis, 0, lastIdx + 1, arr[i]);
					if (idx < 0)
						idx = idx * -1 - 1;
					lis[idx] = arr[i];
//					System.out.println(arr[i] + ": " + idx);
				}
				
			}
			System.out.println(Arrays.toString(lis));
			sb.append("#").append(t).append(" ").append(lastIdx + 1).append("\n");
		}
		System.out.println(sb);

	}

	private static int binarySearch0(Object[] a, int fromIndex, int toIndex, Object key) {
		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			@SuppressWarnings("rawtypes")
			Comparable midVal = (Comparable) a[mid];
			@SuppressWarnings("unchecked")
			int cmp = midVal.compareTo(key);

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}

}
//2
//5
//1 3 2 5 4
//6
//4 2 3 1 5 6
