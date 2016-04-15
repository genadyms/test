package kinopoisk.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dao.FilmDB;

public class MainParser {
	private Set<String> inputData;

	private Set<FilmDB> resultFilms = new HashSet<FilmDB>();
	
	public Set<FilmDB> getResultFilms() {
		return resultFilms;
	}

	public void setData(Set<String> data) {
		this.inputData = data;
	}
	
	@SuppressWarnings("static-access")
	public void parseFilms() {
		List<ParserFlm> prs = new ArrayList<ParserFlm>();
		int i=0;
		Iterator<String> iter = inputData.iterator();
		while(iter.hasNext()){
			String key = iter.next();
			ParserFlm  pf = new ParserFlm(key);
			pf.start();
			prs.add(pf);
			i++;
			if(i==50||!iter.hasNext()) {
				i = i==50 ? 0 : i;
				for (ParserFlm pr: prs) {
					try {
						pr.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (ParserFlm pr: prs) {
					resultFilms.add(pr.getFilm());
				}
				prs.clear();
				try {
					Thread.currentThread().sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("close main thread!!!!!!");
	}
}
