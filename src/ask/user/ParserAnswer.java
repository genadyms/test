package ask.user;

public class ParserAnswer {
	private final static String DAY = "day";
	private final static String WEEK = "week";
	
	public String getParamsRequest(String input) throws NoDayDataException{
		String output = "";
		if(input.indexOf(DAY)>-1) {
			String[] splitInput = input.split("=");
			if(splitInput.length!=2) throw new NoDayDataException();
			output = splitInput[1];
		}
		if(input.indexOf(WEEK)>-1) {
			output = WEEK;
		}
		return output;
	}
}
