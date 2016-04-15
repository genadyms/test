package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DaoCommon {
	private final Connection con;

	public DaoCommon() {
		MyConnection myCon = new MyConnection();
		con = myCon.getConnection();
	}
	public Set<String> selectNoParsedFilms(){
		String selectTableSQL = "SELECT shedule.name, kinopoisk.name_zala FROM shedule LEFT JOIN kinopoisk on shedule.name = kinopoisk.name_zala";
		Set<String> res = new HashSet<String>();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				String name = rs.getString("shedule.name");
				String nameKinopoisk = rs.getString("kinopoisk.name_zala");
				if(nameKinopoisk==null){
					System.out.println(nameKinopoisk);
					System.out.println(name);
					res.add(name);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public String selectData(String day, double inpRate) {
		if (day.length() == 1)
			day = "0" + day;
		String selectTableSQL = "SELECT shedule.name, shedule.date, shedule.channel,"
				+ "kinopoisk.rating, kinopoisk.director, kinopoisk.year, kinopoisk.name_kinop "
				+ "from kinopoisk, shedule "
				+ "where shedule.name=kinopoisk.name_zala and kinopoisk.rating>="
				+ inpRate
				+ " and DATE_FORMAT(shedule.date, '%d')='"
				+ day
				+ "'";
		Map<String, Set<FilmDBZala>> output = new HashMap<String, Set<FilmDBZala>>();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				String name = rs.getString("shedule.name");
				String channel = rs.getString("shedule.channel");
				String date = rs.getString("shedule.date");
				int inxLast = date.lastIndexOf(":");
				date = date.substring(0, inxLast);
				String rating = rs.getString("kinopoisk.rating");
				String director = rs.getString("kinopoisk.director");
				String year = rs.getString("kinopoisk.year").substring(0, 4);
				String kinoName = rs.getString("kinopoisk.name_kinop");
				List<String> dates = new ArrayList<String>();
				dates.add(date);
				FilmDBZala fz = new FilmDBZala(name, kinoName, director, year,
						channel, dates);
				fz.setVote(Double.valueOf(rating));
				if (output.containsKey(channel)) {
					if (output.get(channel).contains(fz)) {
						Iterator<FilmDBZala> it = output.get(channel)
								.iterator();
						while (it.hasNext()) {
							FilmDBZala f = it.next();
							if (f.equals(fz)) {
								f.getDates().add(date);
							}
						}
					} else
						output.get(channel).add(fz);
				} else {
					Set<FilmDBZala> flms = new HashSet<FilmDBZala>();
					flms.add(fz);
					output.put(channel, flms);
				}
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return getInfo(output);
	}
	
	private String getInfo(Map<String, Set<FilmDBZala>> output) {
		StringBuilder stringBuilder = new StringBuilder();
		int count = 0;
		for (String channel : output.keySet()) {
			stringBuilder.append(channel).append(
					System.getProperty("line.separator"));
			count += output.get(channel).size();
			for (FilmDBZala f : output.get(channel)) {
				stringBuilder.append(f).append(
						System.getProperty("line.separator"));
			}
			stringBuilder.append(System.getProperty("line.separator"));
		}
		stringBuilder.append("Total count films is - " + count).append(
				System.getProperty("line.separator"));
		return stringBuilder.toString();
	}
}
