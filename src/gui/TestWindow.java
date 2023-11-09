package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import content.HorseInCompetition;
import init.BreedingHorses;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

//The main screen of the system , In this screen you can navigate to the other screens in the system
public class TestWindow {
	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow window = new TestWindow(bh);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestWindow(BreedingHorses bh) {
		initialize(bh);
	}
	
	private void initialize(BreedingHorses bh) {
		frame = new JFrame("BreedingHorses System");
		frame.setBounds(100, 100, 712, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
	
		JMenuItem actions_reports = new JMenuItem("Actions and Reports");
		file.add(actions_reports);
		JMenu Add_New = new JMenu("Manage");
		menuBar.add(Add_New);
		actions_reports.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	ActionAndReports pf = new ActionAndReports(bh);
		    }
		});
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
	
		exit.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	System.exit(0);
		    }
		});
	

		JMenuItem trainers = new JMenuItem("Trainers");

 
		trainers.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	PersonFrame pf = new PersonFrame(bh);
		    	pf.setVisible(true);
		    }
		});

		
		Add_New.add(trainers);
		
		JMenuItem vet = new JMenuItem("veterinarians");
		Add_New.add(vet);
		
		vet.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	VeterinarianFrame pf = new VeterinarianFrame(bh);
		    	pf.setVisible(true);
		    }
		});
		JMenuItem eq = new JMenuItem("Equerstrians");
		Add_New.add(eq);
		
		eq.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	EquestrianFrame pf = new EquestrianFrame(bh);
		    	pf.setVisible(true);
		    }
		});
		
		JMenuItem bm = new JMenuItem("BookMakers");
		Add_New.add(bm);
		
		bm.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	BookMakerFrame pf = new BookMakerFrame(bh);
		    	pf.setVisible(true);
		    }
		});
		
		JMenuItem gamblers = new JMenuItem("Gamblers");
		Add_New.add(gamblers);
		
		gamblers.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	GamblerFrame pf = new GamblerFrame(bh);
		    	pf.setVisible(true);
		    }
		});
		
		JMenuItem horses = new JMenuItem("Horses");
		Add_New.add(horses);
		
		horses.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	HorseFrame pf = new HorseFrame(bh);
		    	pf.setVisible(true);
		    }
		});
		
		JMenu competition = new JMenu("Competition");
		menuBar.add(competition);
		
		JMenu gambling = new JMenu("Gambling");
		menuBar.add(gambling);
		
		JMenuItem horses_competitions = new JMenuItem("Horses Competitions");
		competition.add(horses_competitions);
		
		horses_competitions.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	HorsesCompetitions pf = new HorsesCompetitions(bh);
		    }
		});
		
		JMenuItem manage_bets = new JMenuItem("Manage Bets");
		gambling.add(manage_bets);
	
	}
		


}
