package top.n0rthmaster123.shadeac.check.checks.movement.flight;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.motion.MotionA;

import java.util.HashMap;


public class FlightF extends Checker {

    public FlightF(Check check ) {
        super( check );
    }

    HashMap<Player,Double> lastAccels = new HashMap<>();
    HashMap<Player,Integer> buffers = new HashMap<>();

    @EventHandler
    public void onMove(ShadeMoveEvent e) {
        if ( e.onGround( ) ) return;
        if( e.lastDeltaYNull )return;
        Player p = e.getPlayer();

        int buffer = 0;
        if( buffers.get( p ) == null ){
            buffers.put( p , 0 );
            new BukkitRunnable() {
                public void run() {
                    buffers.put( p , null );
                    cancel();
                }
            }.runTaskLater( ShadeAC.getPlugin(), 200 );
        }else {
            buffer = buffers.get( p );
        }

        if( p.isFlying() )return;
        if( MotionA.onLadder( p ) )return;
        if( e.isWaterAround( e.getTo() , 2, 2 ) )return;
        double accel = Math.abs( e.getDeltaY() - e.getLastDeltaY() );
        if( lastAccels.get( p ) != null ){
            double lastAccel = lastAccels.get( p );
            double diff = Math.abs( accel - lastAccel );
            if( diff != 0 && diff < 0.000001 ){ //too small y accel check :)
                if( ++buffer > 2 ) {
                    //e.fail( this );
                    //e.verbose( "diff = " + diff + " buffer = " + buffer );
                    fail( p , "diff = " + diff + " buffer = " + buffer );
                }
            }
            buffers.put( p , buffer );
        }
        lastAccels.put( p , accel );
    }
}
