package top.n0rthmaster123.shadeac.check;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.gui.DyeItemColor;

import java.util.ArrayList;

public class AlertThemeUtil {

    static ArrayList<AlertTheme> themes = new ArrayList<>();
    static AlertTheme currentTheme;

    public static ArrayList<AlertTheme> getList(){
        return themes;
    }

    public static void setup(){
        long tick = System.currentTimeMillis();

        themes.clear();

        themes.add( new AlertTheme( "Default", ShadeAC.prefix + "§c %%player%%§7 failed§6 %%check%%§8(§eVL§a %%vl-check%%§8/§a%%vl%%§8) (§eType:§a %%type%%§8) (§eInfo:§a %%info%% §8)" , Material.BEDROCK , "BaGuAr","Default Theme." ) );
        themes.add( new AlertTheme( "Cheese" , "§7[§8§lSHADE§r§7]§f ➤ §r§6%%player%%§r§f failed:§r §6%%check%% (%%type%%) §8(§7VL:§c %%vl-check%%§8/§c%%vl%%§8) (§7Information:§c %%info%% §8)" , Material.GOLD_BLOCK , "BaGuAr","CheeseAC theme. If you like cheeseac's theme!" ) );
        themes.add( new AlertTheme( "SimpleGray" , "§7§lSHADE§r§7≫§r§c %%player%%§r§7 failed§r §a%%check%% §7[§aType%%type%%§7] [VL:§c %%vl-check%%§7/§c%%vl%%§7] [§7Information:§c %%info%% §7]" , new DyeItemColor( DyeColor.GRAY.getData() ).stack , "BaGuAr","Simple(Gray)theme. " ) );
        themes.add( new AlertTheme( "SimpleDark" , "§8§lSHADE§r§8≫§r§c %%player%%§r§8 failed§r §a%%check%% §8[§aType%%type%%§8] [VL:§c %%vl-check%%§8/§c%%vl%%§8] [§8Information:§c %%info%% §8]" , new DyeItemColor( DyeColor.BLACK.getData() ).stack , "BaGuAr","Simple(Dark) theme" ) );
        themes.add( new AlertTheme( "Alice" , "§7§l[§r§6Shade§7§l]§f§l»§r §6%%player%%§r§f failed:§r §6%%check%% (%%type%%) §r§f| ping:§6 %%ping%%§f | vl:§6 %%vl-check%%§f/§6%%vl%%§f | Information:§6 %%info%%" , Material.DIAMOND_ORE , "BaGuAr","I add this theme because Alice's theme looks like cool!" ) );
        themes.add( new AlertTheme( "LimePurple","§8[§7SHADE§8]§5 %%player%%§7 violated §a%%check%%§8(§b%%type%%§8) (§bx%%vl-check%%/%%vl%%§8) (Info: §b%%info%% §8)" , new DyeItemColor( DyeColor.LIME.getData() ).stack , "BaGuAr","Lime and purple xD" ) );
        themes.add( new AlertTheme( "NNC","§3[SAC]§7 %%player%%§f was detected with a§7 %%check-upper%%_%%type%%§f Hack! %%info%%, vl: %%vl-check%%/%%vl%%" , Material.GRAVEL , "BaGuAr","I add this theme because　NiroNoCheat's theme looks like cool!" ) );
        themes.add( new AlertTheme( "GODSEYE1","§6ShadeAC >>§c %%player%%§7 failed§c %%check%%§8 [§fType%%type%%§8] [§fx%%vl-check%%§7/§f%%vl%%§8] [§fInfo: %%info%% §8]" , Material.EYE_OF_ENDER , "BaGuAr","Within Info Message.\nI add this theme because GodsEye's theme looks like simple and cool!" ) );
        themes.add( new AlertTheme( "GODSEYE2","§6ShadeAC >>§c %%player%%§7 failed§c %%check%%§8 [§fType%%type%%§8] [§fx%%vl-check%%§7/§f%%vl%%§8]" , Material.EYE_OF_ENDER , "BaGuAr","Without Info Message.\nI add this theme because GodsEye's theme looks like simple and cool!" ) );
        themes.add( new AlertTheme( "Karhu1","§8[§b§lShade§r§8]§3 %%player%%§8 -§3 %%check%% %%type%% §b(x%%vl-check%%/%%vl%%) (Info: %%info%% )" , Material.ICE , "BaGuAr","Within Info Message.\nI add this theme because　Karhu's theme looks like cool!" ) );
        themes.add( new AlertTheme( "Karhu2","§8[§b§lShade§r§8]§3 %%player%%§8 -§3 %%check%% %%type%% §b(x%%vl-check%%/%%vl%%)" , Material.ICE , "BaGuAr","Without Info Message.\nI add this theme because　Karhu's theme looks like cool!" ) );
        themes.add( new AlertTheme( "AdvancedCheatBlocker1","§7[§cSAC§7]§9 %%player%%§f has violated check§c %%check%%§7(§b%%type%%§7) §7(Info:§b %%info%%§7 ) §f(VL: %%vl-check%%§7/§f%%vl%%)" , Material.BEACON , "BaGuAr","Within Info Message.\nI added this theme because ACB's theme looks like cool!" ) );
        themes.add( new AlertTheme( "AdvancedCheatBlocker2","§7[§cSAC§7]§9 %%player%%§f has violated check§c %%check%%§7(§b%%type%%§7) §f(VL: %%vl-check%%§7/§f%%vl%%)" , Material.BEACON , "BaGuAr","Without Info Message.\nI added this theme because ACB's theme looks like cool!" ) );
        themes.add( new AlertTheme( "Vulcan1","§cShade §8>§c %%player%% §7failed§b %%check%%§7 *(§bType %%type%%§7)* (Info:§b %%info%%§7 ) (%%vl-check%%/%%vl%%)" , Material.LAVA_BUCKET , "BaGuAr","Within Info Message.\nRequest By Salers" ) );
        themes.add( new AlertTheme( "Vulcan2","§cShade §8>§c %%player%% §7failed§b %%check%%§7 *(§bType %%type%%§7)* (%%vl-check%%/%%vl%%)" , Material.LAVA_BUCKET , "BaGuAr","Without Info Message.\nRequest By Salers" ) );
        themes.add( new AlertTheme( "Buzz1","§b[AC]§e %%player%%§7 failed§e %%check%% %%type%% §7Info: §b%%info%% §7VL %%vl-check%%/%%vl%%" , Material.YELLOW_FLOWER , "BaGuAr","Within Info Message.\nRequest By Salers" ) );
        themes.add( new AlertTheme( "Buzz2","§b[AC]§e %%player%%§7 failed§e %%check%% %%type%% §7VL %%vl-check%%/%%vl%%" , Material.YELLOW_FLOWER , "BaGuAr","Without Info Message.\nRequest By Salers" ) );
        themes.add( new AlertTheme( "Verus1", "§9§lShade§8 >§f %%player%%§7 failed§f %%check%% %%type%%§7 VL[§9%%vl-check%%§7/§9%%vl%%§7] INFO[§9 %%info%% §7]" , Material.DIAMOND_SWORD , "BaGuAr","Within Info Message.\nRequest By Salers" ) );
        themes.add( new AlertTheme( "Verus2", "§9§lShade§8 >§f %%player%%§7 failed§f %%check%% %%type%%§7 VL[§9%%vl-check%%§7/§9%%vl%%§7]" , Material.DIAMOND_SWORD , "BaGuAr","Without Info Message.\nRequest By Salers" ) );
        themes.add( new AlertTheme( "Bridger1", "§7[§c✘§7]§e %%player%%§f detected using§c %%check%% (%%type%%)§7 info: %%info%%§a x%%vl-check%%§7/§a%%vl%%" , Material.BED , "BaGuAr","Within Info Message." ) );
        themes.add( new AlertTheme( "Bridger2", "§7[§c✘§7]§e %%player%%§f detected using§c %%check%% (%%type%%)§a x%%vl-check%%§7/§a%%vl%%" , Material.BED , "BaGuAr","Without Info Message." ) );
        themes.add( new AlertTheme( "SharpCube1", "§7(§c!§7)§e %%player%%§c using§e %%check-upper%%§7(§e%%type%%§7) (§e %%info%% §7) (§e%%vl-check%%§7/§e%%vl%%§7)" , Material.FIREBALL , "BaGuAr","Within Info Message.\nCube and Sharp!" ) );
        themes.add( new AlertTheme( "SharpCube2", "§7(§c!§7)§e %%player%%§c using§e %%check-upper%%§7(§e%%type%%§7) (§e%%vl-check%%§7/§e%%vl%%§7)" , Material.FIREBALL , "BaGuAr","Without Info Message.\nCube and Sharp!" ) );
        themes.add( new AlertTheme( "PAC", "§c§lSAC§f§l:§a %%player%%§7 failed§c %%check%% %%type%%§7 check [VL: %%vl-check%%/%%vl%%] [Info: %%info%% ]" , Material.PRISMARINE , "BaGuAr","I added this theme because PAC's theme looks like good!" ) );
        themes.add( new AlertTheme( "ACR", "§6§lSAC§r§7 %%player%% failed %%check%% %%type%%§8 |§7 %%info%% §8|§7 VL: %%vl-check%%/%%vl%%" , Material.GOLD_BLOCK , "BaGuAr","I added this theme because ACR's theme is cool!" ) );
        themes.add( new AlertTheme( "NAC", "§7(§6!§7)§6 %%player%%§7 // Reason:§6 %%check%% §7// Type:§6 %%type%%§7 // VL:§6 %%vl-check%%§7/§6%%vl%%" , Material.SEA_LANTERN , "BaGuAr","I added this theme because NAC's theme is cool!" ) );


        if( setTheme( ShadeAC.getPlugin().config.getString( "theme.type" ) ) != 0 ){
            setTheme( "Default" );
//            ShadeAC.getPlugin().config.set( "theme.type" , "Default" );
//            ShadeAC.getPlugin().saveConfig();
//            ShadeAC.getPlugin().reloadConfig();
//            ShadeAC.getPlugin().config = ShadeAC.getPlugin().getConfig();
        }

        System.out.println( "ShadeAC> AlertTheme(Util) is loaded Successfully in " + ( System.currentTimeMillis() - tick ) + "ms." );
    }

    public static int setTheme(String name){
        for( AlertTheme theme : themes ){
            if( theme.name.equalsIgnoreCase( name ) ){
                if( currentTheme == theme ){
                    return 2; //failed for Already.
                }
                currentTheme = theme;
                ShadeAC.getPlugin().config.set( "theme.type" , theme.name );
                ShadeAC.getPlugin().saveConfig();
                ShadeAC.getPlugin().reloadConfig();
                ShadeAC.getPlugin().config = ShadeAC.getPlugin().getConfig();
                return 0; //successfully
            }
        }
        return 1; //failed for Unknown name.
    }

    public static AlertTheme getCurrentTheme(){
        return currentTheme;
    }

}
