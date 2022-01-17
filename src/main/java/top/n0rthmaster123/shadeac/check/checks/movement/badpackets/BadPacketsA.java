package top.n0rthmaster123.shadeac.check.checks.movement.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class BadPacketsA extends Checker {
    public BadPacketsA(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        WrappedPacketInFlying wrapped = new WrappedPacketInFlying( e.getNMSPacket() );
        if( Math.abs( wrapped.getPitch() ) > 90 ){
            e.fail( this );
            e.verbose( "pitch = " + wrapped.getPitch() );
        }
    }
}
