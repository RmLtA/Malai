package org.malai.sphinx4.interaction.library;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.Sphinx4Interaction;
import org.malai.sphinx4.interaction.StatusChangedTransition;

import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class Transcriber extends Sphinx4Interaction{
	protected State state;

	public Transcriber() throws IOException {
		super();
		initStateMachine();
	}


	public void reinit() {
		super.reinit();
		state = null;
	}


	@SuppressWarnings("null")
	protected void initStateMachine() {
		final TerminalState heard = new TerminalState("heard"); 
		URL audioURL = null;
		String[] args = null;
		
		addState(heard);

		@SuppressWarnings("unused")
		StatusChangedTransition s =new StatusChangedTransition(initState, heard); 
/***************************************************************************************************/
        if (args.length > 0) {
            try {
				audioURL = new File(args[0]).toURI().toURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            audioURL = Transcriber.class.getResource("10001-90210-01803.wav");
        }

        URL configURL = Transcriber.class.getResource("config.xml");

        ConfigurationManager cm = new ConfigurationManager(configURL);
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");

        /* allocate the resource necessary for the recognizer */
        recognizer.allocate();

        // configure the audio input for the recognizer
        AudioFileDataSource dataSource = (AudioFileDataSource) cm.lookup("audioFileDataSource");
        dataSource.setAudioFile(audioURL, null);

        // Loop until last utterance in the audio file has been decoded, in which case the recognizer will return null.
        Result result;
        while ((result = recognizer.recognize())!= null) {

                String resultText = result.getBestResultNoFiller();
                System.out.println(resultText);
        }
    }
}
