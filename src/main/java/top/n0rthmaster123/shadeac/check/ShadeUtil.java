package top.n0rthmaster123.shadeac.check;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ShadeUtil {

    static HashMap<Player,Boolean> alert_status = new HashMap<>();
    static HashMap<Player,Integer> violations_all = new HashMap<>();
    static HashMap<Player,HashMap<String,Integer>> violations = new HashMap<>();
    static HashMap<Player, Location> lastLocation = new HashMap<>();
    static HashMap<Player,Integer> airtick = new HashMap<>();
    static HashMap<Player,Double> lastDeltaY = new HashMap<>();
    static HashMap<Player,Double> lastDeltaX = new HashMap<>();
    static HashMap<Player,Double> lastDeltaZ = new HashMap<>();
    static HashMap<Player,Double> fallDistance = new HashMap<>();


    public static boolean bypass = false;
    public static boolean status = true;

    public static boolean isAlertEnabled(Player p){
        //return true;
        return alert_status.getOrDefault( p , false );
    }

    public static void setAlerEnabled(Player p,boolean status){
        alert_status.put( p , status );
    }

    public static void setUpPlayerViolation(Player p){
        if( violations.get( p ) != null && violations_all.get( p ) != null )return;
        HashMap<String,Integer> base = new HashMap<>();
        for( Checker check : CheckUtil.checks ){
            base.put( check.check.getCheck() + "." + check.check.getType() , 0 );
        }
        violations_all.put( p , 0 );
        violations.put(p, base);
    }
    public static void resetPlayerViolation(Player p){
        HashMap<String,Integer> base = new HashMap<>();
        for( Checker check : CheckUtil.checks ){
            base.put( check.check.getCheck() + "." + check.check.getType() , 0 );
        }
        violations_all.put( p , 0 );
        violations.put(p, base);
    }

    public static void addVL(Check check,Player p){
        if( check == null ){
            violations_all.put( p , getVL( null , p ) + 1 );
            return;
        }
        violations.get( p ).put( check.getCheck() + "." + check.getType() , getVL( check , p ) + 1 );
    }

    public static void addVL(Check check,Player p,int vl){
        if( check == null ){
            violations_all.put( p , vl );
            return;
        }
        violations.get( p ).put( check.getCheck() + "." + check.getType() , vl );
    }

    public static int getVL(Check check,Player p){
        if( check == null ){
            return violations_all.getOrDefault( p , 0 );
        }
        return violations.get( p ).getOrDefault( check.getCheck() + "." + check.getType() , 0 );
    }

    public static String getBukkitVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static int getPing(Player p) {
        try {
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + getBukkitVersion() + ".entity.CraftPlayer");
            Object handle = craftPlayer.getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
            Field pingField = handle.getClass().getDeclaredField("ping");
            pingField.setAccessible(true);
            return pingField.getInt(handle);
        } catch (IllegalAccessException|ClassNotFoundException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException|NoSuchFieldException e) {
            return -1;
        }
    }


}
