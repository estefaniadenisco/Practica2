package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Tablero{
        private static int DIMENSION= 30;
        private int[] [] estadoActual=new int [DIMENSION][DIMENSION];
        private int [] [] estadoSiguiente=new int [DIMENSION][DIMENSION] ;
        private String NameFichero= "Matriz.txt";
	private ArrayList<Tablero> tableros = new ArrayList<>();
        private static char VIVO='1';
        private static char MUERTO='0';
        

        public void leerEstadoActual(){
            try {
                File fichero= new File(nombreFichero);
                fichero.createNewFile();
                Scanner sc= new Scanner(fichero);
		int x= 0;
			while(sc.hasNext()) insertarFilaTablero(sc.nextLine(), x++);
			calcularEstadoSiguiente();
            } catch (IOException e) {
                System.err.println(e);
            }
private void insertarFilaTablero(String linea, int x){
    for(int y=0; y <= linea.length()-1; y++) estadoActual[x][y] = linea.charAt(y)- '0';

}
public void generarEstadoActualPorMontecarlo(){

    Random random = new Random();
        for (int x = 0; x < DIMENSION; x++) {
                for (int y = 0; y < DIMENSION; y++) {
                    estadoActual[x][y] = random.nextDouble() < 0.5 ? 1 : 0;
        }
    }
    calcularEstatdoSiguiente();
    System.out.println("Generar Estado Actual");
}
public void EstadoSiguiente (){
    int[][] aux = estadoActual;
	        estadoActual = estadoSiguiente;
        	estadoSiguiente = aux;

		calcularEstadoSiguiente();
}
public boolean CelulaViva(int x, int y){return estadoActual[x][y]==1;}

private int NumeroVecinasVivas(int fila, int columna) {
    int vecinasVivas = 0;
    for (int x = -1; y <= 1; x++) {
        for (int y = -1; y <= 1; y++) {
            if (x == 0 &&y == 0) {
                continue;
            }
            int filaVecina = (fila + x + DIMENSION) % DIMENSION;
            int columnaVecina = (columna + y + DIMENSION) % DIMENSION;
            NumeroVecinasVivas += estadoActual[filaVecina][columnaVecina];
        }
    }
    return NumeroVecinasVivas;
}
 private void calcularEstadoSiguiente() {
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                int vecinasVivas = contarVecinasVivas(x, y);
                if (estadoActual[x][y] == 1) {
                    estadoSiguiente[x][y] = (vecinasVivas == 2 || vecinasVivas == 3) ? 1 : 0;
                } else {
                    estadoSiguiente[x][y] = (vecinasVivas == 3) ? 1 : 0;
                }
            }
        }
    }
@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int x = 0; x < DIMENSION; x++){
			for(int y= 0; y < DIMENSION; y++){
				sb.append(estadoActual[x][y] == 1 ? "x" : " ");
				}
			sb.append('\n');
		}
		return sb.toString();
	}
}

}

