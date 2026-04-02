package getLicense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class LicenseIdUpdate {

	public void  licenseIdUpdate(Path checkPathParent,String newLicenseId) throws VrConnectException, IOException {
		Path[] payloadFileArray=null;

		try {
			payloadFileArray=Files.walk(checkPathParent,1)
					 .map(filepath->filepath.getFileName())
					 .map(filepath->filepath.toString())
					 .filter(strpath->strpath.contains("request"))
					 .map(strpath->Path.of(strpath))
					 .map(fileNamePath->Path.of(checkPathParent.toString(),fileNamePath.toString()))
					 .toArray(Path[]::new);
		} catch (IOException e) {
			e.printStackTrace();
			throw new VrConnectException("LicenseIdUpdateでターゲット配列の生成に失敗しました。");
		}

		for(Path payloadFilPath : payloadFileArray) {
				String result=null;
				List<String> payloadList=null;
					System.out.println("★チェック："+payloadFilPath);
					payloadList = Files.readAllLines(payloadFilPath);
					result =payloadList.stream().collect(Collectors.joining("&"));

				int wordindex = result.indexOf("LicenseIds");
				String nowLicenseId = result.substring(wordindex+14,wordindex+50);
				System.out.println("★プロパティファイル内のライセンスＩＤ："+nowLicenseId);

				List<String> newpayloadList =payloadList.stream()
						.map(payload->payload.replaceAll(nowLicenseId, newLicenseId))
						.collect(Collectors.toList());

						try {
							Files.write(payloadFilPath, newpayloadList);
						} catch (IOException e) {
							e.printStackTrace();
						}
		}
	}

}
