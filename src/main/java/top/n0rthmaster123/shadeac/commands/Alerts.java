package top.n0rthmaster123.shadeac.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.ShadeUtil;

public class Alerts implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if ( sender.hasPermission( "shade.alerts" ) && sender instanceof Player ) {
            Player p = ( (Player) sender ).getPlayer();
            ShadeUtil.setAlerEnabled( p , !ShadeUtil.isAlertEnabled( p ) );
            p.sendMessage( ShadeAC.prefix + " §eAlert Log is " + ( ShadeUtil.isAlertEnabled( p ) ? "§aEnabled" : "§cDisabled" ) );
            return true;
        }
        sender.sendMessage( ShadeAC.prefix + " §c/shade help" );
        return true;
    }
}
