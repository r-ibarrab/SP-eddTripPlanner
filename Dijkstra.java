import java.util.*;

public class Dijkstra{
    static HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
    static ArrayList<BinaryTree> vehiculos = new ArrayList<BinaryTree>();
    static HashMap<String,ArrayList<Places>> citiesPoints = new HashMap<String,ArrayList<Places>>();

    Dijkstra(){
        graph.put("mexicali",new ArrayList<Edge>(Arrays.asList(new Edge("san felipe",197),new Edge("puerto penasco",297), new Edge("tecate",130),new Edge("san luis",80))));
        graph.put("san felipe", new ArrayList<Edge>(Arrays.asList(new Edge("mexicali",197), new Edge("ensenada",246))));
        graph.put("ensenada",new ArrayList<Edge>(Arrays.asList(new Edge("tecate",108),new Edge("tijuana",106), new Edge("san felipe",246), new Edge("rosarito",88))));
        graph.put("tecate",new ArrayList<Edge>(Arrays.asList(new Edge("mexicali",130), new Edge("ensenada",108), new Edge("tijuana",54))));
        graph.put("rosarito",new ArrayList<Edge>(Arrays.asList(new Edge("ensenada",88),new Edge("tijuana",21))));
        graph.put("san luis",new ArrayList<Edge>(Arrays.asList(new Edge("puerto penasco",238), new Edge("mexicali",80),new Edge("caborca",351))));
        graph.put("caborca",new ArrayList<Edge>(Arrays.asList(new Edge("san luis",351),new Edge("hermosillo",282), new Edge("puerto penasco",178))));
        graph.put("puerto penasco",new ArrayList<Edge>(Arrays.asList(new Edge("san luis",238),new Edge("mexicali",297), new Edge("caborca",178))));
        graph.put("hermosillo",new ArrayList<Edge>(Arrays.asList(new Edge("caborca",282))));
        graph.put("tijuana",new ArrayList<Edge>(Arrays.asList(new Edge("tecate",54), new Edge("rosarito",21))));

        citiesPoints.put("mexicali", new ArrayList<Places>(Arrays.asList(new Places("Bosque de la ciudad"),new Places("Sol del nino"),new Places("Museo UABC"))));
        citiesPoints.put("san felipe", new ArrayList<Places>(Arrays.asList(new Places("Aventuras del desierto (Tour)"),new Places("Playas de San Felipe"))));
        citiesPoints.put("ensenada", new ArrayList<Places>(Arrays.asList(new Places("Las canadas"),new Places("Vinedos"),new Places("La Bufadora"),new Places("El Parque de la Bandera"))));
        citiesPoints.put("tecate", new ArrayList<Places>(Arrays.asList(new Places("Cerveceria Tecate"),new Places("Canada del Sol"))));
        citiesPoints.put("rosarito", new ArrayList<Places>(Arrays.asList(new Places("Bahia Los Angeles"),new Places("Tour Islas Coronado"))));
        citiesPoints.put("san luis", new ArrayList<Places>());
        citiesPoints.put("caborca", new ArrayList<Places>(Arrays.asList(new Places("Pueblo Viejo"),new Places("Casa de la cultura"),new Places("Casa de artesanias"))));
        citiesPoints.put("puerto penasco", new ArrayList<Places>(Arrays.asList(new Places("Cholla Mall"),new Places("Mercado de la sirena"))));
        citiesPoints.put("hermosillo", new ArrayList<Places>(Arrays.asList(new Places("Parque la Ruina"), new Places("Estadio corona"))));
        citiesPoints.put("tijuana", new ArrayList<Places>(Arrays.asList(new Places("Mercado de pulgas"),new Places("Centro cultural Tijuana"),new Places("Estadio Caliente Xoloitzcuintles"), new Places("Mercado el Popo"))));


        Node kiaDeluxe= new Node("Kia Forte ",16,150, 5);
        Node kiaSport= new Node("Kia Forte Sport",9,210, 5);
        Node kiaLite= new Node("Kia Forte Deluxe",12,380, 5);
        kiaDeluxe.left = kiaSport;
        kiaDeluxe.right = kiaLite;
        BinaryTree kia = new BinaryTree(kiaDeluxe);

        Node toyota1= new Node("Toyora Camry",14,225, 5);
        Node toyota2= new Node("Toyora Camry Sport",16,300, 5);
        Node toyota3= new Node("Toyora Camry Deluxe",9,430, 5);
        toyota1.left = toyota2;
        toyota1.right = toyota3;
        BinaryTree toyota = new BinaryTree(toyota1);

        Node audi1= new Node("Audi A1 ",11,450, 4);
        Node audi2= new Node("Audi A1 Sport",8,540, 4);
        Node audi3= new Node("Audi A1 Deluxe",14,710, 4);
        audi1.left = audi2;
        audi1.right = audi3;
        BinaryTree audi = new BinaryTree(audi1);
        

        vehiculos.add(kia);
        vehiculos.add(audi);
        vehiculos.add(toyota);
       
        
        

    }
    

