package org.malai.sphinx4.interaction;

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


public class SpeechEventManager extends BasicEventManager<StateListener> implements StateListener{
	private static final String PROP_DECODER = null;
	private static final String PROP_MONITORS = null;
	private List<SpeechEventHandler> speechHandlers;
	
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
		if(speechHandlers != null) {
			for(final SpeechEventHandler handler : speechHandlers)
				handler.onSpeechStateChanged(arg0);
		}
		
	}
		


	
	
	


}
