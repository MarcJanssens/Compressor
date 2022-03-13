import java.io.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/*

Writer.write("Files in Java are seriously good!!");

*/

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void comprimirImatge() {
    }

    private static boolean comprimirLogotip(String nomFitxer) throws FileNotFoundException, InputMismatchException {

        boolean correcte = false;
        File Obj = new File(nomFitxer);
        Scanner Document = new Scanner(Obj);
        int[][] imatge = processarImatge(Document);


        return correcte;

    }
    private static int[][] processarImatge(Scanner Document) {
        String format = Document.nextLine();
        int[][] taula = new int[0][1];
        if (format.equals("P2")) {
            Document.nextLine();
            String resolucio = Document.nextLine();
            int col = Integer.parseInt(resolucio.split(" ")[0]);
            int fil = Integer.parseInt(resolucio.split(" ")[1]);
            int[][] taulaPas = new int[fil][col];
            for (int i = 0; i < fil; i++) {
                for (int j = 0; j < col; j++) {
                    taulaPas[i][j] = Integer.parseInt(Document.nextLine());
                }
            }
            taula=taulaPas;
        }
        return taula;
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
