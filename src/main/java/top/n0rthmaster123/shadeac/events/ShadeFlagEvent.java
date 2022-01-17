package top.n0rthmaster123.shadeac.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.ShadeUtil;

public class ShadeFlagEvent extends Event implements Cancellable{

    private static final HandlerList handlers = new HandlerList();
    Player p;
    int vl,vl_check;
    boolean cancelled = false;
    Check check;

    public ShadeFlagEvent(Player p,Check check){
        this.p = p;
        ShadeUtil.setUpPlayerViolation( p );
        this.vl = ShadeUtil.getVL( null , p );
        this.vl_check = ShadeUtil.getVL( check , p );
        this.check = check;
    }

    public Player getPlayer(){
        return p;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public int getVL(){
        return vl;
    }

    public int getVLCheck(){
        return vl_check;
    }

    public Check getCheck(){
        return check;
    }
}
