import java.awt.*;

public class Troff {
    public Rectangle rec;
    public int xpos;
    public int ypos;
    public int width;
    public int height;


public Troff(int pXpos, int pYpos){
    xpos = pXpos;
    ypos = pYpos;
    width = 50;
    height = 50;
    rec = new Rectangle(xpos, ypos, 50,50);

}
}
