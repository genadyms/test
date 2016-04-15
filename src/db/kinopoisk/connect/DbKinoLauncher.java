package db.kinopoisk.connect;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;

//import javax.mail.MessagingException;

import parser.tvset.TvSetFilmLauncher;
import kinopoisk.parser.MainParser;
import zala.parser.AdapterDbZala;
import zala.parser.ZalaTvLauncher;
import ask.user.AskForParse;
import ask.user.NoDayDataException;
import ask.user.ParserAnswer;
import dao.DaoCommon;
import dao.DaoKinopoisk;
import dao.DaoZala;
import dao.FilmDB;
import dao.FilmZala;

public class DbKinoLauncher {
	static {
		System.getProperties().put("http.proxyHost", "10.254.42.4");
		System.getProperties().put("http.proxyPort", "8080");
		System.getProperties().put("https.proxyHost", "10.254.42.4");
		System.getProperties().put("https.proxyPort", "8080");
}
	private final static String SAVE_DATA_FILE = "_03_2016.txt";

	public static void main(String[] args) {
		 System.out.println("Hello!!!");
//		fillNewZalaDataInDB();
		// parseTvSet();
		 queryFilms();
//		 runKinopoiskParserNewFilms();
//		 insertNewZalaData();
	}

	private static void parseTvSet() {
		// DaoZala daoZala = new DaoZala();
		// daoZala.removeAll();
		TvSetFilmLauncher lan = new TvSetFilmLauncher();
		lan.parseFilms();
	}

	private static void insertNewZalaData() {
		ZalaTvLauncher zl = new ZalaTvLauncher();
		zl.execute();
		Set<String> keys = zl.getFilms().keySet();
		for(String key: keys){
			System.out.println(key);
			System.out.println(zl.getFilms().get(key));
		}
		AdapterDbZala az = new AdapterDbZala(zl.getFilms());
		DaoZala dz = new DaoZala();
		Set<FilmZala> flms = az.getFilmsZala();
		for(FilmZala f : flms) System.out.println(f); 
		for (FilmZala film : flms) {
			dz.insertFilm(film);
		}
	}

	private static void queryFilms() {
		DaoCommon dc = new DaoCommon();
		String data = dc.selectData(getDay(), 7.7);
		System.out.println(data);
		// String fileName = getDay()+SAVE_DATA_FILE;
		// saveInFile(fileName, data);
		// sendEmail(data,getDay(),fileName);
		// deleteTempFile(fileName);

	}

	// private static void sendEmail(String data, String date,String file) {
	// SendMail mail = new SendMail();
	// mail.send("films "+date, data, "genady_m@tut.by","genady_m@tut.by",
	// file);;
	//
	// }
	private static void runKinopoiskParserNewFilms() {
		DaoCommon dao = new DaoCommon();
		Set<String> films = dao.selectNoParsedFilms();
		System.out.println(films.size());
//		runKinopoiskParser(films);
	}

	private static void runKinopoiskParser(Set<String> selectNoParsedFilms) {
		MainParser kinopoiskParser = new MainParser();
		kinopoiskParser.setData(selectNoParsedFilms);
		kinopoiskParser.parseFilms();
		DaoKinopoisk daoKinop = new DaoKinopoisk();
		daoKinop.insertFilms(kinopoiskParser.getResultFilms());
	}

	private void dialogWithUserForSetParams(String data) {
		if (data.isEmpty()) {
			AskForParse ask = new AskForParse();
			ParserAnswer prsAnsw = new ParserAnswer();
			try {
				System.out.println(prsAnsw.getParamsRequest(ask.getData()));
			} catch (NoDayDataException e) {
				System.out.println(e.toString());
			}
		}
	}

	private static void fillNewZalaDataInDB() {
		ZalaTvLauncher zala = new ZalaTvLauncher();
		zala.execute();
		System.out.println(zala.getFilms().size());
		AdapterDbZala adapter = new AdapterDbZala(zala.getFilms());
		DaoZala daoZala = new DaoZala();
		daoZala.removeAll();
		for (FilmZala fz : adapter.getFilmsZala()) {
			daoZala.insertFilm(fz);
		}
	}

	private static void deleteTempFile(String fileName) {
		try {
			Files.delete(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveInFile(String fileName, String outData) {
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

			out.write(outData);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getDay() {
		Date day = new Date();
		return (day.toString().split(" ")[2]);
	}

}
