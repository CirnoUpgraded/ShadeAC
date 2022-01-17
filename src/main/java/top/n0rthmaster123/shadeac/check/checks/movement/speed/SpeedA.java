package top.n0rthmaster123.shadeac.check.checks.movement.speed;

import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.motion.MotionA;

public class SpeedA extends Checker {
    public SpeedA(Check check) {
        super( check );
    }


    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.besideGround() )return;
        if( e.getPlayer().isFlying() )return;
        if( e.getDeltaXZ() == 0 )return;
        if( MotionA.onLadder( e.getPlayer() ) )return;
        if( e.isWaterAround( e.getTo() , 2 ,2 ) )return;
        if( e.getAirtick() > 2 ){
            if( e.getLastDeltaXZ() == e.getDeltaXZ() ){
                e.fail( this );
                e.verbose( "deltaXZ and last deltaXZ is same." );
            }
        }
    }
}
