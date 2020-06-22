package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_1043_거짓말 {
	static int N, M, max;
	static boolean[] visited;
	static boolean[] partyvisited;
	static ArrayList<Integer>[] party;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		visited = new boolean[N + 1];
		partyvisited = new boolean[M];

		// 거짓말 아는 사람 수
		String[] str2 = br.readLine().split(" ");

		if (Integer.parseInt(str2[0]) == 0)
			max = M;
		else {

			// 거짓말 아는 사람 명단
			for (int i = 0; i < Integer.parseInt(str2[0]); i++) {
				visited[Integer.parseInt(str2[i + 1])] = true;
			}
//			System.out.println(Arrays.toString(visited));
			party = new ArrayList[M];
			// 배열 총 크기는 M
			for (int i = 0; i < M; i++) {
				String[] str3 = br.readLine().split(" ");
				int pn = Integer.parseInt(str3[0]);
				party[i] = new ArrayList<Integer>();

				if (pn == 0)
					party[i].add(0);
				else {

					for (int j = 0; j < pn; j++) {
						party[i].add(Integer.parseInt(str3[j + 1]));
					}
				}
			}

//		System.out.println(Arrays.toString(party));

//		System.out.println(Arrays.toString(visited));
			Solution(0);

			for (int i = 0; i < M; i++) {
				if (!partyvisited[i])
					max++;
			}
		}

		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();

	}

	private static void Solution(int idx) {

		L:while (true) {
//			System.out.println(idx);
			if (idx == M)
				return;

			if (!partyvisited[idx]) {

				for (int i = 0; i < party[idx].size(); i++) {
					if (visited[party[idx].get(i)]) {
						for (int k : party[idx]) {
							visited[k] = true;
						}
						partyvisited[idx] = true;
						idx = 0;
						continue L;
					}

				}
			}

			idx++;
		}

	}

}
