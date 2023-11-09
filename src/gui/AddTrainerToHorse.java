package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import content.ValidateInput;
import init.BreedingHorses;
import utils.Genre;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddTrainerToHorse extends JFrame {

	private JPanel contentPane;
	private BreedingHorses bh;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton add;
	private JButton cancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTrainerToHorse window = new AddTrainerToHorse(bh);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddTrainerToHorse(BreedingHorses bh) {
		super("Add Trainer To Horse");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel trainer_id = new JLabel("Trainer Id");
		GridBagConstraints gbc_trainer_id = new GridBagConstraints();
		gbc_trainer_id.insets = new Insets(0, 0, 5, 5);
		gbc_trainer_id.gridx = 1;
		gbc_trainer_id.gridy = 1;
		contentPane.add(trainer_id, gbc_trainer_id);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel worker_id = new JLabel("Worker Id");
		GridBagConstraints gbc_worker_id = new GridBagConstraints();
		gbc_worker_id.insets = new Insets(0, 0, 5, 5);
		gbc_worker_id.gridx = 1;
		gbc_worker_id.gridy = 2;
		contentPane.add(worker_id, gbc_worker_id);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 2;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel horse_id = new JLabel("Horse Id");
		GridBagConstraints gbc_horse_id = new GridBagConstraints();
		gbc_horse_id.insets = new Insets(0, 0, 5, 5);
		gbc_horse_id.gridx = 1;
		gbc_horse_id.gridy = 3;
		contentPane.add(horse_id, gbc_horse_id);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 3;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel horse_name = new JLabel("Horse Name");
		GridBagConstraints gbc_horse_name = new GridBagConstraints();
		gbc_horse_name.insets = new Insets(0, 0, 5, 5);
		gbc_horse_name.gridx = 1;
		gbc_horse_name.gridy = 4;
		contentPane.add(horse_name, gbc_horse_name);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.gridx = 5;
		gbc_textField_3.gridy = 4;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		add = new JButton("add");
		GridBagConstraints gbc_add = new GridBagConstraints();
		gbc_add.anchor = GridBagConstraints.NORTHWEST;
		gbc_add.insets = new Insets(0, 0, 5, 5);
		gbc_add.gridx = 5;
		gbc_add.gridy = 6;
		contentPane.add(add, gbc_add);
		add.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);
	        		String trainer_id = textField.getText();
	        		String worker_id = textField_1.getText();
	        		String horse_id = textField_2.getText();
	        		String horse_name = textField_3.getText();

	           	 if(opt == 0) {
	        		bh.addTrainerToHorse(trainer_id, worker_id, horse_id, horse_name);
	    			JOptionPane.showMessageDialog(null, "Added Successfully!");
	        		 setVisible(false);
        	}
        	}
		});
		cancel = new JButton("Cancel");
		cancel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_cancel = new GridBagConstraints();
		gbc_cancel.insets = new Insets(0, 0, 5, 5);
		gbc_cancel.anchor = GridBagConstraints.WEST;
		gbc_cancel.gridx = 6;
		gbc_cancel.gridy = 6;
		contentPane.add(cancel, gbc_cancel);
		cancel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
        	}

		});
	}
}
