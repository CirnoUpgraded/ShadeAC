package top.n0rthmaster123.shadeac.check.checks.movement.groundspoof;

import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.flight.FlightA;

public class GroundSpoofA extends Checker {
    public GroundSpoofA(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.isBadBlockAround() || e.isWaterAround( e.getTo() , 2 ,2 ) ){
            return;
        }
        if( FlightA.isNearBlockAction( e.getPlayer() ) )return;
        if( e.getPlayer().isOnGround() && ( !( e.getAirtick() > 2 ) && !e.onGround() ) ){
            fail( e.getPlayer() , "" );
        }
    }

}
