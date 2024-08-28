package game;
import java.awt.*;
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
    public final void initializeBricks(){
        int nextbrickx=100;
        int nextbricky=100;
        for (int i=0; i<nom; i++){
            p3[i]=new Brick(this,p2);
            p3[i].setposition(nextbrickx,nextbricky);
            nextbrickx= nextbrickx+p3[i].Objheight;
            if(nextbrickx>=618){
                nextbricky= nextbricky+p3[i].Objwidth;
                nextbrickx=100;
            }
            System.err.println(p3[i].bounds);
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
    public Objects all[]= {p1,p3[0],p3[1],p3[2],p3[3],p3[4],p3[5],p3[6],p3[7],p3[8],p3[9]};
    public void StartgameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        
        int fps=30;
        double drawinterval= 100000000/fps;
        double nexttime=System.nanoTime()+drawinterval;
        while (gameThread != null){ 
            update();
            repaint();
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
       for (Brick o:p3) {
          o.update();
       }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        p2.draw(g2);
        for(Brick i:p3){
            if(i!=null  && i.isvisible){
                i.draw(g2);
            }
        }
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
