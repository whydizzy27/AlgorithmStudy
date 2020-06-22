package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SWEA_1808_지희의고장난계산기 {

	static int min, tarLen, target;
	static int[] visited;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			String[] str = br.readLine().split(" ");

			visited = new int[10];
			for (int i = 0; i < 10; i++) {
				visited[i] = Integer.parseInt(str[i]);
			}

			list = new ArrayList<Integer>();
			String s = br.readLine();
			target = Integer.parseInt(s);
			tarLen = s.length();

			makeNum(0, 0);

			min = Integer.MAX_VALUE;
			dfs(target, 0);
			if (min == Integer.MAX_VALUE) {
				min = -2;
			}
			bw.write("#" + tc + " " + String.valueOf(min + 1) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int stat, int cnt) {
		if (stat == 0)
			return;

		String s = String.valueOf(stat);
		int len = s.length();

		if (len + cnt > min)
			return;

		// 명단안에 잇으면 ///////////////////////////
		if (list.contains(stat)) {
			if (min > len + cnt)
				min = len + cnt;
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) <= stat && list.get(i) != 1 && list.get(i) != 0)
				if (stat % list.get(i) == 0)
					dfs(stat / list.get(i), cnt + 1 + String.valueOf(list.get(i)).length());
		}

	}

	private static void makeNum(int idx, int itemp) {
		if (itemp > target)
			return;

		if (idx < tarLen) {
				list.add(itemp);
		}

		if (idx == tarLen) {
			list.add(itemp);
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (visited[i] == 1) {
				makeNum(idx + 1, itemp * 10 + i);
			}
		}
	}

}