    public static ArrayList<BinaryTree> getVehicles(){
        return vehiculos;
    }

    public static void addCity(){
        Scanner sc = new Scanner(System.in);
        String ciudad="";

        boolean error = false;
        boolean exit=false;
        System.out.println("Escriba la ciudad que desea registar:");
        do{
            if(error){
                System.out.println("La ciudad ya esta registrada, ingresa otra ciudad ciudad o escribe exit para salir");
            }

            ciudad = sc.nextLine();

            if(isCityRegistered(ciudad)){
                error = true;
            }else if(ciudad.toLowerCase().equals("exit")){
                exit=true;
                error=true;
            }else{
                exit=false;
                error=false;
            }

        }while(error);

        if(!exit){
            boolean finished = false;
            System.out.println("\n-Ingrese las ciudades conectadas con "+ ciudad +".\n-Registre una ciudad, apriete enter para pasar a la siguiente.\nSi desea salir, escriba EXIT.");
            String op="";
            ArrayList<String> closeCities = new ArrayList<String>();
            do{
                
                System.out.print("> ");
                op = sc.nextLine();

                if(op.toLowerCase().equals("exit")){
                    finished =true;
                }else{

                    if(op.toLowerCase().equals(ciudad)){
                        System.out.println("No puedes asociarle la ciudad madre");
                    }else if(!isCityRegistered(op)){
                        System.out.println(op+" no había sido registrada previamente, no te olvides de agregarle sus ciudades vecinas!");
                        closeCities.add(op);

                    }else{
                        closeCities.add(op);
                    }
                    
                    
                }

    
    
            }while(!finished);

            
            if(!closeCities.isEmpty()){
                ArrayList<Edge> relations = new ArrayList<Edge>();
           
            for(int c=0;c<closeCities.size();c++){
                System.out.println("¿Cual es la distancia en kilometros de "+ciudad+" a "+closeCities.get(c)+"?");
                int distance = sc.nextInt();
                if(distance<0){
                    System.out.println("La distancia no puede ser menor a 0");
                    c--;
                }else{
                    relations.add(new Edge(closeCities.get(c),distance));
                }
            }
            addCityRelations(ciudad,relations);

            for(Edge obj: relations){
                if(graph.get(obj.pointer)==null){
                    addCityRelations(obj.pointer,new ArrayList<Edge>(Arrays.asList(new Edge(ciudad,obj.distance))));
                }else{
                    ArrayList<Edge>smn = graph.get(obj.pointer);
                    smn.add(new Edge(ciudad,obj.distance));
                    graph.put(obj.pointer,smn);

                }
            }
            }
            // for(Edge obj: relations){
            //     List<Edge> rel = graph.get(obj.pointer);
            //     System.out.println(rel);
            //     System.out.println(obj);
            //     System.out.println(obj.pointer);
            //     // rel.add(new Edge(ciudad,obj.distance));
            //     // graph.put(obj.pointer,rel);
            // }

        }

    }

