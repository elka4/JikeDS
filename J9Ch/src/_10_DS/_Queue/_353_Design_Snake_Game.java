package _10_DS._Queue;
import java.util.*;


public class _353_Design_Snake_Game {
//    Java Deque and HashSet design with detailed comments
    public class SnakeGame {

        //2D position info is encoded to 1D and stored as two copies
        Set<Integer> set; // this copy is good for fast loop-up for eating body case
        Deque<Integer> body; // this copy is good for updating tail
        int score;
        int[][] food;
        int foodIndex;
        int width;
        int height;

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            set = new HashSet<>();
            set.add(0); //intially at [0][0]
            body = new LinkedList<>();
            body.offerLast(0);
        }


        public int move(String direction) {
            //case 0: game already over: do nothing
            if (score == -1) {
                return -1;
            }

            // compute new head
            int rowHead = body.peekFirst() / width;
            int colHead = body.peekFirst() % width;
            switch (direction) {
                case "U" : rowHead--;
                    break;
                case "D" : rowHead++;
                    break;
                case "L" : colHead--;
                    break;
                default :  colHead++;
            }
            int head = rowHead * width + colHead;

            //case 1: out of boundary or eating body
            set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily
            if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
                return score = -1;
            }

            // add head for case2 and case3
            set.add(head);
            body.offerFirst(head);

            //case2: eating food, keep tail, add head
            if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
                set.add(body.peekLast()); // old tail does not change, so add it back to set
                foodIndex++;
                return ++score;
            }

            //case3: normal move, remove tail, add head
            body.pollLast();
            return score;

        }
    }
//-----------------------------------------------------------------------------//
//Share my easy java solution
public class SnakeGame2 {

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isEqual(Position p) {
            return this.x == p.x && this.y == p.y;
        }
    }

    int len;
    int rows, cols;

    int[][] food;
    LinkedList<Position> snake;

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame2(int width, int height, int[][] food) {
        this.rows = height;
        this.cols = width;
        this.food = food;

        snake = new LinkedList<Position>();
        snake.add(new Position(0, 0));
        len = 0;
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        //if(len>=food.length) return len;

        Position cur = new Position(snake.get(0).x, snake.get(0).y);

        switch (direction) {
            case "U":
                cur.x--;
                break;
            case "L":
                cur.y--;
                break;
            case "R":
                cur.y++;
                break;
            case "D":
                cur.x++;
                break;
        }

        if (cur.x < 0 || cur.x >= rows || cur.y < 0 || cur.y >= cols) return -1;


        for (int i = 1; i < snake.size() - 1; i++) {
            Position next = snake.get(i);
            if (next.isEqual(cur)) return -1;
        }
        snake.addFirst(cur);
        if (len < food.length) {
            Position p = new Position(food[len][0], food[len][1]);
            if (cur.isEqual(p)) {
                len++;
            }
        }
        while (snake.size() > len + 1) snake.removeLast();

        return len;
    }


    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */


//-----------------------------------------------------------------------------//
/*    Easy Java solution using linkedlist
    I use a linkedlist to mimic the snake movement. Every time we move one step forward, we get a new x and y.
    We have three situations:
            (1) The new position is out of the boundary, return -1
            (2) The new position reach the end of the snake body, remember to delete the oldest point in the linkedlist first!
            (3) the new position is the food, then we just need to add the deleted point back to the linkedlist. The list will be one point longer then before.
    We keep an foodIndex to reserve the score and the index for food array.*/

    public class SnakeGame3 {
        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        int[][] food;
        int width;
        int height;
        int foodIndex;
        LinkedList<int[]> track;
        public SnakeGame3(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.foodIndex = 0;
            this.food = food;
            track = new LinkedList<int[]>();
            int[] n = {0,0};
            track.add(n);
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            int x = track.get(0)[0];
            int y = track.get(0)[1];
            int[] newpoint = new int[2];
            if(direction.equals("U")) {
                x = x-1;
                y = y;
            }
            else if(direction.equals("L")) {
                x = x;
                y = y-1;
            }
            else if(direction.equals("R")) {
                x = x;
                y = y+1;
            }
            else {
                x = x+1;
                y = y;
            }
            if(x< 0 || x>=height || y <0 || y >=width) return -1;
            newpoint[0] = x;
            newpoint[1] = y;
            int[] todelete = track.get(track.size()-1);
            track.remove(track.size()-1);
            if(isdead(x,y)) return -1;
            track.add(0,newpoint);
            if(food.length > foodIndex && x == food[foodIndex][0] && y == food[foodIndex][1]) {
                track.add(todelete);
                foodIndex++;
                return foodIndex;
            }
            return foodIndex;
        }

        //to test whether it will reach the tails
        public boolean isdead(int x, int y) {
            for(int i = track.size() -1; i>=0; i--) {
                int[] t= track.get(i);
                if(t[0] == x && t[1] ==y) return true;
            }
            return false;
        }
    }
//-----------------------------------------------------------------------------//
// 9Ch
    public class SnakeGameJiuzhang {
        /* Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        private class Position {
            public int x, y;

            public Position(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        private boolean gameOver;
        private int[][] food;
        private int foodGet;
        private int width, height;
        private HashSet<Integer> usedMap;
        private Deque<Position> queue;

        public SnakeGameJiuzhang(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            gameOver = false;
            foodGet = 0;
            usedMap = new HashSet<Integer>();
            this.food = food;
            queue = new LinkedList<Position>();
            usedMap.add(0);
            queue.offer(new Position(0, 0));
            if (food.length > 0 && food[foodGet][0] == 0 && food[foodGet][1] == 0) {
                foodGet++;
            }
        }

        /**
         * Moves the snake.
         *
         * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         * @return The game's score after the move. Return -1 if game over.
         * Game over when snake crosses the screen boundary or bites its body.
         */
        public int move(String direction) {
            if (gameOver) {
                return -1;
            }
            int incx = 0, incy = 0;
            switch (direction) {
                case "U":
                    incx = -1;
                    incy = 0;
                    break;
                case "L":
                    incx = 0;
                    incy = -1;
                    break;
                case "R":
                    incx = 0;
                    incy = 1;
                    break;
                case "D":
                    incx = 1;
                    incy = 0;
                    break;
                default:
                    break;
            }
            int x = queue.peekLast().x + incx, y = queue.peekLast().y + incy;
            if (x >= 0 && x < height && y >= 0 && y < width) {
                queue.offerLast(new Position(x, y));
                if (foodGet < food.length && x == food[foodGet][0] && y == food[foodGet][1]) {
                    foodGet++;
                } else {
                    usedMap.remove(queue.peekFirst().x * width + queue.peekFirst().y);
                    queue.pollFirst();
                }
                if (usedMap.contains(x * width + y)) {
                    return -1;
                } else {
                    usedMap.add(x * width + y);
                }
                return foodGet;
            } else {
                gameOver = true;
                return -1;
            }
        }
    }
//-----------------------------------------------------------------------------//

}
}
/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

 */