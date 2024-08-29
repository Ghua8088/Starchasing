package obj;
import game.*;
import java.awt.*;
public class Ball extends Objects {
    Gamepanel gp;
    public int count=0;
    public Ball (Gamepanel gp){
        this.gp = gp;
        setDefault();
        bounds= new Rectangle(x,y,Objheight,Objwidth);
    }
    final public void setDefault(){
        x=300;
        y=300;
        xspeed=0;
        yspeed=1;
        this.Objwidth=gp.tilesize/2;
        this.Objheight=gp.tilesize/2;
    }
    public void update(){
        bounce(gp.p1);
        x+=xspeed;
        y+=yspeed;
        bounds.x=x;
        bounds.y=y;
        if (y <= 0 ||y >= 718) {
            bounce(1, -1);
        }
        if (x<=0||x>=718){
            bounce(-1,1);
        }
        

    }
    void bounce(int x,int y){
        xspeed=x*xspeed;
        yspeed=y*yspeed;
    }
    public void bounce(Objects i){
            if(i==null) return;
            if(bounds.intersects(i.bounds)){
                if(i.ismoving){
                    int dirvel=i.dir.equals("left")?i.xspeed:-i.xspeed;
                    xspeed=-(xspeed)+(dirvel);
                    yspeed=-yspeed;
                }else{
                    Rectangle intersection = bounds.intersection(i.bounds);
                    if (intersection.width >= intersection.height) {
                        bounce(1,-1);
                    }else {
                        bounce(-1,1);
                    }
                }
        }
    }
    public void draw(Graphics g2){
        g2.setColor(Color.RED);
        g2.fillRoundRect(this.x,this.y,gp.tilesize/2, gp.tilesize/2,48,48);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24)); 
        g2.drawString("Score: " + count, 50, 50);
    }
}
