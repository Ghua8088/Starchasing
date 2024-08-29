package game;
import entity.*;
import java.awt.*;
import javax.swing.JPanel;
public class Gamepanel extends JPanel implements Runnable{
    final int OriginalTilesize=16;
    final int scale=3;
    public final int tilesize= OriginalTilesize*scale;
    final int maxscreencol=16;
    final int maxscreenrow=16;
    final int screenwidth= maxscreencol*tilesize;
    final int screenheight= maxscreenrow*tilesize;
    KeyHandler Keyh= new KeyHandler();
    Thread gameThread;
    Player p1=new Player(this,Keyh);
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
       p1.update();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        p1.draw(g2);
        g2.dispose();
    }
    public Gamepanel(){
        this.setPreferredSize(new Dimension(screenwidth,screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(Keyh);
        this.setFocusable(true);
    }
}
