package top.n0rthmaster123.shadeac.check.checks.movement.vclip;

import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class VClipA extends Checker {
    public VClipA(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.getLastDeltaY() != 0 )return;
        if( e.getTo().getY() < e.getFrom().getY() )return;
        if( e.isPistonAround( e.getTo() , 2 ,2 ) )return;
        double max = ( 0.5 + ( e.getPotionLevel( PotionEffectType.JUMP ) * 0.15 ) );
        //e.getPlayer().sendMessage( "you are now here " + e.getDeltaY() );
        if( e.getDeltaY() > max ){
            //e.fail( this );
            //e.verbose( "Faster Vertical Speed data = " + e.getDeltaY() + " > " + max );
            fail( e.getPlayer() , "Faster Vertical Speed data = " + e.getDeltaY() + " > " + max );
        }
    }
}
