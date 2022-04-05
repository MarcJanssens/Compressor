package aplicacióConsola;

import dades.ContingutCompressor;
import dades.GetFileSize;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compressor {
    public static void main(String[] args) {
        try {
            Scanner teclat = new Scanner(System.in);
            String fitxerNom;
            String opcio = demanarOpcio(teclat);
            switch (opcio) {
                case "Si":
                case "Sí":
                case "si":
                case "sí":
                    fitxerNom = demanarNomFitxer(teclat);
                    comprimirLogotip(fitxerNom);

                    System.out.println("Compressió realitzada!");


                    break;
                case "No":
                case "no":
                    System.out.println("Opció no implementada.");
                    //comprimirImatge();
                default:
                    System.out.println("Introdueix una resposta correcta.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void comprimirImatge() {

    }

    /**
     * @param nomFitxer
     * @return
     * @throws FileNotFoundException
     * @throws InputMismatchException
     */
    private static void comprimirLogotip(String nomFitxer) throws IOException, InputMismatchException {

        boolean correcte = false;
        File Obj = new File(nomFitxer);
        System.out.println("Fitxer trobat");
        Scanner Document = new Scanner(Obj);
        int[][] imatge = entaularImatge(Document);
        System.out.println("Imatge entaulada correctament");
        ContingutCompressor taulaComprimidah = comprimirTaulaH(imatge);
        ContingutCompressor taulaComprimidav = comprimirTaulaV(imatge);

        String res = imatge.length+" "+imatge[0].length+"\n";
        FileOutputStream CompHori = new FileOutputStream("CompHori.txt");
        CompHori.write("CH".getBytes());
        CompHori.write(res.getBytes());
        for(int i=0; i<taulaComprimidah.getNumElements(); i++) {
            CompHori.write(taulaComprimidah.getContingutComprimit()[i].getBytes());
            CompHori.write("\n".getBytes());
        }
        CompHori.flush();
        CompHori.close();

        FileOutputStream CompVert = new FileOutputStream("CompVert.txt");
        CompVert.write("CV".getBytes());
        CompVert.write(res.getBytes());
        for(int i=0; i<taulaComprimidav.getNumElements(); i++) {
            CompVert.write(taulaComprimidav.getContingutComprimit()[i].getBytes());
            CompVert.write("\n".getBytes());
        }

        CompVert.close();

        GetFileSize.printFileSizeNIO("CompHori.txt");
        GetFileSize.printFileSizeNIO("CompVert.txt");
        System.out.println("El fitxer que ocupa menys és " + GetFileSize.compareFileSizeNIO("CompHori.txt","CompVert.txt")+".");


    }

    /**
     * Aquesta funció fa que la imatge es torni una taula per poder treballar amb ella correctament.
     *
     * @param Document la imatge
     * @return retorna la imatge feta taula. taula[files][colums]
     * @throws InputMismatchException
     */
    private static int[][] entaularImatge(Scanner Document) throws InputMismatchException {
        String format = Document.nextLine();

        if (!format.equals("P2")) return null;

        Document.nextLine();
        String resolucio = Document.nextLine();
        int col = Integer.parseInt(resolucio.split(" ")[0]);
        int fil = Integer.parseInt(resolucio.split(" ")[1]);
        int valMax = Integer.parseInt(Document.nextLine());
        int[][] taula = new int[fil][col];

        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                taula[i][j] = Integer.parseInt(Document.nextLine());
            }
        }

        return taula;
    }

    private static ContingutCompressor comprimirTaulaH(int[][] taula) {

        int fil = taula.length;
        int col = taula[0].length;

        ContingutCompressor taulaHoritzontal = new ContingutCompressor();

        int comparat;
        int comptador = 0;
        String afegito;
        int treballant = taula[0][0];


        for (int[] ints : taula) {
            for (int j = 0; j < taula[0].length; j++) {

                comparat = ints[j];
                if (treballant == comparat) {
                    comptador++;
                } else {

                    afegito = treballant + " " + comptador;
                    taulaHoritzontal.afegirValor(afegito);
                    treballant = comparat;
                    comptador = 1;
                }
            }
        }
        afegito = treballant + " " + comptador;
        taulaHoritzontal.afegirValor(afegito);

        return taulaHoritzontal;
    }

    private static ContingutCompressor comprimirTaulaV(int[][] taula) {


        int fil = taula.length;
        int col = taula[0].length;


        ContingutCompressor taulaVertical = new ContingutCompressor();


        int comparat;
        int comptador = 0;
        String afegito;
        int treballant = taula[0][0];


        for (int j = 0; j < col; j++) {
            for (int[] ints : taula) {

                comparat = ints[j];
                if (treballant == comparat) {
                    comptador++;
                } else {

                    afegito = treballant + " " + comptador;
                    taulaVertical.afegirValor(afegito);
                    treballant = comparat;
                    comptador = 1;
                }
            }
        }
        afegito = treballant + " " + comptador;
        taulaVertical.afegirValor(afegito);

        return taulaVertical;
    }

    private static String demanarNomFitxer(Scanner teclat) {
        boolean correcte = false;
        String nom = "";
        do {
            try {
                System.out.println("Indiqui el nom del fitxer: ");
                nom = teclat.nextLine();
                correcte = true;
            } catch (InputMismatchException error) {
                System.out.println("Valor no vàlid");
                teclat.nextLine();
            }
        } while (!correcte);

        return nom;
    }

    private static String demanarOpcio(Scanner teclat) {
        boolean correcte = false;
        String opcio = "";
        do {
            try {
                System.out.println("La imatge a comprimir és un logotip? ");
                opcio = teclat.nextLine();
                correcte = true;
            } catch (InputMismatchException error) {
                System.out.println("Valor no vàlid");
                teclat.nextLine();
            }
        } while (!correcte);

        return opcio;
    }
}
