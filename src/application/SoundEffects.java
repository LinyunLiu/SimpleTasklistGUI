// AUTHOR: LINYUN LIU
// DATE: MARCH 15th, 2021

package application;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffects {
	public static Clip clip;
	
    public static void playSound(File sound) throws Exception{
    	clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(sound)); 
        clip.start();
    }
    public static void StopSound() throws Exception{
    	clip.stop();
    }

}
