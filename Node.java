public class Node{

    String nombre;
    int rendimiento;
    int precio;
    int pasajeros;
    Node left;
    Node right;

    Node(String nom,int rend,int prec, int pasa){
        this.nombre= nom;
        this.rendimiento=rend;
        this.precio=prec;
        this.pasajeros=pasa;
        
    }
    Node(){
        
        
    }
}
