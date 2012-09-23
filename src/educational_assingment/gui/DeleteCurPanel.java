package educational_assingment.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import educational_assingment.data_base.CurriculumTransaction;
import educational_assingment.models.StoreCurriculumModels;
import educational_assingnment.executers.Dispatcher;

public class DeleteCurPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private StoreCurriculumModels m;

	public DeleteCurPanel(StoreCurriculumModels store) {
		super("Удаление учебных планов");
		this.m=store;
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/удаление.png").getImage());
		setIconImages(icons);
		DEFAULT_WIDTH = 380;
		DEFAULT_HEIGHT = 180;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setResizable(false);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) dim_screen.getWidth() / 2 - DEFAULT_WIDTH / 2,
				(int) dim_screen.getHeight() / 2 - DEFAULT_HEIGHT / 2);
		
		JLabel l = new JLabel("Специальность");
		JComboBox<String> c = new JComboBox<String>();
		CurriculumTransaction trans = new CurriculumTransaction();
		ArrayList<String> spec = trans.getSpecialties();
		for(String str : spec)
			c.addItem(str);
		l.setPreferredSize(new Dimension(130,40));
		l.setMaximumSize(l.getPreferredSize());
		l.setMinimumSize(l.getPreferredSize());
		c.setPreferredSize(new Dimension(230,40));
		c.setMaximumSize(c.getPreferredSize());
		c.setMinimumSize(c.getPreferredSize());
		c.setBackground(Color.white);
		
		JPanel f = BoxLayoutUtils.createHorizontalPanel();
		f.add(l);
		f.add(c);
		JPanel ff = new JPanel(new GridLayout(1,1));
		ff.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		ff.add(f);
		JPanel fff = new JPanel(new GridLayout(1,1));
		fff.setBorder(BorderFactory.createEtchedBorder());
		fff.add(ff);
		
		JPanel button_panel = BoxLayoutUtils.createHorizontalPanel();
		button_panel.setBorder(BorderFactory.createEmptyBorder(15,5,5,7));
		button_panel.setPreferredSize(new Dimension(400,50));
		button_panel.setMaximumSize(button_panel.getPreferredSize());
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Отмена");
		ok.setPreferredSize(new Dimension(110,27));
		ok.setMaximumSize(ok.getPreferredSize());
		ok.setMinimumSize(ok.getPreferredSize());
		cancel.setPreferredSize(new Dimension(110,27));
		cancel.setMaximumSize(cancel.getPreferredSize());
		cancel.setMinimumSize(cancel.getPreferredSize());
		button_panel.add(Box.createGlue());
		button_panel.add(ok);
		button_panel.add(Box.createHorizontalStrut(5));
		button_panel.add(cancel);
		
		JPanel content = BoxLayoutUtils.createVerticalPanel();
		content.add(fff);
		content.add(button_panel);
		this.setContentPane(content);
		ok.addActionListener(new ButtonsActionListener(this, ok, cancel, c, m));
		cancel.addActionListener(new ButtonsActionListener(this,ok, cancel, c, m));
		this.setVisible(true);
	}
	private class ButtonsActionListener  implements ActionListener{
		private JFrame frame;
		private JButton ok;
		private JButton cancel;
		private StoreCurriculumModels store;
		private JComboBox<String> combo_spec;
		public ButtonsActionListener(JFrame frame, JButton ok , JButton cancel, JComboBox<String> combo_spec,
				StoreCurriculumModels store){
			this.store = store;
			this.ok = ok;
			this.cancel = cancel;
			this.combo_spec = combo_spec;
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e){
			String specialty = combo_spec.getSelectedItem().toString();
			if(e.getSource()==cancel){
				frame.dispose();
			}
			if(e.getSource()==ok){
				Dispatcher.deleteCurriculumData(specialty, store);
				frame.dispose();
			}
		}
	}
	private int DEFAULT_WIDTH;
	private int DEFAULT_HEIGHT;

}
