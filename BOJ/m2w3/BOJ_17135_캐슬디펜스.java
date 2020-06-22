package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	int x;
	int y;
	int d;

	public Pair(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

}

public class BOJ_17135_캐슬디펜스 {
	static int[][] map;
	static int[][] copymap;
	static boolean[][] bfsVisited;
	static int[] temp;
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	static int N, M, D, cnt, totalCnt, max;
	static LinkedList<Pair> list;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		// �� �� �Ÿ�
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		D = Integer.parseInt(str[2]);
		bfsVisited = new boolean[N][M];
		// �� ����� & �� �ڸ� ���� n+1
		map = new int[N + 1][M];
		copymap = new int[N + 1][M];
		temp = new int[3];

		// ������ �Է�
		for (int i = 0; i < N; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str2[j]);
			}
		}
		// ���� �ü� ��� ��ġ�Ұ��� ���� ���
		combi(0, 0);
		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}

	// ���� �ϼ�
	public static void combi(int idx, int k) {
		if (idx == 3) {
			// cnt �ʱ�ȭ
			totalCnt = 0;
//				for (int i = 0; i < 3; i++) {
//					System.out.print(temp[i]+" ");
//				}
//				System.out.println();
			copy();
			arrange();
//				for (int i = 0; i < M; i++) {
//					System.out.print(copymap[N][i]+" ");
//				}
//				System.out.println();
			// n�� ������ ��
			for (int u = 0; u < N; u++) {
				cnt = 0;
				bfs();
				totalCnt += cnt;
//					System.out.println(cnt);
				down();

			}
//				System.out.println("-----");
			if (totalCnt > max)
				max = totalCnt;
			return;
		}
		for (int i = k; i < M; i++) {

			temp[idx] = i;
			combi(idx + 1, i + 1);
		}

	}

	public static void copy() {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				copymap[i][j] = map[i][j];
			}
		}
	}

	// �ü� ��ġ
	public static void arrange() {
		for (int i = 0; i < 3; i++) {
			copymap[N][temp[i]] = 1;
		}
	}

	public static void bfs() {
		for (int i = 0; i < M; i++) {

			if (copymap[N][i] == 1) {
				
				Queue<Integer> q = new LinkedList<>();
				q.offer(N);
				q.offer(i);
				q.offer(0);
//					System.out.println("����" +N +" "+ i);
				
				// �����Ÿ� �� ���� ��� ����Ʈ
				// ����Ʈ �ʱ�ȭ
				list = new LinkedList<>();
				while (!q.isEmpty()) {

					int x = q.poll();
					int y = q.poll();
					int d = q.poll();
					if(d==D) break;
					if(d<D&&!list.isEmpty()) break;
					for (int k = 0; k < 3; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
							// ���� 1 �̸�
							if (copymap[nx][ny] == 1) {
								list.add(new Pair(nx, ny, d+1));
							}
							q.offer(nx);
							q.offer(ny);
							q.offer(d+1);
						}
					}
				}

				// list�� ��������� �״�� ���� �ƴϸ� ���� ���� ģ�� ����
				if (!list.isEmpty()) {
					// �ϴ� �Ÿ� ���� �ֵ� �����̴ϱ� �� ���� ������ ����
					Collections.sort(list, new Comparator<Pair>() {

						@Override
						public int compare(Pair o1, Pair o2) {
							if(o1.d!=o2.d)
								return o1.d-o2.d;
							return o1.y - o2.y;
						}
					});
					// ���� ��ġ ��ĥ
					bfsVisited[list.get(0).x][list.get(0).y] = true;
				}

			}
		}

		for (int j = 0; j < N; j++) {
			for (int j2 = 0; j2 < M; j2++) {
				if (bfsVisited[j][j2]) {
					bfsVisited[j][j2] = false;
					copymap[j][j2] = 0;
					cnt++;
				}
			}
		}

	}

	// ���� �����´�!
	public static void down() {

		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				copymap[i][j] = copymap[i - 1][j];
			}
		}
		for (int j = 0; j < M; j++) {
			copymap[0][j] = 0;
		}
	}
}
