/*
난이도 : D6 , 시간 : 111ms
아이디어 : 위상정렬을 이용하여 진입차수 가 0 인 노드들을 큐에 집어넣고, 연결된 작업들을 하나씩 해나가면서 진입차수가 
0인 노드들만 넣었습니다. 
*/
import java.util.*;
import java.io.*;
public class Main
{

    static StringBuilder sb; //정답 String을 저장할 StringBuilder
    static int[] out; //진입차수를 기록할 배열
    static List<Integer>[] relation; //각 작업간의 관계를 담은 배열
    static Deque<Integer> dq; //작업순서를 정하기 위할 deque
    //작업순서 정하는 bfs
    public static void bfs(){
        while(dq.size() > 0){ //dq가 빌때까지
            
            int temp = dq.pollFirst(); //queue에서 진입차수가 0인 첫번째 원소 가져오기
            //temp가 선행작업인 작업들을 돌면서
            for(int l : relation[temp]){
                if(--out[l] == 0){ //선행작업을 하나씩 줄이고, 진입차수가 0이게 되면 작업순서에 넣고, 큐에 넣기기
                    dq.add(l);
                    sb.append(l+" ");
                }
            }
        }
            
    }
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1; tc <= 10; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int v = Integer.parseInt(st.nextToken()); //노드의 수 
		    int e = Integer.parseInt(st.nextToken()); //간선의 수
		    out = new int[v+1];
		    relation = new List[v+1];
		    for(int i = 0 ; i < v+1; i++){
		        relation[i] = new ArrayList<>(); //relation 초기화화
		    }
		    st = new StringTokenizer(br.readLine());
		    for(int i = 0 ; i < e; i++){
		        int inV = Integer.parseInt(st.nextToken());
		        int outV = Integer.parseInt(st.nextToken());
		        relation[inV].add(outV); //relation 관계 넣기
		        out[outV]++; //진입차수 추가가
		    }

		    sb = new StringBuilder();
		    sb.append("#").append(tc).append(" ");
		    dq = new ArrayDeque<>();
		    //노드들을 돌면서 진입차수가 0인 노드가 있으면 q에 넣고 작업 큐에 넣기 (초기에 진입차수가 0인것들부터 작업되어야 함함)
		    for(int i = 1; i <= v; i++){
		        if (out[i] == 0){
		          dq.add(i);  
		          sb.append(i+" ");
		        }
		    }
		    bfs(); //bfs 실행행
		    System.out.println(sb.toString());
		    
		}
	}
}

/*
중요했던점
- visited가 필요없다
  - 작업이 안되면 다시 돌아와야하니까 visited는 의미 없음
- 초기에 진입차수가 0인 노드들을 전부 처리해야한다.
*/
