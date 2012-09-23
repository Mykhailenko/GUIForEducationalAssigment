package educational_assingment.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import educational_assingment.models.StoreCurriculumModels;
import educational_assingnment.executers.Dispatcher;

public class GUIEducAssignment extends JFrame {

	private static final long serialVersionUID = 1L;
	private StoreCurriculumModels m;

	public GUIEducAssignment() {
		super("Расписание");
		m = new StoreCurriculumModels(new JTabbedPane());
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/Notebook.png").getImage());
		setIconImages(icons);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		DEFAULT_WIDTH = (int) dim_screen.getWidth();
		DEFAULT_HEIGHT = (int) dim_screen.getHeight();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT - 50);
		String s = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file_menu = new JMenu("Файл");
		JMenu settings_menu = new JMenu("Установки");
		menuBar.add(file_menu);
		menuBar.add(settings_menu);
		JMenuItem load_cur = new JMenuItem("Загрузить уч.план", new ImageIcon(
				"images/загрузка меню.png"));
		file_menu.add(load_cur);
		load_cur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dispatcher.safetyDevice() == true)
					new CurriculumLoaderPanel(m);
				else
					JOptionPane
							.showMessageDialog(null,
									"Определите служебную информацию. (Меню 'Установки')");
			}
		});
		load_cur.setPreferredSize(new Dimension(250, 30));
		load_cur.setMinimumSize(load_cur.getPreferredSize());
		load_cur.setMaximumSize(load_cur.getPreferredSize());
		// load_tt.addActionListener();
		JMenuItem delete_cur = new JMenuItem("Удалить уч.план", new ImageIcon(
				"images/удаление меню.png"));
		file_menu.add(delete_cur);
		delete_cur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dispatcher.safetyDevice() == true)
					new DeleteCurPanel(m);
				else
					JOptionPane
							.showMessageDialog(null,
									"Определите служебную информацию. (Меню 'Установки')");
			};
		});
		delete_cur.setPreferredSize(new Dimension(250, 30));
		delete_cur.setMinimumSize(load_cur.getPreferredSize());
		delete_cur.setMaximumSize(load_cur.getPreferredSize());
		// delete_tt.addActionListener();
		file_menu.addSeparator();
		JMenuItem save_all = new JMenuItem("Сохранить все...", new ImageIcon(
				"images/сохранить все.png"));
		file_menu.add(save_all);
		save_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (m.getModelsCount() != 0)
					Dispatcher.saveChanges(m);
				else
					JOptionPane.showMessageDialog(null,
							"Система не содержит учебных планов.");
			};
		});
		save_all.setPreferredSize(new Dimension(250, 30));
		save_all.setMinimumSize(load_cur.getPreferredSize());
		save_all.setMaximumSize(load_cur.getPreferredSize());
		// print_version.addActionListener();
		JMenuItem create_edAssigment = new JMenuItem(
				"Сформировать учебные поручения",
				new ImageIcon("images/уп.png"));
		file_menu.add(create_edAssigment);
		create_edAssigment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dispatcher.isAvailableCreation())
					new EducAssignmentPanel();
				else
					JOptionPane.showMessageDialog(null,
							"Не всем предметам назначены кафедры или количество подгрупп. Опция недоступна.");
			};
		});
		create_edAssigment.setPreferredSize(new Dimension(250, 30));
		create_edAssigment.setMinimumSize(load_cur.getPreferredSize());
		create_edAssigment.setMaximumSize(load_cur.getPreferredSize());
		// print_version.addActionListener();
		file_menu.addSeparator();
		JMenuItem exit = new JMenuItem("Выход", new ImageIcon(
				"images/выход.png"));
		file_menu.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int indicator = JOptionPane.showConfirmDialog(null, "Сохранить внесенные изменения?");
				if(indicator==0)
					Dispatcher.saveChanges(m);
				if(indicator==0 || indicator==1)
					System.exit(0);
			}
		});
		exit.setPreferredSize(new Dimension(250, 30));
		exit.setMinimumSize(load_cur.getPreferredSize());
		exit.setMaximumSize(load_cur.getPreferredSize());

		JMenuItem cathedra = new JMenuItem("Определение списка кафедр...",
				new ImageIcon("images/кафедра меню.png"));
		settings_menu.add(cathedra);
		cathedra.setPreferredSize(new Dimension(250, 30));
		cathedra.setMinimumSize(load_cur.getPreferredSize());
		cathedra.setMaximumSize(load_cur.getPreferredSize());
		cathedra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(m.getModelsCount());
				if (m.getModelsCount() != 0) {
					JOptionPane
							.showMessageDialog(
									null,
									"Для того чтобы внести изменения в установочную информации"
											+ " предворительно удалите все загруженные учебные планы.");
				} else
					new SettingsCathedraPanel();
			}
		});
		JMenuItem specialty = new JMenuItem("Определение специальностей...",
				new ImageIcon("images/специальность меню.png"));
		settings_menu.add(specialty);
		specialty.setPreferredSize(new Dimension(250, 30));
		specialty.setMinimumSize(load_cur.getPreferredSize());
		specialty.setMaximumSize(load_cur.getPreferredSize());
		specialty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (m.getModelsCount() != 0) {
					JOptionPane
							.showMessageDialog(
									null,
									"Для того чтобы внести изменения в установочную информации"
											+ " предворительно удалите все загруженные учебные планы.");
				} else
					new SettingsSpecialtyPanel();
			}
		});
		// insert panels...
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(1, 1));
		main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		main.add(new CurriculumWorkPanel(m));

		this.getContentPane().add(main);
		setVisible(true);
	}

	private int DEFAULT_WIDTH;
	private int DEFAULT_HEIGHT;

	public static void main(String[] args) {
		new GUIEducAssignment();
	}
}
