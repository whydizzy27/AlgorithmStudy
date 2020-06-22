package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Won {
	int x, y;

	public Won(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_17822_원판돌리기 {
	static int[][] map;
	static int N, M, sum, remaincnt;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		remaincnt = N*M;
		map = new int[N + 1][M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum+=map[i][j];
			}
		}
		int[][] turnInfo = new int[T][3];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			// 배수 방향 칸
			// 방향 0이면 시계 1이면 반시계
			turnInfo[i][0] = Integer.parseInt(st.nextToken());
			turnInfo[i][1] = Integer.parseInt(st.nextToken());
			turnInfo[i][2] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝

		for (int k = 0; k < T; k++) {
			// 일단 회전
			rotate(turnInfo[k][0], turnInfo[k][1], turnInfo[k][2]);

			// 인접기준은 같은 원판과 원판 간 열 => 즉 같은 행 열 bfs. 돈 후 그 애들 -1 만듬

//			
			check = false;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == -1)
						continue;
					bfs(new Won(i, j));
//					System.out.println("교체");
				}
			}
			// 없는 경우엔 평균 구할 때 더블형 구하고 평균보다 큰 수면 1빼고 작으면 1 더함.
			if (!check) {
//				int sum = 0;
//				int cnt = 0;
//				for (int i = 1; i <= N; i++) {
//					for (int j = 0; j < M; j++) {
//						if (map[i][j] == -1)
//							continue;
//						sum += map[i][j];
//						cnt++;
//					}
//				}
				// 평균 구할때 -1과 0인덱스는 세지 않음.
				if (remaincnt != 0) {
					double avg = (double) sum / remaincnt;
					for (int i = 1; i <= N; i++) {
						for (int j = 0; j < M; j++) {
							if (map[i][j] == -1)
								continue;
							if (map[i][j] > avg) {
								sum--;
								map[i][j]--;
							}
							else if (map[i][j] < avg) {
								sum++;
								map[i][j]++;
							}
						}
					}
				}
			}
//			
		}

		// 최종 회전 마치고 원판에 적힌 수의 합
//		int ans = 0;
//		for (int i = 1; i <= N; i++) {
//			for (int j = 0; j < M; j++) {
//				if (map[i][j] == -1)
//					continue;
//				ans += map[i][j];
//			}
//		}

		bw.write(String.valueOf(sum) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Won won) {
		Queue<Won> q = new LinkedList<>();
		q.offer(won);
		boolean first = false;
		int value = map[won.x][won.y];
		map[won.x][won.y]=-1;
		sum-=value;
		remaincnt--;
		while (!q.isEmpty()) {
			Won w = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = w.x + dx[i];
				int ny = w.y + dy[i];

				if (nx < 1 || nx > N)
					continue;

				if (ny < 0)
					ny = M-1;
				else if (ny >= M)
					ny = 0;

				if (map[nx][ny] == value) {
					map[nx][ny] = -1;
					sum-=value;
					remaincnt--;
					check = true;
					first = true;
					q.offer(new Won(nx, ny));
				}
			}
			
			if (!first) {
				map[w.x][w.y] = value;
				sum+=value;
				remaincnt++;
			}
		}

	}

	private static void rotate(int bae, int dir, int how) {
		// 시계 방향
		if (dir == 0) {
			for (int i = bae; i <= N; i += bae) {
					
					int[] temp = map[i].clone();
					for (int j = 0; j < M; j++) {
						int h = (j + how) % M;
						map[i][h] = temp[j];
					}
			}
		}
		// 반시게
		else {
			for (int i = bae; i <= N; i += bae) {
				int[] temp = map[i].clone();
				for (int j = 0; j < M; j++) {
					int h = (j + M-how) % M;
					map[i][h] = temp[j];
				}
			}
		}

	}

}
