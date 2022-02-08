package top.n0rthmaster123.shadeac.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.Check;
import top.n0rthmaster123.shadeac.check.CheckUtil;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeUtil;
import top.n0rthmaster123.shadeac.gui.GUICreator;


public class Shade implements CommandExecutor {

    public final String help =
            "§b§m=================================================\n" +
            "§8Shade §aAnti§cCheat§b " + ShadeAC.version + "§a By§b " + ShadeAC.author + "\n" +
            "§b§m=================================================§r\n \n" +
            "§b/shade menu§7 - Opens ShadeAC Control Panel.\n" +
            "§b/shade§7 or§b /shade help§7 - Show ShadeAC commands and info.\n" +
            "§b/shade violations <player>§7 or§b/violations <player>§7 - Show player's all violations.\n" +
            "§b/shade reset <[check(example: Fly.A)]/checks/vl/help> <player>§7 - Reset player's vl.\n" +
            "§b/shade alerts§7 or §b/alerts§7 - Enable or Disable Alert Log.\n \n" +
            "§b§m=================================================";

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if( args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase( "help" ) ) ){
            if( sender.hasPermission( "shade.help" ) ){
                sender.sendMessage( help );
                return true;
            }
        }
        if( args.length == 1 && args[0].equalsIgnoreCase( "alerts" ) ){
            if ( sender.hasPermission( "shade.alerts" ) && sender instanceof Player ) {
                Player p = ( (Player) sender ).getPlayer();
                ShadeUtil.setAlerEnabled( p , !ShadeUtil.isAlertEnabled( p ) );
                p.sendMessage( ShadeAC.prefix + " §eAlert Log is " + ( ShadeUtil.isAlertEnabled( p ) ? "§aEnabled" : "§cDisabled" ) );
                return true;
            }
        }
        if( args.length == 1 && args[0].equalsIgnoreCase( "menu" ) ){
            //sender.sendMessage( "§cComing soon!" );
            if( sender instanceof Player && sender.hasPermission( "shade.menu" ) ){
                Player p = ( (Player) sender ).getPlayer();
                p.openInventory( new GUICreator( GUICreator.InvType.MENU , "" ).inv );
                return true;
            }
        }
        if( args.length == 2 && args[0].equalsIgnoreCase( "violations" ) ){
            if( sender.hasPermission( "shade.violations" ) ){
                Player p = Bukkit.getPlayer( args[1] );
                if( p != null ) {
                    ShadeUtil.setUpPlayerViolation( p );
                    String lines = "§b§m=================================================§r\n§c" + p.getName() + "§b's violations ( §4" + ( ShadeUtil.getVL( null , p ) > 0 ? ShadeUtil.getVL( null , p ) : "NONE" ) + "§b )";
                    int a = 0;
//                    if( ShadeUtil.getVL( null ,p ) == 0 ) {
//                        lines = lines + "\n§bThis Player has NO violations.";
//                    } else{
//
//                    }
                    for( Checker check : CheckUtil.checks ){
                        int vl = ShadeUtil.getVL( check.check , p );
                        if( vl > 0 ){
                            a++;
                            lines = lines + "\n§b" + check.check.getCheck() + " " + check.check.getType() + ":§4 " + vl;
                        }
                    }
                    if( a == 0 ){
                        lines = lines + "\n§bThis Player has NO violations.";
                    }
                    sender.sendMessage( lines + "\n§b§m=================================================" );
                    return true;
                }
                sender.sendMessage( ShadeAC.prefix + " §cPlayer not found." );
                return true;
            }
        }
        String prefix = " §7(§cRESET-VIOLATION§7) ";
        if( args.length == 2 && args[0].equalsIgnoreCase( "reset" ) ){
            if( sender.hasPermission( "shade.reset" ) ){
                sender.sendMessage( ShadeAC.prefix + prefix + "§aMore information for the command: " +
                        "\n§bvl§7 - player's total violations count." +
                        "\n§b[check]§7 - player's violation count per check." +
                        "\n§bchecks§7 - player's all violation count per check." );
                return true;
            }
        }
        if( args.length == 3 && args[0].equalsIgnoreCase( "reset" ) ){
            if( sender.hasPermission( "shade.reset" ) ){
                Player p = Bukkit.getPlayer(args[2] );
                args[1] = args[1].replace( ".","-" );
                ShadeUtil.setUpPlayerViolation( p ); // anti null
                //Check check = CheckUtil.getCheck( args[1].split( "-" )[0] ,args[1].split( "-" )[1] );
                if( p != null ){
                    if( args[1].equalsIgnoreCase( "help" ) ){
                        sender.sendMessage( ShadeAC.prefix + prefix + "§aMore information for the command: " +
                                "\n§bvl§7 - player's total violations count." +
                                "\n§b[check]§7 - player's violation count per check." +
                                "\n§bchecks§7 - player's all violation count per check." );
                    }else if( args[1].equalsIgnoreCase( "checks" ) ){
                        for( Checker chk : CheckUtil.checks ){
                            ShadeUtil.addVL( chk.check , p , 0 );
                        }
                        sender.sendMessage( ShadeAC.prefix + prefix + "§e" + args[2] + "§a's CHECKS is cleared." );
                    }else if( args[1].equalsIgnoreCase( "vl" ) ){
                        ShadeUtil.addVL( null , p , 0 );
                        sender.sendMessage( ShadeAC.prefix + prefix + "§e" + args[2] + "§a's VL is cleared." );
                    }else if(  args[1].split( "-" ).length == 1 ){
                        sender.sendMessage( ShadeAC.prefix + prefix + " §cCheck not found." );
                    }else if(  CheckUtil.getCheck( args[1].split( "-" )[0] ,args[1].split( "-" )[1] ) != null ){
                        Check check =  CheckUtil.getCheck( args[1].split( "-" )[0] ,args[1].split( "-" )[1] );
                        ShadeUtil.addVL( check , p , 0 );
                        sender.sendMessage( ShadeAC.prefix + prefix + "§e" + args[2] + "§a's CHECK(" + check.getCheck() + " " + check.getType() + ") is cleared." );
                    }else {
                        sender.sendMessage( ShadeAC.prefix + prefix + " §cCheck not found." );
                    }
                }else {
                    sender.sendMessage( ShadeAC.prefix + prefix + " §cPlayer not found." );
                }
                return true;
            }
        }
        sender.sendMessage( ShadeAC.prefix + " §cYou cannot use this command!" );
        return true;
    }
}
