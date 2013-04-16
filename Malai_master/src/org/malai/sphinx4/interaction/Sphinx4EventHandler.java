package org.malai.sphinx4.interaction;


import edu.cmu.sphinx.recognizer.Recognizer;


public interface Sphinx4EventHandler{
	
  void onStatusChanged(Recognizer.State status);
  
}
