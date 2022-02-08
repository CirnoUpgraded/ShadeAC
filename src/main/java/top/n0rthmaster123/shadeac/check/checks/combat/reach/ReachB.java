package top.n0rthmaster123.shadeac.check.checks.combat.reach;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeUtil;

import java.util.HashMap;

public class ReachB extends Checker {
    public ReachB(Check check) {
        super( check );
    }


    HashMap<Player,Integer> reachBuffers = new HashMap<>();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if( e.getDamager() instanceof Player ){
            Player p = ( (Player) e.getDamager() ).getPlayer();
            if( p.getGameMode().equals( GameMode.CREATIVE ) )return;
            Location me = p.getEyeLocation().clone(); me.setY( 0 );
            Location target = e.getEntity().getLocation().clone(); target.setY( 0 );
            target = target.add( 0 , 1 , 0 );
            double reach = me.distance( target ) - 0.4551123128148737;
            double max = 2.9 + ( ( ShadeUtil.getPing( p ) % 50 ) * 0.1 );

            if( reach > max ){
                int buffer = 0;
                if( reachBuffers.get( p ) != null ){
                    buffer = reachBuffers.get( p );
                    if( ++buffer > 5 ){
                        fail( p , "reach = " + reach + " >= " + max + " | buffer = " + buffer );
                    }
                }else{
                    //reachBuffers.put( p , buffer );
                    new BukkitRunnable() {
                        public void run() {
                            if( p != null ){
                                reachBuffers.put( p , null );
                            }
                            cancel();
                        }
                    }.runTaskLater( ShadeAC.getPlugin(), 100L);
                }
                reachBuffers.put( p , buffer );
            }
            //p.sendMessage( "reach = " + reach );
        }
    }
}
