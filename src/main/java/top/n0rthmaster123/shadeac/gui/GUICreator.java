package top.n0rthmaster123.shadeac.gui;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.*;

import java.util.ArrayList;
import java.util.List;

public class GUICreator {

    public enum InvType{
        MENU,CHECKS_CATEGORY,CHECKS_BY_CATEGORY,CHECK,THEME,CHECK_CATEGORY
    }

    public Inventory inv;

    public GUICreator(InvType type,String cate){
        if( type == InvType.MENU ){
            InvGUI gui = new InvGUI();
            gui.create( 3,9,"ShadeAC[Menu]" );
            List about = new ArrayList();
            about.add( "Author: " + ShadeAC.author );
            about.add( "Version: " + ShadeAC.version );
            about.add( "Status: " + ( ShadeUtil.status ? "§aEnable" : "§cDisable" ) );
            gui.addItem( Material.NETHER_BRICK , "About",1,about,12 );
            gui.addItem( Material.CHEST , "Checks",1,10 );
            List list = new ArrayList();
            list.add( "Click to " + ( ShadeUtil.status ? "§cDisable" : "§aEnable" ) );
            Material mat = ( ShadeUtil.status ? Material.ENCHANTED_BOOK : Material.BOOK );
            gui.addItem( mat  , "Status",1,list ,14  );
            gui.addItem( Material.EYE_OF_ENDER , "Themes",1,16 );
            inv = gui.getInventory();
        }else if( type == InvType.CHECKS_CATEGORY ){
            InvGUI gui = new InvGUI();
            gui.create( 5,9 , "ShadeAC[Checks]" ); //category
            int slot = -1;
            List added = new ArrayList();
            for( Checker chk : CheckUtil.checks ){
                if( !added.contains( chk.check.getCheck() ) ){
                    slot++;
                    int amount = 0;
                    for( Checker chk1 : CheckUtil.checks ){
                        if( chk1.check.getCheck().equalsIgnoreCase( chk.check.getCheck() ) ){ amount++; }
                        //bad performance... :(
                    }
                    if( chk.check.getCheck() == "Flight" || chk.check.getCheck() == "Speed" ){
                        gui.addItem( Material.FEATHER , chk.check.getCheck() , amount ,slot );
                    }
                    else if( chk.check.getCheck() == "Motion" ){
                        gui.addItem( Material.RABBIT_FOOT, chk.check.getCheck() , amount ,slot );
                    }
                    else if( chk.check.getCheck() == "GroundSpoof"){
                        gui.addItem( Material.GRASS , chk.check.getCheck() , amount ,slot );
                    }
                    else if( chk.check.getCheck() == "BadPackets"){
                        gui.addItem( Material.BARRIER , chk.check.getCheck() , amount ,slot );
                    }
                    else if( chk.check.getCheck() == "Step"){
                        gui.addItem( Material.WOOD_STAIRS , chk.check.getCheck() , amount ,slot );
                    }
                    else{
                        gui.addItem( Material.BOOKSHELF , chk.check.getCheck() , amount ,slot );
                    }
                    added.add( chk.check.getCheck() );
                }
            }
            gui.addItem( Material.ARROW , "Back to CheckCategory" ,1 ,44 );
            //gui.addItem( Material.ARROW , "Back to Menu" ,1 ,44 );
            inv = gui.getInventory();
        }else if( type == InvType.CHECKS_BY_CATEGORY ){
            InvGUI gui = new InvGUI();
            gui.create( 5,9 , "ShadeAC[Checks]" ); //checks from category
            int slot = -1;

            for( Checker chk : CheckUtil.checks ){
                Check check = chk.check;
                if( check.getCheck().equalsIgnoreCase( cate ) ){
                    slot++;
                    gui.addItem( ( check.getStatus() ? Material.ENCHANTED_BOOK : Material.BOOK ) , check.getCheck() + "." + check.getType() , 1,slot );
                }
            }

            gui.addItem( Material.ARROW , "Back to Category" ,1 ,44 );
            inv = gui.getInventory();

        }else if( type == InvType.CHECK ){
//            InvGUI gui = new InvGUI();
//            cate = cate.replace( "."," " );
//            Check check = CheckUtil.getCheck( cate.split( " " )[0] , cate.split( " " )[1] );
//            gui.create( 3,9,"ShadeAC[Check]" );
//            gui.addItem( ( check.getStatus() ? Material.ENCHANTED_BOOK : Material.BOOK ) , "Click to " + ( !check.getStatus() ? "§cDisable§r" : "§aEnable§r" ) + " " + check.getCheck() + "." + check.getType() ,1 ,11 );
//            gui.addItem( Material.ARROW , "Back to " + check.getCheck() ,1 ,15 );
//            inv = gui.getInventory();
            InvGUI gui = new InvGUI();
            cate = cate.replace( "."," " );
            Check check = CheckUtil.getCheck( cate.split( " " )[0] , cate.split( " " )[1] );
            gui.create( 3,9,"ShadeAC[Check]" );
            gui.addItem( ( check.getStatus() ? Material.ENCHANTED_BOOK : Material.BOOK ) , "Click to " + ( check.getStatus() ? "§cDisable§r" : "§aEnable§r" ) + " " + check.getCheck() + "." + check.getType() ,1 ,11 );
            ItemStack dye = new ItemStack(Material.INK_SACK, 1, ( check.autoban ? DyeColor.PURPLE.getData() : 8 ) );
            ItemMeta meta = dye.getItemMeta();
            meta.setDisplayName( "AutoBan" );
            dye.setItemMeta( meta );
            gui.addItem( dye , 13 );

            gui.addItem( Material.ARROW , "Back to " + check.getCheck() ,1 ,15 );
            inv = gui.getInventory();
        }else if( type == InvType.THEME ){
            InvGUI gui = new InvGUI();
            gui.create( 5,9 ,"ShadeAC[Themes]" );
            int i = -1;
            for( AlertTheme theme : AlertThemeUtil.getList() ){
                i++;
                gui.addItem( theme.getIcon(),theme.name,1,gui.getAsList( "Author:" + theme.ath + "\nDescription:\n" + theme.desc ) , i );
            }
            gui.addItem( Material.ARROW , "Back to Menu" ,1 ,44 );
            inv = gui.getInventory();
        }else if( type == InvType.CHECK_CATEGORY ){
            InvGUI gui = new InvGUI();
            gui.create( 3,9 ,"ShadeAC[Checks]" );
            gui.addItem( Material.DIAMOND_SWORD , "Combat",1, 11);
            gui.addItem( Material.FEATHER , "Movement",1, 13);
            ItemStack item = new ItemStack( Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setDisplayName( ChatColor.RESET + "Alex" );
            meta.setOwner("Alex");
            item.setItemMeta(meta);
            gui.addItem( item , "Player",1, 15);
            gui.addItem( Material.ARROW , "Back to Menu" ,1 ,26 );
            inv = gui.getInventory();
        }
    }


    public GUICreator newCheck(CheckCategory cate ){
        InvGUI gui = new InvGUI();
        gui.create( 5,9 , "ShadeAC[Checks]" ); //category
        int slot = -1;
        List added = new ArrayList();
        for( Checker chk : CheckUtil.checks ){
            if( !added.contains( chk.check.getCheck() ) && chk.check.category == cate ){
                slot++;
                int amount = 0;
                for( Checker chk1 : CheckUtil.checks ){
                    if( chk1.check.getCheck().equalsIgnoreCase( chk.check.getCheck() ) ){ amount++; }
                    //bad performance... :(
                }
                if( chk.check.getCheck() == "Flight" || chk.check.getCheck() == "Speed" ){
                    gui.addItem( Material.FEATHER , chk.check.getCheck() , amount ,slot );
                }
                else if( chk.check.getCheck() == "Motion" || chk.check.getCheck() == "VClip" ){
                    gui.addItem( Material.RABBIT_FOOT, chk.check.getCheck() , amount ,slot );
                }
                else if( chk.check.getCheck() == "GroundSpoof"){
                    gui.addItem( Material.GRASS , chk.check.getCheck() , amount ,slot );
                }
                else if( chk.check.getCheck() == "BadPackets"){
                    gui.addItem( Material.BARRIER , chk.check.getCheck() , amount ,slot );
                }
                else if( chk.check.getCheck() == "Step"){
                    gui.addItem( Material.WOOD_STAIRS , chk.check.getCheck() , amount ,slot );
                }
                else{
                    gui.addItem( Material.BOOKSHELF , chk.check.getCheck() , amount ,slot );
                }
                added.add( chk.check.getCheck() );
            }
        }
        gui.addItem( Material.ARROW , "Back to CheckCategory" ,1 ,44 );
        inv = gui.getInventory();
        return this;
    }

}
