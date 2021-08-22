package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	public void getIp() {
		try {
			String os = os();
			String process = os.contains("Windows") ? "ipconfig" : "ifconfig";
			Process p;

			if (os.contains("Windows")) {
				p = Runtime.getRuntime().exec(process);
			} else {
				p = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", process });
			}

			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			while (linha != null) {
				if (os.contains("Windows")) {
					ipWindows(linha);
				} else {
					ipLinux(linha);
				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	public void getPing() {
		try {
			String os = os();
			String process = os.contains("Windows") ? "PING -4 -n 10 www.google.com.br"
					: "PING -4 -c 10 www.google.com.br";
			Process p;

			if (os.contains("Windows")) {
				p = Runtime.getRuntime().exec(process);
			} else {
				p = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", process });
			}

			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			while (linha != null) {
				
				System.out.println(linha);
				if (os.contains("Windows")) {
					pingWindows(linha);
				} else {
					pingLinux(linha);
				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	private String os() {
		return System.getProperty("os.name");
	}

	private void ipWindows(String linha) {
		String adapterName = "";
		String adapterIPv4 = "";

		if (linha.contains("Ethernet")) {
			adapterName = linha.split("Adaptador Ethernet")[1].split(":")[0];
			System.out.println("Nome do adaptador: " + adapterName);
		}

		if (linha.contains("IPv4")) {
			adapterIPv4 = linha.split(":")[1];
			System.out.println("IPv4 do adaptador: " + adapterIPv4 + "\n");
		}
	}

	private void ipLinux(String linha) {
		String adapterName = "";
		String adapterIPv4 = "";

		if (linha.contains("flags")) {
			adapterName = linha.split(":")[0];
			System.out.println("Nome do adaptador: " + adapterName);
		}

		if (linha.contains("inet ")) {
			adapterIPv4 = linha.split("inet")[1].split("netmask")[0].split(" ")[1];
			System.out.println("IPv4 do adaptador: " + adapterIPv4 + "\n");
		}
	}

	private void pingWindows(String linha) {
		String pingMedio = "";
		
		if (linha.contains("M‚dia")) {
			pingMedio = linha.split(",")[2].split("=")[1];
			System.out.println("Ping Médio: " + pingMedio);
		}

	}

	private void pingLinux(String linha) {
		String pingMedio = "";
		
		if (linha.contains("M‚dia")) {
			pingMedio = linha.split(",")[2].split("=")[1];
			System.out.println("Ping Médio: " + pingMedio);
		}
	}
}
