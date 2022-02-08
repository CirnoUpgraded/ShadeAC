package top.n0rthmaster123.shadeac.check.checks.movement.motion;

import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;
import top.n0rthmaster123.shadeac.check.checks.movement.flight.FlightA;

public class MotionB extends Checker {
    public MotionB(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.getTo().getY() % 0.015625 < 0.0001 )return;
        if( e.getTo().getY() <= e.getFrom().getY() )return;
        double max_delta_y = 0.41999998688697815;
        if( e.getPlayer().hasPotionEffect( PotionEffectType.JUMP ) ){
            max_delta_y += ( e.getPotionLevel( PotionEffectType.JUMP ) * 0.1 );
        }
        if( FlightA.isNearBlockAction( e.getPlayer()) )return;
        //e.getPlayer().sendMessage( "diff = " + Math.abs( max_delta_y - e.getDeltaY() ) );
        WrappedPacketInFlying wrapped = new WrappedPacketInFlying( e.getNMSPacket() );
        if( e.getDeltaY() > max_delta_y && !e.isWaterAround( e.getTo() , 2,2 ) ){
//            e.fail( this );
//            e.verbose( "deltaY = " + e.getDeltaY() );
            fail( e.getPlayer() , "deltaY = " + e.getDeltaY() );
        }
    }
}
