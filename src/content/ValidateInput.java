package content;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ValidateInput {
	
	public static boolean validate_bLicenseNo(String LicenseNo) {
		return LicenseNo.matches("\\d[1-9]\\d{4}");
	}

	public static boolean validate_addHorseName(String name) {
		return name.matches("[A-Z][a-z]*");
	}
	
	public static boolean validate_addEquestrianToHorse(String eId) {
		return eId.matches("[a-zA-Z][\\w]{2}[^e]");
	}

}
