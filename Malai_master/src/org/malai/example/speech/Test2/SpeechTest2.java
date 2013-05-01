package org.malai.example.speech.Test2;

import java.net.URL;

import org.malai.speech.interaction.SpeechEventManager;
import org.malai.speech.interaction.library.SpeechInstruction;


import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SpeechTest2 {

	public static void main(String[] args) {
		/**Identification du fichier de configuration*/
		URL url = SpeechInstruction.class.getResource("hellongram.config.xml");
		
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
		 
		 printInstructions();
		 
		 while (true) {
		 System.out.println("Start speaking. Press Ctrl-C to quit.\n");

		 Result result = recognizer.recognize();

		 	if (result != null) {
		 		String resultText = result.getBestFinalResultNoFiller();
		 		System.out.println("You said: " + resultText + '\n');
		 
		 		/**On appelle la méthode correspondante à la méthode reçue*/
		 		if (resultText.compareTo("Start")!=0){
		 			SpeechEventManager m = new SpeechEventManager();
		 			m.onSpeechEvent(resultText);
		 			System.out.println("Start"); 
			 
		 			} else {
		 					System.out.println("répéter");
		 					}
		 } else {
		         System.out.println("I can't hear what you said.\n");
		 		}
		 }
		 
	}
	

    /** Prints out what to say for this demo. */
    private static void printInstructions() {
        System.out.println("Sample sentences:\n" +
                "the green one right in the middle\n" +
                "the purple one on the lower right side\n" +
                "the closest purple one on the far left side\n" +
                "the only one left on the left\n\n" +
                "Refer to the file hellongram.test for a complete list.\n");
    }

}


