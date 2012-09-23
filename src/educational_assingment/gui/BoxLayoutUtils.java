package educational_assingment.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BoxLayoutUtils {
	public static JPanel createVerticalPanel(){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		return p;
	}
	public static JPanel createHorizontalPanel(){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		return p;
	}
}


