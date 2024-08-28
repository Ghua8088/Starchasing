package game;
import java.awt.event.*;
public class KeyHandler implements  KeyListener{
    public boolean leftpressed,rightpressed,ismoving,exit=false;
    public Thread gameThread;

    public KeyHandler(Thread game) {
        gameThread = game;
    }
    @Override
    public void keyTyped(KeyEvent event){
    }
    @Override
    public void keyPressed(KeyEvent event){
        int code=event.getExtendedKeyCode();
        if(code==KeyEvent.VK_A){
            leftpressed=true;
        }
        if(code==KeyEvent.VK_D){
            rightpressed=true;
        }
        ismoving=true;
        if(code==KeyEvent.VK_ESCAPE){
            gameThread.interrupt();
            exit=!(exit);
        }
    }
    @Override
    public void keyReleased(KeyEvent event){
        int code=event.getExtendedKeyCode();

        if(code==KeyEvent.VK_A){
            leftpressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightpressed=false;
        }
        ismoving=false;
    }
}
