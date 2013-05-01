package org.malai.speech.interaction;

import org.malai.interaction.Transition;
import org.malai.stateMachine.SourceableState;
import org.malai.stateMachine.TargetableState;


public class SpeechTransition extends Transition {
	protected String word;
	
	/**
	 * @return The word recognized.
	 * @since 0.2
	 */
	public String getWord() {
		return word;
	}
	/**
	 * Sets the word recognized.
	 * @param button The word recognized. Must not be null.
	 * @since 0.2
	 */
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
