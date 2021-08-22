package controller;

public class RedesController {

	public RedesController() {
		super();
	}

	private String os() {
		return System.getProperty("os.name");
	}
	
	public String getOs() {
		return os();
	}

}
