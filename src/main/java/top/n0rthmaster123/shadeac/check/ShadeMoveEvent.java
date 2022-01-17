package top.n0rthmaster123.shadeac.check;

import io.github.retrooper.packetevents.packetwrappers.NMSPacket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ShadeMoveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Location to,from;
    Player p;
    public boolean failed,verbosed;
    public String verbose;
    public Checker checker;
    double deltaY,fallDist;
    int airtick;
    byte packetID;
    public boolean lastDeltaYNull = false;
    NMSPacket packet;

    public ShadeMoveEvent(Player p,byte id,NMSPacket packet){
        this.p = p;
        to = p.getLocation();
        from = ShadeUtil.lastLocation.get( p );
        airtick = ShadeUtil.airtick.getOrDefault( p , 0 );

        if( ShadeUtil.lastDeltaY.get( p ) != null ){
            deltaY = ShadeUtil.lastDeltaY.get( p );
        }else {
            deltaY = 0;
            lastDeltaYNull = true;
        }
        fallDist = ShadeUtil.fallDistance.getOrDefault( p , 0.0 );
        this.packetID = id;
        this.packet = packet;
    }

    public byte getPacketID(){
        return packetID;
    }

    public NMSPacket getNMSPacket(){
        return packet;
    }

    public boolean isBadBlockAround() {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = to.getWorld().getBlockAt(to.clone().add(x, y, z));
                    String b = block.getType() + "";
                    if(b.contains( "LADDER" ) || b.contains( "VINE" ) || b.contains( "WEB" )
                            || b.contains( "LILY" ) || b.contains( "SNOW" ) || b.contains( "CARPET" ) ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isBadBlockLastAround() {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = from.getWorld().getBlockAt(from.clone().add(x, y, z));
                    String b = block.getType() + "";
                    if(b.contains( "LADDER" ) || b.contains( "VINE" ) || b.contains( "WEB" )
                            || b.contains( "LILY" ) || b.contains( "SNOW" ) || b.contains( "CARPET" ) ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Player getPlayer(){
        return p;
    }

    public int getAirtick(){
        return airtick;
    }

    public boolean onGround(){
        boolean onMathGround =  to.getY() % 0.015625 < 0.0001;
        boolean onAroundGround =  onGroundAround( to );
        if(onMathGround && !onAroundGround){
            return false;
        }else if(!onMathGround){
            return false;
        }
        return true;
    }

    public boolean onLastGround(){
        boolean onMathGround = from.getY() % 0.015625 < 0.0001;
        boolean onAroundGround =  onGroundAround( from );
        if(onMathGround && !onAroundGround){
            return false;
        }else if(!onMathGround){
            return false;
        }
        return true;
    }

    @Deprecated
    public boolean onGroundAround(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean besideGround() {
        int radius = 1;
        int radiusY = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radiusY; y < radiusY; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = p.getEyeLocation().getWorld().getBlockAt(p.getEyeLocation().clone().add(x, y + 1, z));
                    if( !( block.getLocation() == p.getEyeLocation() ) || !(block.getLocation() == getTo()) ){
                        if (block.getType().isSolid()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Location getTo(){
        return to;
    }

    public Location getFrom(){
        return from;
    }

    public boolean isWaterAround(Location loc ,int radius ,int radiusY ) {
        for (int x = -radius; x < radius; x++) {
            for (int y = -radiusY; y < radiusY; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.isLiquid() || block.equals( Material.WATER ) ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isStandingBoat(Player p){
        boolean r = false;
        for(Entity e : p.getWorld().getEntities()){
            double distance = e.getLocation().distance( p.getLocation() );
            if(distance <= 1.5 && e.getName().toLowerCase().contains( "boat" ) && !(e instanceof Player )){
                r = true;
            }
        }
        return r;
    }

    public int getPotionLevel(PotionEffectType type) {
        for (PotionEffect pEffect : p.getActivePotionEffects()) {
            if (pEffect.getType().equals( PotionEffectType.JUMP)) {
                return pEffect.getAmplifier() + 1;
            }
        }
        return 0;
    }

    public double getDeltaY(){
        return Math.abs( to.getY() - from.getY() );
    }

    public double getLastDeltaY(){
        return deltaY;
    }

    public double getDeltaX(){
        return Math.abs( to.getX() - from.getX() );
    }

    public double getDeltaZ(){
        return Math.abs( to.getZ() - from.getZ() );
    }

    public double getLastDeltaX(){
        return ShadeUtil.lastDeltaX.get( p );
    }

    public double getLastDeltaZ(){
        return ShadeUtil.lastDeltaZ.get( p );
    }

    public double getDeltaXZ(){
        return getDeltaX() + getDeltaZ();
    }

    public double getLastDeltaXZ(){
        return getLastDeltaX() + getLastDeltaZ();
    }

    public double getServerSideFallDistance(){
        return fallDist;
    }

    public void fail(Checker checker){
        if(failed)return;
        this.checker = checker;
        failed = true;
    }

    public void verbose(String verbose){
        if(verbosed || !failed )return;
        verbosed = true;
        this.verbose = verbose;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }

}
