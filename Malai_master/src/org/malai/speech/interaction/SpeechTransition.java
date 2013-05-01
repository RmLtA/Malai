package org.malai.speech.interaction;

import org.malai.interaction.Transition;
import org.malai.stateMachine.SourceableState;
import org.malai.stateMachine.TargetableState;


public class SpeechTransition extends Transition {
	protected String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * {@link Transition#Transition(SourceableState, TargetableState)}
	 */
	public SpeechTransition(final SourceableState inputState, final TargetableState outputState) {
		super(inputState, outputState);
	}


}
