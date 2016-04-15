package files.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.FilmZala;

public class FileParserZalaFilms {
	private final String fileName;
	private final static String[] MONTHS = {"января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"};
	public FileParserZalaFilms(String filename) {
		fileName=filename;
	}
	
	public Set<FilmZala> parse(){
		Set<FilmZala> res = new HashSet<FilmZala>();
		String content="";
		try {
			content = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splitContent = content.split("----------");
		for(int i=1;i<splitContent.length;i++){
			String[] dataZala = splitContent[i].trim().split("\n");
			String name = dataZala[0].trim();
			String channel = dataZala[1].trim();
			Set<String> dates = new HashSet<String>();
			for(int j=2; j<dataZala.length;j++){
				dates.add(getDate(dataZala[j]));
			}
			FilmZala fz = new FilmZala(channel, name, dates);
			res.add(fz);
		}
		return res;
	}
	
	private String getDate(String nameMonth) {
		List<String> lst = Arrays.asList(MONTHS);
		String[] splitDate = nameMonth.split(" ");
		String month = splitDate[2];
		String day = splitDate[1].trim();
		String time = splitDate[3].replace("*", "");
		day = day.length()<2 ? "0"+day : day;
		month = String.valueOf(lst.indexOf(nameMonth)+1);
		month = month.length()<2 ? "0"+2 : month;
		return Calendar.getInstance().getWeekYear()+"-"+month+"-"+day+" "+time;
	}
}
