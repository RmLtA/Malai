package org.malai.sphinx4.interaction.library;

import java.awt.Point;

import org.malai.interaction.Interaction;
import org.malai.interaction.IntermediaryState;
import org.malai.interaction.KeyPressureTransition;
import org.malai.interaction.MoveTransition;
import org.malai.interaction.PressureTransition;
import org.malai.interaction.ReleaseTransition;
import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.Sphinx4Interaction;
import org.malai.sphinx4.interaction.StatusChangedTransition;
import org.malai.stateMachine.SourceableState;
import org.malai.stateMachine.TargetableState;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class OneWordInstruction extends Sphinx4Interaction{
	protected State state;
	
	protected Point startPt;
	protected Point endPt;
	protected int button;

	protected IntermediaryState firstPressed;
	protected IntermediaryState heard;
	protected TerminalState secondPressed;

	public OneWordInstruction() {
		super();
		initStateMachine();
	}


	public void reinit() {
		super.reinit();
		state = null;
	}



	protected void initStateMachine() {
		final IntermediaryState firstPressed = new IntermediaryState("firstPressed");
		final IntermediaryState heard = new IntermediaryState("heard");
		final TerminalState secondPressed = new TerminalState("secondPressed");
		ConfigurationManager cm;
		String[] args = null;
		
		addState(firstPressed);
		addState(heard);
		addState(secondPressed);

	
		/*KeyPressureTransition k =new KeyPressureTransition(initState, pressed);
		StatusChangedTransition s =new StatusChangedTransition(initState, heard); */
		
		new PressureTransition(initState, firstPressed) {
			@Override
			public void action() {
				super.action();

				setLastHIDUsed(this.hid);
				OneWordInstruction.this.startPt 	 = new Point(this.x, this.y);
				OneWordInstruction.this.endPt	 	 = new Point(this.x, this.y);
				OneWordInstruction.this.button  	 = this.button;
			}
		};


		new Move4OneWordInstruction(firstPressed, heard);
		new Release4OneWordInstruction(heard, secondPressed);
	}
	
	
	public class Move4OneWordInstruction extends MoveTransition {
		private Point endPt;/*****/

		public Move4OneWordInstruction(final SourceableState inputState, final TargetableState outputState) {
			super(inputState, outputState);
		}

		public void action() {
			super.action();
			Move4OneWordInstruction.this.endPt.setLocation(x, y);
		}
		
		public boolean isGuardRespected() {
			return Move4OneWordInstruction.this.getLastHIDUsed()==this.hid;
		}
	}
	public class Release4OneWordInstruction extends ReleaseTransition {
		public Release4OneWordInstruction(final SourceableState inputState, final TargetableState outputState) {
			super(inputState, outputState);
		}
	
		public boolean isGuardRespected() {
			return OneWordInstruction.this.button==this.button && OneWordInstruction.this.getLastHIDUsed()==this.hid;
		}
	}

	/*****TODO: ajouter la reconnaissance des mots et la gestion des evenements sur button***/
	
		/***************************************************************************************/
		if (args.length > 0) {
		    cm = new ConfigurationManager(args[0]);
		    } else {
		    	cm = new ConfigurationManager(HelloWorld.class.getResource("helloworld.config.xml"));
		    }

		 Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		 recognizer.allocate();

		  // start the microphone or exit if the programm if this is not possible
		 Microphone microphone = (Microphone) cm.lookup("microphone");
		 if (!microphone.startRecording()) {
		     System.out.println("Cannot start microphone.");
		     recognizer.deallocate();
		     System.exit(1);
		 }

		 System.out.println("Say: (Good morning | Hello) ( Bhiksha | Evandro | Paul | Philip | Rita | Will )");

		 // loop the recognition until the programm exits.
		 while (true) {
		 System.out.println("Start speaking. Press Ctrl-C to quit.\n");

		 Result result = recognizer.recognize();

		 if (result != null) {
		 String resultText = result.getBestFinalResultNoFiller();
		 System.out.println("You said: " + resultText + '\n');
		 } else {
		         System.out.println("I can't hear what you said.\n");
		 		}
		 }
	}
	/***************************************************************************************/
}
	
	

        

       
    
