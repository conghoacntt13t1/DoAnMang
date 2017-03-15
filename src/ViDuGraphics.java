import java.awt.*;

/**
 * Created by hoa on 15/03/2017.
 */
public class ViDuGraphics extends Canvas {
    @Override
    public void paint(Graphics graphics){
        graphics.setColor(new Color(255,255,255));
        graphics.fillRect(0,0,getWidth(),getHeight());
    }

    public static void main(String[] args) {
        ViDuGraphics viDuGraphics=new ViDuGraphics();
//        viDuGraphics.paint();
    }
}
