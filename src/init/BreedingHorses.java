package init;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import content.BookMaker;
import content.Competition;
import content.Employee;
import content.Equestrian;
import content.Gambler;
import content.GamblingBetsForCompetition;
import content.Horse;
import content.HorseInCompetition;
import content.Person;
import content.Trainer;
import content.ValidateInput;
import content.Veterinarian;
import gui.ActionAndReports;
import gui.PersonFrame;
import utils.*;

/**
 * This class is the system of the BreedingHorses - contains all methods (and
 * queries) of the BreedingHorses application
 * 
 * @author Administrator
 * 
 */
public class BreedingHorses {

	/**
	 * Map of horses
	 */
	private Map<String, Horse> horses;
	/**
	 * Map of persons
	 */
	private Map<String, Person> persons;
	private DefaultListModel best_horse;	
	private static final String URL = "jdbc:mysql://localhost:3306/breedinghorse";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private Object data[][];
	private Connection connection; //manages connection
	private PreparedStatement selectAllHorses;
	private PreparedStatement insertNewHorse;
	private PreparedStatement insertNewTrainer;
	private PreparedStatement removeTrainer;
	private PreparedStatement removeHorse;
	private PreparedStatement add_trainer_to_horse;
	private PreparedStatement add_horse_to_trainer;
	private DefaultTableModel model;



	/**
	 * 
	 * Breading Horses Constructor
	 * initializing the maps objects.
	 */
	public BreedingHorses() {
		horses = new HashMap<String, Horse>();
		persons = new HashMap<String, Person>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			selectAllHorses = connection.prepareStatement("SELECT * FROM horse ORDER BY nickName");
			
			insertNewHorse = connection.prepareStatement(
					"insert into horse" + 
					"(identifierSerial,nickName,birthDate,weight,gender,color,price,genre,revenue,totalParticipatesTimes,trainer_id)" +
					"VALUES (?,?,?,?,?,?,?,?,?,?,null)");
			
			insertNewTrainer = connection.prepareStatement(
					"insert into trainer" + 
					"(id,fullName,bithDate,workerId,salary,rank,horse_id)" +
					"VALUES (?,?,?,?,?,?,null)");
			
			removeHorse = connection.prepareStatement(
					"DELETE FROM `horse` WHERE identifierSerial = ? AND nickName = ?"
					);
			
			removeTrainer = connection.prepareStatement(
					"DELETE FROM `trainer` WHERE id = ?"
					);
			add_trainer_to_horse = connection.prepareStatement(
					"UPDATE  `horse` SET `trainer_id` = ? WHERE `identifierSerial` = ?"
					);
			
			add_horse_to_trainer = connection.prepareStatement(
					"UPDATE  `trainer` SET `horse_id` = ? WHERE `id` = ?"
					);
			
		}
			
