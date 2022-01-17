package top.n0rthmaster123.shadeac.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.CheckUtil;
import top.n0rthmaster123.shadeac.check.Checker;
import top.n0rthmaster123.shadeac.check.ShadeUtil;

public class Violations implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if( args.length == 1 ){
            if( sender.hasPermission( "shade.violations" ) ){
                Player p = Bukkit.getPlayer( args[0] );
                if( p != null ) {
                    ShadeUtil.setUpPlayerViolation( p );
                    String lines = "§b§m=================================================§r\n§c" + p.getName() + "§b's violations ( §4" + ( ShadeUtil.getVL( null , p ) > 0 ? ShadeUtil.getVL( null , p ) : "NONE" ) + "§b )";
                    int a = 0;
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
        sender.sendMessage( ShadeAC.prefix + " §c/shade help" );
        return true;
    }
}
