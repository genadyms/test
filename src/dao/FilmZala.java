package dao;

import java.util.Set;

public class FilmZala {
	private final Set<String> dates;
	private final String channel;
	private final String name;
	
	public FilmZala(String channel, String name, Set<String> dates) {
		this.name=name;
		this.channel = channel;
		this.dates=dates;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((dates == null) ? 0 : dates.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FilmZala other = (FilmZala) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (dates == null) {
			if (other.dates != null)
				return false;
		} else if (!dates.equals(other.dates))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FilmZala [dates=" + dates + ", channel=" + channel + ", name="
				+ name + "]";
	}

	public String getChannel() {
		return channel;
	}

	public Set<String> getDates() {
		return dates;
	}

	public String getName() {
		return name;
	}
}
