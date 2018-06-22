package br.com.telecomassociates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CryptographerWriter {
	
	public BufferedWriter out = null;
	
	public CryptographerWriter(File f) throws IOException {
		out = new BufferedWriter(new FileWriter(f));
	}
	
	// Escreve as linhas com os indexadores trocados pelo hash
	void writeLines(String[] line, String delimiter) throws IOException {
		out.write(String.join(delimiter, line) + "\n");
	}

}
