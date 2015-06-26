package ed3sign.uni.fp.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.crypto.Data;

import ed3sign.uni.fp.clinica.OrariSettimanali;

//Classe proposta dal professore durante le lezioni, copiata e modificata
public class MyUtil {

	// Scanner
	public static Scanner scan = createScanner();
	private static Scanner createScanner(){
		Scanner newScanner = new Scanner(System.in);
		newScanner.useDelimiter(System.getProperty("line.separator"));
		return newScanner;
	}
	
	private final static String YES = "Si";
	private final static String NO = "No";
	private final static String ERROR_FORMAT = "Attenzione: il dato inserito non è di formato valido!";
	private final static String ERROR_POSITIVE = "Attenzione: è richiesto un numero positivo!";
	private final static String ERROR_NOCHOICE = "Attenzione: il dato inserito non corrisponde a nessuna scelta effettuabile!";
	
	// Range Int Error
	public static String errorRangeInt(int min, int max) {
		String error_range = "Attenzione: e' richiesto un valore compreso tra " +min +" e " +max;
		return error_range;
	}
	
	// Range String Error
	public static String errorRangeString(int max) {
		String error_range = "Attenzione: e' richiesto l'inserimento di massimo " +max +" caratteri";
		return error_range;
	}

	// Read Next Integer
	public static int readInt (String text) {
		boolean exit = false;
		int value = 0;
		do{
			System.out.print(text);
			if (scan.hasNextInt()){
				value = scan.nextInt();
				exit = true;
			}
			else {
				System.out.println(ERROR_FORMAT);
				@SuppressWarnings("unused")
				String throw_away = scan.next();
			}
		} while (!exit);
		return value;
	}
	
	// Read Next Positive Integer
	public static int readPositiveInteger (String text) {
		boolean exit = false;
		int value = 0;
		do {
			value = readInt(text);
			if (value > 0) exit = true;
			else System.out.println(ERROR_POSITIVE);
		} while (!exit);
		return value;
	}
	
	
	// Read Next Integer Within a Range
	public static int readIntRange(String text, int min, int max) {
		boolean exit = false;
		int value = 0;
		do {
			value = readInt(text);
			if (value >= min && value <= max) exit = true;
			else System.out.println(errorRangeInt(min, max));
		} while (!exit);
		return value;
	}
	
	// Read Next Double
	public static double readDouble(String text) {
		boolean exit = false;
		double value = 0;
		
		do{
			System.out.print(text);
			if(scan.hasNextDouble()) {
				value = scan.nextDouble();
				exit = true;
			}
			else {
				System.out.println(ERROR_FORMAT);
				@SuppressWarnings("unused")
				String throw_away = scan.next();
			}
		} while (!exit);
		return value;
	}
	
	
	// Read Next String
	public static String readString(String text) {
		System.out.print(text);
		return scan.next();
	}
	
	// Read Next String with Maximum Length
	public static String readStringRange(String text, int max) {
		boolean exit = false;
		String value = "";
		do {
			value = readString(text);
			if (value.length() <= max) exit = true;
			else System.out.println(errorRangeString(max));
		} while (!exit);
		return value;
	}
	
	// Read Confirmation String
	public static boolean readConfirmation(String text) {
		boolean exit = false;
		boolean choice = false;
		String value = "";
		
		do {
			value = readString(text);
			if (value.equals(YES)) {
				choice = true;
				exit = true;
			} else if(value.equals(NO)){
				choice = false;
				exit = true;
			} else System.out.println(ERROR_NOCHOICE);
		
		} while (!exit);
		return choice;
	}
	
	/* Break Line */
	public static void breakLine(int n){
		for(int i=0; i<n; i++)
			System.out.println();
	}
	
	/* Dash Line */
	public static void breakDash(int n){
		for(int i=0; i<n; i++)
			System.out.println("---------------------------------------------------------");
	}
	
	/* Random number within range */
	public static int randN(int min, int max){
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	/* Nearest Half Round */
	public static double roundN(double n){
		int rounded = (int) (n + 0.5);
		//System.out.println(rounded);
		return rounded;
	}
	
	/* Current Time */
	public static String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String time = dateFormat.format(date).toString();
		return time;
	}
	

	/* Check Codice Fiscale */
	public static boolean checkCodFisc(String cod) {
		if(cod.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$"))
			return true;
		return false;
	}
	
	/* Format Date */
	public static String dateFormatter(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	/* Format Days */
	public static String dayFormatter(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy (EEE)");
		return dateFormat.format(date);
	}

	/* Reverse Format Date */
	public static Date revertDateFormatter(String d) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse(d);
		return date;
	}
	
	/* Generate Unique ID */
	private static AtomicLong idCounter = new AtomicLong();
	public static String createID(){
	    return String.valueOf(idCounter.getAndIncrement());
	}

	/* Genera Codice Albo */
	public static String generaCodAlbo(String cognome) {
		String uniqueID = createID();
		String cod = "M_"+cognome.substring(0,3)+uniqueID;
		return cod;
	}

	/* Convert Array List to One String */
	public static String arrayListToString(ArrayList<String> list) {
		String output="";
		System.out.println("List size: "+list.size());
		for (int i=0; i<list.size(); i++){
			if(list.size() == 1)
				output += list.get(i);
			else if(i == list.size()-1)
				output += list.get(i);
			else
				output += list.get(i) + ", ";
		}
		return output;
	}
	
	/* Simple Date Format */
	public static String timeDateFormat(Date date){
		SimpleDateFormat hf = new SimpleDateFormat("dd/MM/yyyy");
		return hf.format(date);
	}
	
	/* Time Hour Format */
	public static String timeHourFormat(Date date){
		SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
		return hf.format(date);
	}
	
	/* Time Appointment Format */
	public static String timeIntervalFormat(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		String check_in = timeHourFormat(date);
		cal.add(Calendar.MINUTE, OrariSettimanali.TIME_SLOT_DURATION);
		String check_out = timeHourFormat(cal.getTime());

		return check_in + " - "+check_out;
	}
	
	/* Merge Dates */
	public static Date mergeDateTime(Date date, Date time) {
		  // Construct date and time objects
		  Calendar dateCal = Calendar.getInstance();
		  dateCal.setTime(date);
		  Calendar timeCal = Calendar.getInstance();
		  timeCal.setTime(time);

		  // Extract the time of the "time" object to the "date"
		  dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		  dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));

		  // Get the time value!
		  date = dateCal.getTime();
		  return date;
	}
	
	
	/* Get Hours Based on Index */
	public static Date getHours(Calendar cal, int row, int col){
		// Set Starting Time
		cal.set(Calendar.HOUR_OF_DAY, OrariSettimanali.STARTING_HOUR);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
		for(int i=0; i<=row; i++)
			if(i!=0)
				cal.add(Calendar.MINUTE, OrariSettimanali.TIME_SLOT_DURATION);
		return cal.getTime();
	}

	/* Get Rows based on Date */
	public static int getHourRows(Calendar cal, Date date) {
		// Set Starting Time
		cal.set(Calendar.HOUR_OF_DAY, OrariSettimanali.STARTING_HOUR);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    
	    Calendar cal1 = new GregorianCalendar();
	    cal1.setTime(date);
	    
	    for(int i=0; i<=OrariSettimanali.TIME_SLOTS; i++){
			if(i!=0)
				cal.add(Calendar.MINUTE, OrariSettimanali.TIME_SLOT_DURATION);
			
			String d1 = timeHourFormat(cal.getTime());
		    String d2 = timeHourFormat(cal1.getTime());
			if(d1.equals(d2))
				return i;
	    }
		return 0;
	}
}
