package obj;
import game.*;
import java.awt.*;
import javax.swing.JOptionPane;
public class Ball extends Objects {
    Gamepanel gp;
    public int count=0;
    public Ball (Gamepanel gp){
        this.gp = gp;
        setDefault();
        bounds= new Rectangle(x,y,Objheight,Objwidth);
    }
    final public void setDefault(){
        x=100;
        y=300;
        xspeed=1;
        yspeed=2;
        this.Objwidth=gp.tilesize/2;
        this.Objheight=gp.tilesize/2;
    }
    public void update(){
        bounce(gp.all);
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
        if(y >= 718){
            int choice = JOptionPane.showConfirmDialog(null, "Game Over!\n Your score:"+count+" \nTry again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                gp.p1.setDefault();
                gp.p2.setDefault();
                for (Brick brick : gp.p3) {
                    if (brick != null) {
                        brick.isvisible = true;
                    }
                }

            } else {
                System.exit(0);
            }
        }

    }
    void bounce(int x,int y){
        xspeed=x*xspeed;
        yspeed=y*yspeed;
    }
    public void bounce(Objects e2[]){
        for(Objects i:e2) {
            if(i==null){
                continue;
            }
            
            if(bounds.intersects(i.bounds)){
                if(i.ismoving){
                    xspeed=-(xspeed)+i.xspeed;
                    yspeed=-yspeed;
                }else{
                    if (!i.isvisible) {
                        
                        continue;
                    }
                    if (bounds.intersects(i.bounds)) {
                        
                        System.out.println("Collision with brick: " + i);
                    }
                    Rectangle intersection = bounds.intersection(i.bounds);
                    if (intersection.width >= intersection.height) {
                        yspeed = -yspeed;
                    }else {
                        xspeed = -xspeed;
                    }
                    break;
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
