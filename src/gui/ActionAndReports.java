package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.ha.BestResponseTimeBalanceStrategy;

import init.BreedingHorses;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Handler;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.Color;

public class ActionAndReports extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private BreedingHorses bh = new BreedingHorses();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActionAndReports window = new ActionAndReports(bh);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ActionAndReports(BreedingHorses bh) {
		frame = new JFrame("Action and Reports");
		frame.setBounds(100, 100, 712, 509);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		    JPanel p1=new JPanel();
		    JPanel p2=new JPanel();  
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(50,50,280,280);  
	    tp.add("Action Reports",p1);
	    p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
	    
	    JButton best_horse = new JButton("Best Horse");
	    p1.add(best_horse);
	    
	    JButton unlucky_gambler = new JButton("Unlucky Gambler");
	    p1.add(unlucky_gambler);
	    
	    JButton vet_bouns = new JButton("Veterinarian Bonus");
	    p1.add(vet_bouns);
	    tp.add("Info Reports",p2);
	    p2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
	    
	    JButton top_10_trainers = new JButton("Print Top 10 Trainers");
	    top_10_trainers.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 f = new JFrame();
	             f.setTitle("Print Top 10 Trainers");
	            Object col[] = {"1","2","3","4","5","6","7","8","9","10"};
	 			JTable j = new JTable(bh.printTop10TrainersRankedReport(),col);
	 	        j.setBounds(30, 40, 200, 300);
	 	        f.getContentPane().add(j);
	 	        f.setSize(500, 200);
	 	        f.setVisible(true);
	    	}
	    });
	    p2.add(top_10_trainers);
	    
	    JButton all_perosn = new JButton("Print All Person");
	    p2.add(all_perosn);
	    all_perosn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	             f = new JFrame();
	             f.setTitle("JTable Example");
	            Object col[] = {"id","FullName","BirthDate","Class"};
	 			JTable j = new JTable(bh.printAllPersons(),col);

	 	        j.setBounds(30, 40, 200, 300);
	 	        f.getContentPane().add(j);
	 	        f.setSize(500, 200);
	 	        f.setVisible(true);

	        		 }
	       });
	    JButton all_horses = new JButton("Print All Horses");
	    p2.add(all_horses);
	    all_horses.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	             f = new JFrame();
	             f.setTitle("Print All Horses");
	            Object col[] = {"identifierSerial","nickName","birthDate","weight","gender","color","price","genre","revenue","totalParticipatesTimes"};
	 			JTable j = new JTable(bh.printAllHorses(),col);
	 	        j.setBounds(30, 40, 200, 300);
	 	        f.getContentPane().add(j);
	 	        f.setSize(500, 200);
	 	        f.setVisible(true);

	        		 }
	       });
	    JButton all_gamblers = new JButton("Print All Gamblers");
	    p2.add(all_gamblers);
	    
	    JList list = new JList();
	    p2.add(list);
	    
	    JButton all_bk = new JButton("Print ALL Book Makers");
	    p2.add(all_bk);
	    frame.getContentPane().add(tp);  
	    frame.setSize(400,400);  
	    frame.getContentPane().setLayout(null);  
	    
	    JLabel reports = new JLabel("Reports");
	    reports.setBounds(150, 10, 73, 13);
	    frame.getContentPane().add(reports);
	    
	    JSeparator separator = new JSeparator();
	    separator.setBounds(10, 33, 366, 8);
	    frame.getContentPane().add(separator);
	    
	    JSeparator separator_1 = new JSeparator();
	    separator_1.setForeground(Color.GRAY);
	    separator_1.setBounds(10, 340, 366, 200);
	    frame.getContentPane().add(separator_1);
	    
	    JButton exit = new JButton("Exit");
	    exit.setBounds(291, 342, 85, 21);
	    frame.getContentPane().add(exit);
	    frame.setVisible(true);  
	    
	    exit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	        	 AddTrainer at = new AddTrainer(bh,null);
	        	 frame.setVisible(false);
	         }
	      });
	 // create new ButtonHandler for button event handling
	     ButtonHandler handler = new ButtonHandler();
	     best_horse.addActionListener( handler );
	     unlucky_gambler.addActionListener( handler );
	     vet_bouns.addActionListener(handler);
	}
	
	 // handle button event
		 JFrame f= new JFrame();  
		 public class ButtonHandler implements ActionListener
		 {
			 BreedingHorses bh = new BreedingHorses();
			 public void actionPerformed( ActionEvent event )
			 {   //handle button event "Best Horse"
				 if(event.getActionCommand().equals("Best Horse")) { 
					 	JFrame f= new JFrame("Best Horse");  
				     	JTextField weightOfWiningTimes = new JTextField();
				     	weightOfWiningTimes.setBounds(150, 40, 123, 20);
				        f.getContentPane().add(weightOfWiningTimes);
				        
				        JLabel weightOfWiningTimes_label = new JLabel("weightOfWiningTimes");
				        weightOfWiningTimes_label.setBounds(25, 42, 132, 13);
				        f.getContentPane().add(weightOfWiningTimes_label);
				        
				    	JTextField weightOfTotalPoints = new JTextField();
				    	weightOfTotalPoints.setBounds(150, 65, 123, 20);
				        f.getContentPane().add(weightOfTotalPoints);
				        
				        JLabel weightOfTotalPoints_label = new JLabel("weightOfTotalPoints");
				        weightOfTotalPoints_label.setBounds(25, 65, 132, 13);
				        f.getContentPane().add(weightOfTotalPoints_label);
				        
				        JTextField weightOfPopularityRate = new JTextField();
				        weightOfPopularityRate.setBounds(160, 87, 150, 20);
				        f.getContentPane().add(weightOfPopularityRate);
				        
				        JLabel weightOfPopularityRate_label = new JLabel("weightOfPopularityRate");
				        weightOfPopularityRate_label.setBounds(10, 90, 140, 13);
				        f.getContentPane().add(weightOfPopularityRate_label);
				        
				        JButton find = new JButton("Find");
				        find.setBounds(35, 150, 160, 20);
					    f.getContentPane().add(find);
				        f.setSize(400,400);  
				        f.getContentPane().setLayout(null);  
				        f.setVisible(true); 
				       
	     }	//handle button event "Veterinarian Bonus"
				 if(event.getActionCommand().equals("Veterinarian Bonus")) { 
					 	JFrame f= new JFrame("Give Bonus To Vetrinarian");  
					 	
				     	JTextField totalTreatmentHours = new JTextField();
				     	totalTreatmentHours.setBounds(150, 40, 123, 20);
				        f.getContentPane().add(totalTreatmentHours);
				        
				        JLabel totalTreatmentHours_label = new JLabel("totalTreatmentHours");
				        totalTreatmentHours_label.setBounds(25, 42, 132, 13);
				        f.getContentPane().add(totalTreatmentHours_label);
				        
				    	JTextField totalTreatmentHorses = new JTextField();
				    	totalTreatmentHorses.setBounds(150, 65, 123, 20);
				        f.getContentPane().add(totalTreatmentHorses);
				        
				        JLabel totalTreatmentHorses_label = new JLabel("totalTreatmentHorses");
				        totalTreatmentHorses_label.setBounds(25, 65, 132, 13);
				        f.getContentPane().add(totalTreatmentHorses_label);
				        
				        JTextField bonusPersent = new JTextField();
				        bonusPersent.setBounds(150, 87, 128, 20);
				        f.getContentPane().add(bonusPersent);
				        
				        JLabel bonusPersent_label = new JLabel(" bonusPersent");
				        bonusPersent_label.setBounds(30, 90, 140, 13);
				        f.getContentPane().add(bonusPersent_label);
				        //Button To Add Bouns To Vetrinarian
				        JButton find = new JButton("Give Bonus");
				        find.addActionListener(new ActionListener() {
					         public void actionPerformed(ActionEvent event) {
					        	 int TreatmentHours = Integer.parseInt(totalTreatmentHours.getText());
					        	 int TreatmentHorses = Integer.parseInt(totalTreatmentHorses.getText());
					        	 double bouns = Double.parseDouble(bonusPersent.getText());
					          	 int opt = JOptionPane.showConfirmDialog(null, "Are You sure You Want To Give Bouns","add",JOptionPane.YES_NO_OPTION);
					        	 if(opt == 0) {
					        	 bh.giveBonusToVeterinarians(TreatmentHours, TreatmentHorses, bouns);
					     	   	JOptionPane.showMessageDialog(null, "Added Successfully!");
				        		 setVisible(false);
					        	 }
					    	
					         }
					      });
				        find.setBounds(35, 150, 160, 20);
					    f.getContentPane().add(find);
				        f.setSize(400,400);  
				        f.getContentPane().setLayout(null);  
				        f.setVisible(true); 
				       
	     }
	
	 }
	 }
}

