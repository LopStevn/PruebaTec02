
package svlt20232108.entidadesdenegocio;

/*
 * @author MINEDUCYT
 */
public class Book {
    
    private int Id;
    private String Titulo;
    private String Autor;
    private int AñoPubli;
    private int top_aux;

    public Book() {
    }

    public Book(int Id, String Titulo, String Autor, int AñoPubli) {
        this.Id = Id;
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.AñoPubli = AñoPubli;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getAñoPubli() {
        return AñoPubli;
    }

    public void setAñoPubli(int AñoPubli) {
        this.AñoPubli = AñoPubli;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    
    
}
