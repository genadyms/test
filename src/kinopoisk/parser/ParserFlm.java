package kinopoisk.parser;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import dao.FilmDB;

public class ParserFlm extends Thread {
	private final String filmName;
	private final static String URL_BASE = "http://www.kinopoisk.ru/index.php?first=no&what=&kp_query=";
	private FilmDB film;
	
	public ParserFlm(String film) {
		this.filmName = film;
	}

	@Override
	public void run() {
		super.run();
		parseFilm();
	}

	private void parseFilm() {
		Double vote = 0.1;
		String year = "1901";
		String name = "no_name";
		String director = "no_director";
		try {
			int rand = (int) (Math.random()*100);
			Thread.currentThread().sleep(rand);
			Document doc = Jsoup
					.connect(
							URL_BASE
									+ URLEncoder.encode(filmName,
											"Windows-1251"))
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 5.1; rv:40.0) Gecko/20100101 Firefox/40.0")
					.get();
			Thread.currentThread().sleep(10000);
			Element el = doc.getElementsByClass("most_wanted").first();
			try {
				String rate = el.getElementsByClass("right").first()
						.getElementsByClass("rating").first().text();
				name = el.getElementsByClass("name").first().getElementsByTag("a").first().text();
				year = el.getElementsByClass("year").first().text();
				vote = Double.valueOf(rate);
				director=el.getElementsByClass("director").first().text();
			} catch (java.lang.NullPointerException e) {
				System.out.println("Exception with rate " + filmName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFilm(new FilmDB(filmName, name, director,year));
		getFilm().setVote(vote);
	}

	public FilmDB getFilm() {
		return film;
	}

	public void setFilm(FilmDB film) {
		this.film = film;
	}

}
