package HF.HF0_OA9;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//堆
//High Five
public class _8HighFive {


     class Record {
         public int id, score;
         public Record(int id, int score){
             this.id = id;
             this.score = score;
         }
         @Override
         public String toString(){
             return id + " " + score + " ";
         }
     }

//////////////////////////////////////////////////////////////////
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        Map<Integer, Double> answer = new HashMap<Integer, Double>();
        Map<Integer, PriorityQueue<Integer>> hash = new HashMap<Integer, PriorityQueue<Integer>>();

        for (Record r : results) {
            if (!hash.containsKey(r.id)){
                hash.put(r.id, new PriorityQueue<Integer>());
            }
            PriorityQueue<Integer> pq=hash.get(r.id);
            if (pq.size() < 5) {
                pq.add(r.score);
            } else {
                if (pq.peek() < r.score){
                    pq.poll();
                    pq.add(r.score);
                }
            }
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : hash.entrySet()) {
            int id = entry.getKey();
            PriorityQueue<Integer> scores = entry.getValue();
            double average = 0;
            for (int i = 0; i < 5; ++i)
                average += scores.poll();
            average /= 5.0;
            answer.put(id, average);
        }
        return answer;
    }

//////////////////////////////////////////////////////////////////

    @Test
    public void test01(){
        Record[] records = new Record[10];
        records[0] = new Record(1, 91);
        records[1] = new Record(1, 92);
        records[2] = new Record(2, 93);
        records[3] = new Record(2, 99);
        records[4] = new Record(2, 98);
        records[5] = new Record(2, 97);
        records[6] = new Record(1, 60);
        records[7] = new Record(1, 58);
        records[8] = new Record(2, 100);
        records[9] = new Record(1, 61);

        System.out.println(highFive(records));
        //{1=72.4, 2=97.4}
    }

//////////////////////////////////////////////////////////////////

}
/*
There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, find the average of 5 highest scores for each person.

Have you met this question in a real interview? Yes
Example
Given results = [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]

Return
1: 72.40
2: 97.40
 */