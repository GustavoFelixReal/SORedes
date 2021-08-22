package view;

import controller.RedesController;

public class Principal {

	public static void main(String[] args) {
		RedesController redes = new RedesController();
		
		//redes.getIp();
		redes.getPing();
	}
}
