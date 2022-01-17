package top.n0rthmaster123.shadeac.check.checks.movement.motion;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class MotionA extends Checker {
    public MotionA(Check check) {
        super( check );
    }


    public static boolean onLadder(Player player) {
        Material b = player.getLocation().clone().subtract(0, 0.1, 0).getBlock().getType();
        return b == Material.LADDER || b == Material.VINE  || b == Material.WEB;
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.getPlayer().isFlying() )return;
        if( onLadder( e.getPlayer() ) )return;
        if( e.isWaterAround( e.getTo() , 2 ,2 ) )return;
        if( e.getDeltaY() == 0 )return;
        if( e.getAirtick() > 2 ){
            if( e.getDeltaY() ==  e.getLastDeltaY() ){
                e.fail( this );
                e.verbose( "dY = " + e.getDeltaY() + " lastDy = " + e.getLastDeltaY() );
            }
        }


    }
}
