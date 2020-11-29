public class Edge {
    String pointer;
    int distance;
    Edge(){}

    Edge(String pointer, int distance){
        this.pointer = pointer;
        this.distance = distance;
    }

    public String getPointer(){
        return this.pointer;
    }
   
    public int getDistance(){
        return this.distance;
    }
   

}
