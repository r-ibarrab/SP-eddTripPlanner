import java.util.*;
public class index{
    public static void main(String[] args){
        Scanner palabras = new Scanner(System.in);
        Scanner numeros = new Scanner(System.in);

        Dijkstra proyecto = new Dijkstra();

        boolean nivel1 = true;

        do{
            System.out.println("Bienvenido a MyTripPlanner, identificate para continuar:");
            System.out.println("1....Usuario");
            System.out.println("2....Administrador");
            System.out.println("3....Salir");
        
            int user = numeros.nextInt();

            switch(user){
                case 1:
                boolean nivel2=true;
                do{
                    System.out.println("MyTripPlanner es una plataforma que te permite rentar vehiculos y calcular el precio y el tiempo que invertiras en tu viaje.\n\n");
                    System.out.println("Por favor selecciona el vehiculo que piensas rentar para tu viaje.");


                }while(nivel2);
                break;
                case 2:
                break;
                case 3:
                nivel1=false;
                break;
                default:
                System.out.println("Opcion no valida");
                break;
            }
              

        }while(nivel1);

        System.out.println("Hasta Luego!!");


    }
}