    public static void editCity(String city){
        Scanner sc = new Scanner(System.in);
        Scanner datos = new Scanner(System.in);

        if(isCityRegistered(city)){
            boolean exit = false;
            int op;
            do{
                System.out.println("¿Que deseas hacer?");
                System.out.println("1...... Agregar/Modificar relacion");
                System.out.println("2...... Eliminar relacion");
                System.out.println("3...... Cancelar");
                op = sc.nextInt();

                switch (op) {
                    case 1:
                        boolean finished = false;
                        System.out.println("\n-Ingrese las ciudades con las que quiere conectar a "+ city +".\n-Registre una ciudad, apriete enter para pasar a la siguiente.\nSi desea salir, escriba EXIT.");
                        String op2="";
                        ArrayList<String> closeCities = new ArrayList<String>();
                        do{
                            System.out.print("> ");
                            op2 = datos.nextLine();

                            if(op2.toLowerCase().equals("exit")){
                                finished =true;
                            }else{

                                if(op2.toLowerCase().equals(city)){
                                    System.out.println("No puedes asociarle la ciudad madre");
                                }else if(!isCityRegistered(op2)){
                                    System.out.println(op2+" no había sido registrada previamente, no te olvides de agregarle sus ciudades vecinas!");
                                    closeCities.add(op2);

                                }else{
                                    closeCities.add(op2);
                                }
                                    
                            }

                        }while(!finished);
                
                      if(!closeCities.isEmpty()){
                          System.out.println("vacio:"+closeCities.isEmpty());
                        ArrayList<Edge> relations = new ArrayList<Edge>();
                    
                        for(int c=0;c<closeCities.size();c++){
                            System.out.println("¿Cual es la distancia en kilometros de "+city+" a "+closeCities.get(c)+"?");
                            int distance = datos.nextInt();
                            if(distance<0){
                                System.out.println("La distancia no puede ser menor a 0");
                                c--;
                            }else{
                                relations.add(new Edge(closeCities.get(c),distance));
                            }
                        }
                    
                        ArrayList<Edge> prevCities = graph.get(city);
                        for(Edge obj: relations){
                           
                            
                            
                            if(graph.get(obj.pointer) == null){
                                addCityRelations(obj.pointer,new ArrayList<Edge>(Arrays.asList(new Edge(city,obj.distance))));
                                prevCities.add(new Edge(obj.pointer,obj.distance));

                            }else{
                                ArrayList<Edge>smn = graph.get(obj.pointer);
                                boolean exists = false;

                                for(Edge edges:smn){
                                 
                                    if(edges.pointer.equals(city)){
                                        smn.set(smn.indexOf(edges),new Edge(city,obj.distance));
                                        exists = true;
                               

                                    }
                                }
                                if(exists){
                                    for(Edge edges:prevCities){
                        
                                        if(edges.pointer.equals(obj.pointer)){
                                        prevCities.set(prevCities.indexOf(edges),new Edge(obj.pointer,obj.distance));
                                    

                                            
                                        }
                                    }

                                }else{
                                    prevCities.add(new Edge(obj.pointer,obj.distance));
                                    smn.add(new Edge(city,obj.distance));
                                }


                                graph.put(obj.pointer,smn);

                            }
                            graph.put(city,prevCities);

                        }
                      }


                        break;
                    case 2:

                    ArrayList<Edge> cityRels = graph.get(city);
                    int contador = 0;
                    for(Edge edgin: cityRels){
                        System.out.println((contador+1)+" - ("+edgin.pointer+","+edgin.distance+")");
                        contador++;

                    }
                    int opcion=0;
                    boolean mistake=false;
                    do{
                        if(mistake){
                            System.out.println("El numero esta fuera del rango");
                        }
                        System.out.println("¿Que ciudad deseas eliminar? (Introduce el numero correspondiente):");
                        opcion = sc.nextInt();

                        if(opcion>=0 && opcion<=contador){
                            mistake=false;
                        }else{
                            mistake = true;
                        }
                        
                    }while(mistake);

                    int res = opcion-1;

                    Edge eliminacion = cityRels.get(res);
                    ArrayList<Edge> listelim= graph.get(eliminacion.pointer);
                    Edge eliminatededge = new Edge();
                    for(Edge edgin: listelim){
                        if(edgin.pointer.equals(city)){
                            eliminatededge = edgin;
                        }
                    }
                    listelim.remove(eliminatededge);

                    if(listelim.isEmpty()){
                        graph.remove(eliminacion.pointer);
                    }else{
                        graph.put(eliminacion.pointer,listelim);

                    }


                    cityRels.remove(res);
                    if(cityRels.isEmpty()){
                        graph.remove(city);
                    }else{
                        graph.put(city,cityRels);

                    }



                        break;
                    case 3:
                      exit=true;

                        break;
                    default:


                        break;
                }

            }while(!exit);
        }
    }

  


    public static boolean isCityRegistered(String city){
        boolean exists = false;
        for(Map.Entry<String, ArrayList<Edge>> cities: graph.entrySet()){


            if(cities.getKey().equals(city.toLowerCase())){
                exists = true;
            }
           

        }
        return exists;

    }

  
    public static void addCityRelations(String name, ArrayList<Edge> relations){
        graph.put(name,relations);
    }

    // public static void getPath(String start,String end){
        
