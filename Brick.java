package obj;
import game.*;
import java.awt.*;
import javax.swing.JOptionPane;
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
        this.bounds.setBounds(x, y, Objwidth, Objheight);
    }
    public void update(){
        if(isvisible){
        isvisible=this.check(b);
        if(isvisible==false){
            b.count++;
            System.out.println(b.count);
            if(b.count==gp.nom){
                
                int choice = JOptionPane.showConfirmDialog(null, "You won! Try again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    b.count=0;
                    gp.resumeGame();
                    gp.p1.setDefault();
                    gp.p2.setDefault();
                    gp.deleteBricks();
                    gp.initializeBricks();
                } else {
                    System.exit(0);
                }
                }
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
