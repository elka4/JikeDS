/** 613 High Five
 * Medium*/


/**
 * Definition for a Record
 * class Record {
 * public:
 *   int id, score;
 *   Record(int id, int score) {
 *     this->id = id;
 *     this->score = score;
 *   }
 * };
 */
class Solution {
public:
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * map<int, double> (student_id, average_score)
     */
    map<int, double> highFive(vector<Record>& results) {
        // Write your code here
        map<int, vector<int>> hash;
        for (auto result : results) {
            if (hash.find(result.id) == hash.end()) {
                hash[result.id] = vector<int>();
            }
            if (hash[result.id].size() < 5) {
                hash[result.id].push_back(result.score);
            } else {
                int index = 0;
                for (int i = 1; i < 5; ++i)
                    if (hash[result.id][i] < hash[result.id][index])
                        index = i;

                if (hash[result.id][index] < result.score)
                    hash[result.id][index] = result.score;
            }
        }

        map<int, double> answer;
        for (map<int, vector<int>>::iterator it = hash.begin(); it != hash.end(); ++it) {
            int id = it->first;
            vector<int> scores = it->second;
            double average = 0;
            for (int i = 0; i < 5; ++i)
                average += scores[i];
            average /= 5.0;
            answer[id] = average;
        }
        return answer;
    }
};