    //      HashMap<String, Vertex> vertexs = new HashMap<String, Vertex>();
    //     ArrayList<String> registeredCities = new ArrayList<String>();
    //     for(Map.Entry<String, ArrayList<Edge>> cities: graph.entrySet()){
    //         registeredCities.add(cities.getKey());

    //     }

    //     for(String city: registeredCities){
    //         if(city ==start){
    //             vertexs.put(city,new Vertex(0));
    //         }else{
    //             vertexs.put(city,new Vertex());
    //         }
    //     }
    // }


    public static Stack<Vertex> dijsktra(HashMap<String, ArrayList<Edge>> city, String start, String end) {
        HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
        ArrayList<Vertex> unVisited = new ArrayList<Vertex>();
    //     System.out.println("-----------");

    //    System.out.println(start+" "+end);
    //    System.out.println("-----------");

        int contador = 0;
        int index=0;
        int indexf=0;
        for (String key : city.keySet()) {
           
            unVisited.add(new Vertex(key));
            vertices.put(key, unVisited.get(unVisited.size() - 1));
            if(key.equals(start)){
                index = contador;
            }
            if(key.equals(end)){
                indexf=contador;
            }
            contador++;
        }
        unVisited.get(index).pathWeigth = 0;
        // System.out.println("-----------");


        while (unVisited.size() > 0) {
            bubble(unVisited);
            // System.out.println(unVisited.get(0).value);
            relaxation(unVisited.get(0).value, vertices, city);
            unVisited.remove(0);
        }
        // System.out.println("-----------");


        Stack<Vertex> stack = new Stack<Vertex>();

       
        // System.out.println("-----------");
        

        // System.out.println(vertices.get(end).value);

        // for(Map.Entry<String,Vertex> citiesDistance: vertices.entrySet()){

        //     Vertex aux = vertices.get(citiesDistance.getKey());

        //     System.out.println(citiesDistance.getKey() +" - "+ aux.value+" - "+aux.lastVertex+"-"+aux.ruta+"-"+aux.pathWeigth);
                
        // }
        // System.out.println("-----------");


        Vertex actual = vertices.get(end);
        // System.out.println( vertices.get(end).value);
        // System.out.println("-----------");

        while(true){
            stack.add(actual);

            if (actual.value.equals(start)) {
                break;
            }
            actual = vertices.get(actual.lastVertex);
        }

        return stack;
    }
    public static HashMap<String, Vertex> pathTable(HashMap<String, ArrayList<Edge>> city, String start) {
        HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
        ArrayList<Vertex> unVisited = new ArrayList<Vertex>();
        // System.out.println(city.keySet());
        int contador = 0;
        int index=0;
        
        for (String key : city.keySet()) {
           
            unVisited.add(new Vertex(key));
            vertices.put(key, unVisited.get(unVisited.size() - 1));
            if(key.equals(start)){
                index = contador;
            }
            contador++;
        }
        unVisited.get(index).pathWeigth = 0;

        while (unVisited.size() > 0) {
            bubble(unVisited);
            // System.out.println(unVisited.get(0).value);
            relaxation(unVisited.get(0).value, vertices, city);
            unVisited.remove(0);
        }


        return vertices;
       
       
    }

    public static void bubble(ArrayList<Vertex> vertices) {
        for (int i = 2; i < vertices.size(); i++) {
            Vertex auxiliar;

            for (int j = 0; j < vertices.size() - 1; j++) {
                if (vertices.get(j + 1).pathWeigth == null) {
                    continue;
                }
                if (vertices.get(j).pathWeigth == null || vertices.get(j).pathWeigth > vertices.get(j + 1).pathWeigth) {
                    auxiliar = vertices.get(j);
                    vertices.set(j, vertices.get(j + 1));
                    vertices.set(j + 1, auxiliar);
                }
            }
        }
    }

    public static void relaxation(String v, HashMap<String, Vertex> vertices, HashMap<String, ArrayList<Edge>> cities) {
        for (Edge edge : cities.get(v)) {
            if (vertices.get(edge.pointer).pathWeigth == null
                    || vertices.get(v).pathWeigth + edge.distance < vertices.get(edge.pointer).pathWeigth) {
                vertices.get(edge.pointer).pathWeigth = vertices.get(v).pathWeigth + edge.distance;
                vertices.get(edge.pointer).lastVertex = v;
                vertices.get(edge.pointer).ruta = edge.ruta;
            }
        }
    }
    public static void printMap(){
        System.out.println("");
        System.out.println("");

           for(Map.Entry<String, ArrayList<Edge>> cities: graph.entrySet()){

            ArrayList<Edge> neighbours = cities.getValue();
            System.out.print(cities.getKey()+": ");
            for(Edge city: neighbours){
                System.out.print("("+ city.getPointer() +","+city.getDistance()+") ");
                
            }
        System.out.println("");
        System.out.println("");



        }


    }

