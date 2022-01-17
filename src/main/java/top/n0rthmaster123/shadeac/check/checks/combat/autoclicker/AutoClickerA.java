package top.n0rthmaster123.shadeac.check.checks.combat.autoclicker;

import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;

import java.util.*;

public class AutoClickerA extends Checker {
    public AutoClickerA(Check check) {
        super( check );
    }

    public static HashMap<Player,Integer> clicks = new HashMap<>();
    public static HashMap<Player, List> clickes = new HashMap<>();
    public static HashMap<Player,Integer> clickVl = new HashMap<>();

    @EventHandler
    public void onClick(PlayerAnimationEvent e){
        if( e.getAnimationType() != PlayerAnimationType.ARM_SWING )return;
        if( e.isCancelled() )return;
        Player p = e.getPlayer();
        Block b = p.getTargetBlock( (HashSet<Byte>) null, 5 );
        if( b.getType().isSolid() )return;
        if( clicks.get( p ) != null ){
            int count = clicks.get( p ) + 1;
            clicks.put( p , count );
            if( count > 9 ){
                if( clickes.get( p ) != null ){
                    List list = clickes.get( p );
                    if( list.contains( count ) ){
                        if( clickVl.get( p ) != null ){
                            clickVl.put( p , clickVl.get( p ) + 1 );
                        }else{
                            clickVl.put( p , 1 );
                            new BukkitRunnable() {
                                public void run() {
                                    if( p != null ){
                                        clickVl.put( p , null );
                                    }
                                    cancel();
                                }
                            }.runTaskLater( ShadeAC.getPlugin(), 20L);
                        }
                    }
                    list.add( count );
                }else{
                    List list = new ArrayList();
                    list.add( count );
                    clickes.put( p , list );
                }
            }
            if( count > 20 ){
                fail( p , "Impossible! count = " + count );
                clicks.put( p , 0 );
            }
        }else {
            clicks.put( p , 1 );
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                        clicks.put( p , null );
                    }
                    cancel();
                }
            }.runTaskLater( ShadeAC.getPlugin(), 20L);
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                        clickes.put( p , null );
                    }
                    cancel();
                }
            }.runTaskLater( ShadeAC.getPlugin(), 200L);
        }
    }

}
