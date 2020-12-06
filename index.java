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
                                System.out.println("1...."+selection.root.nombre+" (Pasajeros:"+selection.root.pasajeros+", Precio: $"+selection.root.precio+" por hora, Rendimiento: "+selection.root.rendimiento+" km/l)");
                                System.out.println("2...."+selection.root.right.nombre+" (Pasajeros:"+selection.root.right.pasajeros+", Precio: $"+selection.root.right.precio+" por hora, Rendimiento: "+selection.root.right.rendimiento+" km/l)");
                                System.out.println("3...."+selection.root.left.nombre+" (Pasajeros:"+selection.root.left.pasajeros+", Precio: $"+selection.root.left.precio+" por hora, Rendimiento: "+selection.root.left.rendimiento+" km/l)");
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
                                                        confvehi = false;
                                                        break;
                                                    case 1:
                                                        nivel4 = false;
                                                        nivel2=false;
                                                        boolean nivel6=false;

                                                        do{
                                                           
                                                            boolean nivel5 = false;
                                                            boolean correctCity=false;
                                                            
                                                            String citySelected;
                                                            do{
                                                                System.out.println("Muy bien, es hora de seleccionar tu punto de partida o escribe exit para salir: \n----------------------");
                                                                proyecto.printGraph();
                                                                System.out.print("----------------------\n");
                                                                if(nivel5){
                                                                    System.out.println("Lo sentimos, no tenemos servicio en esa ciudad :(, selecciona otra o escribe exit para salir");
                                                                }
                                                                citySelected=palabras.nextLine();
                                                                if(citySelected.toLowerCase().equals("exit")){
                                                                    System.out.println("dentro");
                                                                    nivel5=false;
                                                                    correctCity=false;
                                                                    nivel6=false;
                                                                    nivel4=true;
                                                                
                                                                    

                                                                }else if(!proyecto.isCityRegistered(citySelected)){
                                                                    nivel5=true;
                                                                }else{
                                                                    nivel5=false;
                                                                    correctCity=true;
                                                                }
    
    
    
                                                            }while(nivel5);
                                                            
    
                                                            if(correctCity){
                                                                System.out.println("\n\nPerfecto!!"+citySelected.substring(0, 1).toUpperCase() + citySelected.substring(1)+" es una excelente elecciónn, es momento de planear hasta donde llegará tu viaje.\nPuedes elegir entre diferentes modalidades que determinaran tu lugar de destino, entre ellas tu presupuesto, tiempo o ciudad de destino.");
                                                                boolean nivel7=false;
                                                                int modality=0;
                                                                double rendimientoVehiculo = userVehicleSelection.rendimiento;
                                                                double precioVehiculo = userVehicleSelection.precio;
                                                                do{
                                                                    if(nivel7){
                                                                        System.out.println("Selecciona la modalidad");
                                                                    }
                                                                    System.out.println("1......Presupuesto");
                                                                    System.out.println("2......Tiempo");
                                                                    System.out.println("3......Destino");
                                                                    System.out.println("4......Cancelar");
                                                                    modality = numeros.nextInt();


                                                                    switch (modality) {
                                                                        case 1:
                                                                            nivel7=true;
                                                                            boolean nivel8=false;
                                                                            double presupuestoViaje=0;
                                                                            do{
                                                                                if(nivel8){
                                                                                    System.out.println("El presupuesto no puede ser negativo");

                                                                                }

                                                                                System.out.println("Ingresa tu presupuesto para este viaje");
                                                                                presupuestoViaje=numeros.nextInt();

                                                                                if(presupuestoViaje<=0){
                                                                                    nivel8 = true;
                                                                                }else{
                                                                                    nivel8=false;
                                                                                    System.out.println("");
                                                                                    //113 velocidad promedio (75% carretera(130km/hr), 25% ciudad(65km/hr))
                                                                                    double kilometrosTotales = (presupuestoViaje/((precioVehiculo/113)+(17.35/rendimientoVehiculo)));
                                                                                    proyecto.getPossibleCities(kilometrosTotales,citySelected,userVehicleSelection);
                                                                                    
                                                                                }


                                                                            }while(nivel8);

                                                                    

                                                                            
                                                                            break;
                                                                        case 2:
                                                                        nivel7=true;
                                                                        boolean nivel9=false;
                                                                        int tiempoViaje=0;
                                                                        do{
                                                                            if(nivel9){
                                                                                System.out.println("El tiempo no puede ser negativo");

                                                                            }

                                                                            System.out.println("Ingresa el tiempo en horas que tienes disponible para este viaje");
                                                                            tiempoViaje=numeros.nextInt();

                                                                            if(tiempoViaje<=0){
                                                                                nivel9 = true;
                                                                            }else{
                                                                                nivel9=false;
                                                                                System.out.println(tiempoViaje);

                                                                                double kilometrosTiempo = tiempoViaje*113;

                                          
                                                                            }


                                                                        }while(nivel9);

                                                                    

                                                                            
                                                                            break;
                                                                        case 3:
                                                                            nivel7=true;
                                                                            boolean nivel10=false;
                                                                            String ciudadViaje="";
                                                                            System.out.println("Ingresa la ciudad de destino o escriba exit para cancelar:");

                                                                            do{
                                                                                if(nivel10){
                                                                                    System.out.println("No se puede seleccionar esa ciudad, selecione otra o escriba exit para cancelar");
    
                                                                                }
    
                                                                                ciudadViaje=palabras.nextLine();
                                                                                
    
                                                                                if(ciudadViaje.toLowerCase().equals("exit")){
                                                                                    nivel10 = false;
                                                                                }else if(!proyecto.isCityRegistered(ciudadViaje)){
                                                                                    nivel10=true;
                                                                                     
                                                                                }else if(ciudadViaje.equals(citySelected)){
                                                                                    nivel10=true;
                                                           
                                                                                }else{
                                                                                    nivel10=false;  
                                                                                    ciudadViaje= ciudadViaje.toLowerCase();
                                                                                    citySelected=citySelected.toLowerCase();
                                                                                    System.out.println(citySelected+" - "+ciudadViaje);
                                                                                    proyecto.getPathToEnd(citySelected,ciudadViaje,userVehicleSelection);
                                                                                    System.out.println("");
                                                                                    System.out.println("Deseas confirmar tu viaje?");

                                                                                    boolean confirmacion = false;
                                                                                    

                                                                                    do{

                                                                                        if(confirmacion){
                                                                                            System.out.println("Opcion no valida");
                                                                                        }

                                                                                        int confirm= numeros.nextInt();

                                                                                        if(confirm > 1 || confirm<0){
                                                                                            confirmacion=true;
                                                                                        }else if(confirm ==1 ){
                                                                                            nivel7=false;
                                                                                            nivel6=false;   
                                                                                            confirmacion=false;                         
                                                                                    
                                                                                        }else{
                                                                                            confirmacion=false;  
                                                                                            nivel7=true;   
                                                                                        }



                                                                                    }while(confirmacion);


    
                                                                                 
                                                                                    
                                                                                    

                                                                                }
    
    
                                                                            }while(nivel10);

                                                                    

                                                                            
                                                                            break;

                                                                        case 4:
                                                                            nivel7=false;
                                                                            nivel6=true;
                                                                            break;
                                                                    
                                                                        default:
                                                                            nivel7 = true;
                                                                            break;
                                                                    }
                              
                                                                    
                                                                    

                                                                    
                                                                }while(nivel7);
    
                                                            }

                                                        }while(nivel6);






                                                        break;
                                                
                                                    default:
                                                        System.out.println("Opcion no disponible");
                                                        confvehi = true;
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
                            


                        }


                        

                    }while(nivel2);
                break;
                case 2:
                    boolean c2nivel1 = false;

                    do{
                       
                        System.out.println("¿Que deseas hacer?");
                        System.out.println("1......Editar ciudad");
                        System.out.println("2......Agregar ciudad");
                        System.out.println("3......Ver mapa");
                        System.out.println("4......Salir");

                        int opadmin = numeros.nextInt();

                       switch (opadmin) {
                           case 1:
                                String ciudadedit = proyecto.preEdit();
                                proyecto.editCity(ciudadedit);
                                c2nivel1=true;
                               
                               break;
                            case 2:
                            proyecto.addCity();
                           c2nivel1=true;

                               
                               break;
                            case 3:
                            proyecto.printMap();
                           c2nivel1=true;

                               
                               break;
                            case 4:
                                c2nivel1=false;
                               break;
                       
                           default:
                           System.out.println("Opcion no disponible");
                           c2nivel1=true;

                               break;
                       }
                        


                    }while(c2nivel1);


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