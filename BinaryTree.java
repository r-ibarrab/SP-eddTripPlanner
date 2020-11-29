public class BinaryTree{
    Node root;

    BinaryTree(){
        root=null;
    }
    BinaryTree(int num){
        root=new Node(num);
    }

    public void add(int d){
        Node prepared = new Node(d);
        afteradd(prepared);
    }

    public void afteradd(Node nodin){
        Node aux = root;
        while(true){
            if(nodin.data == aux.data) break;

            if(nodin.data>aux.data){
                if(aux.right==null){
                 
                    aux.right=nodin;
                }else{
                    aux=aux.right;
                }
            }else{
                if(aux.left==null){
                    
                    aux.left=nodin;
                }else{
                    aux=aux.left;
                }

            }
            
        }
    }



}