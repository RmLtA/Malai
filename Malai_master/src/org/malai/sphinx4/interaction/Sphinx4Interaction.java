package org.malai.sphinx4.interaction;



import org.malai.interaction.InitState;
import org.malai.interaction.Interaction;
import org.malai.stateMachine.ITransition;

import edu.cmu.sphinx.recognizer.Recognizer.State;

public class Sphinx4Interaction extends Interaction implements Sphinx4EventHandler{
	public Sphinx4Interaction(){
		super();
	}
	
	public Sphinx4Interaction(final InitState initState) {
		super(initState);
	}
	


	@Override
	public void onStatusChanged(State status) {
		if(!activated) return ;

		boolean again = true;
		ITransition t;

		for(int i=0, j=currentState.getTransitions().size(); i<j && again; i++) {
			t = currentState.getTransition(i);

			if(t instanceof StatusChangedTransition) {
				((StatusChangedTransition)t).setState(status);
				again = !checkTransition(t);
			}
		}
		
		
	}

	@Override
	protected void initStateMachine() {
		// TODO Auto-generated method stub
		
	}

}
