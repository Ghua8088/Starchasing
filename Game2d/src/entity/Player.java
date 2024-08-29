package entity;
import game.Gamepanel;
import game.KeyHandler;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Player extends Entity {
    Gamepanel gp;
    KeyHandler Keyh;
    public Player(Gamepanel gp, KeyHandler Keyh){
        this.gp = gp;
        this.Keyh = Keyh;
        setDefault();   
    }
    public void getplayerimage(){
        try {
            up= ImageIO.read(getClass().getResourceAsStream("/player/up.png"));
            
            down= ImageIO.read(getClass().getResourceAsStream("/player/down.png"));
            left= ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
            right= ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
            basic= ImageIO.read(getClass().getResourceAsStream("/player/Basic.png"));
            System.out.println("loaded images");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefault(){
        x=100;
        y=100;
        speed=4;
        direction="up";
    }
    public void update(){
        if(Keyh.uppressed){
            direction="up";
            y-=speed;
        }
        if(Keyh.downpressed){
            direction="down";
            y+=speed;
        }
        if(Keyh.leftpressed){
            direction="left";
            x-=speed;
        }
        if(Keyh.rightpressed){
            direction="right";
            x+=speed;
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.blue);
        g2.fillRect(x,y,gp.tilesize, gp.tilesize);
        /*BufferedImage image=null;
        switch(direction){
            case "up":
                image=up;
                break;
            case "down":
                image=down;
                break;
            case "right":
                image=right;
                break;
            case "left":
                image=left;
                break;
            default:
                image=basic;
                break;
        }
        g2.drawImage(image,x,y,gp.tilesize,gp.tilesize,null);
        */
    }
}
