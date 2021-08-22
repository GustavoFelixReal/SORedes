package view;

import controller.RedesController;

public class Principal {

	public static void main(String[] args) {
		RedesController redes = new RedesController();
		
		String ip = redes.getIp();
		
		System.out.println(ip);

	}
}
