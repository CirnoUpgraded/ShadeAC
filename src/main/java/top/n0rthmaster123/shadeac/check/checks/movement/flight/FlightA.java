package top.n0rthmaster123.shadeac.check.checks.movement.flight;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.motion.MotionA;

import java.util.HashMap;

public class FlightA extends Checker {

    public FlightA(Check check ) {
        super( check );
    }


    static HashMap<Player,Long> lastPlaceAndBreak = new HashMap<>();


    @EventHandler
    public void onLastPlace(BlockPlaceEvent e){
        lastPlaceAndBreak.put( e.getPlayer() , System.currentTimeMillis() );
    }

    @EventHandler
    public void onLastBreak(BlockBreakEvent e){
        lastPlaceAndBreak.put( e.getPlayer() , System.currentTimeMillis() );
    }

    public static boolean isNearBlockAction(Player p){
        if( FlightA.lastPlaceAndBreak.get( p ) != null ){
            if( ( System.currentTimeMillis() - FlightA.lastPlaceAndBreak.get( p ) ) < 1100 ){
                return true;
            }
        }
        return false;
    }

    public static boolean isNearBlockAction(Player p,double a){
        if( FlightA.lastPlaceAndBreak.get( p ) != null ){
            if( ( System.currentTimeMillis() - FlightA.lastPlaceAndBreak.get( p ) ) < a ){
                return true;
            }
        }
        return false;
    }


    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.getAirtick() > 0 ){
            //e.getPlayer().sendMessage( "at = " + e.getAirtick() );
        }
        Player p = e.getPlayer();
        if( e.isBadBlockAround() )return;
        if( e.onLastGround() )return;
        if( e.getPlayer().isFlying() )return;
        if( e.isStandingBoat( p ) )return;
        if( MotionA.onLadder( p ) )return;
        if( isNearBlockAction( p ) )return;
        if( e.isWaterAround( e.getTo() , 2,2 ) || e.isWaterAround( e.getFrom() , 2,2 ) )return;
          if( e.getAirtick() > 11 && e.getTo().getY() >= e.getFrom().getY() ){
//              e.fail( this );
//              e.verbose( "AirTick = " + e.getAirtick() );
              fail( p , "AirTick = " + e.getAirtick() );
          }
    }

}
