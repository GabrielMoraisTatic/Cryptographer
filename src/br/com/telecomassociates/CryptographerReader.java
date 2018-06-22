package br.com.telecomassociates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographerReader {
	
	private BufferedReader br = null;
	private String header = "";
	private CryptographerWriter writer = null;
	private String delimiter = null;
	
	public CryptographerReader(File in, File out, String delimiter) throws IOException{
		br = new BufferedReader(new FileReader(in));
		writer = new CryptographerWriter(out);
		this.delimiter = delimiter;
		addHeader();
	}
	
	void readFile(File f, int[] indexers) throws Exception {
		String line;
		String[] tokens;	
		while ((line = br.readLine()) != null) {
			// Quebra a linha em elementos separados pelo delimitador passado por parâmetro
			tokens = line.split("\\"+delimiter, -1);
			// Troca os elementos indexadores pelo hash
			for (int index : indexers) {
				if(index-1 >= tokens.length) {
					//System.out.println("Algum indice ultrapassa o numero de colunas, ou o delimitador esta incorreto. ");
					throw new IllegalArgumentException("Algum indice ultrapassa o numero de colunas, ou o delimitador esta incorreto. ");
				}
				tokens[index-1] = hash(tokens[index-1]);
			}
			writer.writeLines(tokens, delimiter);
		}
		br.close();
		writer.out.close();
	}

	// Escreve o cabecalho
	private void addHeader() throws IOException {
		header = br.readLine();
		String[] head = new String[1];
		head[0] = header;
		writer.writeLines(head, delimiter);	
	}

	// Retorna o hash
	private String hash(String a) throws NoSuchAlgorithmException {
		if(a == null || "".equals(a)) {
			return a;
		}
		if(!a.isEmpty()) {
			replace57(a);
		}
		MessageDigest message = MessageDigest.getInstance("MD5");
		message.update(a.getBytes(),0,a.length());
		return new BigInteger(1,message.digest()).toString(16);
	}
	
	// Caso indexador nao comece com "57", adiciona essa string a ele
	private void replace57(String a) {
		if(!a.startsWith("57")) {
			"57".concat(a);
		}
	}

}
