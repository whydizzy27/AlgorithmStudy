package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Island {
	int a, b;
	double dist;

	public Island(int a, int b, double dist) {
		this.a = a;
		this.b = b;
		this.dist = dist;
	}
}

public class SWEA_1251_하나로 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// double이라 메모리 부담가므로 배열 재활용 택함. 문제 상황이 정점간 모든 간선 고려이므로 초기화도 필요없음.
		// 초기화가 필요없기 때문에 무조건 재활용하는게 이득.
		// 좌표 배열
		int[][] island = new int[1000][2];
		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			// 섬 개수
			int N = Integer.parseInt(br.readLine());

			String[] str = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(str[i]);
			}
			String[] str2 = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				island[i][1] = Integer.parseInt(str2[i]);

			}

			double E = Double.parseDouble(br.readLine());
			// 입력 종료

			// 간선리스트
			ArrayList<Island> list = new ArrayList<>();

			// 리스트에 두 정점 & 거리 객체 추가. 두 정점 중 작은 거 기준으로 트리 만들거라 i j를 오름차순으로 저장되게 for문 짬
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					list.add(new Island(i, j, distance(island[i][0], island[i][1], island[j][0], island[j][1])));
				}
			}

			// 거리가 짧은 순으로 정렬
			Collections.sort(list, new Comparator<Island>() {

				@Override
				public int compare(Island o1, Island o2) {
					// 컴퍼레이터 판별 기준이 int형이라 형변환
					return ((o1.dist - o2.dist)>=0)? 1:-1 ;
				}
			});
			// 유니온 파인드에 사용할 배열
			parents = new int[N];
			// -1로 초기화. -1이면 자기가 대표자 즉 부모다.
			Arrays.fill(parents, -1);

			double min = 0;
			//리스트 인덱스
			int k = 0;
			//간선개수
			int cnt = 0;
			// (최적)리스트 다돌기전 N-1개의 간선이 완성되거나 (최악)리스트 마지막놈까지 해야 N-1개 간선 완성 될 시 리스트 내 모든 간선 정보 유니온 파인드 진행
			// 간선 N-1개 완성 다 됐다면 남은 리스트 요소 유니온 계속해봐야 이미 merge된 상태이므로 시간낭비다. 가지쳐줌
			while (k != list.size() && cnt != N-1) {

				Island id = list.get(k);
				k++;

				int par = id.a;
				int son = id.b;
				// union 실패 시(서로 같은 parents값 가지면 이미 합병된 상태이므로) continue. 만약 안 제껴주면
				// 사이클이 생겨버리고 그건 MST가 아니게된다.
				if (!union(par, son))
					continue;

				// 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L2)만큼 지불
				min += E * Math.pow(id.dist, 2);
				cnt++;
			}
			// 소수 첫째자리에서 반올림
			bw.write("#" + tc + " " + String.valueOf(Math.round(min)) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	// 파인드 셋하기 전 유니온만 하면 패스 컴프레션 아직 일어나기 전임 고로 depth 그대로라 시간 손해 엄청나다.
	// 그래서 find set 때 패스 컴프레션 한다. 최적화하면 굳이 계단 다시 안타도 되니까 시간절약
	private static int find(int x) { // x원소가 속한 집합의 대표자(루트)를 반환
		if (parents[x] < 0)
			return x; // 자신이 루트(대표자)이면(음수 -1이면) 자신의 값 리턴
		
		//-1이 아니라면?? 얘가 대표자가 아니란 소리이므로 재귀 타주면서 대표자 찾으러간다.
		return parents[x] = find(parents[x]); // 최적화된 버전을 쓸거라했지. 그래서 자신의 부모를 최상단에서 구한 대표자로 삼고 그값까지 전 재귀로 리턴.
		//즉, 대표자 밑 모든 자식들의 parents 값을 전부 대표자로 지정해준다.
		// 이게 바로 Path compression. 계단 굳이 탈필요 없이 바로 대표자로 가는 하이패스 뚫어준거다. dp 처럼.
		// 내 위로 일어나는 과정이지.
	}

	// find함수 돌리면 결과 값이 출력된 거지
	// parents 배열에 아직 -1이 있다.
	// -1이 곧 대표자고 -1의 수가 곧 현 집합의 개수다!!!
	private static boolean union(int x, int y) { // union set
		int xRoot = find(x);
		int yRoot = find(y);
		//두 노드의 대표자가 같으면 이미 합쳐진 같은 식구이므로 유니온 하지 않는다. 다르면 유니온한다. 
		if (xRoot != yRoot) {
			//yRoot의 부모는 xRoot다라고 설정 => 이게 작은 노드를 기준으로 합치는거로 시작했기에 이렇게 설정한다.
			parents[yRoot] = xRoot; // 대표자 기준으로 합친다.
			return true;
		}
		return false;
	}

	private static double distance(int a, int b, int x, int y) {
		return Math.sqrt(Math.pow(a - x, 2) + Math.pow(b - y, 2));
	}

}
