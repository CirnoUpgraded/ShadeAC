package top.n0rthmaster123.shadeac.check.checks.movement.speed;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class SpeedD extends Checker {
    public SpeedD(Check check) {
        super( check );
    }


    @EventHandler
    public void onMove(ShadeMoveEvent e){
        Player p = e.getPlayer();
        if( e.lastDeltaYNull )return;
        double friction = ( ( e.getLastDeltaXZ() * 0.91 + ( p.isSprinting() ? 0.26 : 0.13 ) ) - e.getDeltaXZ() );
        if( friction < 0 ){
            e.fail( this );
            e.verbose( "friction = " + friction );
        }
    }
}
