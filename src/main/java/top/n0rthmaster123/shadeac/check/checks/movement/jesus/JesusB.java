package top.n0rthmaster123.shadeac.check.checks.movement.jesus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;


public class JesusB extends Checker {
    public JesusB(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.onGroundAround( e.getTo() ) )return;
        if( !e.isWaterAround( e.getTo() , 1 ,1 ) )return;
        double motionY = e.getTo().getY() - e.getFrom().getY();
        boolean legitFall = e.getDeltaY() > e.getLastDeltaY();
        Player p = e.getPlayer();
        if( e.getDeltaY() == e.getLastDeltaY() )return;
        if( !legitFall && motionY > 0.05 ){
            fail( p , "motionY = " + motionY );
        }
    }
}
