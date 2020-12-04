public class Places{
    String name;
    int time=15;
    double rating=0.0;
    int raters;

    Places(String name,int time){
        this.name = name;
        this.time = time;
        this.raters=1;
    }
    Places(String name){
        this.name = name;
        this.raters=0;
   
    }

    public void register(double rate, int tiempo){
        this.raters=this.raters+1;
        this.rating = ((this.rating*(this.raters-1))+rate)/this.raters;
        this.time = ((this.time*(this.raters-1))+tiempo)/this.raters;
    }

}