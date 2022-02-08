package top.n0rthmaster123.shadeac.check.checks.movement.motion;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.flight.FlightA;

public class MotionF extends Checker {
    public MotionF(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        Location from = e.getFrom();
        Location to = e.getTo();
        double accelYaw = Math.abs( to.getYaw() - from.getYaw() );
        double accelXZ = Math.abs( e.getDeltaXZ() - e.getLastDeltaXZ() );
        String info = "Yaw = " + accelYaw + " XZ = " + accelXZ;
        if( accelYaw > 1 && accelXZ > 0.9 ){
            //e.getPlayer().sendMessage( info );
            fail( e.getPlayer(),  info );
        }
//        if( FlightA.isNearBlockAction( e.getPlayer() , 500 ) ){
//            e.getPlayer().sendMessage( info );
//        }
    }
}
