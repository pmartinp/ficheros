package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	
	public static boolean DESC=false;
	public static boolean ASC=true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leer= new Scanner(System.in);
		
		boolean menu=true;
		int opcion=0;
		
		while(menu) {
			mostrarMenu();
			try {
				opcion=leer.nextInt();
				
				switch(opcion) {
				
				//Máximo y mínimo
				case 1:
					maximoyMinimo();
					
					break;
					
				//Renombrar carpetas
				case 2:
					renombrarCarpetas();
					
					break;
					
				//Ordenar personas
				case 3:
				
					ordenarPersonas();
					break;
					
				//Renombrar archivos
				case 4:
					renombrarArchivos();
					break;
					
				//Estadísticas de un archivo
				case 5:
					estadisticasArchivo();
					break;
					
				//Salir
				case 6:
					System.out.println("Programa finalizado. Actividad 12: Pedro Manuel Martín Pérez. 29/04/2022");
					menu=false;
					break;
					
				default:
					System.out.println("Opción no contemplada");
					break;
					
				}
			}catch(Exception e) {
				System.out.println("Porfavor introduzca un número");
				leer.next();
			}
		}
	}
	public static void mostrarMenu() {
		System.out.println("\n1. Máximo y mínimo");
		System.out.println("2. Renombrar carpetas");
		System.out.println("3. Ordenar personas");
		System.out.println("4. Renombrar archivos");
		System.out.println("5. Estadísticas de un archivo");
		System.out.println("6. Salir");
	}
	
	public static void maximoyMinimo() {
		int maximo;
		int minimo;
		int numero;
		
		BufferedReader br = null;
		try {
			FileReader doc = new FileReader("files/numeros.txt");
			br = new BufferedReader(doc);//leemos el archivo.
			String line=br.readLine();
			
			//añadimos el primer numero de la lista a nuestras variables máximo y mínimo.
			maximo = Integer.valueOf(line);
			minimo = Integer.valueOf(line);
			while(line != null) {//recorremos nuestro documento por lineas.
				numero = Integer.valueOf(line);
				if(numero>maximo) {
					maximo=numero;//cada vez que haya un número mayor actualizamos nuestro máximo.
				}
				else if(numero<minimo) {
					minimo=numero;//cada vez que haya un número menor actualizamos nuestro mínimo.
				}
				line=br.readLine();
			}
			br.close();
			System.out.println("El valor mínimo de los números es: "+ minimo);
			System.out.println("El valor máximo de los números es: "+ maximo);
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public static void renombrarCarpetas() {
		
		try {
			File media = new File("files/Multimedia");
			File renMedia = new File("files/MEDIA");
			
			File fotos = new File("files/MEDIA/Fotografias");
			File renFotos = new File("files/MEDIA/FOTOS");
			
			File libros = new File("files/MEDIA/Libros");
			File renLibros = new File("files/MEDIA/LECTURA");
			
			if(media.renameTo(renMedia) && fotos.renameTo(renFotos) && libros.renameTo(renLibros)) {//renombramos las carpetas y comprobamos que se hayan renombrado con éxito.
				System.out.println("Carpetas renombradas con éxito");
			}else {
				System.out.println("Error, ya se han renombrado las carpetas.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void ordenarPersonas() {
		File proceso = new File("files/listado_personas_ordenado.txt");
		
		if(proceso.exists()) {
			System.out.println("Ya existe el archivo listado_personas_ordenado.txt");
		}else {
			Scanner leer= new Scanner(System.in);
			BufferedReader br = null;
			BufferedWriter bw = null;
			
			String SEPARATOR=" ";
			int contador=1;
			
			TreeSet<Persona> personas=new TreeSet();
			
			System.out.println("Introduzca el nombre del archivo que desea leer.");
			System.out.println("Sugerencias: 'listado_personas.txt'");
			String archivo = leer.nextLine();
			
			while(!comprobarRuta(archivo)) {//comprobamos que la dirección sea correcta.
				System.out.println("Archivo no encontrado");
				archivo = leer.nextLine();
			}
			
			try {
				FileReader doc = new FileReader("files/" + "\\" + archivo);
				br = new BufferedReader(doc);//leemos el archivo.
				String line=br.readLine();
				
				while(line!=null) {//recorremos nuestro documento por lineas.
					 String [] fields = line.split(SEPARATOR);//separamos los elementos por " ".
					 Persona persona= new Persona(contador, fields[0], fields[1]);
					 
					 personas.add(persona);//añadimos personas a nuestro TreeSet (se ordena automaticamente).
					 contador++;		
					 line=br.readLine();
				}
				br.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
			
			try {
				FileWriter newDoc = new FileWriter("files/listado_personas_ordenado.txt");//creamos un nuevo documento.
				bw = new BufferedWriter(newDoc);
				
				bw.write(personas.toString().replaceAll("[\\[\\]]", "").replace(", ", ""));//escribimos en el nuevo documento los datos de nuestro array de personas ordenado.
				bw.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
			
			System.out.println("Archivo creado con éxito.");
		}
		
		
	}
	
	public static boolean comprobarRuta(String str) {//método para comprobar que la ruta introducida en la opción 3 del menú sea la correcta.
		if(str.equals("listado_personas.txt")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void renombrarArchivos() {
		File[]archivosFotos;
		File[]archivosLibros;
		try {
			
			File Fotos = new File("files/MEDIA/FOTOS");
			File Libros = new File("files/MEDIA/LECTURA");
			
			archivosFotos=Fotos.listFiles();//creamos un array con todos los archivos de nuestra carpeta FOTOS.
			
			for(File x: archivosFotos) {//recorremos los archivos de nuestra carpeta FOTOS
				File renombrar = new File("files/MEDIA/FOTOS/"+x.getName().replaceAll("\\..*$", ""));//sustituimos la extensión.
				x.renameTo(renombrar);
			}
			
			archivosLibros=Libros.listFiles();//creamos un array con todos los archivos de nuestra carpeta LIBROS.
			
			for(File x: archivosLibros) {//recorremos los archivos de nuestra carpeta LIBROS.
				File renombrar = new File("files/MEDIA/LECTURA/"+x.getName().replaceAll("\\..*$", ""));//sustituimos la extensión.
				x.renameTo(renombrar);
			}
			
			System.out.println("Archivos renombrados con éxito");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void estadisticasArchivo() {
		Scanner leer = new Scanner(System.in);
		BufferedReader br;
		
		int numLineas=1;//contador de lineas.
		int numPalabras=0;//contador de palabras.
		int numCaracteres=0;//contador de caracteres.
		int iterador=0;//iterador para recorrer nuestro HashMap.
		HashMap<String, Integer> repPalabras=new HashMap();
		
		File Libros = new File("files/MEDIA/LECTURA");
		File[]archivosLibros = archivosLibros=Libros.listFiles();//creamos un array con los posibles documentos a analizar las estadísticas.
		
		System.out.println("Escribe la ruta del archivo del que quieras saber sus estadísticas");
		System.out.print("Sugerencias: ");
		
		for(File x: archivosLibros) {
			System.out.print("'"+x.getName()+"', ");//nombre de los archivos.
		}
		System.out.println("");
		String archivo=leer.next();
		
		while(!comprobarRutaLibros(archivo, archivosLibros)) {//comprobamos que la ruta introducida sea la correcta.
			System.out.println("Ruta no encontrada, introduzca nueva ruta");
			archivo=leer.next();
		}
		
		try {
			FileReader doc = new FileReader("files/MEDIA/LECTURA/" + "\\" + archivo);//leemos el archivo introducido por el usuario.
			br = new BufferedReader(doc);
			String line=br.readLine();
			
			while(line!=null) {
				numLineas++;
				
				numPalabras+=contarPalabras(line, repPalabras);
				numCaracteres+=contarCaracter(line);
				line=br.readLine();
			}
			br.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		Map<String, Integer> sortedMapDesc = sortByComparator(repPalabras, DESC);//ordenamos nuestro map de manera descendente por su valor.
		
		System.out.println("Número de líneas: "+ numLineas);
		System.out.println("Número de palabras: "+ numPalabras);
		System.out.println("Número de carácteres: "+ numCaracteres);
		System.out.println("Las 10 palabras mas repetidas son: ");
		
		for(Map.Entry<String, Integer> entrada : sortedMapDesc.entrySet()) {//mostramos los 10 primeros resultados de nuestro map.
			if(iterador<10) {
				System.out.println(entrada);
				iterador++;
			}else {
				break;
			}
		}
	}
	
	 private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

	        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<String, Integer>>()
	        {
	            public int compare(Entry<String, Integer> o1,
	                    Entry<String, Integer> o2)
	            {
	                if (order)
	                {
	                    return o1.getValue().compareTo(o2.getValue());
	                }
	                else
	                {
	                    return o2.getValue().compareTo(o1.getValue());

	                }
	            }
	        });

	        // Maintaining insertion order with the help of LinkedList.
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Entry<String, Integer> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap;
	        }
	
	public static int contarPalabras(String line, HashMap<String, Integer> mapa) {
		int numPalabras=0;
		line=line.trim();//eliminamos espacios del principio y final del string.
		line=line.toLowerCase();//convertimos el string a minúscula para evitar errores a la hora de contar la misma palabra.
		line=line.replaceAll("\\p{Punct}", "");//eliminamos todo signo de puntuación para evitar errores a la hora de contar palabras.
		if(!line.matches("")) {
			String[] palabras=line.split(" +");//separamos las palabras por espacios.
			numPalabras+=palabras.length;
			for(String x: palabras) {
				if(mapa.containsKey(x)) {
					mapa.put(x, (mapa.get(x)+1));//si nuestro palabra está en nuestro map sumamos 1 al valor.
				}
				else {
					mapa.put(x, 1);//si la palabra no está en nuestro map la añadimos y contamos 1.
				}
			}
		}
		return numPalabras;
	}
	
	public static int contarCaracter(String line) {
		int numCaracteres=0;
		line=line.replaceAll(" ", "");//contamos todos los carácteres menos los espacios.
		numCaracteres+=line.length();
		
		return numCaracteres;
	}
	
	public static boolean comprobarRutaLibros(String str, File[]archivos) {
		boolean comprobante=false;
		for(File x: archivos) {
			if(str.equals(x.getName())) {
				comprobante=true;
			}
		}
		return comprobante;
	}

}
