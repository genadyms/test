package zala.parser;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.FilmZala;

public class AdapterDbZala {
	private final static String[] MONTHS = { "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа",
			"сентября", "октября", "ноября", "декабря" };
	private Set<FilmZala> filmsZala = new HashSet<FilmZala>();

	public AdapterDbZala(Map<String, List<String>> films) {
		buildZalaDbFilms(films);
	}

	private void buildZalaDbFilms(Map<String, List<String>> films) {
		for (String nameFilm : films.keySet()) {
			List<String> dataFilm = films.get(nameFilm);
			String nameChannel = dataFilm.get(0);
			Set<String> dates = new HashSet<String>();
			for (int i = 1; i < dataFilm.size(); i++) {
				dates.add(getDate(dataFilm.get(i)));
			}
			FilmZala fz = new FilmZala(nameChannel, nameFilm, dates);
			filmsZala.add(fz);
		}
	}

	public Set<FilmZala> getFilmsZala() {
		return filmsZala;
	}

	public void setFilmsZala(Set<FilmZala> filmsZala) {
		this.filmsZala = filmsZala;
	}

	private String getDate(String nameMonth) {
		String[] splitDate = nameMonth.split(" ");
		String month = splitDate[2].trim();
		int search = -1;
		for (int i = 0; i < MONTHS.length; i++) {
			if (MONTHS[i].equals(month)) {
				search = ++i;
				break;
			}
		}
		 String day = splitDate[1].trim();
		 String time = splitDate[3].replace("*", "");
		 day = day.length()<2 ? "0"+day : day;
		month = search<10 ? "0"+search : String.valueOf(search);
		return Calendar.getInstance().getWeekYear()+"-"+month+"-"+day+" "+time;
	}

}
