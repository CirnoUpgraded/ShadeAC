package top.n0rthmaster123.shadeac.check.checks.movement.flight;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;


public class FlightD extends Checker {

    public FlightD(Check check ) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e) {
        if ( e.onGround( ) ) return;
        if( e.lastDeltaYNull )return;
        Player p = e.getPlayer();
        if( p.isFlying() )return;
        if( e.isWaterAround( e.getTo() , 2, 2 ) )return;
        double motionY = e.getTo().getY() - e.getFrom().getY();
        if( e.getAirtick() > 11 && motionY < 0 ){
            boolean legitFall = e.getDeltaY() > e.getLastDeltaY();
                if( !legitFall ){
                    e.fail( this );
                    e.verbose( "motionY = " + motionY );
                }
//            p.sendMessage( " " + (  ) );
        }
    }

    //Maybe it's works well so I do it↓
    @Deprecated
    public static boolean isLegitFall(Location to,Location from,double deltaY,double lastDeltaY){
        double motionY = to.getY() - from.getY();
        if( motionY < 0 ) {
            return deltaY > lastDeltaY;
        }
        return true;
    }
}
