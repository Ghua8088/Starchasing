package obj;
import game.*;
import java.awt.*;
public class Brick extends Objects{
    public boolean isvisible = true;
    Gamepanel gp;
    Ball b;
    public Brick(Gamepanel gp,Ball b){
        this.gp=gp;
        this.bounds=new Rectangle();
        this.b=b;
        this.ismoving=false;
    }
    public void setposition(int x, int y){
        this.x = x;
        this.y = y;
        Objheight=48*2;
        Objwidth=48*1;
        this.bounds.setBounds(x, y,Objheight,Objwidth);
    }
    public void update(){
        if(isvisible){
        isvisible=this.check(b);
        if(isvisible==false){
            b.count++;
            b.bounce(1,-1);
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
            g2.setColor(Color.PINK);
            g2.fillRoundRect(this.x,this.y,Objheight,Objwidth,16,16);    
    }
}
