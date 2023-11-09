package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import content.Horse;
import init.BreedingHorses;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class HorseFrame extends JFrame {

	private JPanel contentPane;
	BreedingHorses bh ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HorseFrame window = new HorseFrame(bh);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HorseFrame(BreedingHorses bh) {
		
		super("Horse");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton = new JButton("Add");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent event) {
		        	 AddHorse at = new AddHorse(bh);
		        	 setVisible(false);
		        	 at.setVisible(true);
		         }
		      });
		
		 
	
		
		

		JButton AddTrainerTohorse = new JButton("Add Trainer To Horse");
		AddTrainerTohorse.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_AddTrainerTohorse = new GridBagConstraints();
		gbc_AddTrainerTohorse.insets = new Insets(0, 0, 5, 5);
		gbc_AddTrainerTohorse.gridx = 2;
		gbc_AddTrainerTohorse.gridy = 5;
		contentPane.add(AddTrainerTohorse, gbc_AddTrainerTohorse);
		
		AddTrainerTohorse.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	        	AddTrainerToHorse ath = new AddTrainerToHorse(bh);
	        	ath.setVisible(true);

	        		 }
	       });
	    Object[] targetArray =  bh.getHorses().values().toArray();
		JList list = new JList(targetArray);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		add( new JScrollPane( list ) );
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.gridwidth = 8;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 6;
		contentPane.add(list, gbc_list);
		
		
	}
}
