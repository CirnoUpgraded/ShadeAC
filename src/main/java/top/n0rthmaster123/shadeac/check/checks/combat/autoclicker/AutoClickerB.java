package top.n0rthmaster123.shadeac.check.checks.combat.autoclicker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;

public class AutoClickerB extends Checker {
    public AutoClickerB(Check check) {
        super( check );
    }

    @EventHandler
    public void onClick(PlayerAnimationEvent e) {
        if ( e.getAnimationType( ) != PlayerAnimationType.ARM_SWING ) return;
        if ( e.isCancelled( ) ) return;
        Player p = e.getPlayer();
        if( AutoClickerA.clickVl.get( p ) != null ){
            if( AutoClickerA.clickVl.get( p ) > 2 ){
                fail( p, "same click speed!" );
                AutoClickerA.clickVl.put( p , 0 );
            }
        }
    }
}
