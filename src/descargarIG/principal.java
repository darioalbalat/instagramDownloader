package descargarIG;

import java.util.Scanner;

public class principal {

	public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Introduzca la url");
			String url = sc.nextLine();
			do {
				System.out.println(Utils.descarga(url) +" Introduzca otra url o -1 para salir");
				url = sc.nextLine();
			} while (!url.equals("-1"));
		}catch (Exception e) {
			System.out.println("Algo salio mal");
		}
	}
}
