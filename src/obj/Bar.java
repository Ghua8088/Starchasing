package obj;
import game.Gamepanel;
import game.KeyHandler;
import java.awt.*;
public class Bar extends Objects {
    Gamepanel gp;
    KeyHandler Keyh;
    public Bar(Gamepanel gp, KeyHandler Keyh){
        this.gp = gp;
        this.Keyh = Keyh;
        setDefault();
        bounds = new Rectangle(x,y,Objwidth,Objheight);  
    }
    final public void setDefault(){
        this.Objwidth=gp.tilesize*4;
        this.Objheight=gp.tilesize/2;
        x=100;
        y=718;
        xspeed=2;
        yspeed=0;
        ismoving=false;
        isvisible=true;
    }
    public void update(){
        if(Keyh.leftpressed){
            if(x<0){
                return;
            }
            x-=xspeed;
            bounds.x=x;
            bounds.y=y;
        }
        if(Keyh.rightpressed){
            if(x>=700){
                return;
            }
            x+=xspeed;
            bounds.x=x;
            bounds.y=y;
        }
        ismoving=Keyh.ismoving;
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.blue);
        g2.fillRoundRect(x,y,gp.tilesize*4, gp.tilesize/2,6,6);
    }
}
