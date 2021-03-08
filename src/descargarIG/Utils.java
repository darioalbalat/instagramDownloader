package descargarIG;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	private static int foto = 1;
	public static String getUrlString(String url){
	    URL miURL = null;
	    InputStreamReader isReader = null;
	    BufferedReader bReader = null;
	    String lineaURL;
	    StringBuffer buffer = new StringBuffer();

	    try {
	      miURL = new URL(url);
	      isReader = new InputStreamReader(miURL.openStream());
	      bReader = new BufferedReader(isReader);
	      while ((lineaURL = bReader.readLine()) != null){
	        buffer.append(lineaURL);
	      }
	      bReader.close();
	      isReader.close();
	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return buffer.toString();
	  }
	public static void guardarFoto(String direccion, int contador) {
		try {
			System.out.println(contador);
			URL url = new URL(direccion);
			URLConnection urlCon = url.openConnection();

			InputStream is = urlCon.getInputStream();

			FileOutputStream fos = new FileOutputStream(Constantes.ruta+foto+++Constantes.formato);

			byte[] array = new byte[1000];

			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}
			
			is.close();
			fos.close();

		} catch (Exception e) {
		}
	}
	
	public static String descarga(String url) {
		try {
			Pattern pattern = Pattern.compile(Constantes.regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(Utils.getUrlString(url));
			
			List<String> lista1 = new ArrayList<String>();
			
			while (matcher.find()) {
			    for (int i = 1; i <= matcher.groupCount(); i++) {
			        lista1.add(matcher.group(i));
			    }
			}
			matcher.reset();
			pattern = Pattern.compile(Constantes.regex2, Pattern.MULTILINE);
			for (String string : lista1) {
				matcher = pattern.matcher(string);
				
			}
			lista1.clear();
			while (matcher.find()) {
			    for (int i = 1; i <= matcher.groupCount(); i++) {
			        lista1.add(matcher.group(i));
			    }
			}
			matcher.reset();
			pattern = Pattern.compile(Constantes.regex3, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			
			String[] urls = new String[lista1.size()];
			int contador = 0;
			for (String string : lista1) {
				matcher = pattern.matcher(string);
				urls[contador++] = matcher.replaceAll(Constantes.subst);
			}
			Utils.guardarFoto(urls[0], contador++);
			return "descarga correcta";
		} catch (Exception e) {
			return "No se pude descargar";
		}
		
	}
}
