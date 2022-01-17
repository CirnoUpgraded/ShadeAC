package top.n0rthmaster123.shadeac.check;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Checker implements Listener {

    public Check check;

    public Checker(Check check){
        this.check = check;
    }

    public boolean fail(Player p,String info){

        if( !CheckUtil.getCheck( check.getCheck() , check.getType() ).getStatus() ){
            return false;
        }
        ShadeUtil.setUpPlayerViolation( p );
        ShadeUtil.addVL( null ,p );
        ShadeUtil.addVL( check,p );

        String alert = AlertThemeUtil.getCurrentTheme().getReplacedMessage( p , check , ShadeUtil.getVL( null , p ) ,ShadeUtil.getVL( check , p ), ( info != "" ? info : "Nothing") );
        for( Player px : Bukkit.getOnlinePlayers() ){
            if( ShadeUtil.isAlertEnabled( p ) && p.hasPermission(  "shade.alerts" ) ){
                //p.sendMessage( ShadeAC.prefix + "§c " + player.getName() + "§7 failed§6 " + check.getCheck() + "§8(§eVL§a " + ShadeUtil.getVL( check , player ) + "§8/§a" + ShadeUtil.getVL( null , player ) + "§8) (§eType:§a " + check.getType() + "§8) (§eInfo:§a " + ( shade.verbosed ? shade.verbose : "Nothing") + " §8)"  );
                px.sendMessage( alert );
            }
        }
        return true;
    }

    public boolean bypass(Player p){
        return  p.hasPermission( "shade.bypass" ) && ShadeUtil.bypass && ShadeUtil.status;
    }

}
