public class Vertex {
    String value;
    Integer pathWeigth = null;
    String lastVertex = null;
    boolean visited = false;
    String ruta;

    public Vertex(){}
    public Vertex(String value){
        this.value = value;
    }
    public Vertex(String value, int pathWeigth){
        this.pathWeigth = pathWeigth;
        this.value = value;
    }
}