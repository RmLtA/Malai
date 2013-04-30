package org.malai.sphinx4.interaction;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.malai.interaction.BasicEventManager;
import org.malai.interaction.EventHandler;
import org.malai.sphinx4.interaction.library.OneWordInstruction;


import edu.cmu.sphinx.decoder.Decoder;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.instrumentation.Monitor;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.recognizer.StateListener;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import edu.cmu.sphinx.util.props.PropertySheet;


public class SpeechEventManager extends BasicEventManager<StateListener> implements StateListener{
	private static final String PROP_DECODER = null;
	private static final String PROP_MONITORS = null;
	private List<SpeechEventHandler> speechHandlers;
	private static String resultText;
	
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
	
	public void oneWordSpeechEvent(String word){
		if(speechHandlers != null) {
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
			
			 System.out.println("Say: ( Start )");
			 
			 while (true) {
			 System.out.println("Start speaking. Press Ctrl-C to quit.\n");

			 Result result = recognizer.recognize();

			 if (result != null) {
			 resultText = result.getBestFinalResultNoFiller();
			 System.out.println("You said: " + resultText + '\n');
			 
			 /**On appelle la méthode correspondante à la méthode reçue*/
			 if (resultText.compareTo("Hello rita")!=0){
				 /*SphinxRect jc = new SphinxRect();
				 jc.setBackground(Color.WHITE);
			     jc.setPreferredSize(new Dimension(800,900));
			     GUIHelper.showOnFrame(jc,"test");*/
				 
			 } else {
				 System.out.println("répéter");
			 	 }

			 } else {
			         System.out.println("I can't hear what you said.\n");
			 		}
			 }
			 }
		
			for(final SpeechEventHandler handler : speechHandlers)
			 handler.onSpeech(resultText);
		}
		
		
	}
	
	




