package HF.HF2_Algo_DS_I_1Interval;

public class Interval {
          int start, end;
      Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
      @Override
      public String toString(){
          return "[" +start + ", " + end + "]";
      }
}
