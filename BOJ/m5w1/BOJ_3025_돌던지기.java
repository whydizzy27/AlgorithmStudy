package m5w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3025_돌던지기 {
	static char map[][];
	static int R, C;
	static Quick[] quick;

	static class Quick {
		int col[]; // r행 때는 어느 열에 위치하는가
		int r; // 장애물 위치

		public Quick() {
			this.col = new int[R];
			this.r = 1;
		}

		// 기존 저장돼있는 위치에 O 올리기
		public void insert() {
			map[r - 1][col[r - 1]] = 'O';
		}

		// 위치 변경 내용 갱신
		public void trim() {
			while (true) {
				// 장애물의 바로 위 위치의 열
				int cur = col[r - 1];

				// 빈 공간이 아니라면
				if (r > 1 && map[r - 1][cur] != '.') {
					r--;
				}
				// 장애물이 맨 밑까지 가면 break
				else if (r == R)
					break;

				// X면 장애물 도착이므로 break
				else if (map[r][cur] == 'X')
					break;
				else if (map[r][cur] == '.')
					col[r++] = cur;
				else {
					// 좌
					if (cur > 0 && map[r][cur - 1] == '.' && map[r - 1][cur - 1] == '.') {
						col[r++] = cur - 1;
					}
					// 우
					else if (cur + 1 < C && map[r][cur + 1] == '.' && map[r - 1][cur + 1] == '.') {
						col[r++] = cur + 1;
					} else
						break;

				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 입력
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 각 열마다 클래스 생성.
		quick = new Quick[C];
		for (int i = 0; i < C; i++) {
			quick[i] = new Quick();
			// 0행 : 공떨어뜨리는 지점. 고로 그 행.
			quick[i].col[0] = i;
			// 초기 상태 토대로 각 quick요소마다 col,r 갱신
			quick[i].trim();
		}

		// 던지는 횟수
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int select = Integer.parseInt(br.readLine()) - 1;

			// 저장 되있는 위치에 O 올림
			quick[select].insert();
			// 그 후 위치 변경 내용 갱신
			for (int j = 0; j < C; j++) {
				quick[j].trim();
			}
		}
		// 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
