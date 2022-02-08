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

public class ReachA extends Checker {
    public ReachA(Check check) {
        super( check );
    }

//    HashMap<Player, List> yaws = new HashMap<>();
//    HashMap<Player, Integer> vl = new HashMap<>();
//    HashMap<Player, Float> lastYaw = new HashMap<>();

    HashMap<Player,Integer> reachBuffers = new HashMap<>();
    //HashMap<Player,Integer> reachBuffersB = new HashMap<>();

    public static int vl = 1;

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if( e.getDamager() instanceof Player ){
            Player p = ( (Player) e.getDamager() ).getPlayer();
            //if( bypass( p ) )return;
            if( p.getGameMode().equals( GameMode.CREATIVE ) )return;
            Location me = p.getEyeLocation().clone(); me.setY( 0 );
            Location target = e.getEntity().getLocation().clone(); target.setY( 0 );
            target = target.add( 0 , 1 , 0 );
            double reach = me.distance( target ) - 0.4551123128148737;
            double max = 3.01 + ( ( ShadeUtil.getPing( p ) % 50 ) * 0.1 );

            if( reach > max ){
                int buffer = 0;
                if( reachBuffers.get( p ) != null ){
                    buffer = reachBuffers.get( p );
                    if( ++buffer > vl ){
                        fail( p , "reach = " + reach + " > " + max + " buffer = " + buffer );
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
                    }.runTaskLater( ShadeAC.getPlugin(), 50L);
                }
                reachBuffers.put( p , buffer );
            }

            if( reach > 3.3 ){
                //p.sendMessage( "§creach = " + reach );
                fail( p , "reach = " + reach + " > 3.3" );
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
