package m3w2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Spot {
	int x, y;

	public Spot(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

class Sipot {
	int x, y, cnt;

	public Sipot(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

}

public class BOJ_2589_보물섬 {
	static char[][] map;
	static int R, C, max;
	static boolean[][] visited;
	static boolean[][] minivisited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<ArrayList<Spot>> listArr;
	static ArrayList<Spot> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");

		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);

		map = new char[R][C];
		visited = new boolean[R][C];
		minivisited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		listArr = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'W' || visited[i][j])
					continue;
				list = new ArrayList<Spot>();
				bfs(new Spot(i, j));
				visited[i][j] = true;

				listArr.add(list);
			}
		}
		max = Integer.MIN_VALUE;

		for (int i = 0; i < listArr.size(); i++) {
			calc(listArr.get(i));
		}

		bw.write(String.valueOf(max) + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Spot spot) {
		Queue<Spot> q = new LinkedList<>();
		q.offer(spot);
		visited[spot.x][spot.y] = true;
		list.add(spot);
		while (!q.isEmpty()) {
			Spot s = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C)
					if (map[nx][ny] == 'L' && !visited[nx][ny]) {
						visited[nx][ny] = true;
						list.add(new Spot(nx, ny));
						q.offer(new Spot(nx, ny));
					}
			}
		}
	}

	private static int bfs2(Spot sA) {
		Queue<Sipot> q = new LinkedList<>();
		int max = 0;
		q.offer(new Sipot(sA.x, sA.y, 0));
		minivisited[sA.x][sA.y] = true;
		while (!q.isEmpty()) {
			Sipot s = q.poll();
			if (s.cnt > max) {
				max=s.cnt;
			}
			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C)
					if (map[nx][ny] == 'L' && !minivisited[nx][ny]) {
						minivisited[nx][ny] = true;
						q.offer(new Sipot(nx, ny, s.cnt + 1));
					}
			}
		}

		return max;
	}

	private static void calc(ArrayList<Spot> arr) {
		int value = 0;
		for (int i = 0; i < arr.size(); i++) {
			value = bfs2(arr.get(i));
			if (max < value)
				max = value;

			for (int k = 0; k < R; k++) {
				Arrays.fill(minivisited[k], false);
			}
		}

	}
}
