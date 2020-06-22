package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Isl {
	int x, y;

	public Isl(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

class Info implements Comparable<Info> {
	int start, end, dist;

	public Info(int start, int end, int dist) {
		this.start = start;
		this.end = end;
		this.dist = dist;
	}

	@Override
	public int compareTo(Info arg0) {
		return Integer.compare(this.dist, arg0.dist);
	}

}

public class BOJ_17472_다리만들기2 {
	static int N, M, map[][], islNo, parents[];
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Isl>[] list;
	static PriorityQueue<Info> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		list = new ArrayList[7];

		for (int i = 1; i <= 6; i++) {
			list[i] = new ArrayList<Isl>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 번호 붙이기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j]==0 || visited[i][j])
					continue;
				islNo++;
				map[i][j]=islNo;
				bfs(new Isl(i, j));
			}
		}
		
		pq = new PriorityQueue<Info>();

		// 만들 수 있는 다리 전부 넣기
		for (int i = 1; i <= islNo; i++) {
			for (Isl isl : list[i]) {
				search(isl);
			}
		}
		
		// 크루스칼 시작
		parents = new int[islNo];
		Arrays.fill(parents, -1);

		int min = 0;
		// 간선개수
		int cnt = 0;
		while (!pq.isEmpty()) {
			if (cnt == islNo - 1)
				break;
			Info info = pq.poll();
			int start = info.start-1;
			int end = info.end-1;
			if (!union(start, end))
				continue;
			min += info.dist;
			cnt++;
		}
	
		if(min==0 || cnt != islNo-1)
			min=-1;

		bw.write(String.valueOf(min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void search(Isl isl) {
		int start = map[isl.x][isl.y];
		for (int i = 0; i < 4; i++) {
			int nx = isl.x;
			int ny = isl.y;
			int dist = 0;
			while (true) {
				nx += dx[i];
				ny += dy[i];
				dist++;

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					break;
				if (map[nx][ny] == start)
					break;
				if (map[nx][ny] != 0) {
					if (dist > 2) {
						pq.offer(new Info(start, map[nx][ny], dist-1));
						break;
					} else {
						break;
					}
				}
			}
		}

	}

	private static void bfs(Isl isl) {
		Queue<Isl> q = new LinkedList<Isl>();
		q.offer(isl);
		visited[isl.x][isl.y] = true;
		list[islNo].add(isl);

		while (!q.isEmpty()) {
			Isl is = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = is.x + dx[i];
				int ny = is.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M)
					if (!visited[nx][ny] && map[nx][ny] == 1) {
						visited[nx][ny] = true;
						map[nx][ny] = islNo;
						list[islNo].add(new Isl(nx, ny));
						q.offer(new Isl(nx, ny));
					}
			}
		}
	}

	private static int find(int x) {
		if (parents[x] < 0)
			return x;
		return parents[x] = find(parents[x]);
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

}
