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
                    Node userVehicleSelection= new Node();
                    System.out.println("\nMyTripPlanner es una plataforma que te permite rentar vehiculos y calcular el precio y el tiempo que invertiras en tu viaje.\n\n");
                    do{
                        System.out.println("Por favor selecciona uno de nuestros vehiculos de renta para tu viaje.");
                        ArrayList<BinaryTree> vehiculos = proyecto.getVehicles();
                        int cont=0;
                        for(BinaryTree marca: vehiculos){
                            System.out.println((cont+1)+"......"+marca.root.nombre);
                            cont++;
                        }

                        System.out.println((cont+1)+"......Cancelar");

                        boolean nivel3=false;
                        int selectedVehicle;
                        do{
                            if(nivel3){
                                System.out.println("Opcion fuera de rango");
                            }
                            selectedVehicle= numeros.nextInt();
                            if(selectedVehicle-1 == cont){
                                nivel3=false;
                                nivel2=false;
                            }else if(selectedVehicle<=0 || selectedVehicle-1>cont){
                                nivel3=true;
                            }else{
                                nivel3=false;
                            }

                        }while(nivel3);

                        if(nivel2){
                            BinaryTree selection = vehiculos.get((selectedVehicle-1));
                            
                            
                           
                            boolean nivel4=false;
                            
                            int opt=0;

                            do{
                                if(nivel4){
                                    System.out.println("Vuelve a seleccionar una opción:");
                                }
                                System.out.println("1...."+selection.root.nombre+" (Pasajeros:"+selection.root.pasajeros+", Precio: $"+selection.root.precio+" por dia, Rendimiento: "+selection.root.rendimiento+" km/l)");
                                System.out.println("2...."+selection.root.right.nombre+" (Pasajeros:"+selection.root.right.pasajeros+", Precio: $"+selection.root.right.precio+" por dia, Rendimiento: "+selection.root.right.rendimiento+" km/l)");
                                System.out.println("3...."+selection.root.left.nombre+" (Pasajeros:"+selection.root.left.pasajeros+", Precio: $"+selection.root.left.precio+" por dia, Rendimiento: "+selection.root.left.rendimiento+" km/l)");
                                System.out.println("4....Cancelar");
                                
                                
                                opt=numeros.nextInt();
                                if(opt!=4){
                                    if(opt<0 || opt>3){
                                        nivel4=true;
                                    }else{
                                        switch (opt) {
                                            case 1:
                                                userVehicleSelection=selection.root;
                                            nivel4=false;
    
                                                
                                                break;
                                            case 2:
                                                userVehicleSelection=selection.root.right;
                                            nivel4=false;
    
    
                                                break;
                                            case 3:
                                                userVehicleSelection=selection.root.left;
                                            nivel4=false;
    
    
                                                break;
                                        
                                            default:
                                            nivel4=true;
                                                break;
                                        }
                                        if(!nivel4){
                                            System.out.println("El vehiculo seleccionado es: "+userVehicleSelection.nombre+", Escribe 1 para confirmar y 0 para cambiar de vehiculo");
                                            boolean confvehi = false;
                                            do{
                                                int nono = numeros.nextInt();
                                                switch (nono) {
                                                    case 0:
                                                        nivel4 = true;
                                                        break;
                                                    case 1:
                                                        nivel4 = false;
                                                        nivel2=false;
                                                        break;
                                                
                                                    default:
                                                        System.out.println("Opcion no disponible");
                                                        break;
                                                }
        
                                            }while(confvehi);
    
                                        }
    
                                    }


                                }else{
                                    nivel4=false;
                                    nivel2=true;
                                }
                                
                                

                            }while(nivel4);
                            
                            System.out.println("bye");


                        }


                        

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