package getLicense;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.List;

public class GetCookies {

	private CookieManager manager ;
	List<HttpCookie> cookies ;

	public GetCookies(){
		 CookieManager manager = new CookieManager();
		 this.manager = manager ;
		 CookieHandler.setDefault(manager);
	 }

	public List<HttpCookie> getCookies(){
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieStore st = manager.getCookieStore();
		 cookies = st.getCookies();

		return cookies ;
	}

		@Override
		public String toString() {
			StringBuilder cookie = new StringBuilder();
		    for (HttpCookie c : cookies) {
		        cookie.append(c);
		        cookie.append("; ");
		   // System.out.println("【setCookie】"+cookie);
		    }
		    String strCookie = cookie.toString();
		    return strCookie ;

		}


}
