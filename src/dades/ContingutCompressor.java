package dades;
import excepcions.ValorIncorrecte;

import java.io.Serializable;
import java.util.Arrays;


public class ContingutCompressor implements Serializable {

    static final long serialVersionUID = 1;


    protected int numElements;

    protected String[] contingutComprimit;



    public ContingutCompressor(){
        this.numElements=0;

        this.contingutComprimit= new String[16000000]; // 4k*4k

    }

    // GETTERS I SETTERS



    public void setContingutComprimit(String[] contingutComprimit) {
        this.contingutComprimit = contingutComprimit;
    }

    public void setNumElements(int numElements) {
        this.numElements = numElements;
    }
    public int getNumElements() {
        return numElements;
    }



    public String[] getContingutComprimit() {
        return contingutComprimit;
    }

    //toString
    @Override
    public String toString(){

        StringBuilder text = new StringBuilder();
        int i;
        for (i = 0; i < this.numElements; i++) text.append("\n").append(contingutComprimit[i]);
        return text.toString();
    }

    public ContingutCompressor copia() throws ValorIncorrecte, IndexOutOfBoundsException{
        ContingutCompressor novaLlista = new ContingutCompressor();
        int i;
        for (i = 0; i < this.numElements; i++){
            novaLlista.afegirValor(this.contingutComprimit[i]);
        }
        novaLlista.numElements=this.numElements;
        return novaLlista;
    }

    public void afegirValor(String nouValor) throws IndexOutOfBoundsException{

        if (this.numElements >= this.contingutComprimit.length)  throw new IndexOutOfBoundsException();

        //Si la llista no està plena
        this.contingutComprimit[numElements] = nouValor;    //afegim el producte al final de la llista
        this.numElements++;

    }

}
