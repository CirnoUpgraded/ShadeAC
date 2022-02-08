package top.n0rthmaster123.shadeac.check.checks.combat.aim;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.checks.combat.autoclicker.AutoClickerA;

import java.util.HashMap;

public class AimA extends Checker {


    public AimA(Check check) {
        super( check );
    }


    HashMap<Player,Integer> buffers = new HashMap<>();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if( e.getDamager() instanceof Player ){
            Player p = ( (Player) e.getDamager() ).getPlayer();
            float[] pos = getFacePosEntity( p , e.getEntity() );
            Location loc = p.getLocation();
            float[] mine = new float[] { loc.getYaw() , loc.getPitch() };
            float yawDiff = Math.abs( Math.abs(pos[0] % 180 ) - Math.abs(mine[0] % 180) );
            float pitchDiff = Math.abs( Math.abs(pos[1] % 90) - Math.abs(mine[1] % 90) );

            int buffer = 0;
            if( buffers.get( p ) != null ){
                buffer = buffers.get( p );
            }else{
                new BukkitRunnable() {
                    public void run() {
                        if( p != null ){
                            buffers.put( p , null );
                        }
                        cancel();
                    }
                }.runTaskLater( ShadeAC.getPlugin(), 20 * 30 );
            }

            if( yawDiff < 1 || pitchDiff < 0.5 ){
                //p.sendMessage( "§cy = " + yawDiff + " " + pitchDiff );
                if( AutoClickerA.getCPS( p ) > 4 ) {
                    if( ++buffer > 1 ){
                        fail( p,"y = " + yawDiff + " p = " + pitchDiff );
                    }
                }
            }else {
                //p.sendMessage( "y = " + yawDiff + " " + pitchDiff );
            }
            buffers.put( p , buffer );
        }
    }

    public static float[] getFacePosEntity(Player p,Entity ent) {
        Location en = ent.getLocation();
        Location px = p.getLocation( );
        double deltaX = en.getX() - px.getX();
        double deltaY = en.getY() - 1.6 + ( 1.8 * 0.85F ) - 0.4 - px.getY();
        double deltaZ = en.getZ() - px.getZ();
        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float) Math.toDegrees(-Math.atan(deltaX - deltaZ));
        float pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
        if (deltaZ < 0.0 && deltaX < 0.0) {
            yaw = (float) (90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else if (deltaZ < 0.0 && deltaX > 0.0) {
            yaw = (float) (-90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else {
            yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ));
        }
        return new float[] {yaw, pitch};
    }
}
