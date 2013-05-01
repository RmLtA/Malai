package org.malai.speech.interaction;

import org.malai.interaction.InitState;
import org.malai.interaction.Interaction;
import org.malai.stateMachine.ITransition;

public class SpeechInteraction extends Interaction implements SpeechEventHandler{
	public SpeechInteraction(){
		super();
	}
	
	public SpeechInteraction(final InitState initState) {
		super(initState);
	}
	


	@Override
	public void onSpeech(String word) {
		if(!activated) return ;

		boolean again = true;
		ITransition t;

		for(int i=0, j=currentState.getTransitions().size(); i<j && again; i++) {
			t = currentState.getTransition(i);

			if(t instanceof SpeechTransition) {
				((SpeechTransition)t).setWord(word);
				again = !checkTransition(t);
			}
		}
		
		
	}

	@Override
	protected void initStateMachine() {
		// TODO Auto-generated method stub
		
	}

}
