package game;
import java.awt.event.*;

public class KeyHandler implements  KeyListener{
    public boolean uppressed,downpressed,leftpressed,rightpressed;
    @Override
    public void keyTyped(KeyEvent event){

    }
    @Override
    public void keyPressed(KeyEvent event){
        int code=event.getExtendedKeyCode();
        if(code==KeyEvent.VK_W){
            uppressed=true;
        }
        if(code==KeyEvent.VK_A){
            leftpressed=true;
        }
        if(code==KeyEvent.VK_S){
            downpressed=true;
        }
        if(code==KeyEvent.VK_D){
            rightpressed=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent event){
        int code=event.getExtendedKeyCode();
        if(code==KeyEvent.VK_W){
            uppressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftpressed=false;
        }
        if(code==KeyEvent.VK_S){
            downpressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightpressed=false;
        }
    }
}
