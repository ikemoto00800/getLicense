package getLicense;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class VrcipMenu {
	String USER_AGENT = ApplicationContext.getFilePosition("cu_USER_AGENT");
	String PROXY_HOST = ApplicationContext.getFilePosition("cu_PROXY_HOST");
	int PROXY_PORT = Integer.parseInt(ApplicationContext.getFilePosition("cu_PROXY_PORT"));
	String menuUrl = ApplicationContext.getFilePosition("cu_menuUrl");
	HttpsURLConnection conn = null;

	public void vrcipMenu(List<HttpCookie> cookies) throws IOException {
	//（4）ログイン後 menuページへ。menuページの中身を抜き出す
	SetCookies sc = new SetCookies();
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
	URL menuurl = new URL(menuUrl);
	conn = (HttpsURLConnection) menuurl.openConnection(proxy);
	conn.setInstanceFollowRedirects(false);
	conn.setRequestMethod("GET");
	conn.setRequestProperty("Accept",
			"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
	conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	conn.setRequestProperty("Accept-Language", "ja,en-US;q=0.9,en;q=0.8");
	conn.setRequestProperty("Cache-Control", "max-age=0");
	conn.setRequestProperty("Connection", "Keep-Alive");
	conn.setRequestProperty("Host", "cipinex.videor.jp");
	conn.setRequestProperty("Referer", "https://cipinex.videor.jp/top/Security/login.aspx");
	conn.setRequestProperty("Sec-Fetch-Mode", "navigate");
	conn.setRequestProperty("Sec-Fetch-Site", "same-origin");
	conn.setRequestProperty("Sec-Fetch-User", "?1");
	//add requestHeader
	conn.setRequestProperty("Sec-Fetch-Dest", "document");
	//
	conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
	conn.setRequestProperty("User-Agent", USER_AGENT);

	//取得したクッキーをセット
	conn.addRequestProperty("Cookie", sc.setCookies(cookies));

	conn.connect();
	int status4 = conn.getResponseCode();
	System.out.println("【1)menu画面のステータスコード】" + status4);
	}
}
