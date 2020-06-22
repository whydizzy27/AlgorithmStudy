package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class SWEA_1247_최적경로 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			N = Integer.parseInt(br.readLine());

			String[] str = br.readLine().split(" ");
			Pair[] cusArr = new Pair[N+2];

			Pair start = new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			Pair end = new Pair(Integer.parseInt(str[2]), Integer.parseInt(str[3]));

			cusArr[0]=start;
			cusArr[N+1]=end;
			
			for (int i = 0; i < N; i++) {
				cusArr[i+1] = new Pair(Integer.parseInt(str[i * 2 + 4]), Integer.parseInt(str[i * 2 + 5]));
			}
			// 입력끝

			int[][] disInfo = new int[N + 2][N + 2];

			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					disInfo[i][j]=calcDist(cusArr[i], cusArr[j]);
				}
			}
			
			//조합
			int[] temp = new int[N];
			for (int i = 0; i < N; i++) {
				temp[i]=i+1;
			}
			
			Pair[] test = new Pair[N+2];
			test[0]=start;
			test[N+1]=end;

			int min = Integer.MAX_VALUE;
			do {
				
				for (int i = 0; i < N; i++) {
					test[i+1] = cusArr[temp[i]];
				}
				//출발지랑 첫배송지 값
				int sum = 0;
				
				sum += disInfo[0][temp[0]];
				//전부각자
				for (int i = 0; i < N-1; i++) {
					sum += disInfo[temp[i]][temp[i+1]];
				}
				//마지막배송지랑 도착지
				sum += disInfo[temp[N-1]][N+1];

				if(sum<min)
					min=sum;
			} while (np(temp));

			bw.write("#" + tc + " " + String.valueOf(min) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean np(int[] numbers) {
		int i = N - 1;

		while (i > 0 && numbers[i - 1] >= numbers[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;

		while (numbers[i - 1] > numbers[j])
			--j;

		int temp = numbers[i - 1];
		numbers[i - 1] = numbers[j];
		numbers[j] = temp;

		j = N - 1;
		while (i < j) {
			temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
			++i;
			--j;
		}
		return true;
	}

	private static int calcDist(Pair p1, Pair p2) {

		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);

	}

}
