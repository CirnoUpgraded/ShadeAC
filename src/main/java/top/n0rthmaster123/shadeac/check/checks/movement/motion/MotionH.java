package top.n0rthmaster123.shadeac.check.checks.movement.motion;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;


public class MotionH extends Checker {
    public MotionH(Check check) {
        super( check );
    }

    HashMap<Player,Integer> buffers = new HashMap<>();

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        Player p = e.getPlayer();
        if( e.isWaterAround( e.getTo() , 1 ,1  ) )return;
        int buffer = 0;
        if( buffers.get( p ) != null ){
            buffer = buffers.get( p );
        }
        if( e.getAirtick() < 6 )return;
        if( e.onGround() )return;
        double motionY = e.getTo().getY() - e.getFrom().getY();
        if(  motionY > 0 ? e.getDeltaY() <= e.getLastDeltaY() : e.getDeltaY() >= e.getLastDeltaY() ){
            if( ++buffer > 2 ){
                fail( p , "buffer = " + buffer );
            }
        } else {
            buffer -= buffer;
        }
        buffers.put( p , buffer );
    }
}
