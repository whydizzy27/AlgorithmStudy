package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class swexpert_����3 {
	static Stack<Character> before;
	static ArrayList<Character> temp;
	static Stack<Integer> after;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		before = new Stack<>();
		temp = new ArrayList<>();
		after = new Stack<>();
		for (int u = 0; u < 10; u++) {
			// ����
			int len = Integer.parseInt(br.readLine());
			// ����ǥ����� �迭
			String str = br.readLine();
//			System.out.println(str);
			for (int i = 0; i < len; i++) {
				char ch = str.charAt(i);
				if (ch == '(') {
					before.push(ch);
				} else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
					priority(ch, before.peek());
				}

				else if (ch == ')') {
					while (before.peek() != '(') {
						temp.add(before.pop());
					}
					before.pop();
				} else
					// ���ڸ� ����Ʈ�� �ֱ�
					temp.add(ch);
			}
			// ó�� ��ȣ �ȳ����� �� ���
			if (!before.isEmpty()) {
				while (!before.isEmpty())
					temp.add(before.pop());
			}
//		System.out.println(temp);
			for (int i = 0; i < temp.size(); i++) {
				char h = temp.get(i);

				if (h == '+') {
					after.push(after.pop() + after.pop());

				} else if (h == '-') {
					int x = after.pop();
					int y = after.pop();
					after.push(y - x);
				} else if (h == '*') {
					after.push(after.pop() * after.pop());
				} else if (h == '/') {
					int x = after.pop();
					int y = after.pop();
					after.push(y / x);
				} else {
					after.push(h - '0');
				}
			}
			bw.write("#"+(u+1)+" "+String.valueOf(after.pop())+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

	public static void priority(char c, char p) {
		int cp = 0;// ���� �� �켱������
		int pp = 0;// ��ũ �� �켱������
		if (c == '*' || c == '/')
			cp = 2;
		else if (c == '+' || c == '-')
			cp = 1;
		else
			cp = 0;
		if (p == '*' || p == '/')
			pp = 2;
		else if (p == '+' || p == '-')
			pp = 1;
		else
			pp = 0;
		if (cp > pp)
			before.push(c);
		else {
			temp.add(before.pop());
			before.push(c);
		}
	}

}
