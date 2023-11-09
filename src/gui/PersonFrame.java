package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import content.Gambler;
import content.Person;
import content.Trainer;
import init.BreedingHorses;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JList;

// Trainer Frame 
public class PersonFrame extends JFrame {
	private JPanel contentPane;
	private DefaultListModel<String> ListPeopleModel = new DefaultListModel<>();
	private JList<String> list = new JList<>(ListPeopleModel);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonFrame window = new PersonFrame(bh);
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
	
	public PersonFrame(BreedingHorses bh) {
		super("Trainers");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton = new JButton("Add");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent event) {
		        	 AddTrainer at = new AddTrainer(bh,ListPeopleModel);
		     		//list = new JList(ListPeopleModel);
		    		GridBagConstraints gbc_list = new GridBagConstraints();
		    		gbc_list.gridwidth = 4;
		    		gbc_list.insets = new Insets(0, 0, 0, 5);
		    		gbc_list.fill = GridBagConstraints.BOTH;
		    		gbc_list.gridx = 0;
		    		gbc_list.gridy = 7;
		    		contentPane.add(list, gbc_list);
		        	 at.setVisible(true);
		         }
		      });
		
		 
	
		
		JButton btnNewButton_1 = new JButton("Remove");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 5;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	
		btnNewButton_1.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	        	 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to remove","remove",JOptionPane.YES_NO_OPTION);
	        	 if(opt == 0) {
	        	 DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
	        	 int selectedIndex = list.getSelectedIndex();
	        	 List<String> list1 = list.getSelectedValuesList();
	        	 String[] selectedVal = list1.get(selectedIndex).split(" ");
	        	 String id = selectedVal[0];
	       
	        	 String worker_id = selectedVal[8];
	        	 Trainer trainer_remove = new Trainer(id, worker_id);
	        	 bh.removePerson(trainer_remove);
	        	 if (selectedIndex != -1) {
	        	     model.remove(selectedIndex);
	        	 }
	        	 JOptionPane.showMessageDialog(null, "Removed Successfully!" );

	         }
	         }	 
	        	 
	        
	      });	
		}
	
	 // handle button event
	
	


	
	
	

}
