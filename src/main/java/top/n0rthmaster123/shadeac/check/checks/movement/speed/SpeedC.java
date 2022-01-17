package top.n0rthmaster123.shadeac.check.checks.movement.speed;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeMoveEvent;

import java.util.HashMap;

public class SpeedC extends Checker {
    public SpeedC(Check check) {
        super( check );
    }

    HashMap<Player,Integer> timer = new HashMap<>();
    HashMap<Player,Long> joined = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        joined.put( e.getPlayer() , System.currentTimeMillis() );
    }

    public boolean isNearJoin(Player p){
        if( joined.get( p ) != null ){
            if( ( System.currentTimeMillis( ) - joined.get( p ) ) < 1100 ){
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onMove(ShadeMoveEvent e){
        Player p = e.getPlayer();
        if( isNearJoin( p ) )return;
        if( timer.get( p ) != null ){
            timer.put( p , timer.get( p ) + 1 );
            double speed = timer.get(p)/20.0;
            if( speed > 1.05 ){
                e.fail( this );
                e.verbose( "TimerSpeed = " + speed );
            }
        }else {
            timer.put( p , 1 );
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                       timer.put( p , null );
                    }
                    cancel();
                }
            }.runTaskLater( ShadeAC.getPlugin(), 20L);
        }
    }
}
