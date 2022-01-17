package top.n0rthmaster123.shadeac.check;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AlertTheme {

    public String format = "";
    public String name = "";
    public ItemStack item;
    public String ath = "BaGuAr";
    public String desc = "No Description...";

    public AlertTheme(String name,String theme,Material item){
        this.name = name;
        format = theme;
        this.item = new ItemStack(item);
    }

    public AlertTheme(String name,String theme,ItemStack item){
        this.name = name;
        format = theme;
        this.item = item;
    }

    public AlertTheme(String name,String theme,Material item,String author,String description){
        this.name = name;
        format = theme;
        this.item = new ItemStack(item);
        ath = author;
        desc = description;
    }

    public AlertTheme(String name,String theme,ItemStack item,String author,String description){
        this.name = name;
        format = theme;
        this.item = item;
        ath = author;
        desc = description;
    }

    public AlertTheme(String name,String theme){
        this.name = name;
        format = theme;
        item =  new ItemStack(Material.BOOK);
    }

    public ItemStack getIcon(){
        return item;
    }

    public String getReplacedMessage(Player p,Check check,int vl,int check_vl,String info){
        return format.replace( "%%player%%",p.getName() ).replace( "%%check%%",check.getCheck() ).replace( "%%type%%",check.getType() ).replace( "%%vl%%" , vl + "" ).replace( "%%vl-check%%" ,check_vl + "" ).replace( "%%info%%" , info ).replace( "%%check-upper%%",check.getCheck().toUpperCase() );
    }

    public String getReplacedMessageExample(){
        return format.replace( "%%player%%","Player" ).replace( "%%check%%","Example" ).replace( "%%type%%","A").replace( "%%vl%%" , "1" ).replace( "%%vl-check%%" , "5" ).replace( "%%info%%" , "Nothing" ).replace( "%%check-upper%%","EXAMPLE" );
    }

}
