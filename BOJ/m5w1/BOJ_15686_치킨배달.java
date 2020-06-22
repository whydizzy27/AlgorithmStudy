package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Chi {
	int x, y;

	public Chi(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15686_치킨배달 {
	static int N, M, min;
	static ArrayList<Chi> customerlist, chickenlist;
	static Chi[] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		customerlist = new ArrayList<Chi>();
		chickenlist = new ArrayList<Chi>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1)
					customerlist.add(new Chi(i, j));
				else if (value == 2)
					chickenlist.add(new Chi(i, j));
			}
		}

		temp = new Chi[M];
		min = Integer.MAX_VALUE;
		combination(0, 0);

		bw.write(String.valueOf(min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void combination(int idx, int cur) {
		if (idx == M) {
			int cost = 0;
			for (Chi home : customerlist) {
				int minn=Integer.MAX_VALUE;
				for (Chi chi : temp) {
					int sub = Math.abs(chi.x - home.x) + Math.abs(chi.y - home.y);
					if(sub<minn)
						minn=sub;
				}
				cost+=minn;
			}
			if (min > cost) {
				min = cost;
			}

			return;
		}

		for (int i = cur; i < chickenlist.size(); i++) {
			temp[idx] = chickenlist.get(i);
			combination(idx + 1, i + 1);
		}

	}

}
