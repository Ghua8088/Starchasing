package entity;
import game.Gamepanel;
import game.KeyHandler;
import java.awt.*;
public class Barrier extends Entity{
    Gamepanel gp;
    public Barrier(int x,int y,Gamepanel gp){
        this.x = x;
        this.y = y;
        this.speed =0;
        this.gp=gp;
    }
    public void draw(Graphics2D g2){
        g2.fillRect(x,y,gp.tilesize*4, gp.tilesize);
    }
}
