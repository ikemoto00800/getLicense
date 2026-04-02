package getLicense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VrcipLoginTop {

	String USER_AGENT = ApplicationContext.getFilePosition("cu_USER_AGENT");
	String PROXY_HOST = ApplicationContext.getFilePosition("cu_PROXY_HOST");
	int PROXY_PORT = Integer.parseInt(ApplicationContext.getFilePosition("cu_PROXY_PORT"));
	String loginUrl = ApplicationContext.getFilePosition("cu_loginUrl");
	String menuUrl = ApplicationContext.getFilePosition("cu_menuUrl");
	HttpsURLConnection conn = null;

	public String vrcipLoginTop(String id, String password)throws IOException{

		//(1)Security/top/login.aspxにGETしてフォームデータの作成

		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
		URL loginurl = new URL(loginUrl);

		conn = (HttpsURLConnection) loginurl.openConnection(proxy);
		conn.setInstanceFollowRedirects(false);

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		conn.setRequestProperty("Accept-Language", "ja,en-US;q=0.9,en;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Host", "cipinex.videor.jp");
		conn.setRequestProperty("Sec-Fetch-Dest", "document");
		conn.setRequestProperty("Sec-Fetch-Mode", "navigate");
		conn.setRequestProperty("Sec-Fetch-Site", "none");
		conn.setRequestProperty("Sec-Fetch-User", "?1");
		conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		conn.setRequestProperty("User-Agent", USER_AGENT);

		conn.connect();
		int status = conn.getResponseCode();
		System.out.println("【ログイン画面ステータスコード】：" + status);

		// htmlの中身を抜き出す
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		StringBuilder response = new StringBuilder();
		while ((line = br.readLine()) != null) {
			response.append(line);
		}

		br.close();

		//リクエストフォーム作成

		String html = response.toString();
		Document doc = Jsoup.parse(html);
		Element loginform = doc.getElementById("form1");
		Elements inputElements = loginform.getElementsByTag("input");
		List<String> paramList = new ArrayList<String>();
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");
			if (key.equals("UserId")) {
				value = id;
			}
			if (key.equals("Password")) {
				value = password;
			}

			if (!key.equals("BtnLogin")) {
				paramList.add(key + "=" + URLEncoder.encode(value, "utf-8"));
			}
		}
		StringBuilder result = new StringBuilder();
		for (String param : paramList) {
			if (result.length() == 0) {
				result.append(param);
			} else {
				result.append("&" + param);
			}
		}
		String postParams = result.toString();
//		System.out.println("【リクエストフォーム】"+postParams) ;
		return postParams;
	}
}
