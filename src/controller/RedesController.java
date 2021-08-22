package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	private String os() {
		return System.getProperty("os.name");
	}
	
	public void getIp() {
		String os = os();
		String process = "";
		
		if (os.contains("Windows")) {
			process = "ipconfig";
		} else {
			process = "ifconfig";
		}
		
		readProcess(process, os);
	}
	
	
	public void readProcess(String process, String os) {
		try {
			Process p;
			
			if (os.contains("Windows")) {
				p = Runtime.getRuntime().exec(process);
			} else {
				p = Runtime.getRuntime().exec(new String[]{ "/bin/bash", "-c", process });
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
	
	
	public void ipWindows(String linha) {
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
	
	public void ipLinux(String linha) {
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
	
	
	
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException error) {
			String msgErro = error.getMessage();
			
			if (msgErro.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("cmd /c ");
				buffer.append(process);
				
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException error2) {
					error2.printStackTrace();
				}
				
			} else {
				error.printStackTrace();
			}
		}
	}
	
	
	public void killProcess(String param) {
		String cmdPid = "TASKKILL /PID";
		String cmdNome = "TASKKILL /IM";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			pid = Integer.parseInt(param);
			buffer.append(cmdPid + " " + pid);
		} catch (NumberFormatException error) {
			buffer.append(cmdNome + " " + param);
		}
		
		
		callProcess(buffer.toString());
	}

}
