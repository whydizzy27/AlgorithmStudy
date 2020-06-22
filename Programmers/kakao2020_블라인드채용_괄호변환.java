import java.util.Stack;

public class kakao2020_블라인드채용_괄호변환 {

	public static void main(String[] args) {

		String s = "()))((()";
		System.out.println(solution(s));
	}

	public static String solution(String p) {
		//빈 문자열이면 그대로 출력
		if(p.equals("")) {
			return "";
		}
		
		if(isCorrect(p)) {
			return p;
		}
		
		String answer = dfs(p);
		
        return answer;
    }
	
	private static String dfs(String p) {
		if(p.equals("")) {
			return "";
		}
		int len = p.length();
		int open = 0;
		int close = 0;
		int idx = 0;
		for (int i = 0; i < len; i++) {
			char c = p.charAt(i);
			
			if(c=='(') 
				open++;
			else
				close++;
			
			if(open==close && open>0) {
				idx=i+1;
				break;
			}
		}
		String u = p.substring(0,idx);
		String v = p.substring(idx);
		
		if(isCorrect(u)) {
			return u+dfs(v);
		}
		else {
			String temp = "";
			temp+='('+dfs(v)+')';
			u=u.substring(1,u.length()-1);
			
			StringBuilder sb = new StringBuilder(u);
			for (int i = 0; i < sb.length(); i++) {
				char c = sb.charAt(i);
				if(c=='(')
					sb.setCharAt(i, ')');
				else
					sb.setCharAt(i, '(');
			}
			temp+=sb.toString();
			return temp;
		}
	}
	
	private static boolean isCorrect(String p) {
		int len = p.length();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			char c = p.charAt(i);

			if(stack.isEmpty()) {
				stack.push(c);
				continue;
			}
			if(c==')') {
				if(stack.peek()=='(') {
					stack.pop();
				}else {
					stack.push(c);
				}
			}
			else {
				stack.push(c);
			}
			
		}//end of for
		
		if(stack.isEmpty()) 
			return true;
		else
			return false;
	}

}
