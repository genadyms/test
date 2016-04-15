package dao;
public class FilmDB {
	
	private final String nameZala;
	private final String nameKino;
	private final String director;
	private final String year;
	private Double vote;
	public static final String NEW_LINE = "\n";
	public static final String LINE = " - ";
	
	public FilmDB(String nameZala, String nameKinopoisk, String director, String year) {
		this.nameZala = nameZala;
		this.nameKino = nameKinopoisk;
		this.director = director;
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result
				+ ((nameKino == null) ? 0 : nameKino.hashCode());
		result = prime * result
				+ ((nameZala == null) ? 0 : nameZala.hashCode());
		result = prime * result + ((vote == null) ? 0 : vote.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmDB other = (FilmDB) obj;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (nameKino == null) {
			if (other.nameKino != null)
				return false;
		} else if (!nameKino.equals(other.nameKino))
			return false;
		if (nameZala == null) {
			if (other.nameZala != null)
				return false;
		} else if (!nameZala.equals(other.nameZala))
			return false;
		if (vote == null) {
			if (other.vote != null)
				return false;
		} else if (!vote.equals(other.vote))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	public Double getVote() {
		return vote;
	}

	public void setVote(Double vote) {
		this.vote = vote;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Film name Zala").append(LINE).append(this.nameZala).append(NEW_LINE);
		result.append("Film name Kinopoisk").append(LINE).append(this.nameKino).append(NEW_LINE);
		result.append("Year").append(LINE).append(this.year).append(NEW_LINE);
		result.append("Director").append(LINE).append(this.director).append(NEW_LINE);
		result.append("Rating").append(LINE).append(this.vote).append(NEW_LINE);
		return result.toString();
	}
	
	public String getNameZala() {
		return nameZala;
	}
	public String getNameKino() {
		return nameKino;
	}

	public String getDirector() {
		return director;
	}

	public String getYear() {
		return year;
	}
}
