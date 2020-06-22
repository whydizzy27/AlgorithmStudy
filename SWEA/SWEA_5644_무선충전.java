package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static int sum, A, bc[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			int[] moveA = new int[M];
			int[] moveB = new int[M];

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st1.nextToken());
				moveB[i] = Integer.parseInt(st2.nextToken());
			}
			bc = new int[A][4];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				// x y 범위 충전량
				bc[i][1] = Integer.parseInt(st.nextToken());
				bc[i][0] = Integer.parseInt(st.nextToken());
				bc[i][2] = Integer.parseInt(st.nextToken());
				bc[i][3] = Integer.parseInt(st.nextToken());
			}
			// 입력 끝
			sum = 0;

			// a b위치
			int ax = 1;
			int ay = 1;
			int bx = 10;
			int by = 10;

			sum += charge(ax, ay, bx, by);

			for (int i = 0; i < M; i++) {
				// 이동
				ax += dx[moveA[i]];
				ay += dy[moveA[i]];
				bx += dx[moveB[i]];
				by += dy[moveB[i]];

				sum += charge(ax, ay, bx, by);
			}
			bw.write("#" + tc + " " + String.valueOf(sum) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static int charge(int ax, int ay, int bx, int by) {
		// A B 사람과 충전소와의 위치 거리차이 저장배열
		// 충전범위 외면 충전불가이므로 0 set
		int[][] howChargeArr = new int[2][A];

		// A경우
		for (int i = 0; i < A; i++) {
			howChargeArr[0][i] = getChargeAmount(ax, ay, i);
		}

		// B경우
		for (int i = 0; i < A; i++) {
			howChargeArr[1][i] = getChargeAmount(bx, by, i);
		}
		
		//둘 고려해서 최대 총합 만들기
		int sum=0;
		int max=0;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				sum = howChargeArr[0][i]+howChargeArr[1][j];
				
				if(i==j && howChargeArr[0][i]==howChargeArr[1][j])
					sum/=2;
				
				if(max<sum)
					max=sum;
			}
		}
		return max;
	}

	private static int getChargeAmount(int x, int y, int bcNo) {
		int dist = Math.abs(x - bc[bcNo][0]) + Math.abs(y - bc[bcNo][1]);

		if (dist <= bc[bcNo][2])
			return bc[bcNo][3];
		else
			return 0;
	}

}
