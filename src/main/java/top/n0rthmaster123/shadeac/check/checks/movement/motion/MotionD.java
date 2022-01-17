package top.n0rthmaster123.shadeac.check.checks.movement.motion;

import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;

public class MotionD extends Checker {
    public MotionD(Check check) {
        super( check );
    }

    HashMap<Player,Integer> groundTick = new HashMap<>();

    public int addAndGet(Player p){
        if( groundTick.get( p ) != null ){
            groundTick.put( p , groundTick.get( p ) + 1 );
            return groundTick.get( p );
        }
        groundTick.put( p , 1 );
        return 1;
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        WrappedPacketInFlying wrapped = new WrappedPacketInFlying( e.getNMSPacket() );
        if( wrapped.isOnGround() ){
            int tick = addAndGet( e.getPlayer() );
            double motionY = e.getTo().getY() - e.getFrom().getY();
            if( wrapped.getPosition().getY() < -0.079 && e.getTo().getY() <= e.getFrom().getY() && tick > 2 ){
                e.fail( this );
                //e.verbose( "DeltaY = " + motionY + " groundTick = " + tick );
            }
        }
    }
}
