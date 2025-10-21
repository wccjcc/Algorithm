import java.util.*;

class Solution {
    static String Begin;
    static String Target;
    static String[] Words;
    static boolean[] visited;

    //한개의 알파벳만 바꿀 수 있는지 확인하는 메서드
    public static int isPos(String word1, String word2){
        int flag = 0;
        for(int i = 0 ; i < word1.length(); i++){
            if(word1.charAt(i) != word2.charAt(i)){
                flag++;
            }
        }
        return (flag == 1) ? 1 : -1;
    }

    //bfs 메서드
    public static int bfs(){
        //queue 정의
        Deque<int[]> dq = new ArrayDeque<>(); //level과 index 넣을 큐

        //begin과 한글자 차이인것들 다 level 1로
        for(int i = 0 ; i < Words.length; i++){
            if(isPos(Begin, Words[i])==1){
                visited[i] = true;
                dq.add(new int[]{i,1});
            }
        }

        //큐 빌때까지 반복
        while(!dq.isEmpty()){
            int[] temp = dq.poll();
            int idx = temp[0];
            int level = temp[1];
            //원하던 target이라면 level return하기
            if(Words[idx].equals(Target)) return level;
            for(int i = 0 ; i < Words.length; i++){
                if(isPos(Words[idx],Words[i])==1 && !visited[i]){
                    visited[i] = true;
                    dq.add(new int[] {i,level+1});
                }
            }
        }

        //안나오면 return 0;
        return 0;
    }
    //메인 메서드
    public int solution(String begin, String target, String[] words) {
        Begin = begin;
        Target = target;
        Words = words;
        visited = new boolean[words.length];

        //target이 words 안에 없으면 0 반환
        for(String w : words){
            if(w.equals(target)) return bfs();
        }
        return 0;
    }
}
