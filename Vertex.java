public class Vertex{
    String previous;
    int path=-1;
    
    Vertex(){

    }

    Vertex(int path, String prev){
        this.previous = prev;
        this.path = path;
    }
    Vertex(int path){
        this.path = path;
    }

}