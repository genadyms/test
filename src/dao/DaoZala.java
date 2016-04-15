package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class DaoZala {
	private final static String INSERT_DATA = "INSERT INTO shedule (date,channel,name) values(?,?,?)";
	private final static String DELETE_FILMS = "Delete from shedule where name!='hello from world'";
	private final Connection con;

	public DaoZala() {
		MyConnection myCon = new MyConnection();
		con = myCon.getConnection();
	}

	public void insertFilms(String date, String channel, String name) {
		PreparedStatement prStatem = getPrepStatement(INSERT_DATA);
		try {
			prStatem.setString(1, date);
			prStatem.setString(2, channel);
			prStatem.setString(3, name);
			prStatem.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closePrepStatent(prStatem);
	}
	public void insertFilm(FilmZala film) {
		PreparedStatement prStatem = getPrepStatement(INSERT_DATA);
		String channel = film.getChannel();
		String name = film.getName();
		Set<String> dates = film.getDates();
		for(String date : dates) {
			try {
				prStatem.setString(1, date);
				prStatem.setString(2, channel);
				prStatem.setString(3, name);
				prStatem.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closePrepStatent(prStatem);
	}
	public void removeAll() {
		try {
			Statement st = con.createStatement();
			st.execute(DELETE_FILMS);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement getPrepStatement(String statnt) {
		try {
			return con.prepareStatement(statnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closePrepStatent(PreparedStatement prSt) {
		try {
			prSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
