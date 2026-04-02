package getLicense;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

/**
 * VRCIPログインメソッド
 * 同じIDでログインされるいる場合は、強制ログインで接続。
 * VrcipMenuクラスvrcipMenuメソッドで接続確率
 * @author ikemoto
 *
 */
public class VrcipLogin {

	private static List<HttpCookie> cookies = null;
	private static String postParams = null;

	public List<HttpCookie> vrcipLogin(String id, String password) throws IOException, VRConnectException2 {
		VrcipLoginTop login = new VrcipLoginTop();
		postParams = login.vrcipLoginTop(id, password);

		VrcipCookies vc = new VrcipCookies();
		cookies = vc.vrcipCookies(postParams);
//		System.out.println("cookies:" + cookies);

			if (cookies.isEmpty()) {
				System.out.println("強制ログイン処理");
				postParams = login.vrcipLoginTop(id, password);
				postParams = postParams.replace("ForceLogin=Normal", "ForceLogin=Force")
						.replace("ForceUserId=", "ForceUserId=home067").replace("ForcePassword=", "ForcePassword=3535");
				cookies = vc.vrcipCookies(postParams);
//				System.out.println("強制ログインcookies:" + cookies);
			}
		if(!cookies.isEmpty()) {
			System.out.println("接続完了");
		}else {
			System.out.println("接続失敗");
			throw new VRConnectException2(100);
		}
		VrcipMenu vm = new VrcipMenu();
		vm.vrcipMenu(cookies);
		return cookies;
	}
}
