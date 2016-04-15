package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DaoKinopoisk {
	private final static String INSERT_DATA = "INSERT INTO kinopoisk (name_zala,name_kinop,year,director,rating) values(?,?,?,?,?)";
	private final static String SELECT_ALL_DATA = "SELECT * FROM kinopoisk";
	private final static String SELECT_FILM_BY_NAME = "SELECT * FROM kinopoisk WHERE name_zala='";
	private final Connection con;

	public DaoKinopoisk() {
		MyConnection myCon = new MyConnection();
		con = myCon.getConnection();
	}

	public void insertFilm(FilmDB film) {
		PreparedStatement prStatem = getPrepStatement(INSERT_DATA);
		try {
			prStatem.setString(1, film.getNameZala());
			prStatem.setString(2, film.getNameKino());
			prStatem.setString(3, film.getYear());
			prStatem.setString(4, film.getDirector());
			prStatem.setDouble(5, film.getVote());
			prStatem.executeUpdate();
			prStatem.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertFilms(Set<FilmDB> films) {
		PreparedStatement prStatem = getPrepStatement(INSERT_DATA);
		for (FilmDB film : films) {
			try {
				prStatem.setString(1, film.getNameZala());
				prStatem.setString(2, film.getNameKino());
				prStatem.setString(3, film.getYear());
				prStatem.setString(4, film.getDirector());
				prStatem.setDouble(5, film.getVote());
				prStatem.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		closePrepStatent(prStatem);
	}

	public FilmDB getFilm(String inpName) {
		FilmDB res = null;
		try {
			Statement statement = con.createStatement();
			String query = SELECT_FILM_BY_NAME + inpName + "'";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nameZala = rs.getString("name_zala").trim();
				String nameKinopoisk = rs.getString("name_kinop").trim();
				String year = rs.getString("year").trim().substring(0, 4);
				String director = rs.getString("director").trim();
				String rating = rs.getString("rating");
				res = new FilmDB(nameZala, nameKinopoisk, director, year);
				res.setVote(Double.valueOf(rating));
				break;
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Set<FilmDB> getAllKinopoiskData() {
		Set<FilmDB> res = new HashSet<FilmDB>();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL_DATA);
			while (rs.next()) {
				String nameZala = rs.getString("name_zala").trim();
				String nameKinopoisk = rs.getString("name_kinop").trim();
				String year = rs.getString("year").trim().substring(0, 4);
				String director = rs.getString("director").trim();
				String rating = rs.getString("rating");
				FilmDB curFilm = new FilmDB(nameZala, nameKinopoisk, director,
						year);
				curFilm.setVote(Double.valueOf(rating));
				res.add(curFilm);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
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
