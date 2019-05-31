import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button2D {
    float x,y, width,height;

    String text = "This is a long phrase";
    public Button2D(float x, float y, float width, float height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public Button2D(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void renderButton(Canvas2D canvas, TextRenderer textRenderer) {
        float x1,x2,y1,y2;
        x1 = x - width/2;
        x2 = x + width/2;
        y1 = y - height/2;
        y2 = y + height/2;


        //bot
        canvas.strokeLine(x1,y1,x2,y1);
        //top
        canvas.strokeLine(x1,y2,x2,y2);
        //left
        canvas.strokeLine(x1,y1,x1,y2);
        //right
        canvas.strokeLine(x2,y1,x2,y2);

        if(text.length() > 0) {
            textRenderer.beginRendering(1000, 1000);
            textRenderer.draw(text, (int)(x+500-150*width), (int)y+500);
            textRenderer.endRendering();
        }

    }

    public boolean isClicked(float x, float y){
        x/=1000;
        x-=0.5;
        y/=1000;
        y-=0.5;
        y=-y;

         return   (x >= this.x -width/4&& x <=  this.x+width/4)
                 && (y <= this.y+height/4&& y >=  this.y-height/4);
    }
}
