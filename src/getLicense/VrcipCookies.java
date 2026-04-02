package getLicense;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class VrcipCookies {
	String USER_AGENT = ApplicationContext.getFilePosition("cu_USER_AGENT");
	String PROXY_HOST = ApplicationContext.getFilePosition("cu_PROXY_HOST");
	int PROXY_PORT = Integer.parseInt(ApplicationContext.getFilePosition("cu_PROXY_PORT"));
	String loginUrl = ApplicationContext.getFilePosition("cu_loginUrl");

	private HttpsURLConnection conn = null;
	private List<HttpCookie> cookies = null;

	public List<HttpCookie> vrcipCookies(String postParams) throws IOException{
		GetCookies getcookie = new GetCookies();

		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
		URL loginurl = new URL(loginUrl);
		conn = (HttpsURLConnection) loginurl.openConnection(proxy);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		conn.setRequestProperty("Accept-Language", "ja,en-US;q=0.9,en;q=0.8");
		conn.setRequestProperty("Cache-Control", "max-age=0");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Host", "cipinex.videor.jp");
		conn.setRequestProperty("Origin", "https://cipinex.videor.jp");
		conn.setRequestProperty("Referer", "https://cipinex.videor.jp/top/Security/login.aspx");
		conn.setRequestProperty("Sec-Fetch-Mode", "navigate");
		conn.setRequestProperty("Sec-Fetch-Site", "same-origin");
		conn.setRequestProperty("Sec-Fetch-User", "?1");
		conn.setRequestProperty("Sec-Fetch-Dest", "document");
		conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		conn.setRequestProperty("User-Agent", USER_AGENT);

		conn.setDoOutput(true);
		conn.setDoInput(true);

		conn.connect();

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.write(postParams.getBytes(StandardCharsets.UTF_8));
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("【loginのステータスコード】" + responseCode);

		cookies = getcookie.getCookies();
		System.out.println("cookies:"+cookies);
		return cookies ;
	}
}
