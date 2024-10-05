package obj;
import game.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import music.Musicplayer;
public class Brick extends Objects{
    public boolean isvisible = true;
    Gamepanel gp;
    Ball b;
    Musicplayer Mplay= new Musicplayer();
    
    public BufferedImage image;
    public Brick(Gamepanel gp,Ball b){
        this.gp=gp;
        this.bounds=new Rectangle();
        this.b=b;
        this.ismoving=false;
        getBrickimage();
    }
    public void getBrickimage(){
        try {
            image=ImageIO.read(getClass().getResourceAsStream("star.png"));
        } catch (Exception e) {
            System.err.println("Error loading brick image");
        }
    }
    public void setposition(int x, int y){
        this.x = x;
        this.y = y;
        Objheight=16;
        Objwidth=16;
        this.bounds.setBounds(x, y,Objheight,Objwidth);
    }
    public void update(){
        if(isvisible){
        isvisible=this.check(b);
        if(isvisible==false){
            b.count++;
            b.bounce(1,-1);
            try {
                Mplay.playmusic("D:\\game\\Blockbreaker\\src\\obj\\d4.wav");
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            System.out.println(b.count);
        }
      }
    }
    public boolean check(Objects obj){
        
        if(bounds.intersects(obj.bounds)){
            bounds.setBounds(x, y, 0, 0);
            return false ;
        }
        return true;
    }
    public void draw(Graphics g2) {
            g2.drawImage(image,x,y,Objheight,Objwidth,null);   
    }
}
