package top.n0rthmaster123.shadeac.check.checks.movement.motion;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;


public class MotionG extends Checker {
    public MotionG(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        Location from = e.getFrom();
        Location to = e.getTo();
        double accel = Math.abs( e.getDeltaXZ() - e.getLastDeltaXZ() );
        double remainder = ( accel % e.getDeltaXZ() );
        Player p = e.getPlayer();
        String debug = "deltaXZ = " + e.getDeltaXZ() + " accelXZ = " + accel + " " + remainder  ;
        //p.sendMessage( debug );
        if( remainder > 0.435 ){
            //p.sendMessage( "maybe you hacking (1)" );
            fail( p , "remainder = " + remainder );
        }
        //beta
    }
}
