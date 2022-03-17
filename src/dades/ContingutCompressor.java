package dades;
import excepcions.ValorIncorrecte;

import java.io.Serializable;



public abstract class ContingutCompressor implements Serializable {

    static final long serialVersionUID = 1;


    protected int numElements;

    protected String[] contingutComprimit;



    public ContingutCompressor(int filesTaula, int colmsTaula, String[] contingutComprimit)throws ValorIncorrecte{
        if(filesTaula<0||colmsTaula<0)throw new ValorIncorrecte();
        this.numElements=colmsTaula;

        this.contingutComprimit= new String[16000000]; // 4k*4k

    }
    public ContingutCompressor(){
        this.numElements=1;

        this.contingutComprimit = new String[1];
            contingutComprimit[0] = "Taula buida";

    }

    // GETTERS I SETTERS



    public void setContingutComprimit(String[] contingutComprimit) {
        this.contingutComprimit = contingutComprimit;
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

    public void afegirValor(){

    }

}
