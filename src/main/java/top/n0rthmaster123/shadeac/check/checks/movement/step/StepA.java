package top.n0rthmaster123.shadeac.check.checks.movement.step;

import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class StepA extends Checker {

    public StepA(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.getTo().getY() % 0.015625 < 0.0001 ) {
            if ( e.onLastGround( ) ) {
                if ( e.getDeltaY( ) > ( 0.5625 + ( e.getPlayer().hasPotionEffect( PotionEffectType.JUMP ) ? e.getPotionLevel( PotionEffectType.JUMP ) * 0.1 : 0 ) ) ) {
                    //e.fail( this );
                    //e.verbose( "DeltaY = " + e.getDeltaY() );
                    fail( e.getPlayer() , "DeltaY = " + e.getDeltaY() );
                }
            }
        }
    }
}
