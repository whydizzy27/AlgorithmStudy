package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Ger {
	int x, y, life, remLife;
	boolean alive, dead, now;

	public Ger(int x, int y, int life, int remLife, boolean alive, boolean dead, boolean now) {
		super();
		this.x = x;
		this.y = y;
		this.life = life;
		this.remLife = remLife;
		this.alive = alive;
		this.dead = dead;
		this.now = now;
	}

}

public class SWEA_5653_줄기세포배양 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M;
	static Ger map[][];
	static ArrayList<Ger> list, remove, germList;

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
			map = new Ger[1001][1001];
			germList = new ArrayList<Ger>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int life = Integer.parseInt(st.nextToken());
					if (life == 0)
						continue;
					germList.add(map[500 - N / 2 + i][500 - M / 2 + j] = new Ger(500 - N / 2 + i, 500 - M / 2 + j, life,
							life, false, false, false));
				}
			}

			for (int k = 0; k < K; k++) {
				list = new ArrayList<Ger>();
				remove = new ArrayList<Ger>();
				for (Ger g : germList) {
//					if (g.dead)
//						continue;
					bfs(g);

				}

				for (Ger g : list) {
					bunsik(g);
					g.remLife -= 1;
					if (g.remLife == 0) {
						//g.dead = true;
						germList.remove(g);
					}
				}
				for (Ger g : remove) {
					g.now = false;
					germList.add(g);
				}
//				System.out.println(germList.size());
			}
			int cnt = germList.size();
//			int cnt = 0;
//			for (Ger g : germList) {
//				// System.out.println(g.dead);
//				if (!g.dead)
//					cnt++;
//			}
			// 처음 x시간동안 비활성화
			// 그 시간 지나면 활성화
			// 활성화 시 x시간 동안 생존. 이후 사망
			// 죽은 상태로 칸 차지

			// 1시간마다 상하좌우 번식
			// 번식 시 비활성상태
			// 번식은 자기 생명력과 동일
			// 이미 세포 존재 시 번식X
			// 동시 같은 칸 동시 번식하려할 떼 생명력수치 높은 애가 점령
			// 죽은 세포 말고 비활성 + 활성 수 구하기

			bw.write("#" + tc + " " + String.valueOf(cnt) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bunsik(Ger g) {
		for (int i = 0; i < 4; i++) {
			int nx = g.x + dx[i];
			int ny = g.y + dy[i];

			if (nx >= 0 && nx < 1001 && ny >= 0 && ny < 1001) {
				Ger ng = map[nx][ny];
				if (ng == null) {
					map[nx][ny] = new Ger(nx, ny, g.life, g.life, false, false, true);
					remove.add(map[nx][ny]);
				} else if (ng.now) {
					if (ng.life < g.life) {
						remove.remove(ng);
						map[nx][ny] = new Ger(nx, ny, g.life, g.life, false, false, true);
						remove.add(map[nx][ny]);
					}
				}
			}

		}
	}

	private static void bfs(Ger g) {

		if (!g.alive && g.remLife != 0) {
			g.remLife -= 1;
			if(g.remLife == 0) {
				g.alive = true;
				g.remLife = g.life;
			}
		} else if (g.alive && g.remLife != 0) {
			list.add(g);
		}
	}

}
