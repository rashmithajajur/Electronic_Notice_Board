import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StandAlone {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateInString = "2014-12-13 13:00:00";
	 
		try {
	 
			Date date = formatter.parse(dateInString);
			System.out.println(date);
			System.out.println(formatter.format(date));
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
