package top100.HighFreq._3Graph;


import java.util.*;
import java.io.*;

class Snake{

    class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    LinkedList<Point> body;
    char space[][]; // board
    int spaceSize;
    int direction; //A  0 right, 1 down, 2 left, 3 up   L (direction+3)%4 R (direction+1)%4 D: direction
    Point offset[]; // 4

    Snake(int size){
        this.spaceSize = size;
        direction = 0; //
        body = new LinkedList<Point>();
        body.add( new Point(1,1) );

        space = new char[spaceSize+2][spaceSize+2];
        for(int i=0; i<spaceSize+2;++i){ // fill in the wall
            space[0][i] = 'W';
            space[spaceSize+1][i] = 'W';
            space[i][0] = 'W';
            space[i][spaceSize+1] = 'W';
        }
        for(int i =1; i<=spaceSize; ++i){ // fill in the middle
            for(int j=1;j<=spaceSize;++j)
                space[i][j]='.';
        }
        space[1][1]='S'; //snake self

        offset = new Point[4];
        offset[0] = new Point(0,1);
        offset[1] = new Point(1,0);
        offset[2] = new Point(0,-1);
        offset[3] = new Point(-1,0);
    }

    void display(){
        for(int i=0;i<spaceSize+2;++i){
            for(int j=0;j<spaceSize+2;++j){
                System.out.print(space[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    boolean move(char order){
        switch(order){
            case 'L':
                direction = (direction+3)%4;//?
                break;
            case 'R':
                direction = (direction+1)%4;
                break;
        }

        Point head = body.getFirst();
        int newX = head.x + offset[direction].x; // new head position
        int newY = head.y + offset[direction].y;

        Point tail;
        switch (space[newX][newY]){
            case 'W': //wall?
                return false;
            case 'o':
                space[newX][newY] = 'S';
                body.addFirst(new Point(newX,newY));
                break;
            case '.':
                space[newX][newY] = 'S';
                body.addFirst(new Point(newX,newY)); //add
                tail = body.removeLast(); //move tail
                space[tail.x][tail.y] = '.';
                break;
            case 'S': // hit self
                tail = body.getLast();
                if(tail.x != newX || tail.y != newY) return false;
                body.addFirst(body.removeLast());
                break;
        }

        return true;
    }
}

public class _31Snake {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        while(n!=-1){
            Snake snake = new Snake(n); //create board
//            snake.display();
            int m = in.nextInt();
            while(m--!=0) { // add bean
                snake.space[in.nextInt()+1][in.nextInt()+1]='o';
//                snake.display();
            }

            in.nextLine();
            String movement = in.nextLine();
            for(int i=0;i<movement.length();++i){
                boolean state = snake.move(movement.charAt(i));
                if(state == false ) break;
            }
            System.out.println(snake.body.size());
            n = in.nextInt();
        }
        in.close();
    }
}