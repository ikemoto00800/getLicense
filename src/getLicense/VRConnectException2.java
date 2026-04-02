package getLicense;

public class VRConnectException2 extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -7729707472137402416L;

	private int status ;

	public VRConnectException2(int status){
		this.status = status ;
	}



	@Override
	public String toString() {
		return "ステータスコード"+status+"で接続不可能";
	}


}
