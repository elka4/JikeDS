package ss;

public class Course05_3_StringTest {
	public static final int n=1<<16;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append("H");
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        start=System.currentTimeMillis();
        String str="";
        for(int i=1;i<=n;i++){
            str+="H";
        }
        end=System.currentTimeMillis();
        System.out.println(end-start);
    }

}
