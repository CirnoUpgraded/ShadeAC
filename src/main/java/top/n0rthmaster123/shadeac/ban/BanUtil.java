package top.n0rthmaster123.shadeac.ban;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.ShadeUtil;

public class BanUtil {

    static String reason = "§l|§8 Shade§r §l|\n§eHey,%%player%%\nAnti-Cheat caught you cheating.";
    static String command = "kick %%player%% [Cheating]";
    static enum Type{
        BAN,KICK,CUSTOM,NONE
    }

    public static enum VL_Type{
        TOTAL,CHECK
    }

    public static VL_Type getVlType(){
        return vlType;
    }

    static enum Case{
        UPPER,LOWER,DEFAULT
    }

    static boolean animation,status = false;

    static Type type = Type.NONE;
    static VL_Type vlType = VL_Type.CHECK;
    static Case ply,chck,typi;
    static int vl_total = 15;

    public static int getVlTotal(){
        return vl_total;
    }

    public static void setup(ShadeAC ac){
        long tick = System.currentTimeMillis();

        status = ac.config.getBoolean( "autopunish.status" );
        reason = ac.config.getString( "autopunish.reason" ).replace( "&","§" ).replace( "§§","&" ).replace( "%%n%%","\n" );
        String typestr = ac.config.getString( "autopunish.type" );
        vl_total = ac.config.getInt( "autopunish.vl-total" );
        if( typestr.equalsIgnoreCase( "BAN" ) ){
            type = Type.BAN;
        } else if( typestr.equalsIgnoreCase( "KICK" ) ){
            type = Type.KICK;
        } else if( typestr.equalsIgnoreCase( "CUSTOM" ) ){
            type = Type.CUSTOM;
            command = ac.config.getString( "autopunish.custom-command" );
        } else if( typestr.equalsIgnoreCase( "NONE" ) || typestr.equalsIgnoreCase( "DISABLE" ) ){
            type = Type.NONE;
        }
        typestr = ac.config.getString( "autopunish.vl-type" );
        if( typestr.equalsIgnoreCase( "total" ) ){
            vlType = VL_Type.TOTAL;
        }else if( typestr.equalsIgnoreCase( "check" ) ){
            vlType = VL_Type.CHECK;
        }
        typestr = ac.config.getString( "autopunish.case.player" );
        if( typestr.equalsIgnoreCase( "upper" ) ){
            ply = Case.UPPER;
        }else if( typestr.equalsIgnoreCase( "lower" ) ){
            ply = Case.LOWER;
        }else if( typestr.equalsIgnoreCase( "default" ) ){
            ply = Case.DEFAULT;
        }
        typestr = ac.config.getString( "autopunish.case.check" );
        if( typestr.equalsIgnoreCase( "upper" ) ){
            chck = Case.UPPER;
        }else if( typestr.equalsIgnoreCase( "lower" ) ){
            chck = Case.LOWER;
        }else if( typestr.equalsIgnoreCase( "default" ) ){
            chck = Case.DEFAULT;
        }
        typestr = ac.config.getString( "autopunish.case.type" );
        if( typestr.equalsIgnoreCase( "upper" ) ){
            chck = Case.UPPER;
        }else if( typestr.equalsIgnoreCase( "lower" ) ){
            chck = Case.LOWER;
        }else if( typestr.equalsIgnoreCase( "default" ) ){
            chck = Case.DEFAULT;
        }


        animation = ac.config.getBoolean( "autopunish.animation" );

        System.out.println( "ShadeAC> AutoPunish(Util) is loaded Successfully in " + ( System.currentTimeMillis() - tick ) + "ms." );
    }

    public static void ban(Player p,Check check){
        if( type == Type.NONE || !status )return;
        ShadeUtil.setUpPlayerViolation( p );
        if( type == Type.CUSTOM ){
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                        if( ply == Case.UPPER ){
                            Bukkit.dispatchCommand( Bukkit.getConsoleSender() , replacer( command.replace( "%%player%%" , p.getName().toUpperCase() ) , check ) );
                        }
                        if( ply == Case.LOWER ){
                            Bukkit.dispatchCommand( Bukkit.getConsoleSender() , replacer( command.replace( "%%player%%" , p.getName().toLowerCase() ) , check ) );
                        }
                        if( ply == Case.DEFAULT ){
                            Bukkit.dispatchCommand( Bukkit.getConsoleSender() , replacer( command.replace( "%%player%%" , p.getName() ) , check ) );
                        }
                    }
                }
            }.runTaskLater( ShadeAC.getPlugin(), 5L);
            return;
        }
        if( type == Type.BAN ){
            Bukkit.getBanList( BanList.Type.NAME ).addBan( p.getName() , reason,null,null );
        }
        if( animation ){
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                        if( type == Type.CUSTOM ){

                        } else {
                            p.kickPlayer( replacer( reason.replace( "%%player%%" , p.getName().toLowerCase() ) , check ) );
                        }
                    }
                }
            }.runTaskLater( ShadeAC.getPlugin(), 20L);
            for( int i = 0; i < 500; i++ ){
                if( p != null ){
                    p.sendMessage( "§c§k§l1234567890QWERTYUIOPASDFGHJKLZXCVBNM1234567890QWERTYUIOPASDFGHJKLZXCVBNM1234567890QWERTYUIOPASDFGHJKLZXCVBNM" );
                }
            }
        }else {
            new BukkitRunnable() {
                public void run() {
                    if( p != null ){
                        p.kickPlayer( replacer( reason.replace( "%%player%%" , p.getName().toLowerCase() ) , check ) );
                    }
                }
            }.runTaskLater( ShadeAC.getPlugin(), 5L);
        }
    }

    public static String replacer(String str,Check check){
        if( check != null ){
            if( chck == Case.UPPER ){
                str = str.replace( "%%check%%",check.getCheck().toUpperCase() );
            }
            if( chck == Case.LOWER ){
                str = str.replace( "%%check%%",check.getCheck().toLowerCase() );
            }
            if( chck == Case.DEFAULT ){
                str = str.replace( "%%check%%",check.getCheck() );
            }

            if( typi == Case.UPPER ){
                str = str.replace( "%%check%%",check.getCheck().toUpperCase() );
            }
            if( typi == Case.LOWER ){
                str = str.replace( "%%check%%",check.getCheck().toLowerCase() );
            }
            if( typi == Case.DEFAULT ){
                str = str.replace( "%%check%%",check.getCheck() );
            }
        } else {
            str = str.replace( "%%check%%","Unknown" );
            str = str.replace( "%%check%%","Unknown" );
        }

        return str;
    }


}
