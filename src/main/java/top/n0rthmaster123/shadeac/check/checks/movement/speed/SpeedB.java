package top.n0rthmaster123.shadeac.check.checks.movement.speed;

import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class SpeedB extends Checker {
    public SpeedB(Check check) {
        super( check );
    }


    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.getDeltaXZ() == 0 )return;
        double accel = Math.abs( Math.abs( e.getDeltaXZ() ) - Math.abs( e.getLastDeltaX() ) );
        if( e.getLastDeltaXZ() < e.getDeltaXZ() && accel > 0.8 ){
            e.fail( this );
            e.verbose( "accelXZ= " + accel );
        }
    }
}
