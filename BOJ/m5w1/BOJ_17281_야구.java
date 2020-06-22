package m5w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	static int max, temp[], N, origin[][];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		origin = new int[N][9];
		visited = new boolean[9];
		temp = new int[9];
		temp[3] = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		// 타순 정하는 순열. 1번 선수가 무조건 4번 타자(인덱스는 3)
		permutation(0, 0);

		bw.write(String.valueOf(max) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void permutation(int idx, int flag) {
		if (idx == 3) {
			permutation(idx + 1, flag);
			return;
		}
		if (idx == 9) {
			int score = 0;
			// 오더 배열 만들기
//			int[][] order = new int[N][9];
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < 9; j++) {
//					order[i][j]=origin[i][temp[j]];
//				}
//			}
			// 루 배열

			// 다음 이닝 때도 순서 유지. 1로 초기화 안됨
			int cur = 0;
			L: for (int i = 0; i < N; i++) {
				boolean[] roo = new boolean[3];
				// 선수 돌림
				int out = 0;
				while (true) {

					for (int j = cur; j <= 9; j++) {

						// 3아웃 미발생 시 발생할때까지 타순 돌림
						if (j == 9)
							j = 0;
						int w = origin[i][temp[j]];
						// 아웃
						if (w == 0) {
							out++;
							// 3아웃 발생 시 이닝 종료
							if (out == 3) {
								cur=j+1;
								continue L;
							}
						}
						// 루 배열 둔다. 인덱스 넘으면 점수 올리는 식으로 쉬프팅
						// 홈런
//					else if(origin[i][temp[q]]==4) {
//						for (int j = 0; j < 3; j++) {
//							if(!roo[j]) continue;
//							roo[j]=false;
//							score++;
//						}
//						score++;
//					}
						// 1 2 3루타
						else {
							for (int x = 2; x >= 0; x--) {
								if (!roo[x])
									continue;
								if (x + w > 2) {
									roo[x] = false;
									score++;
								} else {
									roo[x + w] = true;
									roo[x] = false;
								}
							}
							if (w == 4) {
								score++;
							} else {
								roo[w - 1] = true;
							}
						}

//					cur++;
					}

				}
			}

			// 득점 최대 경우 구하기
			if (max < score)
				max = score;
			return;
		}

		for (int i = 1; i < 9; i++) {

			if ((flag & (1 << i)) != 0)
				continue;
			temp[idx] = i;
			permutation(idx + 1, flag | (1 << i));
		}
	}

}
