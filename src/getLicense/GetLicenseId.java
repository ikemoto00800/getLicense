package getLicense;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;





/**
 * iNex集計処理時にサーバーが発行するLicenseID取得。
 *
 * @author 00800
 *
 */


public class GetLicenseId {

	/**
	 *
	 * @return String :LicenseID文字列
	 * @throws IOException
	 * @throws VRConnectException2
	 */
	public static String getLicenseId() throws IOException, VRConnectException2 {
		String id = ApplicationContext.getFilePosition("sc_id");
		String password = ApplicationContext.getFilePosition("sc_password");
		VrcipLogin login = new VrcipLogin();
		List<HttpCookie> cookies=null;

		try {
			cookies=login.vrcipLogin(id, password);
			SetCookies sc = new SetCookies();

			String licenseidUrl = ApplicationContext.getFilePosition("cu_licenseidUrl");
			String PROXY_HOST = ApplicationContext.getFilePosition("cu_PROXY_HOST");
			String USER_AGENT = ApplicationContext.getFilePosition("cu_USER_AGENT");
			int PROXY_PORT  = Integer.parseInt(ApplicationContext.getFilePosition("cu_PROXY_PORT"));

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
			URL excelurl = new URL(licenseidUrl);
			HttpsURLConnection conn = (HttpsURLConnection) excelurl.openConnection(proxy);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Host", "cipinex.videor.jp");
			conn.setRequestProperty("Connection", "Keep-alive");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Sec-Fetch-Dest", "iframe");
			conn.setRequestProperty("Sec-Fetch-Mode", "navigate");
			conn.setRequestProperty("Sec-Fetch-User", "?1");
			conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
			conn.setRequestProperty("Sec-Fetch-Site", "same-origin");
			conn.setRequestProperty("Referer", "https://cipinex.videor.jp/top/menu.aspx");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			conn.setRequestProperty("Accept-Language","ja,en-US;q=0.9,en;q=0.8");
            conn.addRequestProperty("Cookie", sc.setCookies(cookies));
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();

			int status = conn.getResponseCode();
			System.out.println("【ファイルダウンロード】" + status);
			InputStream in8 = conn.getInputStream();

			StringBuilder sb = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(in8));
		               ) {
		        		String line= null;
		        		while((line=br.readLine())!=null) {
		        			sb.append(line);
		        		}
			}
    		String licenseStr = sb.toString();

			JSONObject json = new JSONObject(licenseStr);
			JSONArray jsonArray = json.getJSONArray("LicenseIds");
			System.out.println(jsonArray);

			String lisenceid = jsonArray.getString(0);
			System.out.println("iNexのライセンスＩＤ："+lisenceid);
			return lisenceid;
//			return "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";


		}
		finally {
			VrcipLogout logout = new VrcipLogout();
			logout.vrcipLogout(cookies);
		}
	}

}
