package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// A전봇대에서 나오는 순서로 B대응값 정렬한 배열을 만든 후
// 거기서 LIS 사용하여 최장 증가 수열을 구한다. 그럼 그 나머지가 필요없는 것들이다.
public class BOJ_2568_전깃줄2 {
	static class Elc{
		int a,b;

		public Elc(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Elc[] arr = new Elc[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i]=new Elc(a, b);
		}
		
		Arrays.sort(arr, new Comparator<Elc>() {

			@Override
			public int compare(Elc e1, Elc e2) {
				return Integer.compare(e1.a, e2.a);
			}
		});

		int[] C = new int[N];
		int[] path = new int[N];
		boolean[] visited = new boolean[N];
		
		int size=0;
		
		path[size]=-1;
		C[size++]=0;
		
		for (int i = 1; i < N; i++) {
			if(arr[C[size-1]].b<arr[i].b) {
				path[i]=C[size-1];
				C[size++]=i;
			}
			else {
				int temp = binarySearch0(arr, C, 0, size, arr[i].b);
				if(temp<0) temp = -temp-1;
				path[i]=path[C[temp]];
				C[temp]=i;
			}
		}
		for (int i = C[size-1]; i!=-1 ; i=path[i]) { //가장 큰 값의 인덱스부터 역추적
			visited[i]=true;
		}
		bw.write((N-size) + "\n");
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				bw.write(arr[i].a + "\n");
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static int binarySearch0(Elc[] arr, int[] C, int fromIndex, int toIndex, int key) {
		int low = fromIndex; //이건 인덱스고
		int high = toIndex - 1;
		
		while(low <= high) {
			int mid = (low + high) >>> 1; //나누기 2를 쉬프트 연산으로 함.
			int midVal = arr[C[mid]].b;
			
			if(midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid -1;
			else
				return mid; // key found
		}
		return -(low+1); // key not found
				
	}
}