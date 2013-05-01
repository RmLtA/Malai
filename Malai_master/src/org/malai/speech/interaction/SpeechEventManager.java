package org.malai.speech.interaction;

import java.util.ArrayList;
import java.util.List;

import org.malai.interaction.BasicEventManager;
import org.malai.interaction.EventHandler;

import edu.cmu.sphinx.decoder.Decoder;
import edu.cmu.sphinx.instrumentation.Monitor;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.recognizer.StateListener;
import edu.cmu.sphinx.util.props.PropertyException;
import edu.cmu.sphinx.util.props.PropertySheet;

/**
 * A speech event manager gathers speech events produces by the recognizer and transfers them handlers.<br>
 * <br>
 * This file is part of Malai.<br>
 * Copyright (c) 2009-2013 Tom Demulier--Chevret, Juliette Gourlaouen, Maxime Lorant, Liantsoa Rasata-Manantena <br>
 * <br>
 * Malai is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * <br>
 * Malai is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br>
 * <br>
 * 2013-02-26<br>
 * @author Tom Demulier--Chevret
 * @author Juliette Gourlaouen
 * @author Maxime Lorant
 * @author Liantsoa Rasata-Manantena
 * @since 0.2
 */

public class SpeechEventManager extends BasicEventManager<StateListener> implements StateListener{
	private static final String PROP_DECODER = null;
	private static final String PROP_MONITORS = null;
	
	/** A subset of the set 'handlers' corresponding to the Speech Handlers. */
	private List<SpeechEventHandler> speechHandlers;

	/**
	 * Create Speech Event Manager 
	 */
	public SpeechEventManager() {
		super();
		speechHandlers = null;
	}
	
	@Override
	public void detachForm(StateListener state) {
		if(state!=null){
			((Recognizer) state).removeStateListener(this);
		}
		
	}
	
	@Override
	public void attachTo(final StateListener state) {
		if(state!=null){
			((Recognizer) state).addStateListener(this);
		}
		
	}
	
	@Override
	public void addHandlers(final EventHandler h) {
		super.addHandlers(h);
		if(h instanceof SpeechEventHandler) {
			if(speechHandlers==null) 
				speechHandlers = new ArrayList<SpeechEventHandler>();
			speechHandlers.add((SpeechEventHandler)h);
		}
	}


	@Override
	public void removeHandler(final EventHandler h) {
		super.removeHandler(h);
		if(h != null && speechHandlers!=null) 
			speechHandlers.remove(h);
	}
	

	@Override
	public void newProperties(PropertySheet arg0) throws PropertyException {
		 @SuppressWarnings("unused")
		Decoder decoder = (Decoder) arg0.getComponent(PROP_DECODER);
	     @SuppressWarnings("unused")
	     List<Monitor> monitors = arg0.getComponentList(PROP_MONITORS, Monitor.class);

	     @SuppressWarnings("unused")
		String name = arg0.getInstanceName();
		
	}


	
	@Override
	public void statusChanged(State arg0) {	
		
	}
	
	public void onSpeechEvent(String word){
		System.out.println("reçu onSpeechEvent 1");
		if(speechHandlers != null) {
			for(final SpeechEventHandler handler : speechHandlers)
			 handler.onSpeech(word);
			System.out.println("reçu onSpeechEvent 2"); /**problème*/
		}	
	}
}
	
	




