package getLicense;

public class VrConnectException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -7729707472137402416L;

	public VrConnectException(int status) {
		System.out.println("回接続"+status+"接続エラー");
	}

	public VrConnectException(String msg) {
		System.out.println(msg);
	}




}
