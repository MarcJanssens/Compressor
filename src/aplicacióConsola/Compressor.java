package aplicacióConsola;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class Compressor {
    public static void main(String[] args)
    {
        try {
            Scanner teclat = new Scanner(System.in);
            String fitxerNom;
            String opcio = demanarOpcio(teclat);
                switch (opcio){
                    case "Si":
                    case "Sí":
                    case "si":
                    case "sí":
                        fitxerNom=demanarNomFitxer(teclat);
                        if(comprimirLogotip(fitxerNom)){
                            System.out.println("Compressió realitzada!");
                        }else {
                            System.out.println("Compressió no realitzada.");
                        }
                        break;
                    case "No":
                    case "no":
                        System.out.println("Opció no implementada.");
                        //comprimirImatge();
                    default:
                        System.out.println("Introdueix una resposta correcta.");
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
        }
    }

    private static void comprimirImatge() {

    }

    /**
     *
     * @param nomFitxer
     * @return
     * @throws FileNotFoundException
     * @throws InputMismatchException
     */
    private static boolean comprimirLogotip(String nomFitxer) throws FileNotFoundException, InputMismatchException {

        boolean correcte = false;
        File Obj = new File(nomFitxer);

        Scanner Document = new Scanner(Obj);
        int[][] imatge = entaularImatge(Document);
        comprimirTaula(imatge);


        return correcte;

    }

    /**
     * Aquesta funció fa que la imatge es torni una taula per poder treballar amb ella correctament.
     * @param Document la imatge
     * @return retorna la imatge feta taula. taula[files][colums]
     * @throws InputMismatchException
     */
    private static int[][] entaularImatge(Scanner Document) throws InputMismatchException{
        String format = Document.nextLine();

        if (!format.equals("P2")) return null;

        Document.nextLine();
        String resolucio = Document.nextLine();
        int col = Integer.parseInt(resolucio.split(" ")[0]);
        int fil = Integer.parseInt(resolucio.split(" ")[1]);

        int[][] taula = new int[fil][col];

        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                taula[i][j] = Integer.parseInt(Document.nextLine());
            }
        }

        return taula;
    }

    private static String[] comprimirTaula(int[][] taula){

        int fil=taula.length;
        int col=taula[0].length;
        int mida=col*fil;

        String[] taulaComprimida = new String[mida];
        String[] taulaHoritzontal = new String[mida];
        String[] taulaVertical = new String[mida];
        //
        int treballant=0;
        int comparat=-1;
        int comptador = 1;
        int filesComprimitH=0;
        treballant = taula[0][0];
        comparat = taula[1][0];

        for(int i=0; i < taula.length; i++){
            for(int j=0; j < taula[0].length;j++){

                comparat = taula[i][j];

                if(treballant==comparat){
                    comptador++;
                }else{

                    taulaHoritzontal[filesComprimitH] = treballant + " " + comptador;
                    filesComprimitH++;
                    treballant = comparat;
                    comptador=1;
                }
            }
        }

        int filesComprimitV = 0;
        for(int i=0; i < taula[0].length ; i++){
            for(int j=0; j < taula.length;j++){
                if(i!=0&&j!=0){
                    comparat = taula[i][j];

                    if(treballant==comparat){
                        comptador++;
                    } else{
                        taulaVertical[filesComprimitV]=treballant+" "+comptador;
                        treballant = comparat;
                    }
                }else {
                    treballant = taula[1][1];
                }

            }
        }


        //TODO fer lo mateix per la vertical. després implementar-ho per tal que ho faci en quadrats més petits

        if(taulaHoritzontal.length>taulaVertical.length){
            taulaComprimida=taulaHoritzontal;
        }else taulaComprimida=taulaVertical;

        return taulaComprimida;
    }



    private static String demanarNomFitxer(Scanner teclat){
        boolean correcte = false;
        String nom = "";
        do {
            try {
                System.out.println("Indiqui el nom del fitxer: ");
                nom = teclat.nextLine();
                correcte = true;
            }
            catch (InputMismatchException error){
                System.out.println("Valor no vàlid");
                teclat.nextLine();
            }
        } while (!correcte);

        return nom;
    }

    private static String demanarOpcio(Scanner teclat){
        boolean correcte = false;
        String opcio="";
        do {
            try {
                System.out.println("La imatge a comprimir és un logotip? ");
                opcio = teclat.nextLine();
                correcte = true;
            }
            catch (InputMismatchException error){
                System.out.println("Valor no vàlid");
                teclat.nextLine();
            }
        } while (!correcte);

        return opcio;
    }
}
