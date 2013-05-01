package org.malai.speech.interaction;

/*import edu.cmu.sphinx.recognizer.Recognizer;*/


public interface SpeechEventHandler{
	
  void onSpeech(String word);

  /*void onSpeechStateChanged(Recognizer.State status);*/
  
}
