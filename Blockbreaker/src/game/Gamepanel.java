package game;
import java.awt.*;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import obj.*;
public class Gamepanel extends JPanel implements Runnable{
    final int OriginalTilesize=16;
    final int scale=3;
    public final int tilesize= OriginalTilesize*scale;
    final int maxscreencol=16;
    final int maxscreenrow=16;
    final int screenwidth= maxscreencol*tilesize;
    final int screenheight= maxscreenrow*tilesize;
    public Thread gameThread;
    KeyHandler Keyh= new KeyHandler(gameThread);
    public Bar p1=new Bar(this,Keyh);
    public Ball p2=new Ball(this);
    public int nom=12;
    public Brick p3[]=new Brick[nom];
    public Objects all[]=new Objects[nom+1];
    public boolean ispaused=false;
    Random rand = new Random();
    public final void initializeBricks(){
        int nextbrickx;
        int nextbricky;
        for (int i=0; i<nom; i++){
            nextbrickx=rand.nextInt(0,618);
            nextbricky=rand.nextInt(0,400);
            p3[i]=new Brick(this,p2);
            p3[i].setposition(nextbrickx,nextbricky);
        }
        all[0]=p1;
        for(int i=1;i<nom;i++){
            all[i]=p3[i];
        }
    }
         
    public synchronized void pauseGame() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void resumeGame() {
        notify();
    }
    public void deleteBricks() {
        for (int i = 0; i < nom; i++) {
            p3[i] = null;
        }
        System.out.println("All bricks deleted.");
    }
    public void StartgameThread(){
        gameThread= new Thread(this);
        gameThread.start();
        
    }
    @Override
    public void run(){
        int fps=60;
        double drawinterval= 100000000/fps;
        double nexttime=System.nanoTime()+drawinterval;
        while (gameThread != null){ 
            if(ispaused==false){
                update();
                
                repaint();
            }
            try {
                double remaining=nexttime-System.nanoTime();
                remaining=remaining/1000000;
                if(remaining<0){
                    remaining=0;
                }
                Thread.sleep((long)remaining);
                nexttime+=drawinterval;
                if(Keyh.exit){
                    this.setVisible(false);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        p1.update();
        
        p2.update();
        if(p2.y >= 718){
            ispaused=true;
            int choice = JOptionPane.showConfirmDialog(null, "Game Over!\n Your score:"+p2.count+" \nTry again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                this.p1.setDefault();
                this.p2.setDefault();
                for (Brick brick : this.p3) {
                    if (brick != null) {
                        brick.isvisible = true;
                    }
                }
                ispaused = false;
            } else {
                System.exit(0);
            }
        }
        for (Brick o:p3) {
            o.update();
        }
        if(p2.count==nom){
            ispaused = true;
            int choice = JOptionPane.showConfirmDialog(null, "You won! Try again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                /*
                */
                p1.setDefault();
                p2.setDefault();
                deleteBricks();
                initializeBricks();
                p2.count=0;
                ispaused=false;
            } else {
                System.exit(0);
            }
        }
       
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        for(Brick i:p3){
            if(i!=null  && i.isvisible){
                i.draw(g2);
            }
        }
        p2.draw(g2);
        p1.draw(g2);
        g2.dispose();
    }
    public Gamepanel(){
        this.initializeBricks();
        this.setPreferredSize(new Dimension(screenwidth,screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(Keyh);
        this.setFocusable(true);
    }
}
