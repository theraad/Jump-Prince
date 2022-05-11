package JumpPrince.sound;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class Sound{

	private String path;
	private boolean playing = false;
	private boolean loop = false;
	private Clip clip;
	private File file;
	private AudioInputStream stream;
	private DataLine.Info info;
	
	public Sound(String path, boolean loop) {
		this.path = path;
		this.loop = loop;
		file = new File(System.getProperty("user.dir") + "/res" + path);
		
	}
	
	public synchronized void start() {
		playing = true;
		try {
			stream = AudioSystem.getAudioInputStream(file);
			info = new DataLine.Info(Clip.class, stream.getFormat());
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(-50.0f);
			clip.start();
			if(loop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void stop() {
		clip.stop();
		playing = false;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
}
