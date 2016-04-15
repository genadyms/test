package zala.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZalaTvLauncher {

	private final static String MAIN_PAGE = "http://zala.by/base-pack/premera";
	private final static Map<String,List<String>> FILMS = new HashMap<String,List<String>>();

//	public static void main(String[] args) {
//		ZalaTvLauncher zala = new ZalaTvLauncher();
//		zala.execute();
//		System.out.println(zala.getFilms().size());
//		for (String key : zala.getFilms().keySet()){
//			System.out.println(key);
//			System.out.println(zala.getFilms().get(key));
//			System.out.println("----------------");
//		}
//	} 
//	
	public void execute() {
		ZalaMainPage mainPage = new ZalaMainPage();
		mainPage.setPage(MAIN_PAGE);
		try {
			mainPage.parse();
		} catch (NoPageException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int g =0;
		for (String url : mainPage.getURLs().keySet()) {
//			if(g>3) break;
			if(g==33){
				g++;
				continue;
			}
			System.out.println(String.format("%s count is and url is %s", g,url));
			String description = mainPage.getURLs().get(url);
			ParserPage page = new ParserPage(url,description);
			page.run();
//			page.showFilms();
			addFilms(page.getFilms());
			g++;
//			if(++g ==15) break;
		}
	}

	public Map<String,List<String>> getFilms() {
		return FILMS;
	}

	private void addFilms(Map<String,List<String>> films) {
		this.FILMS.putAll(films);
	}

}
