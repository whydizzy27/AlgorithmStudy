package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

class Bang implements Comparable<Bang> {
	int value, cost;

	public Bang(int value, int cost) {
		this.value = value;
		this.cost = cost;
	}

	@Override
	public int compareTo(Bang o) {
		if (this.value == o.value) {
			return this.cost - o.cost;
		}
		return o.value - this.value;
	}

}

public class BOJ_1082_방번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 숫자 개수
		int N = Integer.parseInt(br.readLine());

		// 숫자 입력 받기
		String[] str = br.readLine().split(" ");
		Bang[] arr = new Bang[N];

		for (int i = 0; i < N; i++) {
			arr[i] = new Bang(i, Integer.parseInt(str[i]));
		}
		Arrays.sort(arr);
		int money = Integer.parseInt(br.readLine());

		int min = Integer.MAX_VALUE;
		int minValue = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i].cost < min) {
				min = arr[i].cost;
				minValue = arr[i].value;
			}
		}
		if (N == 1) {
			bw.write(String.valueOf(0));
		} else {

			int jarisu = money / min;
			int remain = money % min;
			StringBuilder sb = new StringBuilder();
			// 작은값으로 도배
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int i = 0; i < jarisu; i++) {
				list.add(minValue);
			}

			// 맨앞0아닐때
			if (minValue != 0) {

				int n = 0;

				while (n != jarisu) {
					int k = 0;
					while (k != N) {

						if (remain + min >= arr[k].cost) {
							list.set(n, arr[k].value);
							remain = remain + min - arr[k].cost;
							break;
						} else {
							k++;
						}
					}

					n++;

				}

				for (int i = 0; i < list.size(); i++) {
					sb.append(list.get(i));
				}
				// 출력
				bw.write(sb.toString());
			}
			// 맨앞0일때
			else {
				int n = 0;

				while (n != jarisu) {
					int k = 0;
					boolean check = false;
					remain += min;
//				System.out.println(remain);
					while (k != N) {

						if (remain >= arr[k].cost && arr[k].value != 0) {
							list.set(0, arr[k].value);
							remain -= arr[k].cost;
							check = true;
							break;
						} else {
							k++;
						}
					}
					if (check)
						break;

					list.remove();

					n++;

				}
//			System.out.println(list);
//			System.out.println(n);
				if (n == jarisu) {
					bw.write(String.valueOf(0));
				}

				else {
					int m = 1;

					while (m != list.size()) {
						int k = 0;
						while (k != N) {

							if (remain + min >= arr[k].cost) {
								list.set(m, arr[k].value);
								remain = remain + min - arr[k].cost;
								break;
							} else {
								k++;
							}
						}

						m++;

					}

					for (int i = 0; i < list.size(); i++) {
						sb.append(list.get(i));
					}
					// 출력
					bw.write(sb.toString());

				}
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