		catch (SQLException sqlExeption) {
			sqlExeption.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a given person to the Persons Map.
	 * it also checks if the object is already exist before adding
	 * 
	 * @param person
	 * @return true if succeed otherwise returns false
	 */
	public boolean addPerson(Person person) {
		if(person instanceof Trainer) {
			Trainer tr = (Trainer) person;
			int result = 0; 
			String id = person.getId();
			String fullName = person.getFullName();
			String birthDate1 = tr.getBithDate().toString();
			String workerId	 = tr.getSerialId();
			String salary = Double.toString(tr.getSalary());
			String gender1 =String.valueOf(tr.getRank());
			try {
				insertNewTrainer.setString(1, id);
				insertNewTrainer.setString(2, fullName);
				insertNewTrainer.setString(3,birthDate1);
				insertNewTrainer.setString(4, workerId);
				insertNewTrainer.setString(5, gender1);
				insertNewTrainer.setString(6, salary);
				insertNewHorse.setString(7, gender1);
				result = insertNewTrainer.executeUpdate();
			}
			catch (SQLException sqlExeption) {
				sqlExeption.printStackTrace();
				System.exit(1);
			} 
		}
		if (!persons.containsKey(person.getPrimaryKey())) {
			this.persons.put(person.getPrimaryKey(), person);
			return true;
		}

		return false;
	}	
	
	/**
	 * Removes a given person from persons map 
	 * It also checks if the object is already exist before removing
	 * @param person
	 * @return true if succeed otherwise returns false
	 */
	public boolean removePerson(Person person) {
		if(person instanceof Trainer) {
			try {
				Trainer tr = (Trainer) person;
				int result = 0; 
				String id = tr.getId();
			removeTrainer.setString(1,id);
			result = removeTrainer.executeUpdate();

			}
			catch (SQLException sqlExeption) {
				sqlExeption.printStackTrace();
				System.exit(1);
			} 
		}
		else {
		if (this.persons.containsKey(person.getPrimaryKey())) {
			this.persons.remove(person.getPrimaryKey());
			return true;
		}
		}
		return false;
	}


	/**
	 * 
	 * Adds new horse to horses map return true if the adding process success
	 * return false if the horse already exist 
	 * 
	 * @param hid
	 * @param name
	 * @param birthDate
	 * @param weight
	 * @param gender
	 * @param color
	 * @param price
	 * @param genre
	 * @param revenue
	 * @param totalParticipatesTimes
	 * @return true if succeed otherwise returns false
	 */
	public boolean addHorse(String hid, String name, Date birthDate,
			double weight, Character gender, Color color, double price,
			Genre genre, double revenue, int totalParticipatesTimes) {
		
		Horse newHorse = new Horse(hid, name);
		
		if (!horses.containsKey(newHorse.getPrimaryKey())) {
			horses.put(newHorse.getPrimaryKey(), new Horse(hid, name,
					birthDate, weight, gender, color, price, genre, revenue,
					totalParticipatesTimes));

		}
		
		int result = 0; 
		String color1 = color.getClass().toString();
		String genre1 = genre.toString();
		String birthDate1 = birthDate.toString();
		String gender1 =String.valueOf(gender);  
		try {
			insertNewHorse.setString(1, hid);
			insertNewHorse.setString(2, name);
			insertNewHorse.setString(3,birthDate1);
			insertNewHorse.setDouble(4, weight);
			insertNewHorse.setString(5, gender1);
			insertNewHorse.setString(6, color1);
			insertNewHorse.setDouble(7, price);
			insertNewHorse.setString(8, genre1);
			insertNewHorse.setDouble(9, revenue);
			insertNewHorse.setDouble(10, totalParticipatesTimes);
			result = insertNewHorse.executeUpdate();
		}
		catch (SQLException sqlExeption) {
			sqlExeption.printStackTrace();
			System.exit(1);
		} 
		return true;
		
	}

	
	/**
	 * 
	 * Removes Horse from horses map. When removing a horse succeed 
	 * this method must also take care of:
	 * 
	 * 1) The related trainer if exists
	 * (NOTE: updates the trainer object that this horse was removed. 
	 *        THIS METHOD DOESN'T REMOVE THE TRAINER OBJECT FROM PERSONS MAP)
	 *         
	 * 2) The related Equestrian if exists
	 * 3) The related Veterinarians if exist
	 *
	 * Otherwise this method makes a roll-back (i.e don't removes the Horse )
	 * if the removing is not completly succeded
	 *         
	 * @param hId
	 * 
	 * @param hName
	 * 
	 * @return true if succeed otherwise returns false
	 **/
	public boolean removeHorse(String hId, String hName) {
		try {
		removeHorse.setString(0,hId);
		removeHorse.setString(1,hName);
		int i = removeHorse.executeUpdate();
		}
		catch (SQLException sqlExeption) {
			sqlExeption.printStackTrace();
			System.exit(1);
		} 
		return true;
		/*
		Horse horse = new Horse(hId, hName);
		
		if (horses.containsKey(horse.getPrimaryKey())) {
			
			horse = horses.get(horse.getPrimaryKey());
			Trainer trainer = horse.getTrainer();
			Equestrian equestrian = horse.getEquestrian();

			if (trainer != null && trainer.removeHorse(horse)
					&& horse.removeTrainer()) {
				
				if (equestrian != null && horse.removeEquestrian()
						&& equestrian.removeHorse()) {
					if (horse.removeAllVeterinarians()) {
						horses.remove(horse.getPrimaryKey());
						return true;
					} else {
						horse.addTrainer(trainer);
						horse.addEquestrian(equestrian);
						return false;
					}
				} else {
					horse.addTrainer(trainer);
					return false;
				}
			} else {
				horse.addTrainer(trainer);
				return false;
			}
		}
		*/

	}

	
	
	
	
	/**
	 * Adds an existed trainer to an existed horse returns true if the adding
	 * process succeed, otherwise returns false if: 
	 * 1) The horse is not exists in the horses map 
	 * 2) The trainer is not exists in the persons map 
	 * 3) The existed trainer is already added to the existed horse 
	 * 4) The existed horse is already has a trainer
	 * 
	 * Otherwise this method makes a roll-back (i.e don't adds the Trainer to the existed horse)
	 * if the adding is not completly succeded
	 * 
	 * @param trId
	 * @param trSerialId
	 * @param hId
	 * @param hName
	 * @return true if succeed otherwise returns false
	 */
	public boolean addTrainerToHorse(String trId, String trSerialId,
			String hId, String hName) {


		try {
			add_trainer_to_horse = connection.prepareStatement(
					"UPDATE  `horse` SET `trainer_id` = ? WHERE `identifierSerial` = ?"
					);
			int result1 = 0; 
			int result2 = 0; 
			add_trainer_to_horse.setString(1,trId);
			add_trainer_to_horse.setString(2,hId);
			result1 = add_trainer_to_horse.executeUpdate();
			add_horse_to_trainer.setString(1,hId);
			add_horse_to_trainer.setString(2,trId);
		    result2 = add_horse_to_trainer.executeUpdate();

			}
			catch (SQLException sqlExeption) {
				sqlExeption.printStackTrace();
				System.exit(1);
			} 
			return true;
		/*
		Trainer trainer = new Trainer(trId, trSerialId);
		Horse horse = new Horse(hId, hName);
		
		if (horses.containsKey(horse.getPrimaryKey())
				&& persons.containsKey(trainer.getPrimaryKey())) {

			trainer = (Trainer) persons.get(trainer.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			if (horse.addTrainer(trainer) && trainer.addHorse(horse)) {
				return true;
			} else {
				horse.removeTrainer();
				return false;
			}
		}
		return false;
		*/
	}
	
	/**
	 * Removes an existed Trainer from an existed horse returns true if the
	 * removing process succeed, otherwise returns false if: 
	 * 1) The horse is not exists in the horses map 
	 * 2) The Trainer is not exists in the persons map 
	 * 3) The existed horse does not have this Trainer
	 * 
	 * Otherwise this method makes a roll-back (i.e don't removes the Trainer from the existed horse)
	 * if the removing is not completly succeded
	 * 
	 * @param gId
	 * @param gSerialId
	 * @param hId
	 * @param hName
	 * @return true if succeed otherwise returns false
	 **/

	public boolean removeTrainerFromHorse(String gId, String gSerialId,
			String hId, String hName) {
		
		Trainer trainer = new Trainer(gId, gSerialId);
		Horse horse = new Horse(hId, hName);

		if (persons.containsKey(trainer.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())) {

			trainer = (Trainer) persons.get(trainer.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			if (horse.removeTrainer() && trainer.removeHorse(horse)) {
				return true;
			} else {
				horse.addTrainer(trainer);
				return false;
			}
		}
		return false;
	}


	/**
	 * Adds an existed Equestrian to an existed horse returns true if the adding
	 * process succeed, otherwise returns false if: 
	 * 1) The horse is not exists in the horses 
	 * 2) The Equestrian is not exists in the persons map
	 * 3) The existed Equestrian is already added to the existed horse 
	 * 4) The existed horse is already has a Equestrian
	 * 
	 * Otherwise this method makes a roll-back (i.e don't removes the Equestrian to the existed horse)
	 * if the adding is not completly succeded
	 * 
	 * @param eId
	 * @param hId
	 * @param hName
	 * @return true if succeed otherwise returns false
	 */
	public boolean addEquestrianToHorse(String eId, String hId, String hName) {
		
		Equestrian equestrian = new Equestrian(eId);
		Horse horse = new Horse(hId, hName);
		if(!ValidateInput.validate_addEquestrianToHorse(eId)) {
			System.out.println("Invalid input eId");
		}
		if (persons.containsKey(equestrian.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())) {
			equestrian = (Equestrian) persons.get(equestrian.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			if (horse.addEquestrian(equestrian) && equestrian.addHorse(horse)) {
				return true;
			} else {
				horse.removeEquestrian();
				return false;
			}
		}
		return false;

	}
	
	/**
	 * 
	 * Removes an exised Equestrian From an exised Horse and return false if: 
	 * 1) The horse is not exists in the horses 
	 * 2) The Equestrian is not exists in the persons map
	 * 3) The existed Equestrian doesn't related to the existed horse 
	 * 4) The existed horse doesn't has the given Equestrian
	 * 
	 * Otherwise this method makes a roll-back (i.e don't add the Equestrian from an existed horse)
	 * if the removing is not completly succeded
	 * 
	 * @param pId
	 * @param hId
	 * @param hName
	 * @return true if succeed otherwise returns false
	 **/
	public boolean removeEquestrianFromHorse(String pId,
			String hId, String hName) {
		
		Equestrian equestrian = new Equestrian(pId);
		Horse horse = new Horse(hId, hName);
		
		if (persons.containsKey(equestrian.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())) {
			
			equestrian = (Equestrian) persons.get(equestrian.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			
			if (horse.removeEquestrian() && equestrian.removeHorse()) {
				return true;
			} else {
				horse.addEquestrian(equestrian);
				return false;
			}
		}
		return false;
	}


	/**
	 * Adds an existed Veterinarian to an existed horse returns true if the
	 * adding process succeed, otherwise returns false if:
	 *  1) The horse is not exists in the horses map 
	 *  2) The Veterinarian is not exists in the persons map 
	 *  3) The existed Veterinarian is already added to the existed horse 
	 *  4) The existed horse is already has a three Veterinarians
	 *  
	 * otherwise this method makes a roll-back (i.e don't add the Veterinarian to an existed horse)
	 * if the adding is not completly succeded
	 * 
	 * @param vId
	 * @param vSerialId
	 * @param hId
	 * @param hName
	 * @return true if succeed otherwise returns false
	 */
	public boolean addVeterinarianToHorse(String vId, String vSerialId,
			String hId, String hName) {
		
		Veterinarian veterinar = new Veterinarian(vId, vSerialId);
		Horse horse = new Horse(hId, hName);
		
		if (persons.containsKey(veterinar.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())) {
			
			veterinar = (Veterinarian) persons.get(veterinar.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			
			if (horse.addVeterinarian(veterinar) && veterinar.addHorse(horse)) {
				return true;
			} else {
				horse.removeVeterinarian(veterinar);
				return false;
			}
		}
		return false;
	}
	

	
	/**
	 * Removes an existed Veterinarian from an existed horse returns true if the
	 * removing process succeed, otherwise returns false if: 
	 * 1) The horse is not exists in the horses map
	 * 2) The Veterinarian is not exists in the persons map
	 * 3) The existed horse does not have this Veterinarian
	 * 
	 * otherwise this method makes a roll-back (i.e don't remove the Veterinarian from an existed horse)
	 * if the removing is not completly succeded
	 * 
	 * @param vId
	 * @param vSerialId
	 * @param hId
	 * @param hName
	 * @return true if succeed otherwise returns false
	 * 
	 **/
	public boolean removeVeterinarianFromHorse(String vId, String vSerialId,
			String hId, String hName) {
		
		Veterinarian veterinar = new Veterinarian(vId, vSerialId);
		Horse horse = new Horse(hId, hName);

		if (persons.containsKey(veterinar.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())) {
			veterinar = (Veterinarian) persons.get(veterinar.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());

			if (horse.removeVeterinarian(veterinar)
					&& veterinar.removeHorse(horse)) {
				return true;
			} else {
				horse.addVeterinarian(veterinar);
				return false;
			}
		}
		return false;
	}
	
	
	/**
	 * Adds a Horse to a Competition. return false if: 
	 * 1) There given horse is not existed in the horses map 
	 * 2) The Horse does not have an Equestrian
	 * 3) The Horse already participate in the given Competition 
	 * 
	 * otherwise this method makes a roll-back (i.e don't add the horse to the Competition)
	 * if the adding is not completly succeded
	 * 
	 * @param hIdentificalNo
	 * @param nickName
	 * @param serialNum
	 * @param placeOfCompetitions
	 * @param type
	 * @param startDate
	 * @param level
	 * @return true if succeed otherwise returns false
	 */
	public boolean addHorseToCompetition(String hIdentificalNo,
			String nickName, int serialNum, String placeOfCompetitions,
			Type type, Date startDate, short level) {
		
		Horse horse = new Horse(hIdentificalNo, nickName);
		
		if (horses.containsKey(horse.getPrimaryKey())) {
			
			horse = horses.get(horse.getPrimaryKey());
			
			if (horse.getEquestrian() != null) {
				
				Competition competition = new Competition(serialNum,
						placeOfCompetitions, type, startDate, level);
				if (!horse.getCompetitions().contains(competition)) {
					
					

					HorseInCompetition hic = new HorseInCompetition(horse,competition);
					
					if (competition.addHorseToCompetition(hic) && horse.addCompetitionToHorse(hic)) {
						return true;
					} else {
						competition.removeHorseFromCompetition(hic);
						return false;
					}
				}
			}

		}

		return false;
		
		
		
		
	}

	/**
	 * Removes Horse from Competition the method return false if:
	 * 
	 * 1) The Horse is not exist in the horse map 
	 * 2) The Horse is not participated in the given Competition
	 * 
	 * Otherwise this method makes a roll-back (i.e don't remove the horse from the Competition)
	 * if the removing is not completly succeded
	 * 
	 * @param hIdentificalNo
	 * @param nickName
	 * @param serialNum
	 * @param type
	 * @return true if succeed otherwise returns false
	 */
	public boolean removeHorseFromCompetition(String hIdentificalNo,
			String nickName, int serialNum, Type type) {
		
		Horse horse = new Horse(hIdentificalNo, nickName);
		
		if (this.horses.containsKey(horse.getPrimaryKey())) {
			
			horse = horses.get(horse.getPrimaryKey());
			
			Competition competition = new Competition(serialNum, type);
			HorseInCompetition hic = new HorseInCompetition(horse, competition);
			
			if (horse.getCompetitions().contains(hic))

			{
				for(HorseInCompetition c:horse.getCompetitions()){
					if(c.equals(competition)){
							competition=c.getCompetition();
						if (competition.removeHorseFromCompetition(hic)
								&& horse.removeCompetitionFromHorse(hic))
							return true;
						else {
							competition.addHorseToCompetition(hic);
							return false;
						}
					}
				}
				Iterator<HorseInCompetition> itr = horse.getCompetitions().iterator();
				while (itr.hasNext()) {
					hic = itr.next();
					if (hic.getCompetition().equals(competition)) {
						competition = hic.getCompetition();
						if (competition.removeHorseFromCompetition(hic)
								&& horse.removeCompetitionFromHorse(hic))
							return true;
						else {
							competition.addHorseToCompetition(hic);
							return false;
						}
					}
				}

			}
		}
		return false;

	}

	/**
	 * Adds a Gambler bet on a Horse in a Competition throuth the Bookmaker
	 * The method return false if: 
	 * 
	 * 1) The given bookmaker is not exists in persons map
	 * 2) The given gambler is not exists in persons map
	 * 3) The given Competition is not exists
	 * 4) The given horse is not exists in horses map
	 * 
	 * Otherwise this method makes a roll-back (i.e don't adds the bet)
	 * if the adding is not completly succeded
	 * 
	 * @param bId
	 * @param bLicenseNo
	 * @param gId
	 * @param hIdentificationNo
	 * @param hNickName
	 * @param cIdNumber
	 * @param cType
	 * @param betAmount
	 * @return true if succeed otherwise returns false
	 */
	public boolean addGamblingBetsForCompetition(String bId, String bLicenseNo,
			String gId, String hIdentificationNo, String hNickName,
			int cIdNumber, Type cType, double betAmount) {
		
		BookMaker bookMaker = new BookMaker(bId, bLicenseNo);
		Gambler gambler = new Gambler(gId);
		Horse horse = new Horse(hIdentificationNo, hNickName);
		Competition competition = new Competition(cIdNumber, cType);
		HorseInCompetition hic = new HorseInCompetition(horse, competition);
		
		if(!ValidateInput.validate_bLicenseNo(bLicenseNo)) {
			System.out.println("Invalid input");
		}
		
		if (persons.containsKey(bookMaker.getPrimaryKey())
				&& persons.containsKey(gambler.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())
				&& horses.get(horse.getPrimaryKey()).getCompetitions()
						.contains(hic)) {
		
			bookMaker = (BookMaker) persons.get(bookMaker.getPrimaryKey());
			gambler = (Gambler) persons.get(gambler.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			
			if (!horse.getCompetitions().isEmpty()
					&& horse.getCompetitions().contains(hic)) {
				
				Iterator<HorseInCompetition> itr = horse.getCompetitions().iterator();
				
				while (itr.hasNext()) {
					hic = itr.next();
					if (hic.getCompetition().equals(competition)) {
						competition = hic.getCompetition();
						break;
					}
				}

				GamblingBetsForCompetition gbfc = new GamblingBetsForCompetition(
						bookMaker, gambler, betAmount, competition, horse);
				
				if (bookMaker.addBetToBookMaker(gbfc)) {
					if (competition.addBetToCompetition(gbfc)) {
						if (gambler.addBetsInCompetition(gbfc)) {
							return true;
						} else {
							bookMaker.removeBetFromBookMaker(gbfc);
							competition.removeBetFromCompetition(gbfc);
							return false;
						}

					} else {
						bookMaker.removeBetFromBookMaker(gbfc);
						return false;
					}

				}
			}
		}

		return false;
	
	}
	
	
	
	

	/**
	 * Removes Gambler's bet for a Horse in a Competition 
	 * It returns false if: 
	 * 
	 * 1) The given bookmaker is not exists in persons map
	 * 2) The given gambler is not exists in persons map
	 * 3) The given Competition is not exists
	 * 4) The given horse is not exists in horses map
	 * 
	 * Otherwise this method makes a roll-back (i.e don't Removes the bet)
	 * if the removing is not completly succeded
	 * 
	 * @param bId
	 * @param bLicenseNo
	 * @param gId
	 * @param hIdentificationNo
	 * @param hNickName
	 * @param cIdNumber
	 * @param cType
	 * @return true if succeed otherwise returns false
	 */
	public boolean removeGamblingBetsForCompetition(String bId,
			String bLicenseNo, String gId, String hIdentificationNo,
			String hNickName, int cIdNumber, Type cType) {

		BookMaker bookMaker = new BookMaker(bId, bLicenseNo);
		Gambler gambler = new Gambler(gId);
		Horse horse = new Horse(hIdentificationNo, hNickName);
		Competition competition = new Competition(cIdNumber, cType);
		HorseInCompetition hic = new HorseInCompetition(horse, competition);
		
		if (persons.containsKey(bookMaker.getPrimaryKey())
				&& persons.containsKey(gambler.getPrimaryKey())
				&& horses.containsKey(horse.getPrimaryKey())
				&& horses.get(horse.getPrimaryKey()).getCompetitions()
						.contains(hic)) {
			
			bookMaker = (BookMaker) persons.get(bookMaker.getPrimaryKey());
			gambler = (Gambler) persons.get(gambler.getPrimaryKey());
			horse = horses.get(horse.getPrimaryKey());
			
			if (!horse.getCompetitions().isEmpty()
					&& horse.getCompetitions().contains(hic)) {
				
				Iterator<HorseInCompetition> itr = horse.getCompetitions().iterator();
				
				while (itr.hasNext()) {
					hic = itr.next();
					if (hic.getCompetition().equals(competition)) {
						competition = hic.getCompetition();
						break;
					}
				}

				GamblingBetsForCompetition gbfc = 
					new GamblingBetsForCompetition(bookMaker, gambler, competition, horse);
					
				if (bookMaker.removeBetFromBookMaker(gbfc)) {
						
					  if (competition.removeBetFromCompetition(gbfc)) {
							gambler.removeBetsInCompetition();
							return true;

						} else {
							bookMaker.addBetToBookMaker(gbfc);
							return false;
						}

					}

				}
			}
		
		return false;
	}
	
	
	
	/**
	 * Finds the best horse
	 * The method got as parameters the weights of wining Times, total points,and the popularity rate
	 * Use an apropriate comparator in order to compare the
	 * horses
	 * for evrey horse the method calculate the total weighted score as: 
	 * 
	 *     TotalPoints * weightOfTotalPoints + 
	 *     WiningTimes * weightOfWiningTimes + 
	 *     popularityRate() * weightOfPopularityRate
	 * 
	 * according to this score the horses are compared and the method returns the horse with the maximal score
	 * 
	 * NOTE: The complexity of this method must be O(N) and required to use comparator
	 *
	 * @param weightOfWiningTimes
	 * @param weightOfTotalPoints
	 * @param weightOfPopularityRate
	 * @return the best horse
	 */
	public Horse findTheBestHorse(double weightOfWiningTimes,
			double weightOfTotalPoints, double weightOfPopularityRate) {
		/*
		Horse bestHorse = null;
		HorseComparator hc = new HorseComparator(weightOfWiningTimes,
				weightOfTotalPoints, weightOfPopularityRate);
		
		
	    for (Horse h : horses.values()) {
			if (bestHorse == null) {
				bestHorse = h;
			} else {
				if (hc.compare(bestHorse, h) > 0) {
					bestHorse = h;
				}
			}
		}
		 
		return bestHorse;
		*/
		
		Optional<Horse> horse = horses.values().stream()
				.sorted((h1 , h2 )->{
					Double o2Calc = h2.calculateTotalPoints()
							* weightOfTotalPoints + h2.calculateWiningTimes()
							* weightOfWiningTimes + h2.getPopularityRate()
							* weightOfPopularityRate;
					return o2Calc.compareTo(h1.calculateTotalPoints()
							* weightOfTotalPoints + h1.calculateWiningTimes()
							* weightOfWiningTimes + h1.getPopularityRate()
							* weightOfPopularityRate);
				})
				.findFirst();
		
		return horse.get();		
		}

	

	
	/**
	 * Finds the Most unlucky Gambler
	 * i.e The one that have the smallest gain
	 * 
	 * @return The most unlucky gambler object
	 */

	public Gambler findTheMostUnluckyGambler() {
		/*
		List<Gambler> gamblers = new ArrayList<Gambler>();
		Gambler unluckyGambler = null;
		
		for (Person p : persons.values()) {
			if (p instanceof Gambler) {
				gamblers.add((Gambler) p);
			}
		}
		
		for (Gambler g : gamblers) {
			if (unluckyGambler == null) {
				unluckyGambler = g;
			} else {
				if (unluckyGambler.getGain() >= g.getGain()) {
					unluckyGambler = g;
				}
			}
		}
		
		return unluckyGambler;
		*/		

		Optional<Gambler> gambler = persons.values().stream()
				.filter(p -> p instanceof Gambler)
				.map(c -> (Gambler) c)
				.sorted(Comparator.comparing(Gambler::getGain))
				.findFirst();
		
		return gambler.get();
						}


	
	/**
	 * Prints (to the output.txt file) the top ten trainers report according to
	 * their success rate sorted from the highest trainers to the lowest one by
	 * NOTE: You must use a comparator object on 
	 * the successRate field and use the Collections.sort method
	 */
	public Object[][] printTop10TrainersRankedReport() {
		
			List<Trainer> trainers = persons.values().stream()
				.filter(p -> p instanceof Trainer)
				.map(c -> (Trainer) c)
				.sorted(Comparator.comparing(Trainer::getSuccessRate).reversed())
				.limit(10)
				  .collect(Collectors.toList());

			data = new  Object[trainers.size()][8]; 
			int index = 0;
			for (Trainer tr : trainers) {
				if (tr != null) {
					data[index][0] = tr.getId();
					data[index][1] = tr.getFullName();
					data[index][2] = tr.getBithDate();
					data[index][3] = tr.getRank();
					data[index][4] = tr.getSalary();
					data[index][5] = tr.getSuccessRate();
					data[index][6] = tr.getTotalWinnerHorses();
					data[index][7] = tr.getHorsesCount();
					index++;
				}	
					}

			return data;


			
	 
			

/*	
 	List<Trainer> tempTrainerList = new ArrayList<Trainer>();
 

		int trainerCount = 0;

		for (Person p : persons.values())
			if (p instanceof Trainer) {
				trainerCount++;
				tempTrainerList.add((Trainer) p);
			}
		
        if (trainerCount < 10) {
			SaveLineToFile
					.writeToFileInSeparateLine("\n==== Top10 Trainers Ranked Report ====");
			SaveLineToFile
					.writeToFileInSeparateLine("The total number of trainers in the system is not enoght for this report");
			SaveLineToFile
					.writeToFileInSeparateLine("\n==== End of Top10 Trainers Ranked Report ====");
		} else {

			int c = 0;

			Collections.sort(tempTrainerList,new TrainerComparatorBySuccessRate());
			
			// print the first 10 trainers to the output.txt file
			SaveLineToFile
					.writeToFileInSeparateLine("\n==== Top10 Trainers Ranked Report ====");
			
			for(Person p:tempTrainerList){
				
				if(c!=10){
					SaveLineToFile.writeToFileInSeparateLine("\n"+p);
					c++;
				}
			}

			SaveLineToFile.writeToFileInSeparateLine("\n==== End of Top10 Trainers Ranked Report ====\n");
		}
	*/
	}
	
	
	/**
	 * updates the salaries of veterinarians by a given bonusPersent if that
	 * veterinarian works more than a given totalTreatmentHours and treats more
	 * than given totalTreatmentHorses.
	 * 
	 * @param totalTreatmentHours
	 * @param totalTreatmentHorses
	 * @param bonusPersent
	 *        
	 */
	public void giveBonusToVeterinarians(int totalTreatmentHours,
			int totalTreatmentHorses, double bonusPersent) {

		// TODO complete the implementation of this method.

		for (Person p : persons.values()) {

			if (p instanceof Veterinarian) {
				Veterinarian tempVeterinar = (Veterinarian) p;

				if (tempVeterinar.getTotalTreatmentHours() > totalTreatmentHours
						&& tempVeterinar.getHorsesCount() > totalTreatmentHorses) {
					tempVeterinar.setSalary(tempVeterinar.getSalary()
							* (1.0 + bonusPersent));
				}
			}
		}
	}
	
	
	
	
	/**
	 * The method prints all the persons in the persons-map into the text-file.
	 */
	public  Object[][] printAllPersons() {
		
		data = new  Object[persons.size()][5]; 
		int index = 0;
		for (Person p : persons.values()) {
			if (p != null) {
				data[index][0] = p.getId();
				data[index][1] = p.getFullName();
				data[index][2] = p.getBithDate();
				data[index][3] = p.getClass().getName();
				index++;
			}	
				}
		return data;
	}

	//String id, String fullName, Date bithDate, String workerId,
	//double salary, Rank rank
	
	/**
	 * The method prints all the Horses in the horses-array into the text-file.
	 */
	public Object[][] printAllHorses() {
		// TODO complete the implementation of this method
		/*
		for (Horse h : horses.values()) {
			if (h != null)
				System.out.println(h);
				//SaveLineToFile.writeToFileInSeparateLine("\n" + h.toString());
		}
		*/
		ResultSet resultSet = null;
		try {
			
			resultSet = selectAllHorses.executeQuery();
			data = new  Object[20][10]; 
			int i = 0;
			while(resultSet.next()) {
				String identifierSerial = resultSet.getString("identifierSerial");
				String nickName = resultSet.getString("nickName");
				String birthDate = resultSet.getString("birthDate");
				double weight = resultSet.getDouble("weight");
				String gender = resultSet.getString("gender");
				String color = resultSet.getString("color");
				double price = resultSet.getDouble("price");
				String genre = resultSet.getString("genre");
				double revenue = resultSet.getDouble("revenue");
				int totalParticipatesTimes = resultSet.getInt("totalParticipatesTimes");
				data[i][0] = identifierSerial + "";
				data[i][1] = nickName + "";
				data[i][2] = birthDate + "";
				data[i][3] = weight + "";
				data[i][4] = gender + "";
				data[i][5] = color + "";
				data[i][6] = price + "";
				data[i][7] = genre + "";
				data[i][8] = revenue + "";
				data[i][9] = totalParticipatesTimes + "";
				i++;
			}

		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return data;

	}



	
	/**
	 * The method prints all the Gamblers in the persons-map into the text-file.
	 * must use printCollection method to print the objects to the text-file
	 */
	public void printAllGamblers() {
	
		ArrayList<Gambler> arr = new ArrayList<Gambler>();
		for (Person p : persons.values()) {
			if (p instanceof Gambler) {
				Gambler g = (Gambler) p;
				arr.add(g);
			}
		}
		printCollection(arr);
	}

	/**
	 * The method prints a sorted BookMaker list (according to the primary key fields) into the
	 * text-file.
	 * NOTE: you must use a TreeSet for sorting
	 * must use printCollection method to print the objects to the text-file
	 */
	public Map<String, Horse> getHorses() {
		return horses;
	}
	
	public void printAllBookMakers() {
		
		SortedSet<BookMaker> sorted = null;
		List<BookMaker> bookMakers = new ArrayList<BookMaker>();
		for (Person p : persons.values()) {
			if (p instanceof BookMaker) {
				bookMakers.add((BookMaker) p);
			}
		}
		sorted = new TreeSet<BookMaker>(bookMakers);
		
		printCollection(sorted);
	}
	
	/**
	  This generic method prints the given collection to the text-file
	*/
	private <T> void printCollection(Collection<? extends T> collection){
		for (T t : collection) {
			SaveLineToFile.writeToFileInSeparateLine(t.toString()+"\n");
		}
	}
	

	




	
	
}
