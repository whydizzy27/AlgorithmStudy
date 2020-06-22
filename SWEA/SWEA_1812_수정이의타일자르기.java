package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

class Rec {
	int x, y;

	public Rec(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class SWEA_1812_수정이의타일자르기 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);

			ArrayList<Integer> list = new ArrayList<Integer>();

			ArrayList<Rec> remain = new ArrayList<Rec>();

			String[] str2 = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				list.add((int) (Math.pow(2, Integer.parseInt(str2[i]))));
			}

			// 크신 분 부터
			list.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

			int cnt = 1;
			remain.add(new Rec(M,M));
			for (int i = 0; i < N; i++) {
				int temp = list.get(i);
				boolean needNew = true;
				int len = remain.size();
				for (int j = 0; j < len; j++) {

					Rec r = remain.get(j);
					int rx = r.x;
					int ry = r.y;
					if (temp <= rx && temp <= ry) {
						needNew=false;
						
						int xdif = rx - temp;
						int ydif = ry - temp;
						if (rx != 0 && ydif != 0) {
							// 큰게 행으로 들어가야함
							// 첫번째 조각
							if (rx >= ydif)
								remain.add(new Rec(rx, ydif));
							else
								remain.add(new Rec(ydif, rx));
						}
						if (temp != 0 && xdif != 0) {
							// 두번째조각

							if (temp >= xdif)
								remain.add(new Rec(temp, xdif));
							else
								remain.add(new Rec(xdif, temp));
						}
						remain.remove(j);
						break;
						// 리무브 시 인덱스 안칸 땡기기
					}
				}
				if (needNew) {
					cnt++;
					remain.add(new Rec(M, M));
					i--;
				}
			}

			bw.write("#" + tc + " " + String.valueOf(cnt) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
