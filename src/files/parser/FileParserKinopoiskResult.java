package files.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import dao.FilmDB;

public class FileParserKinopoiskResult {
	private final String fileName;
	
	public FileParserKinopoiskResult(String filename) {
		this.fileName = filename;
	}
	
	public Set<FilmDB> parse(){
		Set<FilmDB> res = new HashSet<FilmDB>();
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] itemsFilm = content.split("\n--\n");
		for(String itemFilm: itemsFilm) {
			String[] datasFilm = itemFilm.split("\n");
			String nameZala = datasFilm[0].split(" - ")[1].trim();
			String nameKinopoisk = datasFilm[1].split(" - ")[1].trim();
			String year = datasFilm[2].split(" - ")[1].trim().substring(0,4);
			String director = datasFilm[3].split(" - ")[1].replace("реж.","").trim();
			String rating = datasFilm[4].split(" - ")[1].trim().substring(0,3);
			if(nameKinopoisk.equals("no_name")||year.equals("1901")||director.equals("no_director")||rating.equals("0.1")) continue;
			FilmDB currFilm = new FilmDB(nameZala, nameKinopoisk, director, year);
			currFilm.setVote(Double.valueOf(rating));
			res.add(currFilm);
		}
		return res;
	}
}
