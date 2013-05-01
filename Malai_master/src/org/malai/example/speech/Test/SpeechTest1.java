package org.malai.example.speech.Test;

import java.net.URL;

import org.malai.speech.interaction.SpeechEventManager;
import org.malai.speech.interaction.library.SpeechInstruction;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechTest1 {

	public static void main(String[] args) {
		/**Identification du fichier de configuration*/
		URL url = SpeechInstruction.class.getResource("helloworld.config.xml");
		
		/**Chargement de la configuration*/
		ConfigurationManager cm = new ConfigurationManager(url);
		
		/**Cr�ation de l'objet de reconnaissance*/
		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();
		
		/**Cr�ation de l'objet microphone*/
		 Microphone microphone = (Microphone) cm.lookup("microphone");
		 
		 /**On v�rifie si le microphone est pr�t � enregistrer*/
		 if (!microphone.startRecording()) {
		     System.out.println("Cannot start microphone.");
		     recognizer.deallocate();
		     System.exit(1);
		 }
		
		 System.out.println("Say: ( Start | Stop | Left | Right )");
		 
		 while (true) {
		 System.out.println("Start speaking. Press Ctrl-C to quit.\n");

		 Result result = recognizer.recognize();

		 	if (result != null) {
		 		String resultText = result.getBestFinalResultNoFiller();
		 		System.out.println("You said: " + resultText + '\n');
		 
		 		/**On appelle la m�thode correspondante � la m�thode re�ue*/
		 		if (resultText.compareTo("Start")!=0){
		 			SpeechEventManager m = new SpeechEventManager();
		 			m.onSpeechEvent(resultText);
		 			System.out.println("Start"); 
			 
		 			} else {
		 					System.out.println("r�p�ter");
		 					}
		 } else {
		         System.out.println("I can't hear what you said.\n");
		 		}
		 }
		 
	}

}


