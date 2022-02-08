package top.n0rthmaster123.shadeac.check.checks.movement.jesus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;


public class JesusA extends Checker {
    public JesusA(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.onGroundAround( e.getTo() ) )return;
        if( !e.isWaterAround( e.getTo() , 1 ,1 ) )return;
        Player p = e.getPlayer();
        //e.getPlayer().sendMessage( "motionY = " + motionY + " legitfall = " + legitFall );
        if( e.getLastDeltaY() == e.getDeltaY() ){
            //p.sendMessage( "§cYOU CHEATINIG BRO x1" );
            fail( p , "same deltaY in water" );
        }
    }
}
