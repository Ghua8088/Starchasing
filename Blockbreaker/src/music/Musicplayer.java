package music;
import java.io.*;
import javax.sound.sampled.*;
public class Musicplayer {
    public void playmusic(String path) throws IOException{
        try {
            File f = new File(path);
            if(f.exists()){
                AudioInputStream in =AudioSystem.getAudioInputStream(f);
                Clip clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();
            }else{
                System.out.println("File does not exist");
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

}
