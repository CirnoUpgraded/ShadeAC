package top.n0rthmaster123.shadeac.check.checks.movement.flight;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;


public class FlightC extends Checker {

    public FlightC(Check check ) {
        super( check );
    }

    HashMap<Player,Integer> vl = new HashMap<>();

    @EventHandler
    public void onMove(ShadeMoveEvent e) {
        if ( e.onGround( ) ) return;
        Player p = e.getPlayer();
        if( p.isFlying() )return;
        if( e.isWaterAround( e.getTo() , 2,2 ) )return;
        if( e.getDeltaY() < 0.09 ){
            if( e.getDeltaY() != 0 ){
                if( vl.get( p ) != null ){
                    vl.put( p , vl.get( p ) + 1 );
                }else {
                    vl.put( p , 1 );
                    startReset( p );
                }
            }
        }else {
            if( vl.get( p ) != null ){
                vl.put( p , vl.get( p ) - 1 );
            }else {
                vl.put( p , -1 );
                startReset( p );
            }
        }
        if( vl.get( p ) != null && vl.get( p ) > 2 ){
            //e.fail( this );
            //e.verbose( "deltaY = " + e.getDeltaY() );
            fail( p , "deltaY = " + e.getDeltaY() );
        }
    }

    public void startReset(Player p){
        new BukkitRunnable() {
            public void run() {
                vl.put( p , null );
                cancel();
            }
        }.runTaskLater( ShadeAC.getPlugin(), 20L);
    }
}
