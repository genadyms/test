package parser.tvset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dao.DaoZala;

public class TvSetFilmLauncher {
	
	public void parseFilms(){
		String urlStart = "http://www.vsetv.by/schedule_films_";
		String urlEnd = "_week.html";
		Map<String,String> urls = getChannelsURL();
		for(String s : urls.keySet()){
			String url = urlStart+s+urlEnd;
			JsoupParser jp = new JsoupParser(url,urls.get(s));
			DaoZala dz = new DaoZala();
			jp.seDao(dz);
			jp.start();
//			break;
		}
	}
	
	
	private Map<String,String> getChannelsURL() {

		Map<String,String> res = new HashMap<String,String>();
		res.put("channel_698","8 канал (Беларусь)");//
//		res.put("channel_22","RTVI");//
		res.put("channel_384","STV");
		res.put("channel_107","TV1000 East");//
		res.put("channel_255","TV1000 Русское кино");//
		
		res.put("channel_118","Беларусь 1");//0
		res.put("channel_148","Беларусь 2");//0
		res.put("channel_883","Беларусь 3");
		res.put("channel_932","Беларусь 5");
		res.put("channel_231","Беларусь 24");//0
//		
		res.put("channel_139","ВТВ");//
		res.put("channel_578","Детский мир / Телеклуб");//
		res.put("channel_526","Любимое кино");//
		res.put("channel_29","Наше кино");//
		res.put("channel_699","НТВ-Беларусь");//0
		res.put("channel_82","НТВ-Мир");
		res.put("channel_111","ОНТ");//0
		res.put("channel_177","Русский иллюзион");//
		res.put("channel_17","СТС");//
		res.put("channel_63","ТВ-3 Россия");//
		res.put("channel_700","РТР-Беларусь");//0
		res.put("channel_82","НТВ-Мир");//0
		
//		res.put("channel_487","Еврокино");//
		res.put("channel_608","Кино Плюс");//
		
		res.put("channel_68","ТВ XXI");//
		
		res.put("channel_1060","Мужское кино");//
		
		return res;
	}
}
