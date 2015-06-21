package ed3sign.uni.fp.utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class MyFile {
	
	/**
	 * Carica Oggetto da File
	 * @param f file da cui caricare i dati
	 * @return oggetto generico letto dal file
	 */
	public static Object loadObject (File f, String path){
		if(f.exists()){
			try{
				FileInputStream saveFile = new FileInputStream(path);
				ObjectInputStream save = new ObjectInputStream(saveFile);
				Object saved = save.readObject();
				save.close();
				return saved;
				
			} catch(Exception e){ e.printStackTrace(); }
		}
		else
			System.out.println("Nessun file presente!");
		return null;
	 }
	
	/**
	 * Scrivi Oggetto su File
	 * @param f file su cui scrivere i dati
	 * @param toSave oggetto da salvare
	 */
	public static void saveObject (File f, Object toSave, String path){
		try{ 
			FileOutputStream saveFile=new FileOutputStream(path);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			save.writeObject(toSave);
			
			save.close(); 
		} catch(Exception e){ e.printStackTrace(); }
	}
	
	
	/**
	 * File Close
	 * @param file file Formatter
	 */
	public static void closeFile(PrintWriter writer){
		writer.close();
	}

}
