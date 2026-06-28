package modelos;

public class Libro implements Comparable<Libro> {
    private int codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private String estado;      //"Disponible", "Solicitado" , "Devuelto" , "No Disponible"
    private int stock;
    public Libro(int codigo, String titulo, String autor, String categoria, int anio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.estado = "Disponible";
        this.stock = 1; 
    }
    public Libro(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro otro = (Libro) obj;
        return this.codigo == otro.codigo;
    }
    @Override
    public int compareTo(Libro otro){
        return Integer.compare(this.codigo, otro.codigo);
    }

    @Override
    public String toString(){
        return String.format("ID: %d | %s - %s [%s] (%d) -> %s",
            codigo, titulo, autor, categoria, anio, estado
        );
    }
}
