package getLicense;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ApplicationContext {


	private static Properties filename ;
	private static Properties mailinfo ;
	private static Properties connecturl ;
	private static Properties connectdb ;
	private static Properties security ;
	private static Properties programcode ;
	private static Properties programcode_monthly ;
	private static Properties nouseprogramcode ;
	private static Properties requestpayload_forCheck;
	private static Properties requestpayload_weekly ;
	private static Properties requestpayload_daily ;
	private static Properties requestpayload_monthly ;
	private static Properties macropath ;
	private static Properties requestpayload_weekly_conventional;

	static {
		try {
			filename = loadProperty("C:\\poltech_6\\filename.properties");
			mailinfo = loadProperty("C:\\poltech_6\\mailinfo.properties");
			connecturl = loadProperty("C:\\poltech_6\\connecturl.properties");
			connectdb = loadProperty("C:\\poltech_6\\connectdb.properties");
			security = loadProperty("C:\\poltech_6\\security.properties");
			programcode = loadProperty("C:\\poltech_6\\programcode.properties");
			programcode_monthly = loadProperty("C:\\poltech_6\\programcode_monthly.properties");
			nouseprogramcode = loadProperty("C:\\poltech_6\\nouseprogramcode.properties");

			security = loadProperty("C:\\poltech_6\\security.properties");
			requestpayload_forCheck = loadProperty("C:\\poltech_6\\requestpayload_forCheck.properties");
			requestpayload_daily = loadProperty("C:\\poltech_6\\requestpayload_daily.properties");
			requestpayload_weekly = loadProperty("C:\\poltech_6\\requestpayload_weekly.properties");
			requestpayload_monthly = loadProperty("C:\\poltech_6\\requestpayload_monthly.properties");
			macropath = loadProperty("C:\\poltech_6\\macropath.properties");

			requestpayload_weekly_conventional= loadProperty("C:\\poltech_6\\requestpayload_weekly_conventional.properties");


		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private static Properties loadProperty(String filename) throws IOException {
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
			Properties p = new Properties();
			p.load(in);
			return p;
		}
	}

	public static String getFilePosition(String key) {
		Properties p;
		switch (key.substring(0, 3)) {
		case "fn_":
			p = filename ;
			break;
		case "mi_":
			p = mailinfo ;
			break;
		case "cu_":
			p = connecturl ;
			break;
		case "cd_":
			p = connectdb ;
			break;
		case "sc_":
			p = security ;
			break ;
		case "pn_":
			p= programcode ;
			break ;
		case "pc_":
			p= programcode ;
			break ;
		case "cm_":
			p= programcode_monthly ;
			break ;
		case "nm_":
			p= programcode_monthly ;
			break ;
		case "nou":
			p= nouseprogramcode ;
			break ;
		case "rw_":
			p= requestpayload_weekly ;
			break;
		case "rd_":
			p= requestpayload_daily ;
			break;
		case "rm_":
			p= requestpayload_monthly ;
			break;
		case "rc_":
			p= requestpayload_forCheck ;
			break;

//PM導入前のVRサーバー接続時はこのprefixを使用
		case "rmx":
			p= requestpayload_monthly ;
			break;
		case "rwc":
			p= requestpayload_weekly_conventional ;
			break;

		case "mp_":
			p= macropath ;
			break;

		default:
			throw new IllegalArgumentException("該当プロパティが存在しません:" + key);
		}
		return p.getProperty(key);
	}

}
