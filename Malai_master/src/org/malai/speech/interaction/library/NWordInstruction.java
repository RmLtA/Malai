package org.malai.speech.interaction.library;

import org.malai.interaction.TerminalState;
import org.malai.speech.interaction.SpeechInteraction;
import org.malai.speech.interaction.SpeechTransition;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class NWordInstruction extends SpeechInteraction{
	protected State state;

	public NWordInstruction() {
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
		ConfigurationManager cm;
		String[] args = null;
		
		addState(heard);

		@SuppressWarnings("unused")
		SpeechTransition s =new SpeechTransition(initState, heard); 
/**********************************************************************/		
		if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(NWordInstruction.class.getResource("hellongram.config.xml"));
        }

        // allocate the recognizer
        System.out.println("Loading...");
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

        printInstructions();

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestResultNoFiller();
                System.out.println("You said: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }


    /** Prints out what to say for this demo. */
    private static void printInstructions() {
        System.out.println("Sample sentences:\n" +
                "the green one right in the middle\n" +
                "the purple one on the lower right side\n" +
                "the closest purple one on the far left side\n" +
                "the only one left on the left\n\n" +
                "Refer to the file hellongram.test for a complete list.\n");
    }
}
