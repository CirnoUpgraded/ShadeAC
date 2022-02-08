package top.n0rthmaster123.shadeac.check.checks.movement.vclip;

import org.bukkit.event.EventHandler;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

public class VClipB extends Checker {
    public VClipB(Check check) {
        super( check );
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        boolean up = e.getTo().getY() > e.getFrom().getY();
        if( up )return;
        if( e.getDeltaY() > 1 && e.getLastDeltaY() == 0 && ( e.getAirtick() == 1 || e.getAirtick() == 0 ) ) {
            fail( e.getPlayer() , "" );
        }
    }
}
