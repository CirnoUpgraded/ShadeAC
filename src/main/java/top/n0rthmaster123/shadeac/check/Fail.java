package top.n0rthmaster123.shadeac.check;


public class Fail {

    public String info;
    public Check check;
    public boolean failed = false;
    public boolean vbed = false;

    public Fail(String info,Check check){
        this.info = info;
        this.check = check;
    }

    public Fail clone(){
        return new Fail( info , check);
    }

}
