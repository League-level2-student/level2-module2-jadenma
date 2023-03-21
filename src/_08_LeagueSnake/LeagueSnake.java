package _08_LeagueSnake;
import java.util.ArrayList;
import processing.core.PApplet;
public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
     /* Game variables
     * Put all the game variables here.*/
    
    Segment Segment;
    int foodX;
    int foodY;
    int direction = UP;
    int numFoodEaten = 0;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    
     /* Setup methods
     * These methods are called at the start of the game. */
    
    @Override
    public void settings() {
        size(500, 500);
    }
    @Override
    public void setup() {
        Segment = new Segment(250, 250);
        frameRate(20);
        dropFood();
    }
    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    }
    
     /* Draw Methods
     * These methods are used to draw the snake and its food*/
    
    @Override
    public void draw() {
        background(0, 0, 0);
        drawFood();
        move();
        drawSnake();
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(255, 200, 0);
        rect(foodX, foodY, 10, 10);
    }
    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0, 255, 255);
    	rect(Segment.x, Segment.y, 10, 10);
    	manageTail();
    }
    void drawTail() {
        // Draw each segment of the tail
        for (int i = 0; i < segments.size(); i++) {
        	Segment eachSegment = segments.get(i);
        	rect(eachSegment.x, eachSegment.y, 10, 10);
        }
    }
     /* Tail Management methods
     * These methods make sure the tail is the correct length.*/

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    	drawTail();
    	Segment newSegment = new Segment(Segment.x, Segment.y);
		segments.add(newSegment);
        segments.remove(0);
    }
    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        for (Segment eachSegment : segments) {
        	if (Segment.x == eachSegment.x && Segment.y == eachSegment.y) {
        		segments = new ArrayList<>();
        		numFoodEaten = 0;
        		Segment newSegment = new Segment(Segment.x, Segment.y);
        		segments.add(newSegment);
        	}
        }
    }
    
     /* Control methods
     * These methods are used to change what is happening to the snake */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
        if (keyCode == UP && direction != DOWN) {
        	direction = UP;
        }
        if (keyCode == DOWN && direction != UP) {
        	direction = DOWN;
        }
        if (keyCode == LEFT && direction != RIGHT) {
        	direction = LEFT;
        }
        if (keyCode == RIGHT && direction != LEFT) {
        	direction = RIGHT;
        }
    }
    void move() {
        // Change the location of the Snake head based on the direction it is moving.
        if (direction == UP) {
            // Move head up
            Segment.y -=10;
        } else if (direction == DOWN) {
            // Move head down
            Segment.y +=10;
        } else if (direction == LEFT) {
            Segment.x -=10;
        } else if (direction == RIGHT) {
            Segment.x +=10;
        }
        checkBoundaries();
    }
    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if (Segment.x > 490) {
        	Segment.x = 0;
        }
        if (Segment.x < 0) {
        	Segment.x = 490;
        }
        if (Segment.y > 490) {
        	Segment.y = 0;
        }
        if (Segment.y < 0) {
        	Segment.y = 490;
        }
    }
    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if (Segment.x == foodX && Segment.y == foodY) {
        	numFoodEaten++;
        	foodX = ((int)random(50)*10);
        	foodY = ((int)random(50)*10);
        	Segment newSegment = new Segment(Segment.x, Segment.y);
        	segments.add(newSegment);
        }
    }
    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
