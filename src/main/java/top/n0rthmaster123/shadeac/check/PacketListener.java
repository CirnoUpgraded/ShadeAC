package top.n0rthmaster123.shadeac.check;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import top.n0rthmaster123.shadeac.ban.BanUtil;
import top.n0rthmaster123.shadeac.events.ShadeFlagEvent;
import top.n0rthmaster123.shadeac.events.ShadePunishEvent;


public class PacketListener extends PacketListenerAbstract {
    public PacketListener(){
        super( PacketListenerPriority.LOW);
    }


    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent e){
        //WrappedPacketInBlockDig dig = new WrappedPacketInBlockDig( e.getNMSPacket() );

        if( PacketType.Play.Client.Util.isInstanceOfFlying( e.getPacketId() )  ){ //e.getPacketId() == PacketType.Play.Client.POSITION || e.getPacketId() == PacketType.Play.Client.LOOK || e.getPacketId() == PacketType.Play.Client.POSITION_LOOK || e.getPacketId() == PacketType.Play.Client.FLYING

//            if ( PacketType.Play.Client.Util.isInstanceOfFlying( e.getPacketId() ) ) { //NO NEED XDDD
//            }

            boolean doFallDist = false;

            if( onGround( e.getPlayer().getLocation() ) || isCloseToGround( e.getPlayer().getLocation() ) ){
                //e.getPlayer().sendMessage( "yes ground cs = " + e.getPlayer().isOnGround() + " 0");
                ShadeUtil.airtick.put( e.getPlayer() , 0 );
                ShadeUtil.fallDistance.put( e.getPlayer() , 0.0 );
            }
            else {
                if ( ShadeUtil.airtick.get( e.getPlayer( ) ) != null ) {
                    ShadeUtil.airtick.put( e.getPlayer( ),ShadeUtil.airtick.get( e.getPlayer( ) ) + 1 );
                } else {
                    ShadeUtil.airtick.put( e.getPlayer( ),1 );
                }
                //e.getPlayer().sendMessage( " at = " + ShadeUtil.airtick.get( e.getPlayer() ) + " | pack = " + e.getPacketId() );
                doFallDist = true;
                //e.getPlayer().sendMessage( "no ground cs = " + e.getPlayer().isOnGround() + " " + ShadeUtil.airtick.get( e.getPlayer( ) ) );
            }


            ShadeMoveEvent shade = new ShadeMoveEvent( e.getPlayer() , e.getPacketId() , e.getNMSPacket() );
            boolean eventCall = ShadeUtil.lastLocation.get( shade.p ) != null;

            if( shade.p.hasPermission( "shade.bypass" ) && ShadeUtil.bypass ){
                eventCall = false;
            }

            if( !shade.lastDeltaYNull ){
                double deltaY = shade.to.getY() - shade.from.getY();

                if ( doFallDist ){
                    if ( ShadeUtil.fallDistance.get( e.getPlayer() ) != null  ){
                        ShadeUtil.fallDistance.put( e.getPlayer() ,  ShadeUtil.fallDistance.get( e.getPlayer() ) + deltaY );
                    }else {
                        ShadeUtil.fallDistance.put( e.getPlayer() ,  deltaY );
                    }
                }
            }

            if( !ShadeUtil.status ){
                eventCall = false;
            }

            if( eventCall ){
                Bukkit.getPluginManager().callEvent(shade);
            }


            if( eventCall && shade.failed && CheckUtil.getCheck( shade.checker.check.getCheck() , shade.checker.check.getType() ).getStatus() ){
                Check check = shade.checker.check;
                //e.getPlayer().sendMessage( shade.checker.check.getStatus() + " " + CheckUtil.getCheck( shade.checker.check.getCheck() , shade.checker.check.getType() ).getStatus() );

                Player player = shade.p;

                ShadeFlagEvent flagevent = new ShadeFlagEvent( player , check );

                Bukkit.getPluginManager().callEvent(flagevent);
                if( !flagevent.isCancelled() ){

                    ShadeUtil.setUpPlayerViolation( player );
                    ShadeUtil.addVL( null ,player );
                    ShadeUtil.addVL( check,player );

                    String alert = AlertThemeUtil.getCurrentTheme().getReplacedMessage( player , check , ShadeUtil.getVL( null , player ) ,ShadeUtil.getVL( check , player ), ( shade.verbosed ? shade.verbose : "Nothing") );
                    for( Player p : Bukkit.getOnlinePlayers() ){
                        if( ShadeUtil.isAlertEnabled( p ) && p.hasPermission(  "shade.alerts" ) ){
                            //p.sendMessage( ShadeAC.prefix + "§c " + player.getName() + "§7 failed§6 " + check.getCheck() + "§8(§eVL§a " + ShadeUtil.getVL( check , player ) + "§8/§a" + ShadeUtil.getVL( null , player ) + "§8) (§eType:§a " + check.getType() + "§8) (§eInfo:§a " + ( shade.verbosed ? shade.verbose : "Nothing") + " §8)"  );
                            p.sendMessage( alert );
                        }
                    }


                    ShadePunishEvent punishEvent = new ShadePunishEvent( player, check );
                    if( BanUtil.getVlType() == BanUtil.VL_Type.TOTAL ){
                        if( ShadeUtil.getVL( null , player ) >= BanUtil.getVlTotal() ){
                            Bukkit.getPluginManager( ).callEvent(punishEvent);
                            if( !punishEvent.isCancelled() ){
                                BanUtil.ban( player , check );
                            }
                        }
                    }else if( BanUtil.getVlType() == BanUtil.VL_Type.CHECK ){
                        if( ShadeUtil.getVL( check , player ) >= check.vl && check.autoban ){
                            Bukkit.getPluginManager( ).callEvent(punishEvent);
                            if( !punishEvent.isCancelled() ){
                                BanUtil.ban( player , check );
                            }
                        }
                    }
                }

            }

            if( ShadeUtil.lastLocation.get( e.getPlayer() ) != null ){
                ShadeUtil.lastDeltaY.put( e.getPlayer()  , shade.getDeltaY() );
                ShadeUtil.lastDeltaX.put( e.getPlayer() , shade.getDeltaX() );
                ShadeUtil.lastDeltaZ.put( e.getPlayer() , shade.getDeltaZ() );
            }
            ShadeUtil.lastLocation.put( e.getPlayer() , e.getPlayer().getLocation() );
        }
    }
    public boolean isCloseToGround(Location location) {
    /*
     Made by @Salers
     From https://github.com/SalersFR/HBAC/blob/main/src/main/java/gg/salers/honeybadger/processor/impl/MovementProcessor.java
     Thanks you Salers :)
    */
        double distanceToGround = 0.3D;
        for (double locX = -distanceToGround; locX <= distanceToGround; locX += distanceToGround) {
            for (double locZ = -distanceToGround; locZ <= distanceToGround; locZ += distanceToGround) {
                if (location.clone().add(locX, -0.5001, locZ).getBlock().getType() == Material.AIR) {
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean onGroundAround( Location loc) {
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

    public boolean onGround(Location to){
        boolean onMathGround =  to.getY() % 0.015625 < 0.0001;
        boolean onAroundGround =  onGroundAround( to );
        if(onMathGround && !onAroundGround){
            return false;
        }else if(!onMathGround){
            return false;
        }
        return true;
    }

}
