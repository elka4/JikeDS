package ss;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;

public class Course12_3_071SimplifyPath {
    public String simplifyPath(String path) {
        LinkedList<String> stack=new LinkedList<String>();
        String[] array=path.split("/");
        for(String str:array){
            if(str.equals("")||str.equals("")){
                continue;
            }else{
                if(str.equals("")){
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                }else{
                    stack.push(str);
                }
            }
        }
        if(stack.isEmpty()){
            return "/";
        }else{
            Collections.reverse(stack);
            StringBuilder sb=new StringBuilder();
            while(!stack.isEmpty()){
                sb.append("/").append(stack.pop());
            }
            return sb.toString();
        }
    }


    @Test
    public void test(){
        System.out.println(simplifyPath("/../../"));
        System.out.println(simplifyPath("/home//foo//"));
        System.out.println(simplifyPath("a/./b/../../../c/d/"));
    }
}
