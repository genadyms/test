package ask.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AskForParse {

	public String getData() {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			writer.write("Enter data for parse: week or day?");
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer="";
		try {
			 answer= br.readLine();
			System.out.println(answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

}
