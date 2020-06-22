package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크연결 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Integer[][] arr = new Integer[M][3];
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			arr[j][0] = Integer.parseInt(st.nextToken());
			arr[j][1] = Integer.parseInt(st.nextToken());
			arr[j][2] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[2] - o2[2];
			}
		});

		int min = 0;
		int cnt=0;
		parents = new int[N + 1];
		Arrays.fill(parents, -1);

		for (int i = 0; i < M; i++) {
			//간선 N-1개가 MST조건이므로 완성시 뒤에 간선정보볼거 없이 가지쳐줌
			if(cnt==N-1) break;

			if (!union(arr[i][0], arr[i][1]))
				continue;

			min += arr[i][2];
			cnt++;
		}
		bw.write(String.valueOf(min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot != yRoot) {
			parents[yRoot] = xRoot;
			return true;
		}

		return false;
	}

	private static int find(int x) {
		if (parents[x] < 0)
			return x;

		return parents[x] = find(parents[x]);
	}

}
