package org.malai.sphinx4.interaction.library;

import java.awt.Point;
import java.net.URL;


import org.malai.interaction.IntermediaryState;
import org.malai.interaction.MoveTransition;
import org.malai.interaction.PressureTransition;
import org.malai.interaction.ReleaseTransition;
import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.Sphinx4Interaction;
import org.malai.stateMachine.SourceableState;
import org.malai.stateMachine.TargetableState;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class OneWordInstruction extends Sphinx4Interaction{
	protected State state;
	/** The starting point of the interaction. */
	protected Point startPt;
	/** The ending point of the interaction. */
	protected Point endPt;
	/** The button of the device used to performed the interaction. */
	protected int button;


	public OneWordInstruction() {
		super();
		initStateMachine();
	}


	public void reinit() {
		super.reinit();
		state = null;
		startPt 	= null;
		endPt 		= null;
		button		= -1;
	}
	
	public Point getStartPt() {
		return startPt;
	}

	public Point getEndPt() {
		return endPt;
	}
	
	public int getButton() {
		return button;
	}



	protected void initStateMachine() {
		final IntermediaryState pressed = new IntermediaryState("pressed");
		final IntermediaryState ordered = new IntermediaryState("ordered");
		final TerminalState released = new TerminalState("realesed");
		
		addState(pressed);
		addState(ordered);
		addState(released);
		
		new PressureTransition(initState, pressed) {
			@Override
			public void action() {
				super.action();

				setLastHIDUsed(this.hid);
				OneWordInstruction.this.startPt 	 = new Point(this.x, this.y);
				OneWordInstruction.this.endPt	 	 = new Point(this.x, this.y);
				OneWordInstruction.this.button  	 = this.button;
			}
		};


		new Move4OneWordInstruction(pressed, ordered);
		new Release4OneWordInstruction(ordered, released);
	}
	
	
	public class Move4OneWordInstruction extends MoveTransition {
		private Point endPt;
		

		public Move4OneWordInstruction(final SourceableState inputState, final TargetableState outputState) {
			super(inputState, outputState);
		}

		public void action() {
			super.action();
			
			/**Identification du fichier de configuration*/
			URL url = OneWordInstruction.class.getResource("helloworld.config.xml");
			
			/**Chargement de la configuration*/
			ConfigurationManager cm = new ConfigurationManager(url);
			
			/**Création de l'objet de reconnaissance*/
			Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
			recognizer.allocate();
			
			/**Création de l'objet microphone*/
			 Microphone microphone = (Microphone) cm.lookup("microphone");
			 
			 /**On vérifie si le microphone est prêt à enregistrer*/
			 if (!microphone.startRecording()) {
			     System.out.println("Cannot start microphone.");
			     recognizer.deallocate();
			     System.exit(1);
			 }
			
			 System.out.println("Say: ( Square )");
			 
			 while (true) {
			 System.out.println("Start speaking. Press Ctrl-C to quit.\n");

			 Result result = recognizer.recognize();

			 if (result != null) {
			 String resultText = result.getBestFinalResultNoFiller();
			 System.out.println("You said: " + resultText + '\n');
			 // On appelle ici la méthode correspondante à l'ordre reçue
			 
			 
			 } else {
			         System.out.println("I can't hear what you said.\n");
			 		}
			 
			Move4OneWordInstruction.this.endPt.setLocation(x, y);
		}
		}
	
		
		public boolean isGuardRespected(){
			return OneWordInstruction.this.getLastHIDUsed()==this.hid;
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
	

}	
	

        

       
    
