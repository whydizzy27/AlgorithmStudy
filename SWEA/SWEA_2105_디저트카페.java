package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_2105_디저트카페 {
	static int[][] map;
	static int max, startX, startY, N;
	static boolean[] visited, typeVisited;
	static int[] dx = { -1, 1, 1, -1 };
	static int[] dy = { 1, 1, -1, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			// 사방 방문
			visited = new boolean[4];
			typeVisited = new boolean[101];

			max = 0;

			for (int i = 0; i < N; i++) {
				startX = i;
				for (int j = 0; j < N; j++) {
					startY = j;
					typeVisited[map[i][j]] = true;
					// 자기지점 스타트니까 자기포함 1시작
					dfs(i, j, -1, 1);

					typeVisited[map[i][j]] = false;

				}
			}

			if (max == 0)
				max = -1;

			bw.write("#" + tc + " " + String.valueOf(max) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int i, int j, int dir, int cnt) {

		// 갓다온 방향 체크
		// 4방 탐색
		
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			// 범위 밖 컷
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			// 지나온적있으면 컷
			if (typeVisited[map[nx][ny]])
				if (nx == startX && ny == startY && cnt > 2) {
					if (max < cnt)
						max = cnt;
					continue;
				} else 
					continue;
				

			if (!visited[k] || dir == k) {
				typeVisited[map[nx][ny]] = true;
				visited[k] = true;
				dfs(nx, ny, k, cnt + 1);
				typeVisited[map[nx][ny]] = false;
				visited[k] = false;
			}

			if (dir != -1)
				visited[dir] = true;
		}

	}

}
