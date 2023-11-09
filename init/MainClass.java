package init;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import content.*;

import utils.Genre;
import utils.SaveLineToFile;
import utils.Rank;
import utils.Type;

/**
 * Main Class for Gardening Project
 */
public class MainClass {

	/**
	 * The main method of this system, gets input from text file. Writes output
	 * to output.txt file
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ParseException {

		String command;

		SaveLineToFile.initializeMyFileWriter(); // writer buffer creation

		Scanner input = null;

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));

		input = new Scanner(new File("input.txt")); // creation of connection to
		// input file

		BreedingHorses sys = null;

		if (input.hasNext()) {

			sys = new BreedingHorses(); // creation of
			// Gardening
			// object
		}

		boolean isUpdated;

		// read the commands. loop which performed until end of input file
		// [false in [input.hasnext()]
		while (input.hasNext() && sys != null) {
			command = input.next();// read the command, must be first at line.
			// command value determine which method will be activated in sys
			// object.
			isUpdated = false;
			if (command.equals("addHorse")) {

				String hid = input.next();
				isUpdated = sys.addHorse(hid, input.next(), new Date(input
						.next()), input.nextDouble(), (Character) input.next()
						.toCharArray()[0], new Color(input.nextInt(), input
						.nextInt(), input.nextInt()), input.nextDouble(), Genre
						.valueOf(input.next()), input.nextDouble(), input
						.nextInt());
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addHorse returns: "
									+ "Successfully added Horse " + hid
									+ " to BreedingHorses\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addHorse returns: "
									+ "Failed to add new Horse " + hid
									+ " to BreedingHorses\n");
				}

			} else if (command.equals("addTrainer")) {

				String personId = input.next();
				String personName = input.next();

				isUpdated = sys.addPerson(new Trainer(personId, personName,
						new Date(input.next()), input.next(), input
								.nextDouble(), Rank.valueOf(input.next())));
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addTrainer returns: "
									+ "Successfully added Trainer " + personId
									+ " fullName " + personName
									+ " to BreedingHorses\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addTrainer returns: "
									+ "Failed to add new Trainer " + personId
									+ " fullName " + personName
									+ " to BreedingHorses\n");
				}

			} else if (command.equals("addEquestrian")) {

				String personId = input.next();
				String personName = input.next();
				isUpdated = sys.addPerson(new Equestrian(personId, personName,
						new Date(input.next()), Rank.valueOf(input.next()),
						Time.valueOf(input.next())));
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addEquestrian returns: "
									+ "Successfully added Equestrian "
									+ personId
									+ " fullName "
									+ personName
									+ " to BreedingHorses\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addEquestrian returns: "
									+ "Failed to add Equestrian "
									+ personId
									+ " fullName "
									+ personName
									+ " to BreedingHorses\n");
				}

			} else if (command.equals("addGambler")) {

				String personId = input.next();
				String personName = input.next();

				isUpdated = sys.addPerson(new Gambler(personId, personName,
						new Date(input.next()), input.nextDouble()));
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addGambler returns: "
									+ "Successfully added Gambler " + personId
									+ " fullName " + personName
									+ " to BreedingHorses\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addGambler returns: "
									+ "Failed to add new Gambler " + personId
									+ " fullName " + personName
									+ " to BreedingHorses\n");
				}

			} else if (command.equals("addBookMaker")) {
				String licenseId = input.next();
				String personId = input.next();
				String personName = input.next();

				isUpdated = sys
						.addPerson(new BookMaker(licenseId, personId,
								personName, new Date(input.next()), input
										.nextDouble()));
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addBookMaker returns: "
									+ "Successfully added BookMaker "
									+ personId + " fullName " + personName
									+ " to BreedingHorses\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addBookMaker returns: "
									+ "Failed to add new BookMaker " + personId
									+ " fullName " + personName
									+ " to BreedingHorses\n");
				}

			} else if (command.equals("addEquestrianToHorse")) {

				String riderId = input.next();
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.addEquestrianToHorse(riderId, horseId,
						horseName);
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addEquestrianToHorse returns: "
									+ "Successfully added Equestrian "
									+ riderId
									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName + "\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addEquestrianToHorse returns: "
									+ "Failed to add Equestrian "
									+ riderId
									+ " to Horse "
									+ horseId
									+ " with nickName " + horseName + "\n");
				}

			} else if (command.equals("addVeterinarian")) {
				String personId = input.next();
				String personName = input.next();
				isUpdated = sys.addPerson(new Veterinarian(personId,
						personName, new Date(input.next()), input.next(), input
								.nextDouble(), input.nextInt()));

				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addVeterinarian returns: "
									+ "Successfully added Veterinarian "
									+ personId
									+ " fullName "
									+ personName
									+ " to BreedingHorses\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addEquestrian returns: "
									+ "Failed to add Veterinarian "
									+ personId
									+ " fullName "
									+ personName
									+ " to BreedingHorses\n");
				}
			} else if (command.equals("addTrainerToHorse")) {

				String trainerId = input.next();
				String serialId = input.next();
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.addTrainerToHorse(trainerId, serialId, horseId,
						horseName);
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addTrainerToHorse returns: "
									+ "Successfully added Trainer "
									+ trainerId

									+ " to Horse "
									+ horseId
									+ " with nickName " + horseName + "\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addTrainerToHorse returns: "
									+ "Failed to add Trainer "
									+ trainerId
									+ " to Horse "
									+ horseId
									+ " with nickName " + horseName + "\n");
				}

			} else if (command.equals("addVeterinarianToHorse")) {

				String veterinarianId = input.next();
				String serialId = input.next();
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.addVeterinarianToHorse(veterinarianId,
						serialId, horseId, horseName);
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addVeterinarianToHorse returns: "
									+ "Successfully added Veterinarian "
									+ veterinarianId

									+ " to Horse "
									+ horseId
									+ " with nickName " + horseName + "\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addVeterinarianToHorse returns: "
									+ "Failed to add Veterinarian "
									+ veterinarianId
									+ " to Horse "
									+ horseId
									+ " with nickName " + horseName + "\n");
				}
			} else if (command.equals("findTheMostUnluckyGambler")) {
				SaveLineToFile
						.writeToFileInSeparateLine("\n=== findTheMostUnluckyGambler returns: ===\n");
				SaveLineToFile.writeToFileInSeparateLine(sys
						.findTheMostUnluckyGambler().toString()
						+ "\n");
			} else if (command.equals("findTheBestHorse")) {
				SaveLineToFile
						.writeToFileInSeparateLine("\n=== findTheBestHorse returns: ===\n");
				SaveLineToFile.writeToFileInSeparateLine(sys.findTheBestHorse(
						input.nextDouble(), input.nextDouble(),
						input.nextDouble()).toString()
						+ "\n");
			} else if (command.equals("printTop10TrainersRankedReport")) {
				sys.printTop10TrainersRankedReport();
			} else if (command.equals("giveBonusToVeterinarians")) {
				int totalTreatmentHours = input.nextInt();
				int totalTreatmentHorses = input.nextInt();
				double bonusPersent = input.nextDouble();
				sys.giveBonusToVeterinarians(totalTreatmentHours,
						totalTreatmentHorses, bonusPersent);
			} else if (command.equals("printAllPersons")) {
				SaveLineToFile
						.writeToFileInSeparateLine("\n=== printAllPersons returns: ===\n");
				sys.printAllPersons();
			} else if (command.equals("printAllHorses")) {
				SaveLineToFile
						.writeToFileInSeparateLine("\n=== printAllHorses returns: ===\n");
				sys.printAllHorses();
			} else if (command.equals("printAllBookMakers")) {
				SaveLineToFile
						.writeToFileInSeparateLine("\n=== printAllBookMakers returns: ===\n");
				sys.printAllBookMakers();
			} else if (command.equals("printAllGamblers")) {
				SaveLineToFile
						.writeToFileInSeparateLine("\n=== printAllGamblers returns: ===\n");
				sys.printAllGamblers();
			} else if (command.equals("addHorseToCompetition")) {

				String hIdentificalNo = input.next();
				String nickName = input.next();
				int serialNum = input.nextInt();
				isUpdated = sys.addHorseToCompetition(hIdentificalNo, nickName,
						serialNum, input.next(), Type.valueOf(input.next()),
						new Date(input.next()), input.nextShort());
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addHorseToCompetition returns: "
									+ "Successfully added Horse "
									+ hIdentificalNo + " to Competition No "+serialNum+"\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addHorseToCompetition returns: "
									+ "Failed to add new Horse "
									+ hIdentificalNo + " to Competition No "+serialNum+"\n");
				}

			} else if (command.equals("removeHorseFromCompetition")) {

				String hIdentificalNo = input.next();
				String nickName = input.next();
				int serialNum = input.nextInt();
				isUpdated = sys.removeHorseFromCompetition(hIdentificalNo,
						nickName, serialNum, Type.valueOf(input.next()));
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("removeHorseFromCompetition returns: "
									+ "Successfully remove Horse "
									+ hIdentificalNo + " from Competition No "+serialNum+"\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeHorseFromCompetition returns: "
									+ "Failed to remove new Horse "
									+ hIdentificalNo + " from Competition No "+serialNum+"\n");
				}

			} else if (command.equals("addGamblingBetsForCompetition")) {

				String bId = input.next();
				String bLicenseNo = input.next();
				String gId = input.next();
				String hIdentificationNo = input.next();
				String hNickName = input.next();
				Integer cIdNumber = input.nextInt();
				Type cType = Type.valueOf(input.next());
				isUpdated = sys.addGamblingBetsForCompetition(bId, bLicenseNo,
						gId, hIdentificationNo, hNickName, cIdNumber, cType,
						input.nextDouble());
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("addGamblingBetsForCompetition returns: "
									+ "Successfully added Bet from Bookmaker No "
									+ bId
									+ " of Gambler No "
									+ gId
									+ " to Competition No "
									+ cIdNumber
									+ " on Horse No "
									+ hIdentificationNo
									+ " with Name " + hNickName + ".\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("addGamblingBetsForCompetition returns: "
									+ "Failed add Bet from Bookmaker No "
									+ bId
									+ " of Gambler No "
									+ gId
									+ " to Competition No "
									+ cIdNumber
									+ " on Horse No "
									+ hIdentificationNo
									+ " with Name " + hNickName + ".\n");
				}

			} else if (command.equals("removeGamblingBetsForCompetition")) {

				String bId = input.next();
				String bLicenseNo = input.next();
				String gId = input.next();
				String hIdentificationNo = input.next();
				String hNickName = input.next();
				Integer cIdNumber = input.nextInt();
				Type cType = Type.valueOf(input.next());
				isUpdated = sys.removeGamblingBetsForCompetition(bId,
						bLicenseNo, gId, hIdentificationNo, hNickName,
						cIdNumber, cType);
				if (isUpdated) { // if adding successfully, then true returned,
					// and following message written to the
					// output file
					SaveLineToFile
							.writeToFileInSeparateLine("removeGamblingBetsForCompetition returns: "
									+ "Successfully removed Bet from Bookmaker No "
									+ bId
									+ " of Gambler No "
									+ gId
									+ " to Competition No "
									+ cIdNumber
									+ " on Horse No "
									+ hIdentificationNo
									+ " with Name " + hNickName + ".\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeGamblingBetsForCompetition returns: "
									+ "Failed remove Bet from Bookmaker No "
									+ bId
									+ " of Gambler No "
									+ gId
									+ " to Competition No "
									+ cIdNumber
									+ " on Horse No "
									+ hIdentificationNo
									+ " with Name " + hNickName + ".\n");
				}

			}

			else if (command.equals("removeEquestrian")) {
				String personId = input.next();

				isUpdated = sys.removePerson(new Equestrian(personId));
				if (isUpdated) { // if removing successfully, then true //
					// returned, and following message written
					// // to the output file
					SaveLineToFile
							.writeToFileInSeparateLine("removeEquestrian returns: "
									+ "Successfully removed Equestrian "
									+ personId + "\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeEquestrian returns: "
									+ "Failed to remove Equestrian "
									+ personId
									+ "\n");
				}
			}

			else if (command.equals("removeHorse")) {
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.removeHorse(horseId, horseName);
				if (isUpdated) {
					// if removing successfully, then true // returned, and
					// following message written to the output file
					SaveLineToFile
							.writeToFileInSeparateLine("removeHorse returns: "
									+ "Successfully removed Horse with ID "
									+ horseId + " and name " + horseName + "\n");
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeHorse returns: "
									+ "Failed to remove Horse with ID "
									+ horseId + " and name " + horseName + "\n");
				}
			}

			else if (command.equals("removeTrainer")) {

				String personId = input.next();
				String serialNum = input.next();

				isUpdated = sys.removePerson(new Trainer(personId, serialNum));
				if (isUpdated) {
					SaveLineToFile
							.writeToFileInSeparateLine("removeTrainer returns: "
									+ "Successfully removed Trainer "
									+ personId);
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeTrainer returns: "
									+ "Failed to remove Trainer " + personId);
				}
			} else if (command.equals("removeVeterinarian")) {
				String personId = input.next();
				String serialNum = input.next();

				isUpdated = sys.removePerson(new Veterinarian(personId,
						serialNum));
				if (isUpdated) {
					SaveLineToFile
							.writeToFileInSeparateLine("removeVeterinarian returns: "
									+ "Successfully removed Veterinarian "
									+ personId);
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeVeterinarian returns: "
									+ "Failed to remove Veterinarian "
									+ personId);
				}
			}

			else if (command.equals("removeEquestrianFromHorse")) {
				String riderId = input.next();
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.removeEquestrianFromHorse(riderId, horseId,
						horseName);
				if (isUpdated) {
					SaveLineToFile
							.writeToFileInSeparateLine("removeEquestrianFromHorse returns: "
									+ "Successfully removed Equestrian from"
									+ riderId

									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName);
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeEquestrianFromHorse returns: "
									+ "Failed to remove Equestrian from Horse "
									+ riderId

									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName);
				}

			}

			else if (command.equals("removeTrainerFromHorse")) {

				String riderId = input.next();
				String riderName = input.next();
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.removeTrainerFromHorse(riderId, riderName,
						horseId, horseName);
				if (isUpdated) {
					SaveLineToFile
							.writeToFileInSeparateLine("removeTrainerFromHorse returns: "
									+ "Successfully removed Trainer from"
									+ riderId
									+ " fullName "
									+ riderName
									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName);
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeTrainerFromHorse returns: "
									+ "Failed to remove Trainer from Horse "
									+ riderId
									+ " fullName "
									+ riderName
									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName);
				}

			}

			else if (command.equals("removeVeterinarianFromHorse")) {
				String riderId = input.next();
				String riderName = input.next();
				String horseId = input.next();
				String horseName = input.next();
				isUpdated = sys.removeVeterinarianFromHorse(riderId, riderName,
						horseId, horseName);
				if (isUpdated) {
					SaveLineToFile
							.writeToFileInSeparateLine("removeVeterinarianFromHorse returns: "
									+ "Successfully removed Veterinar from"
									+ riderId
									+ " fullName "
									+ riderName
									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName);
				} else {
					SaveLineToFile
							.writeToFileInSeparateLine("removeVeterinarianFromHorse returns: "
									+ "Failed to remove Veterinar from Horse "
									+ riderId
									+ " fullName "
									+ riderName
									+ " to Horse "
									+ horseId
									+ " with nick name " + horseName);
				}

			} else if (command.equals("//")) {
				input.nextLine(); /* ignores line starts by '//' */

			} else
				System.out.printf("Command %s does not exist\n", command);
		} // end of clause - while input has next

		SaveLineToFile.saveLogFile(); // save the output file
		input.close(); // close connection to input file
		System.out.println("[End process]");
		System.out
				.println("NOTICE: \"End process\" does not mean that all your methods are correct.\nYou must check the output.txt file");
	}
}
