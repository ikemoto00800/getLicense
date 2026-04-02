package getLicense;

import java.net.HttpCookie;
import java.util.List;

public class SetCookies {
	// String strCookie = null ;
	public String setCookies(List<HttpCookie> cookies) {
		if (!cookies.isEmpty()) {
			StringBuilder cookie = new StringBuilder();
			for (HttpCookie c : cookies) {
				cookie.append(c);
				cookie.append("; ");
				// System.out.println("【setCookie】"+cookie);
			}
			String strCookie = cookie.toString();
			return strCookie;
		} else {

			String strCookie = " ";
			System.out.println("****NO_COOKIE****");
			return strCookie;

		}
	}
}
