package top.n0rthmaster123.shadeac.check.checks.movement.badpackets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;

public class BadPacketsB extends Checker {
    public BadPacketsB(Check check) {
        super( check );
    }

    HashMap<Player,Integer> vl = new HashMap<>();

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        if( e.lastDeltaYNull )return;
        if( e.getLastDeltaY() != 0 && e.getDeltaY() != 0 && e.getPlayer().isOnGround() ){
            Player p = e.getPlayer();
            if( vl.get( p ) != null ){
                vl.put( p , vl.get( p ) + 1 );
                if( vl.get( p ) > 2 ){
                    e.fail( this );
                    vl.put( p , 0 );
                }
            } else {
                vl.put( p , 1);
                new BukkitRunnable() {
                    public void run() {
                        if( p != null ){
                            vl.put( p , null );
                        }
                        cancel();
                    }
                }.runTaskLater( ShadeAC.getPlugin(), 20L);
            }
        }
    }
}
