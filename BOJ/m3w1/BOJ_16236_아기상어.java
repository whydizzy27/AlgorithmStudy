package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Shark implements Comparable<Shark> {
	int x, y, size, cnt, time;

	public Shark(int x, int y, int size, int cnt, int time) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.cnt = cnt;
		this.time = time;
	}

	@Override
	public int compareTo(Shark o) {
		if (this.x == o.x)
			return this.y - o.y;
		return this.x - o.x;
	}

}

public class BOJ_16236_아기상어 {
	static int N, time, size = 2, cnt;
	static boolean[][] visited;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static LinkedList<Shark> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		Shark shk = null;
		int startX = 0;
		int startY = 0;
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == 9) {
					// 상어 초기위치 잡기
					startX = i;
					startY = j;
					// 상어위치 0으로 초기화. 이유 : 어차피 돌아다닐거라..
					map[i][j] = 0;
				}
			}
		}

		while (findFish()) {
			list = new LinkedList<Shark>();
			visited = new boolean[N][N];
//			System.out.println(startX + " " + startY + " " + size + " " + time);
			shk = new Shark(startX, startY, size, cnt, time);
			bfs(shk);
			if(list.size()==0) break;
			Collections.sort(list);
			Shark shy = list.get(0);
			startX = shy.x;
			startY = shy.y;

			map[startX][startY] = 0;

			if (shy.cnt + 1 == shy.size) {
				size = shy.size + 1;
				time = shy.time;
				cnt = 0;
			} else {
				size = shy.size;
				time = shy.time;
				cnt = shy.cnt + 1;
			}

		}
//		System.out.println(map[N-1][0]);
		bw.write(String.valueOf(time));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Shark shk) {
		int limit = Integer.MAX_VALUE;
		Queue<Shark> q = new LinkedList<Shark>();
		q.offer(shk);
		visited[shk.x][shk.y] = true;
		while (!q.isEmpty()) {
			Shark s = q.poll();
//			System.out.println(s.x + " " +s.y);
			// 더이상 먹을게 없을 때 ans반환. 방법 : 맵전체 순회
			if (s.time > limit)
				continue;

			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];

				// 범위밖
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				// 방문??
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] > s.size)
					continue;
				// 자기자신보다 크면 못지나감. 같으면 지나갈수있음. 작으면 먹음
				// 자기자신 밑이면 먹고 자기 카운트 추가. 카운트가 사이즈랑 같아지면 size+1,카운트 초기화0. 먹은 물고기는 0 초기화

				if (map[nx][ny] == 0 || map[nx][ny] == s.size) {
					visited[nx][ny] = true;
					q.offer(new Shark(nx, ny, s.size, s.cnt, s.time + 1));
				} else if (map[nx][ny] < s.size) {
					if (limit > s.time)
						limit = s.time;
					list.add(new Shark(nx, ny, s.size, s.cnt, s.time + 1));
					visited[nx][ny] = true;
				}

			}

		}
		// 비지티드 어떻게 넣을건지
		// 가까운물고기부터 간다.(거리 같을 시 위 우선. 위 동일시 좌우선)
		// 거리 구하는 함수 제작

	}

	private static boolean findFish() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && map[i][j] < size) {
					return true;
				}
			}
		}
		// 물고기 아직 있었다.
		return false;
	}

}