import java.util.*;
public class Cities{

    String name;
    Double rating=0.0;
    int visitors=0;
    ArrayList<Places> InterestPoints= new ArrayList<Places>();

    Cities(String name){
        this.name=name;
    }
    Cities(String name, ArrayList<Places> lugares){
        this.name=name;
        this.InterestPoints=lugares;
        
    }

    public void registerVisit(String point,double rating,int time){
        for(int i=0;i<this.InterestPoints.size();i++){
            if(this.InterestPoints.get(i).name==point){
                this.InterestPoints.get(i).register(rating,time);
            }
            
        }
   
    }
    public void registerCityVisit(double rate){
        this.visitors=this.visitors+1;
        this.rating=((this.rating*(this.visitors-1))+rate)/this.visitors;
    }

    public void addPlace(String place){
        Scanner sc = new Scanner(System.in);
        if(!this.isContained(place)){
            boolean nivel1=false;
            System.out.println("Cuanto tiempo estuviste en "+place+"?");
            int time=0;
            do{
                if(nivel1){
                    System.out.println("Hubo un error con la cantidad que introdujiste, vuelve a ingresar el tiempo que estuviste en "+place);
                }
                 time = sc.nextInt();
                if(time <= 0){
                    nivel1=true;
                }

            }while(nivel1);

            this.InterestPoints.add(new Places(place,time));

        }else{
            System.out.println("Ese lugar ya ha sido registrado previamente");
        }

    }

    public boolean isContained(String place){

        boolean exists = false;

        for(int c=0;c<this.InterestPoints.size();c++){
            if(this.InterestPoints.get(c).name == place){
                exists=true;
                break;
            }

        }

       
        return exists;
    }
}