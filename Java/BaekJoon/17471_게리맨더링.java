/*
(17471) 게리맨더링 / 난이도 : 골드 3
시간 : 136ms / 메모리 : 17068KB 
아이디어 : 부분집합으로 만들고, 연결되어있는지 확인한 후, 해당하면 인구수 차이 최솟값을 갱신합니다.
*/
import java.util.*;
import java.io.*;
public class Main
{
    static int n; //선거구 수
    static int[] population; //인구수 배열 
    static List<Integer>[] relation; //관계 배열 
    static boolean selected[]; //부분집합 배열 
    static boolean visited[]; //방문정보 배열 
    static int result; //정답답
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
	    population = new int[n];
	    selected = new boolean[n]; //부분집합 만들 때 사용
	    result = Integer.MAX_VALUE; //인구차이(정답답)
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    //인구수 입력받기기
	    for(int i = 0 ; i < n; i++){
	        population[i] = Integer.parseInt(st.nextToken());
	    }
	    //relation list 할당하고 초기화하기
	    relation = new List[n];
	    for(int i = 0 ; i < n ; i++){
	        relation[i] = new ArrayList<>();
	    }
	    //관계 입력받기 
	    for(int i = 0 ; i < n; i++){
	        st = new StringTokenizer(br.readLine());
	        int num = Integer.parseInt(st.nextToken());
	        for(int j = 0 ; j < num; j++){
	            relation[i].add(Integer.parseInt(st.nextToken())-1);
	        }
	    }
	    divide(0); //선거구 나누기
	    if (result == Integer.MAX_VALUE) result = -1;
	    System.out.println(result);
	    
	}
	//부분집합으로 실행해보기
	static void divide(int idx){
	    if(idx == n){
	        //부분집합 다 만들었으면 a선거구, b선거구 리스트에 넣기
	        List<Integer> aList = new ArrayList<>();
	        List<Integer> bList = new ArrayList<>();
	        for(int i = 0 ; i < n ; i++){
	            if(selected[i]) aList.add(i);
	            else bList.add(i);
	        }
	        //한지역에 몰빵하면안돼
	        if(aList.size() == 0 || bList.size() == 0) return;
	        //두 구역이 각각 연결되었는지 확인
	        if(bfs(aList) && bfs(bList)){
	            getPopDiff();
	        }
	        return;
	    }
	    //한 지역구를 선택하냐, 선택하지않느냐 분기 나누기
	    selected[idx] = true;
	    divide(idx+1);
	    selected[idx] = false;
	    divide(idx+1);
	}
	//연결되어있는지 확인하는 bfs 메서드
	static boolean bfs(List<Integer> list){
	    Deque<Integer> q = new ArrayDeque<>();
	    visited = new boolean[n];
	    visited[list.get(0)] = true;
	    q.add(list.get(0));
	    
	    int cnt = 1; // 방문한 지역 개수
	    while(!q.isEmpty()){
	        int cur = q.poll();
	        for(int i = 0 ; i < relation[cur].size(); i++){
	            int temp = relation[cur].get(i);
	            //선거구에 해당하고, 아직 미방문이라면면
	            if(list.contains(temp) && !visited[temp]){
	                q.add(temp);
	                visited[temp] = true;
	                cnt++;
	            }
	        }
	    }
	    if(cnt == list.size()) return true; //선거구의 지역수와 방문한 지역수가 같은경우(다연결된경우)
	    else return false;
	}
	//인구차 구하기 (최솟값 갱신 포함함)
	static void getPopDiff(){
	    int pA = 0, pB = 0;
	    for(int i = 0 ; i < n; i++){
	        if(selected[i]) pA += population[i];
	        else pB += population[i];
	    }
	    int diff = Math.abs(pA-pB);
	    result = Math.min(result,diff);
	}
}
