package getLicense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * ライセンスＩＤが正しければtrueを返す。
 * @author 00800
 *
 */
public class CheckLicenseId {

	public boolean checkLicenseId(Path checkfile) throws IOException, VrConnectException {



			boolean actionflag= false;

				try {
					List<String> requestpayload = Files.readAllLines(checkfile);
					String licenseid = GetLicenseId.getLicenseId();
					actionflag = requestpayload.stream().anyMatch(payload->payload.contains(licenseid));
					System.out.println("actionflag:"+actionflag);
					return actionflag;
				} catch (IOException | VRConnectException2 e) {
					e.printStackTrace();
					return actionflag;
				}


	}
}
