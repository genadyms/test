package dao;

import java.util.List;

import dao.FilmDB;

public class FilmDBZala extends FilmDB {

	private final List<String> dates;
	private final String channel;
	
	public FilmDBZala(String nameZala, String nameKinopoisk, String director,
			String year, String channel,List<String> dates) {
		super(nameZala, nameKinopoisk, director, year);
		this.dates = dates;
		this.channel=channel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmDBZala other = (FilmDBZala) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		StringBuilder outBuilder = new StringBuilder();
		outBuilder.append(super.toString());
		outBuilder.append(channel).append(System.getProperty("line.separator"));
		for(String date : dates) {
			outBuilder.append(date).append(System.getProperty("line.separator"));
		}
		return outBuilder.toString();
//		return "FilmDBZala [dates=" + dates + ", channel=" + channel + "]";
	}

	public List<String> getDates() {
		return dates;
	}

	public String getChannel() {
		return channel;
	}

}
