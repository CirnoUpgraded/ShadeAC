package top.n0rthmaster123.shadeac.check.checks.movement.flight;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;

public class FlightB extends Checker {

    public FlightB(Check check ) {
        super( check );
    }

    HashMap<Player,Integer> vl = new HashMap<>();


    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.onGround() )return;
        if( e.getTo().getY() < 0 )return;
        if( e.getAirtick() > 6 ) {
            double motionY = e.getFrom( ).getY( ) - e.getTo( ).getY( );
            if( motionY == 0 )return;
            //double diff = e.getDeltaY( ) - motionY;
            Player p = e.getPlayer();
            if ( e.getLastDeltaY( ) > motionY ) {
                if( vl.get( p ) != null ){
                    vl.put( p , vl.get( p ) + 1 );
                    if( vl.get( p ) > 2 ){
//                        e.fail( this );
//                        e.verbose( "lastDY = " + e.getLastDeltaY() + " motionY = " + motionY + " airtick = " + e.getAirtick() );
                        fail( p , "lastDY = " + e.getLastDeltaY() + " motionY = " + motionY + " airtick = " + e.getAirtick() );
                        vl.put( p , 0 );
                    }
                }else {
                    new BukkitRunnable() {
                        public void run() {
                            if( p != null ){
                                vl.put( p , null );
                            }
                            cancel();
                        }
                    }.runTaskLater( ShadeAC.getPlugin(), 20L);
                }
            }
        }
    }

}
