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

public class ZalaMainPage {

	private static final Object NEW_STR = "\n\r";
	private static final String DIV_CLASS = "tv_channel-title";
	private static final String RAZDEL = " - ";
	private static final String ZALA = "http://zala.by";
	private String page;
	private final Map<String,String> URLS = new HashMap<String,String>();
	
	public void setPage(String mainPage) {
		this.page = mainPage;
	}

	public void parse() throws NoPageException, IOException {
		if(page.isEmpty()) throw new  NoPageException();
		Document doc = Jsoup.connect(page).get();
		Elements elmts = doc.getElementsByClass(DIV_CLASS);
		for(Element elem : elmts) {
			Element href = elem.child(0);
			URLS.put(ZALA + href.attr("href"), href.text());
		}
	}
	
	public Map<String,String> getURLs() {
		return URLS;
	}
	
	public String getParseInfo() {
		StringBuilder result = new StringBuilder();
		for (String url : URLS.keySet()) {
			result.append(url);
			result.append(RAZDEL);
			result.append(URLS.get(url));
			result.append(NEW_STR);
		}
		return result.toString();
	}

}
