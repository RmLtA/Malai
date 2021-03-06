package test.org.malai.interaction;

import javax.swing.JButton;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.malai.interaction.Interaction;
import org.malai.interaction.InteractionHandler;


public abstract class TestInteraction<T extends Interaction> extends TestCase {
	protected T interaction;

	protected InteractionHandler handler;

	protected boolean visitStart;
	protected boolean visitStop;
	protected boolean visitUpdate;
	protected boolean visitAbort;


	@Override
	@Before
	public void setUp() {
		visitAbort	= false;
		visitStop 	= false;
		visitStart 	= false;
		visitUpdate = false;
	}


	@Test
	public void testOnMoveCrash() {
		JButton button = new JButton();
		interaction.onMove(0, 10, 20, true, -127862, button);
		interaction.onMove(Integer.MAX_VALUE, 10, 20, false, 0, button);
		interaction.onMove(Integer.MIN_VALUE, 10, 20, false, 127862, button);
		interaction.onMove(897948, Integer.MAX_VALUE, 20, true,127862, button);
		interaction.onMove(-897948, Integer.MIN_VALUE, 20, false, 127862, button);
		interaction.onMove(897948, -20, Integer.MAX_VALUE, true,127862, button);
		interaction.onMove(-897948, 20, Integer.MIN_VALUE, true,127862, button);
		interaction.onMove(897948, 20, -10, true,Integer.MAX_VALUE, button);
		interaction.onMove(897948, 20, -10, false, Integer.MIN_VALUE, button);
		interaction.onMove(897948, 20, -10, true,-100, null);
	}


	@Test
	public void testOnReleaseCrash() {
		JButton button = new JButton();
		interaction.onRelease(0, 10, 20, -127862, button);
		interaction.onRelease(Integer.MAX_VALUE, 10, 20, 0, button);
		interaction.onRelease(Integer.MIN_VALUE, 10, 20, 127862, button);
		interaction.onRelease(897948, Integer.MAX_VALUE, 20, 127862, button);
		interaction.onRelease(-897948, Integer.MIN_VALUE, 20, 127862, button);
		interaction.onRelease(897948, -20, Integer.MAX_VALUE, 127862, button);
		interaction.onRelease(-897948, 20, Integer.MIN_VALUE, 127862, button);
		interaction.onRelease(897948, 20, -10, Integer.MAX_VALUE, button);
		interaction.onRelease(897948, 20, -10, Integer.MIN_VALUE, button);
		interaction.onRelease(897948, 20, -10, -100, null);
	}



	@Test
	public void testOnPressCrash() {
		JButton button = new JButton();
		interaction.onPressure(0, 10, 20, -127862, button);
		interaction.onPressure(Integer.MAX_VALUE, 10, 20, 0, button);
		interaction.onPressure(Integer.MIN_VALUE, 10, 20, 127862, button);
		interaction.onPressure(897948, Integer.MAX_VALUE, 20, 127862, button);
		interaction.onPressure(-897948, Integer.MIN_VALUE, 20, 127862, button);
		interaction.onPressure(897948, -20, Integer.MAX_VALUE, 127862, button);
		interaction.onPressure(-897948, 20, Integer.MIN_VALUE, 127862, button);
		interaction.onPressure(897948, 20, -10, Integer.MAX_VALUE, button);
		interaction.onPressure(897948, 20, -10, Integer.MIN_VALUE, button);
		interaction.onPressure(897948, 20, -10, -100, null);
	}


	@Test
	public void testOnKeyReleasedCrash() {
		interaction.onKeyRelease(-10, 0, new JButton());
		interaction.onKeyRelease(Integer.MAX_VALUE, 0, new JButton());
		interaction.onKeyRelease(Integer.MIN_VALUE, 0, new JButton());
		interaction.onKeyRelease(0, 0, new JButton());
		interaction.onKeyRelease(10, 0, new JButton());
		interaction.onKeyRelease(1, Integer.MIN_VALUE, new JButton());
		interaction.onKeyRelease(1, Integer.MAX_VALUE, new JButton());
		interaction.onKeyRelease(1, 10, new JButton());
		interaction.onKeyRelease(1, -10, new JButton());
	}


	@Test
	public void testOnKeyPressedCrash() {
		interaction.onKeyPressure(-10, 0, new JButton());
		interaction.onKeyPressure(Integer.MAX_VALUE, 0, new JButton());
		interaction.onKeyPressure(Integer.MIN_VALUE, 0, new JButton());
		interaction.onKeyPressure(0, 0, new JButton());
		interaction.onKeyPressure(10, 0, new JButton());
		interaction.onKeyPressure(1, Integer.MIN_VALUE, new JButton());
		interaction.onKeyPressure(1, Integer.MAX_VALUE, new JButton());
		interaction.onKeyPressure(1, 10, new JButton());
		interaction.onKeyPressure(1, -10, new JButton());
	}
}
