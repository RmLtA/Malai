package org.malai.example.speech.Test1;

import java.net.URL;

import org.malai.speech.interaction.SpeechEventManager;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechTest1 {

	public static void main(String[] args) {
		
		/**Identification du fichier de configuration*/
		URL url = SpeechTest1.class.getResource("helloworld.config.xml");
		
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
		
		 System.out.println("Say: ( Start | Stop | Left | Right )");
		 
		 while (true) {
		 System.out.println("Start speaking. Press Ctrl-C to quit.\n");

		 Result result = recognizer.recognize();

		 	if (result != null) {
		 		String resultText = result.getBestFinalResultNoFiller();
		 		System.out.println("You said: " + resultText + '\n');
		 
		 		/**On appelle la méthode correspondante à la méthode reçue*/
		 		if (resultText.compareTo("Start")==0){
		 			SpeechEventManager m = new SpeechEventManager();
		 			m.onSpeechEvent(resultText);
		 			System.out.println("Start"); 
		 			} 
		 		if (resultText.compareTo("Stop")==0){
		 			SpeechEventManager m = new SpeechEventManager();
		 			m.onSpeechEvent(resultText);
		 			System.out.println("Stop"); 
		 			} 
		 		if (resultText.compareTo("Left")==0){
		 			SpeechEventManager m = new SpeechEventManager();
		 			m.onSpeechEvent(resultText);
		 			System.out.println("Left"); 
		 			} 
		 		if (resultText.compareTo("Right")==0){
		 			SpeechEventManager m = new SpeechEventManager();
		 			m.onSpeechEvent(resultText);
		 			System.out.println("Right"); 
		 			}		
		 } else {
		         System.out.println("I can't hear what you said.\n");
		 		}
		 }
		 
	}

}


