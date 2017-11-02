package Ch_09_Scalability_and_Memory_Limits.Q9_05_Cache;

public class Question {
	
	public static String[] generateResults(int i) {
		String[] results =  {"resultA" + i, "resultB" + i, "resultC" + i};
		return results;
	}
	
	public static void main(String[] args) {
		Cache cache = new Cache();
		for (int i = 0; i < 20; i++) {
			String query = "query" + i;
			cache.insertResults(query, generateResults(i));
			if (i == 9 || i == 16 || i == 19) {
				cache.getResults("query" + 2);
				cache.getResults("query" + 6);
				cache.getResults("query" + 9);
			}
		}
		
		for (int i = 0; i < 30; i++) {
			String query = "query" + i;
			String[] results = cache.getResults(query);
			System.out.print(query + ": ");
			if (results == null) {
				System.out.print("null");
			} else {
				for (String s : results) {
					System.out.print(s + ", ");
				}
			}
			System.out.println("");
		}		
	}
/*
            query0: null
            query1: null
            query2: resultA2, resultB2, resultC2,
            query3: null
            query4: null
            query5: null
            query6: resultA6, resultB6, resultC6,
            query7: null
            query8: null
            query9: resultA9, resultB9, resultC9,
            query10: null
            query11: null
            query12: null
            query13: resultA13, resultB13, resultC13,
            query14: resultA14, resultB14, resultC14,
            query15: resultA15, resultB15, resultC15,
            query16: resultA16, resultB16, resultC16,
            query17: resultA17, resultB17, resultC17,
            query18: resultA18, resultB18, resultC18,
            query19: resultA19, resultB19, resultC19,
            query20: null
            query21: null
            query22: null
            query23: null
            query24: null
            query25: null
            query26: null
            query27: null
            query28: null
            query29: null
 */
}
