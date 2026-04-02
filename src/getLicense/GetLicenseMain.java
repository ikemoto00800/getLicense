package getLicense;

import java.io.IOException;
import java.nio.file.Path;

public class GetLicenseMain {

	public static void main(String[] args) {

		String checkfile = ApplicationContext.getFilePosition("rc_requestpayload_forCheck");
		System.out.println(checkfile);
		Path checkfilepath= Path.of(checkfile);
		CheckLicenseId checkId = new CheckLicenseId();
		boolean actionflag=false;
		try {
			actionflag = checkId.checkLicenseId(checkfilepath);
		} catch (IOException | VrConnectException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if(!actionflag) {
			LicenseIdUpdate update = new LicenseIdUpdate();
			String checkfileparent = ApplicationContext.getFilePosition("rc_checkPathParent");
			Path parentPath= Path.of(checkfileparent);
			String licenseId=null;
				try {
					licenseId = GetLicenseId.getLicenseId();
				} catch (IOException | VRConnectException2 e) {

					e.printStackTrace();
				}
				try {
					update.licenseIdUpdate(parentPath, licenseId);
				} catch (VrConnectException | IOException e) {

					e.printStackTrace();
				}
		}else {
			System.out.println("ライセンスＩＤは変更なしです。");
		}
	}

}
