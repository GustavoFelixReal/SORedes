package view;

import controller.RedesController;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		RedesController redes = new RedesController();
		
		//int option = JOptionPane.showInputDialog(null, "Escolha uma opção: \n  1 - Usar");
		int option = 0;
		
		do {
			try {
				option = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma opção: \n 1 - Consultar IPv4 \n 2 - Consultar ping médio \n 0 - Sair"));
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Obrigado pela preferência");
			}
			
			switch (option) {
				case 1:
					redes.getIp();
					break;
				case 2: 
					redes.getPing();
					break;
				case 0: 
					JOptionPane.showMessageDialog(null, "Obrigado pela preferência");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida");
			} 
			
		} while (option != 0);
	}
}
