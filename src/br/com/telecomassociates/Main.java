package br.com.telecomassociates;
import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception {
		
		
		if(args.length < 3	) {
			//System.out.println("UTILIZACAO: [NOME ARQUIVO DE ENTRADA] [INDICE DO SEPARADOR] [INDICE DOS INDEXADORES SEPARADOS POR ESPACO]");
			throw new IllegalArgumentException("UTILIZACAO: [NOME ARQUIVO DE ENTRADA] [INDICE DO SEPARADOR] [INDICE DOS INDEXADORES SEPARADOS POR ESPACO]");
		}
		
		// Obtem arquivo de entrada
		File in = new File(args[0]);
		
		// Obtem o caractere ASCII referente ao numero decimal passado
		String delimiter = Character.toString((char)Integer.parseInt(args[1]));
		
		String name = in.getName();
		/* Para imprimir no mesmo diretorio do arquivo input */
		//File out = new File(args[0].replace(name, name.substring(0, (name.length()-4)) + "_PROCESSED.txt"));
		/* Para imprimir no mesmo diretorio do JAR */
		File out = new File(".".concat("/").concat(name.substring(0, (name.length()-4)) + "_PROCESSED.txt"));
		
		// Obtendo o indice dos indexadores
		int[] indexers = new int[args.length-2];
		for(int i=0; i< args.length-2;i++) {
			indexers[i] = Integer.parseInt(args[i+2]);
		}
		
		// Leitura e processamento dos dados
		CryptographerReader reader = new CryptographerReader(in, out, delimiter);
		reader.readFile(in, indexers);

	}
}
