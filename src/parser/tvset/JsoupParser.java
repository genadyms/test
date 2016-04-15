package parser.tvset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.DaoZala;
import dao.FilmZala;

public class JsoupParser extends Thread {
	private final static String  DAY_START = "2016-03-";
	private final String nameChannel;
	private final String url;
	private final List<FilmZala> films = new LinkedList<FilmZala>();
	private DaoZala dao;

	// private final List<FilmsDay> channelProgrms = new ArrayList<FilmsDay>();

	@Override
	public void run() {
		super.run();
		parse();
		for(FilmZala fz : films) dao.insertFilm(fz);
	}

	private void parse() {
		try {
			Document doc = Jsoup
					.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 5.1; rv:40.0) Gecko/20100101 Firefox/40.0")
					.get();
			Thread.currentThread().sleep(5000);

			// try{
			// nameChannel =
			// doc.getElementsByClass("channeltitle_week").get(0).text();
			// }catch (java.lang.IndexOutOfBoundsException e) {
			// }

			Elements allNotes = doc.getElementsByAttributeValue("style",
					"width:300px");
			int cnt = 0;
			int day = 7;
			for (int i = 0; i < 7; i++) {
				int end = cnt + 3;
				String dayStr = getStrDay(day);
				System.out.println(dayStr);
				for (int j = cnt; j < end; j++) {
					try {
						prepare(allNotes.get(j),dayStr);
					} catch (java.lang.IndexOutOfBoundsException e) {
					}

					cnt = end;
				}
				// if (res.isEmpty())
				// continue;
				// FilmsDay fd = new FilmsDay(nameDay.trim());
				// fd.addFilms(res);
				// channelProgrms.add(fd);
				day++;
			}
		} catch (IOException | InterruptedException
				| java.lang.IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	private String getStrDay(int day) {
		String dayStr = String.valueOf(day);
		dayStr = dayStr.length()==1 ? ("0"+dayStr) : dayStr;
		return DAY_START+dayStr;
	}

	//
	private void prepare(Element element,String day) {
		Elements startTime = element.getElementsByClass("time");
		// Elements endTime = element.getElementsByClass("timefin");
		Elements prname = element.getElementsByClass("prname");
		for (int i = 0; i < startTime.size(); i++) {
			String nm = prname.get(i).text();
			int strt = nm.indexOf("\"") + 1;
			int end = nm.lastIndexOf("\"");
			nm = nm.substring(strt, end);
			Set<String> dates = new HashSet<String>();
			dates.add(day+" "+startTime.get(i).text().trim());
			FilmZala fz = new FilmZala(nameChannel, nm, dates);
			films.add(fz);
			// System.out.println(startTime.get(i).text().trim());
			// System.out.println(endTime.get(i).text().replace("-",
			// "").trim());
			// System.out.println(nm);

		}
	}

	public JsoupParser(String url, String nameChannel) {
		this.url = url;
		this.nameChannel = nameChannel;
		System.out.println(url + "|" + nameChannel);
	}

	public void seDao(DaoZala dz) {
		this.dao = dz;
	}

}
