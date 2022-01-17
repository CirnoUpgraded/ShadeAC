package top.n0rthmaster123.shadeac.gui;

import javafx.scene.control.CheckBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.*;

public class GUIListener implements Listener {

    @EventHandler
    public void onPickUp(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked( );
        Inventory open = e.getClickedInventory( );
        ItemStack item = e.getCurrentItem( );
        if ( open == null ) {
            return;
        }
        if( item == null ){
            return;
        }
        if( item.getItemMeta() == null ){
            return;
        }
        String openname = open.getName( );
        String itemname = item.getItemMeta().getDisplayName();
        if ( openname.contains( "ShadeAC[Menu]" ) ) {
            e.setCancelled( true );
            if( itemname.contains( "Status" ) ){
                ShadeUtil.status = !ShadeUtil.status;
                player.openInventory( new GUICreator( GUICreator.InvType.MENU , "" ).inv );
            }
            if( itemname.contains( "Checks" ) ){
                //player.openInventory( new GUICreator( GUICreator.InvType.CHECKS_CATEGORY , "" ).inv );
                player.openInventory( new GUICreator( GUICreator.InvType.CHECK_CATEGORY , null  ).inv );
            }
            if( itemname.equalsIgnoreCase( "Themes" ) ){
                player.openInventory( new GUICreator( GUICreator.InvType.THEME,"" ).inv );
            }
        }
        if( openname.contains( "ShadeAC[Checks]" ) ){
            e.setCancelled( true );
            if( itemname.contains( "Back to " )){
                String target = itemname.replace( "Back to " , "" );
                if( target.equalsIgnoreCase( "Category" ) ){
                    //player.openInventory( new GUICreator( GUICreator.InvType.CHECKS_CATEGORY , "" ).inv );
                    player.openInventory( new GUICreator( null , null ).newCheck( CheckUtil.getCheck( open.getItem( 0 ).getItemMeta().getDisplayName().replace( " ","" ).replace( "."," " ).split( " " )[0]  , "A" ).category ).inv);
                } else if( target.equalsIgnoreCase( "Menu" ) ){
                    player.openInventory( new GUICreator( GUICreator.InvType.MENU , "" ).inv );
                } else if( target.equalsIgnoreCase( "CheckCategory" ) ){
                    player.openInventory( new GUICreator( GUICreator.InvType.CHECK_CATEGORY,null ).inv );
                }
                return;
            }

            if( itemname.equalsIgnoreCase( "Movement" ) ){
                player.openInventory( new GUICreator( null , null ).newCheck( CheckCategory.MOVEMENT ).inv );
                return;
            }else if( itemname.equalsIgnoreCase( "Player" ) ){
                player.openInventory( new GUICreator( null , null ).newCheck( CheckCategory.PLAYER ).inv );
                return;
            }else if( itemname.equalsIgnoreCase( "Combat" ) ){
                player.openInventory( new GUICreator( null , null ).newCheck( CheckCategory.COMBAT ).inv );
                return;
            }


            if( itemname.contains( "." ) ){ // checks from category
                player.openInventory( new GUICreator( GUICreator.InvType.CHECK , itemname ).inv );
            }else { //categories.
                if( CheckUtil.getCheck( itemname , "A" ) != null ){
                    player.openInventory( new GUICreator( GUICreator.InvType.CHECKS_BY_CATEGORY , itemname ).inv );
                }
            }
        }
        if( openname.contains( "ShadeAC[Check]" ) ){
            e.setCancelled( true );
            if( itemname.contains( "Back to " )){
                String target = itemname.replace( "Back to " , "" );
                //player.openInventory( new GUICreator( GUICreator.InvType.CHECKS_BY_CATEGORY , target ).inv );
                //player.openInventory( new GUICreator( GUICreator.InvType.CHECKS_BY_CATEGORY , target ).inv );
                //player.openInventory( new GUICreator( null , null ).newCheck( CheckUtil.getCheck( open.getItem( 11 ).getItemMeta().getDisplayName().replace( "Disable§r","Enable§r").split( "Enable§r " )[1].replace( "."," " ).split( " " )[0],"A" ).category ).inv );
                player.openInventory( new GUICreator( GUICreator.InvType.CHECKS_BY_CATEGORY , open.getItem( 11 ).getItemMeta().getDisplayName().replace( "Disable§r","Enable§r").split( "Enable§r " )[1].replace( "."," " ).split( " " )[0] ).inv);
                return;
            }
            if( itemname.contains( "AutoBan" ) ){
                if( open.getItem( 11 ) != null && open.getItem( 11 ).getItemMeta() != null ){
                    String path = open.getItem( 11 ).getItemMeta().getDisplayName().replace( "Disable§r","Enable§r").split( "Enable§r " )[1].replace( "."," " );
                    Check check = CheckUtil.getCheck( path.split( " " )[0] , path.split( " " )[1] );
                    CheckUtil.setCheckAutoBan( ShadeAC.getPlugin() , check );
                    player.openInventory( new GUICreator( GUICreator.InvType.CHECK , check.getCheck() + "." + check.getType() ).inv );
                }
                return;
            }
            String path = itemname.replace( "Disable§r","Enable§r").split( "Enable§r " )[1].replace( "."," " );
            Check check = CheckUtil.getCheck( path.split( " " )[0] , path.split( " " )[1] );
            CheckUtil.setCheckStatus( ShadeAC.getPlugin() , check );
            player.openInventory( new GUICreator( GUICreator.InvType.CHECK , check.getCheck() + "." + check.getType() ).inv );
        }
        if( openname.contains( "ShadeAC[Themes]" ) ){
            e.setCancelled( true );
            if( itemname.contains( "Back to Menu" ) ){
                player.openInventory( new GUICreator( GUICreator.InvType.MENU , null ).inv );
                return;
            }
            int rp = AlertThemeUtil.setTheme( itemname );
            if( rp == 0 ){
                player.sendMessage( ShadeAC.prefix + "§aSuccessfully to changed the theme to §e" + itemname );
                player.closeInventory();
                return;
            }
            player.sendMessage( ShadeAC.prefix + "§cFailed to changed the theme to §e" + itemname );
            player.openInventory( new GUICreator( GUICreator.InvType.THEME,null ).inv );
        }
    }
}
