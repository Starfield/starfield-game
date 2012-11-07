/**
 * 
 */
package game.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * @author schroeder_jan
 *
 */
public abstract class AbstractToolbar extends JToolBar{

	
	protected JToolBar toolBar;
	
	public AbstractToolbar(String name) {
		 super(name);
		 JToolBar toolBar = new JToolBar(name);
	     addButtons(toolBar);

	     setPreferredSize(new Dimension(100, 130));
	     add(toolBar, BorderLayout.PAGE_START);	 
	}

	protected abstract void addButtons(JToolBar toolBar2);

}
