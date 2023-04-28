public class Confirmation {
    private int confirmation = 0;
    private static Confirmation obj;

    private Confirmation(){}

    public static Confirmation getInstance(){
        if(obj == null){
            obj = new Confirmation();
        }
        return obj;
    }

    public int getConfirmation() {return this.confirmation;}

    public void increment(){
        this.confirmation += 1;
    }
    

}
