package getLicense;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class VrcipLogout{
	String USER_AGENT = ApplicationContext.getFilePosition("cu_USER_AGENT");
	String PROXY_HOST = ApplicationContext.getFilePosition("cu_PROXY_HOST");
	int PROXY_PORT  = Integer.parseInt(ApplicationContext.getFilePosition("cu_PROXY_PORT"));
	String logoutUrl  = ApplicationContext.getFilePosition("cu_logoutUrl");

	public void vrcipLogout(List<HttpCookie> cookies) {

		HttpsURLConnection conn = null;
		SetCookies sc = new SetCookies();

		try {
			//ログアウト処理
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
			URL logouturl = new URL(logoutUrl);
			conn = (HttpsURLConnection) logouturl.openConnection(proxy);

			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Host","cipinex.videor.jp");
			conn.setRequestProperty("Connection","keep-alive");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Sec-Fetch-Mode", "navigate");
			conn.setRequestProperty("Sec-Fetch-User", "?1");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
			conn.setRequestProperty("Sec-Fetch-Site", "same-origin");
			conn.setRequestProperty("Referer", "https://cipinex.videor.jp/top/menu.aspx");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			conn.setRequestProperty("Accept-Language"," ja,en-US;q=0.9,en;q=0.8");
			conn.addRequestProperty("Cookie", sc.setCookies(cookies));

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();

			int status12 = conn.getResponseCode();
			System.out.println("【(11)ログアウト処理のステータス】" + status12);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
