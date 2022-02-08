package top.n0rthmaster123.shadeac.check.checks.movement.flight;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.motion.MotionA;

import java.util.HashMap;


public class FlightE extends Checker {

    public FlightE(Check check ) {
        super( check );
    }


    HashMap<Player,Integer> buffers = new HashMap<>();

    @EventHandler
    public void onMove(ShadeMoveEvent e) {
        if ( e.onGround( ) ) return;
        if( e.lastDeltaYNull )return;
        Player p = e.getPlayer();
        if( p.isFlying() )return;
        if( MotionA.onLadder( p ) )return;
        if( e.isWaterAround( e.getTo() , 2, 2 ) )return;
        if( buffers.get( p ) == null ){
            buffers.put( p , 0 );
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                        buffers.put( p , null );
                    }
                    this.cancel();
                }
            }.runTaskLater( ShadeAC.getPlugin(), 100L);
        }
        int buffer = buffers.get( p );
        double motionY = e.getTo().getY() - e.getFrom().getY();
        if( e.getAirtick() > 1 && motionY > 0 ){
            boolean legitUp = e.getDeltaY() < e.getLastDeltaY();
            if( !legitUp ){
                //e.fail( this );
                if(++buffer > 2 ) {
                    fail( p , "buffer = " + buffer + " my = " + motionY + " at = " + e.getAirtick() );
                    //e.verbose( "buffer = " + buffer + " my = " + motionY + " at = " + e.getAirtick() );
                } else {
                    //e.verbose( "my = " + motionY + " at = " + e.getAirtick() );
                }
            }
        }
        buffers.put( p , buffer );
    }
}
