package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_2105_디저트카페_백트래킹 {
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

			// 아래로만 사각형 조사할 것이므로 N-2행까지는 할 필요 없음 사각형 못만듬. 위로 해봐야 전 행에서 한 사각형과 겹치는 경우 생김.
			for (int i = 0; i < N - 2; i++) {
				startX = i;
				//0 열과 N-1 열도 밑에건 못만들고 위로가면 그 전행 사각형이랑 겹치니까 가지쳐줌
				for (int j = 1; j < N-1; j++) {
					startY = j;
					typeVisited[map[i][j]] = true;
					// 자기지점 스타트니까 자기포함 1시작
					dfs(i, j, -1, 1, 0);

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

	private static void dfs(int i, int j, int dir, int cnt, int change) {
		// 스타트 행이랑 같거나 위로 올라갈 경우 이미 윗 행에서 진행한 경우랑 같은 사각형 나오므로 가지치기
		if (i <= startX && cnt > 1)
			return;

		// 3번째 꺾었을 때 이때까지 쌓아온 cnt-2(꺾은후 현지점 & 꺽기전 마지막 지점 빼야함. 그래야 대칭) 가 max 값의 반도
		// 안되면(대칭이므로) 더 해볼것도 없이 리턴.
		// 3번째 꺾는다는 것이 곧 사각형 구역을 잡았다는 의미다. 참고로 max는 무조건 짝수다. 대칭이므로 홀수 불가.
		if (change >= 3)
			if (max / 2 >= cnt - 2)
				return;

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
				if (dir == k)
					dfs(nx, ny, k, cnt + 1, change);
				else
					dfs(nx, ny, k, cnt + 1, change + 1);
				typeVisited[map[nx][ny]] = false;
				visited[k] = false;
			}

			if (dir != -1)
				visited[dir] = true;
		}

	}

}
