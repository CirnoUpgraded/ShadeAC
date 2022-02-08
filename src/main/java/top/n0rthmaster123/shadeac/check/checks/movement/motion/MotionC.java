package top.n0rthmaster123.shadeac.check.checks.movement.motion;

import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class MotionC extends Checker {
    public MotionC(Check check) {
        super( check );
    }


    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.getDeltaY() > 99 ){
//            e.fail( this );
//            e.verbose( "deltaY = " + e.getDeltaY() );
            fail( e.getPlayer() , "deltaY = " + e.getDeltaY() );
        }
    }
}
