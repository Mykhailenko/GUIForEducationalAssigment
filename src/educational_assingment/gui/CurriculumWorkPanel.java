package educational_assingment.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import educational_assingment.models.StoreCurriculumModels;
import educational_assingnment.executers.Dispatcher;

public class CurriculumWorkPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static private StoreCurriculumModels m;

	public CurriculumWorkPanel(StoreCurriculumModels models) {
		m = models;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel button_panel1 = new JPanel(new GridLayout(1, 1));
		button_panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		button_panel1.setPreferredSize(new Dimension(1400, 100));
		button_panel1.setMaximumSize(button_panel1.getPreferredSize());
		JPanel button_panel2 = new JPanel(new GridLayout(1, 1));
		button_panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel button_panel = BoxLayoutUtils.createHorizontalPanel();
		button_panel.setBorder(BorderFactory.createLineBorder(Color.white));
		save = new JButton("<html>Загрузить<p>уч.план</html>", new ImageIcon(
				"images/загрузка.png"));
		delete = new JButton("<html>Удалить<p>уч.план</html>", new ImageIcon(
				"images/удаление.png"));
		save.setMargin(new Insets(15, 15, 15, 15));
		save.setVerticalAlignment(SwingConstants.TOP);
		save.setVerticalTextPosition(SwingConstants.BOTTOM);
		save.setIconTextGap(10);
		save.setBorderPainted(false);
		save.setPreferredSize(new Dimension(150, 100));
		save.setMinimumSize(save.getPreferredSize());
		save.setMaximumSize(save.getPreferredSize());
		delete.setMargin(new Insets(15, 15, 15, 15));
		delete.setVerticalAlignment(SwingConstants.TOP);
		delete.setVerticalTextPosition(SwingConstants.BOTTOM);
		delete.setIconTextGap(10);
		delete.setBorderPainted(false);
		delete.setPreferredSize(new Dimension(150, 100));
		delete.setMinimumSize(delete.getPreferredSize());
		delete.setMaximumSize(delete.getPreferredSize());
		button_panel.add(save);
		button_panel.add(delete);
		button_panel.add(Box.createGlue());
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));

		button_panel2.add(button_panel);
		button_panel1.add(button_panel2);

		tabbed = new JTabbedPane();
		m.setTabbed(tabbed);
		m.initData();
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dispatcher.safetyDevice()==true)
					new CurriculumLoaderPanel(m);
				else
					JOptionPane
							.showMessageDialog(null,
									"Определите служебную информацию. (Меню 'Установки')");
			};
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dispatcher.safetyDevice()==true)
					new DeleteCurPanel(m);
				else
					JOptionPane
							.showMessageDialog(null,
									"Определите служебную информацию. (Меню 'Установки')");
			};
		});
		JPanel tabbed_panel = new JPanel(new GridLayout(1, 1));
		tabbed_panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		tabbed_panel.add(tabbed);

		add(button_panel1);
		add(tabbed_panel);
	}

	private JButton save;
	private JButton delete;
	private JTabbedPane tabbed;
}
