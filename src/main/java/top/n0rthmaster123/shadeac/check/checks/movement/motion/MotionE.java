package top.n0rthmaster123.shadeac.check.checks.movement.motion;

import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class MotionE extends Checker {
    public MotionE(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.onGround() )return;
        double pred = ( e.getLastDeltaY() - 0.08 ) * 0.98;
        double accel = Math.abs( e.getDeltaY() - pred );
        if( accel > 0.5 && e.getDeltaY() > 5 ){
            e.fail( this );
            e.verbose( "accel = " + accel );
        }
    }
}
