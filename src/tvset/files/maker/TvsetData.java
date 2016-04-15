package tvset.files.maker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TvsetData {
	public final static String DIR = "info/";
	public final List<String[]> dataFilms = new ArrayList<String[]>();
	
	public TvsetData() {
		parse(DIR);
	}

	private void parse(String dir2) {
		File[] files = new File(DIR).listFiles();
		for(File f : files) {
			readFile(f);
		}
	}

	private void readFile(File f) {
		try {
			String str = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			String[]  data = str.split("\n===\n");
			String channel = data[0];
			for(int i=1; i<data.length; i++) {
				String[] films = data[i].split("\n");
				String prepData = films[0].split(" ")[1];
				prepData = prepData.length()==1 ? "2016-02-0"+prepData : "2016-02-"+prepData;
				System.out.println(prepData);
				for(int j=1; j<films.length;j++){
					String[] filmCurr = films[j].trim().split("---");
					if(filmCurr.length<2) continue;
					String name = filmCurr[1].trim();
					String time = prepData+" "+filmCurr[0].substring(0,5);
//					System.out.println(channel+"|"+name+"|"+time);
					String[] res = {channel, time, name};
					dataFilms.add(res);
				}
			}
		} catch (IOException |java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println(f.getAbsolutePath());
		}
	}
}
