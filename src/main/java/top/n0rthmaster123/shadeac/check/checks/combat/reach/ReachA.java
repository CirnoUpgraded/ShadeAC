package top.n0rthmaster123.shadeac.check.checks.combat.reach;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ReachA extends Checker {
    public ReachA(Check check) {
        super( check );
    }

//    HashMap<Player, List> yaws = new HashMap<>();
//    HashMap<Player, Integer> vl = new HashMap<>();
//    HashMap<Player, Float> lastYaw = new HashMap<>();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if( e.getDamager() instanceof Player ){
            Player p = ( (Player) e.getDamager() ).getPlayer();
            if( bypass( p ) )return;
            double reach = p.getLocation().distance( e.getEntity().getLocation() );
            if( reach > 3.5 ){
                fail( p , "reach = " + reach );
            }
//            boolean isNullLastYaw = lastYaw.get( p ) == null;
//            double yawdiff = Math.abs( p.getLocation().getYaw() - lastYaw.getOrDefault( p , 0F ) );
//            if( yaws.get( p ) != null ){
//                if( yaws.containsKey( yawdiff ) && !isNullLastYaw && yawdiff > 0.5 ){
//                    vl.put( p , vl.getOrDefault( p , 0  ) + 1 );
//                }
//                if( vl.get( p ) == null ){
//                    vl.put( p , 0 );
//                }
//                if( vl.get( p ) > 2 ){
//                    fail( p , "" );
//                }
//                if( !isNullLastYaw ){
//                    List val = yaws.get( p );
//                    val.add( yawdiff );
//                    yaws.put(p, val);
//                }
//            }else {
//                List val = new ArrayList();
//                if( !isNullLastYaw ){
//                    val.add( yawdiff );
//                }
//                yaws.put( p , val );
//                new BukkitRunnable() {
//                    public void run() {
//                        if( p != null ){
//                            vl.put( p , null );
//                            yaws.put( p, null );
//                        }
//                        this.cancel();
//                    }
//                }.runTaskLater( ShadeAC.getPlugin(), 20 * 10 );
//            }
//            lastYaw.put( p , p.getLocation().getYaw() );
        }
    }
}
