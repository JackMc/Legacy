package org.ossnipes.shadowdawn.dev.client.engine;



// Normal features
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
// Undocumented
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Sound {
	private AudioStream as;
        private ContinuousAudioDataStream cas;
	private String path;
        private boolean loop;

	public Sound(String File, boolean loop) throws IOException
	{
            this.loop = loop;
            if (loop)
            {
                this.path = File;
                InputStream in = new FileInputStream(File);
                as = new AudioStream(in);
                AudioData ad = as.getData();
                cas = new ContinuousAudioDataStream(ad);
            }
            else if(!loop)
            {
                InputStream in = new FileInputStream(File);
		as = new AudioStream(in);
            }
	}
	public String getPath()
	{
		return path;
	}
	public synchronized void Start()
	{
            if (loop)
            {
                AudioPlayer.player.start(cas);
            }
            else if (!loop)
            {
		AudioPlayer.player.start(as);
            }
	}
	public synchronized void Stop()
	{
            if (loop)
            {
                AudioPlayer.player.stop(cas);
            }
		AudioPlayer.player.stop(as);
	}
        /** @return The length of the track in milliseconds or 0 if it is a continuous audio stream or if there is no sound playing
         */
        public synchronized int Length()
        {
            if (loop)
            {
            return as.getLength();
            }
            return 0;
        }
        @Override
        public String toString()
        {
            return path;
        }
        }
