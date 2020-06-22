package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1808_지희의고장난계산기_약수사용 {
	static ArrayList<Integer> list;
	static int min;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			String[] str = br.readLine().split(" ");

			int[] visited = new int[10];
			for (int i = 0; i < 10; i++) {
				visited[i] = Integer.parseInt(str[i]);
			}

			int target = Integer.parseInt(br.readLine());

			Queue<Integer> q = new LinkedList<>();

			// 약수찾기
			for (int i = 1; i <= Math.sqrt(target); i++) {
				if (target % i == 0) {
					q.offer(i);
					q.offer(target / i);
				}
			}
			// 큐에 약수가 들어있는 상황
			// 가용 약수 구하기.(부서진 버튼 제외)
			list = new ArrayList<Integer>();

			L: while (!q.isEmpty()) {
				int temp = q.poll();
				String s = String.valueOf(temp);

				for (int i = 0; i < s.length(); i++) {
					if (visited[s.charAt(i) - '0'] == 0)
						continue L;
				}

				list.add(temp);
			}
			list.add(1);
			// 제외
			min = Integer.MAX_VALUE;
			dfs(target, 1);

			if (min == Integer.MAX_VALUE)
				min = -1;

			bw.write("#" + tc + " " + String.valueOf(min) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int value, int cnt) {
		
		if (min < cnt)
			return;
		if (list.contains(value)) {
			if (min > cnt + String.valueOf(value).length())
				min = cnt + String.valueOf(value).length();
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (value % list.get(i) == 0 && list.get(i)!=1)
				dfs(value / list.get(i), cnt + 1 + String.valueOf(list.get(i)).length());
		}

	}

}
