package top.n0rthmaster123.shadeac.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DyeItemColor {

    public ItemStack stack;

    public DyeItemColor(byte col){
        stack = new ItemStack(Material.INK_SACK,1,col);
    }
}