    public static void printGraph(){
        // for(Map.Entry<String, ArrayList<Edge>> cities: graph.entrySet()){

        //     ArrayList<Edge> neighbours = cities.getValue();
        //     System.out.print(cities.getKey()+": ");
        //     for(Edge city: neighbours){
        //         System.out.print("("+ city.getPointer() +","+city.getDistance()+") ");
                
        //     }
        //     System.out.println("");


        // }
        for(Map.Entry<String, ArrayList<Edge>> cities: graph.entrySet()){
            System.out.println(cities.getKey().substring(0, 1).toUpperCase() + cities.getKey().substring(1));
        }
    
    }

    public static String preEdit(){
        Scanner sc = new Scanner(System.in);
        String ciudad="";

        boolean error = false;
        boolean exit=false;
        System.out.println("Escriba la ciudad que desea editar:");
        do{
            if(error){
                System.out.println("La ciudad no esta registrada, ingresa otra ciudad o escribe exit para salir");
            }

            ciudad = sc.nextLine();

            if(!isCityRegistered(ciudad)){
                error = true;
            }else if(ciudad.toLowerCase().equals("exit")){
                exit=true;
                error=true;
            }else{
                exit=false;
                error=false;
            }

        }while(error);

        return ciudad.toLowerCase();


    }
    // public static ArrayList<Edge> getPresupuesto(Stack<Vertex> path,int presupuesto){
        
    //     // path.stream().forEach((ciudad) -> 

    //     // System.out.println(S.value)
        
    //     // );


    // }

    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Double> > list = 
               new LinkedList<Map.Entry<String, Double> >(hm.entrySet()); 
  
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
            public int compare(Map.Entry<String, Double> o1,  
                               Map.Entry<String, Double> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          

        HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
        for (Map.Entry<String, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 


    public static void getPathToEnd(String startCity, String endCity,Node vehiculo){
        Stack<Vertex> stack ;
        stack = dijsktra(graph,startCity,endCity);
        double kms =0.0;
        while(!stack.isEmpty()){
            Vertex aux=stack.pop();
            System.out.print(" "+aux.value);
            if(!stack.isEmpty()){
                System.out.print("->");
            }else{
                kms =aux.pathWeigth;
            }
        }

        double tiempoHoras = kms/113;
        double precioFinal = (vehiculo.precio*(kms/113))+((kms/vehiculo.rendimiento)*17.35);

        System.out.println("\nKilometros finales: "+kms+"\nTiempo total del viaje: "+String.format("%.2f",tiempoHoras)+"\nPrecio total del viaje:"+String.format("%.2f", precioFinal));



    }

    public static void getPossibleCities(Double kiloms, String selectedCity, Node car){

            HashMap<String, Vertex> auxi = pathTable(graph,selectedCity.toLowerCase());
            HashMap<String, Double> possibleCities = new HashMap<String,Double>();
            
            for(Map.Entry<String,Vertex> citiesDistance: auxi.entrySet()){

                Vertex aux = auxi.get(citiesDistance.getKey());

                if(aux.pathWeigth<=kiloms){
                    possibleCities.put(aux.value,(kiloms-aux.pathWeigth));

                }

                // System.out.println(aux.value+" - "+aux.lastVertex+"-"+aux.pathWeigth);
                    
            }

            possibleCities = sortByValue(possibleCities);
            if(possibleCities.size()==1){
                System.out.println("El presupuesto es insuficiente para viajara a alguna ciudad :(");

            }else{
                System.out.println("Estas son las ciudades que puedes visitar con tu presupuesto");

                for(Map.Entry<String,Double> ayuda: possibleCities.entrySet()){
                
                      if(!ayuda.getKey().equals(selectedCity)){
                        if(ayuda.getValue()<=kiloms){
                            double kmLeft = kiloms-ayuda.getValue();
                            double change = ((kmLeft/113)*(car.precio))+((kiloms/car.rendimiento)*(17.35));

                            System.out.println(ayuda.getKey()+", Con "+String.format("%.2f",change)+" pesos de sobra");
                        }
                      }
                
                    }

                    // System.out.println(aux.value+" - "+aux.lastVertex+"-"+aux.pathWeigth);
            } 
        }
}