package educational_assingment.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import educational_assingment.data_base.CurriculumTransaction;

public class SettingsSpecialtyPanel extends JFrame{
	private static final long serialVersionUID = 1L;
	private SettingsSpecialtyTable table;
	public   SettingsSpecialtyPanel(){
		super("Определение специальностей...");
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/специальность.png").getImage());
		setIconImages(icons);
		DEFAULT_WIDTH = 700;
		DEFAULT_HEIGHT = 337;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		this.setResizable(false);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) dim_screen.getWidth()/2-DEFAULT_WIDTH/2, (int) dim_screen.getHeight()/2-DEFAULT_HEIGHT/2);
		
		JPanel content = BoxLayoutUtils.createVerticalPanel();
		JPanel first_content = BoxLayoutUtils.createVerticalPanel();
		first_content.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel rings = BoxLayoutUtils.createHorizontalPanel();
		rings.setPreferredSize(new Dimension(700, 271));
		rings.setMaximumSize(rings.getPreferredSize());
		
		JPanel text_panel_first = BoxLayoutUtils.createHorizontalPanel();
		text_panel_first.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JPanel text_label = BoxLayoutUtils.createHorizontalPanel();
		text_label.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel text1_label = BoxLayoutUtils.createHorizontalPanel();
		text1_label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel label = new JLabel("<html>Введите названия специальностей факультета.<p><p>" +
				"Эти данные будут использоваться при идентификации учебных планов.<p><p></html>");
		text1_label.setBackground(Color.white);
		text1_label.add(label);
		text1_label.setPreferredSize(new Dimension(300, 220));
		text1_label.setMaximumSize(text1_label.getPreferredSize());
		text_label.add(text1_label);
		text_panel_first.add(text_label);
		
		JPanel table_panel_first = BoxLayoutUtils.createHorizontalPanel();
		table_panel_first.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		table = new SettingsSpecialtyTable("Специальности", 400);
		table_panel_first.add(new JScrollPane(table.getTable()));
		
		rings.add(text_panel_first);
		rings.add(table_panel_first);
		
		JPanel combo_checks = BoxLayoutUtils.createHorizontalPanel();
		combo_checks.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
		JLabel cours_label = new JLabel(" Количество курсов: ");
		JRadioButton four = new JRadioButton("четыре");
		four.setActionCommand("four");
		JRadioButton five = new JRadioButton("пять");
		five.setActionCommand("five");
		five.setSelected(true);
		JRadioButton six = new JRadioButton("шесть");
		six.setActionCommand("six");
		ButtonGroup group = new ButtonGroup();
		group.add(four);
		group.add(five);
		group.add(six);
		cours_label.setPreferredSize(new Dimension(160,20));
		cours_label.setMinimumSize(cours_label.getPreferredSize());
		cours_label.setMaximumSize(cours_label.getPreferredSize());
		four.setPreferredSize(new Dimension(110,20));
		four.setMinimumSize(four.getPreferredSize());
		four.setMaximumSize(four.getPreferredSize());
		five.setPreferredSize(new Dimension(110,20));
		five.setMinimumSize(five.getPreferredSize());
		five.setMaximumSize(five.getPreferredSize());
		six.setPreferredSize(new Dimension(110,20));
		six.setMinimumSize(six.getPreferredSize());
		six.setMaximumSize(six.getPreferredSize());
		combo_checks.add(cours_label);
		combo_checks.add(four);
		combo_checks.add(five);
		combo_checks.add(six);
		
		JPanel button_panel = BoxLayoutUtils.createHorizontalPanel();
		button_panel.setBorder(BorderFactory.createEmptyBorder(15,5,5,7));
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Отмена");
		ok.setPreferredSize(new Dimension(100,27));
		ok.setMaximumSize(ok.getPreferredSize());
		ok.setMinimumSize(ok.getPreferredSize());
		cancel.setPreferredSize(new Dimension(100,27));
		cancel.setMaximumSize(cancel.getPreferredSize());
		cancel.setMinimumSize(cancel.getPreferredSize());
		button_panel.add(Box.createGlue());
		button_panel.add(ok);
		button_panel.add(Box.createHorizontalStrut(5));
		button_panel.add(cancel);
		
		cancel.addActionListener(new ButtonsActionListener(this, ok, cancel,four, five, six));
		ok.addActionListener(new ButtonsActionListener(this, ok, cancel,four, five, six));
		
		
		first_content.add(rings);
		first_content.add(combo_checks);
		content.add(first_content);
		content.add(button_panel);
		setContentPane(content);
		setVisible(true);
	}
	private class ButtonsActionListener  implements ActionListener{
		private JFrame frame;
		private JButton ok;
		private JButton cancel;
		private JRadioButton four;
		private JRadioButton five;
		private JRadioButton six;
		public ButtonsActionListener(JFrame f, JButton ok , JButton cancel,JRadioButton four,
				JRadioButton five, JRadioButton six){
			frame = f;
			this.ok = ok;
			this.cancel = cancel;
			this.four = four;
			this.five = five;
			this.six = six;
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==cancel){
				frame.dispose();
			}
			if(e.getSource()==ok){
				CurriculumTransaction t = new CurriculumTransaction();
				if(four.isSelected())
					t.insertSpecialiesData(table.getModel().getData(), 4);
				if(five.isSelected())
					t.insertSpecialiesData(table.getModel().getData(), 5);
				if(six.isSelected())
					t.insertSpecialiesData(table.getModel().getData(), 6);
				frame.dispose();
			}
		}
	}
	private int DEFAULT_WIDTH;
	private int DEFAULT_HEIGHT;
}