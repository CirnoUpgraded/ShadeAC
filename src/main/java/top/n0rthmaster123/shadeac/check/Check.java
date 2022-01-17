package top.n0rthmaster123.shadeac.check;


import top.n0rthmaster123.shadeac.ShadeAC;

public class Check {

    public boolean status,autoban;
    String check,type;
    int vl = 10;
    public CheckCategory category;

    public Check(String check,String type,boolean status){
        this.check = check;
        this.type = type;
        this.status = status;
    }

    public Check(String check,String type,ShadeAC ac,CheckCategory cate){
        this.category = cate;
        this.check = check;
        this.type = type;
        this.status = ac.config.getBoolean( "checks." + check + "." + type + ".status" );
        this.autoban = ac.config.getBoolean( "checks." + check + "." + type + ".punish" );
        this.vl = ac.config.getInt( "checks." + check + "." + type + ".vl" );
    }

    public String getCheck(){
        return check;
    }

    public String getType(){
        return type;
    }

    public boolean getStatus(){
        return status;
    }


    public void setStatus(boolean s){
        status = s;
    }

}
