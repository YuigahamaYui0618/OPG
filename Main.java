import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    //0：error 1：> 2: < 3:=

    private static int[][] matrix = {
            {1, 2, 2, 2, 1, 1},
            {1, 1, 2, 2, 1, 1},
            {1, 1, 0, 0, 1, 1},
            {2, 2, 2, 2, 3, 0},
            {1, 1, 0, 0, 1, 1},
            {2, 2, 2, 2, 0, 3}};
    private static char[] Vt = {'+', '*', 'i', '(', ')', '#'};
    public static Stack<Character> stack = new Stack<>();

    public static char peekVt() {
        char a = stack.peek();
        if (a == 'N') {
            stack.pop();
            char b = stack.peek();
            stack.push(a);
            return b;
        }
        else
            return a;
    }

    public static int getIndex(char ch){
        int i;
        for(i = 0;i <= 5;i++){
            if(ch == Vt[i]){
                return i;
            }
        }
        return -1;
    }

    public static void reduce(){
        char a = stack.pop();
        if(a == 'N'){
            char b = stack.pop();
            if(b == '+'||b == '*'){
                if(stack.peek() == 'N'){
                    System.out.println("R");
                    return;
                }}
        }
        else if(a == ')'){
            char c = stack.pop();
            char d = stack.pop();
            if(c == 'N' && d == '('){
                System.out.println("R");
                stack.push('N');}
        }
        else if(a == 'i'){
            //stack.pop();
            System.out.println("R");
            stack.push('N');
        }
        else{
            System.out.println("RE");
            exit(0);
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        String sentence;
        //String FileName = args[0];
        //BufferedReader in = new BufferedReader(new FileReader(FileName));
        BufferedReader in = new BufferedReader(new FileReader("D:\\test\\opg.txt"));
        String OriginalSentence;
        while((OriginalSentence = in.readLine()) != null){
            sentence = OriginalSentence + '#';
            stack.push('#');

            for(int i = 0;i < sentence.length();i++){
                char c = sentence.charAt(i);
                if(stack.empty()){
                    stack.push(c);
                    System.out.println("I" + c);
                }
                else{
                    char inside = peekVt();
                    int x = getIndex(inside);
                    int y = getIndex(c);
                    //System.out.println("x:"+x);
                    //System.out.println("y:"+y);
                    //System.out.println(inside);
                    if(x == -1||y == -1){
                        System.out.println("E");
                        return;
                    }
                    int priority = matrix[x][y];
                    if(priority == 0){
                        System.out.println("E");
                        return;
                    }
                    else if (priority == 1) {
                        reduce();
                        i--;
                    }
                    else if (priority == 2) {
                        stack.push(c);
                        System.out.println("I" + c);
                    }
                    else if (priority == 3) {
                        if (c == '#' && inside == '#')
                            return;
                        stack.push(c);
                        System.out.println("I" + c);
                    }
                }

            }
        }
        in.close();
    }
}
