package top.n0rthmaster123.shadeac.check.checks.movement.motion;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;


public class MotionI extends Checker {
    public MotionI(Check check) {
        super( check );
    }

    HashMap<Player,Integer> buffers = new HashMap<>();

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        Player p = e.getPlayer();
        if( e.isWaterAround( e.getTo() , 1 ,1  ) )return;
        int buffer = 0;
        if( buffers.get( p ) != null ){
            buffer = buffers.get( p );
        }
        double gcd = getGcd( e.getDeltaXZ() , e.getLastDeltaXZ() );
        if( gcd > 0.4 ){
            if( gcd > 0.9 ){
                fail( p , "gcdXZ : " + gcd + " > 0.9" );
                return;
            }
            if(++buffer > 2){
                fail( p , "gcdXZ : " + gcd + " > 0.4 buffer =  " + buffer );
            }
        } else { buffer = 0; }
        buffers.put( p , buffer );
    }


    public static double getGcd(double a,double b) {
        if (a < b)
            return getGcd(b, a);
        if (Math.abs(b) < 0.001D)
            return a;
        return getGcd(b, a - Math.floor(a / b) * b);
    }

}
