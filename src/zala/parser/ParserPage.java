package zala.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserPage extends Thread{

	private static final String DIV_CLASS = "programm_with_time";
	private final String url;
	private final String description;
	private final Map<String,List<String>> FILMS = new HashMap<String,List<String>>();

	public ParserPage(String url, String description) {
		this.url = url;
		this.description = description;
	
	}
	
	public Map<String,List<String>> getFilms() {
		return FILMS;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parse() throws IOException {
		String findFlm1 = "Х/ф";
		String findFlm2 = "Худ. фильм";
		
		Document doc = Jsoup.connect(url).get();
		Element elmt = doc.getElementsByClass(DIV_CLASS).get(0);
		Elements elmts = elmt.getElementsByTag("p").size()==0 ? elmt.getElementsByTag("h6") : elmt.getElementsByTag("p");
		for (Element p : elmts){
			String day = p.text();
			if (day.contains(";**")) continue;
			for (Element li : p.nextElementSibling().getElementsByTag("li")){
				String tvBroadcast = li.text();
				if((tvBroadcast.indexOf(findFlm1)>0||tvBroadcast.indexOf(findFlm2)>0)== false) continue;
				String[] delTvBroad = tvBroadcast.split(" - ");
				String time = delTvBroad[0];
				String nameF = delTvBroad[1].contains("\"") ? delTvBroad[1].split("\"")[1].trim() : delTvBroad[1].split("«")[1].trim();
				String nameFilm = nameF.replace("».", "").replace("\"", "");
				if (FILMS.containsKey(nameFilm)) {
					FILMS.get(nameFilm).add(day + " " + time);
				} else {
					List<String> value = new ArrayList<String>();
					value.add(description);
					value.add(day + " " + time);
					FILMS.put(nameFilm, value);
				}
			}
		}
	}

	public void showFilms() {
		for (String film: FILMS.keySet()) {
			System.out.println(film+" - "+FILMS.get(film));
		}
	}

}
