package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Integer[][] arr = new Integer[M][3];
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			arr[j][0] = Integer.parseInt(st.nextToken());
			arr[j][1] = Integer.parseInt(st.nextToken());
			arr[j][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr,new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[2]-o2[2];
			}
		});

		parents = new int[N+1];
		Arrays.fill(parents, -1);
		
		int min=0;
		int k=0;
		int[] road = new int[N-1];
		for (int i = 0; i < M; i++) {
			if(!union(arr[i][0],arr[i][1]))
				continue;
			
			min+=arr[i][2];
			road[k]=arr[i][2];
			k++;
		}
		Arrays.sort(road);
		
		bw.write(String.valueOf(min-road[road.length-1]) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if(xRoot!=yRoot) {
			parents[yRoot] = xRoot;
			return true;
		}
		return false;
	}

	private static int find(int x) {
		if(parents[x]<0) return x;
		
		return parents[x]=find(parents[x]);
	}

}
