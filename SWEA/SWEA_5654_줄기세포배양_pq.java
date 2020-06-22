package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Ger implements Comparable<Ger> {
	int x, y, life, remLife;

	public Ger(int x, int y, int life, int remLife) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.remLife = remLife;
	}

	@Override
	public int compareTo(Ger arg0) {
		return Integer.compare(arg0.life, this.life);
	}
}

public class SWEA_5654_줄기세포배양_pq {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M,cnt, map[][];
	static PriorityQueue<Ger> pq;
	static boolean[][] visited;
	static LinkedList<Ger> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[700][700];
			pq = new PriorityQueue<Ger>();
			list = new LinkedList<Ger>();
			visited = new boolean[700][700];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int life = Integer.parseInt(st.nextToken());
					if (life == 0)
						continue;
					pq.offer(new Ger(350 - N / 2 + i, 350 - M / 2 + j, life, life));
					visited[350 - N / 2 + i][350 - M / 2 + j] = true;
				}
			}
			for (int k = 0; k <= K; k++) {
				cnt=0;
				list = new LinkedList<Ger>();
				bfs();
				if(k==K) break;
				for (Ger g : list) {
					pq.offer(g);
				}
			}
			bw.write("#" + tc + " " + String.valueOf(cnt) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs() {
		while (!pq.isEmpty()) {
			Ger g = pq.poll();
			
			cnt++;
			if (g.remLife > 0) {
				g.remLife -= 1;
				list.add(g);
				continue;
			} else {
				g.remLife -= 1;
				if (g.remLife != -g.life) {
					list.add(g);
				}
				if(visited[g.x][g.y] && g.remLife != -1) continue;

				for (int i = 0; i < 4; i++) {
					int nx = g.x + dx[i];
					int ny = g.y + dy[i];

					if (nx >= 0 && nx < 700 && ny >= 0 && ny < 700) {
						if (!visited[nx][ny]) {
							visited[nx][ny]=true;
							list.add(new Ger(nx, ny, g.life,g.life));
						}
					}
				}
			}
		}

	}

}
