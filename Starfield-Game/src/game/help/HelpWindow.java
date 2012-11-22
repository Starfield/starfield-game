/**
 * 
 */
package game.help;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Diese Klasse bildet das Hauptfenster der Hilfe ab. <br>
 * Das Fenster soll losgelöst vom Spiel sein, sodass es in einem eigenen Thread
 * neben dem eigentlichen Spielfenster bestehen kann.
 * 
 * @author Jan
 * 
 */
public class HelpWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Singleton Instanz der Hilfe */
	private static HelpWindow _instance;
	/** Content der Hilfe */
	private final JEditorPane _content;
	/** Die geladene Hilfeseiten */
	private Hashtable<String, String> _helpContent;
	/** TreeValues */
	private Vector<String> _treeValues;

	public synchronized static HelpWindow getInstance() {
		if (_instance == null)
			_instance = new HelpWindow();
		if (!_instance.isVisible())
			_instance.setVisible(true);
		_instance.toFront();
		return _instance;
	}

	private HelpWindow() {
		super("Starfield - Help Contents");
		// CloseAction überschreiben
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 0));
		_content = new JEditorPane();
		_content.setEditable(false);
		_content.setContentType("text/html");
		getContentPane().add(new JScrollPane(_content), BorderLayout.CENTER);

		initHelp();

		setMinimumSize(new Dimension(700, 500));
		setLocationRelativeTo(null);
	}

	private void initHelp() {

		readHelpContentFromSystem();
		JTree tree = new JTree(_treeValues);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent pE) {
				String selection = pE.getPath().getLastPathComponent()
						.toString();
				String pageName = _helpContent.get(selection);
				if (pageName != null) {
					String loadedPage = readHTMLPage(pageName);
					if (loadedPage != null)
						setContent(loadedPage);
				}

			}
		});
		tree.setSelectionRow(0);
		getContentPane().add(tree, BorderLayout.WEST);
	}

	private void setContent(String htmlPage) {

		_content.setText(htmlPage);
	}

	private String readHTMLPage(String pageName) {
		URL url = ClassLoader.class.getResource(pageName);
		if (url == null) {
			System.out.println("Resource '" + pageName + "' nicht gefunden");
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader is = new InputStreamReader(url.openStream());
			BufferedReader in = new BufferedReader(is);

			for (String s; (s = in.readLine()) != null;) {

				sb.append(s);

			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}

	private void readHelpContentFromSystem() {
		URL url = ClassLoader.class.getResource("/helpcontent/help.xml");
		if (url == null) {
			System.out.println("Fehler im ClassLoader");
		}

		try {
			File help = new File(url.toURI());
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document dom;
			dom = db.parse(help);
			Element docEle = dom.getDocumentElement();
			NodeList nl;
			nl = docEle.getElementsByTagName("kapitel"); // liefert den Inhalt
															// den der tag
															// umschließt

			_helpContent = new Hashtable<String, String>();
			_treeValues = new Vector<String>();
			for (int i = 0; i < nl.getLength(); i++) {
				_treeValues.add(nl.item(i).getFirstChild().getTextContent());
				_helpContent.put(nl.item(i).getFirstChild().getTextContent(),
						nl.item(i).getLastChild().getTextContent());
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
