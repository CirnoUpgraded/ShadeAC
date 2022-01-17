package top.n0rthmaster123.shadeac.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InvGUI {

    Inventory inv;

    public InvGUI(){

    }

    public InvGUI create(int hight,int width,String name){
        inv = Bukkit.createInventory( null ,hight*width,name );
        return this;
    }


    public InvGUI create(Inventory inv) {
        this.inv = inv;
        return this;
    }

    public Inventory getInventory(){
        return inv;
    }

    public InvGUI addItem(ItemStack stack,int index){
        this.inv.setItem(index,stack);
        return this;
    }

    public InvGUI addItem(Material material,String name,int amount,List list,int index){
        ItemStack stack;
        stack = new ItemStack(material);
        stack.setAmount( amount );
        ItemMeta meta = stack.getItemMeta( );
        meta.setDisplayName( name );
        meta.setLore( list );
        stack.setItemMeta( meta );
        addItem(stack , index);
        return this;
    }

    public InvGUI addItem(Material material,String name,int amount,int index){
        ItemStack stack;
        stack = new ItemStack(material);
        stack.setAmount( amount );
        ItemMeta meta = stack.getItemMeta( );
        meta.setDisplayName( name );
        stack.setItemMeta( meta );
        addItem(stack , index);
        return this;
    }
    public InvGUI addItem(ItemStack stack,String name,int amount,int index){
        ItemStack newStack = stack.clone();
        newStack.setAmount( amount );
        ItemMeta meta = newStack.getItemMeta();
        meta.setDisplayName(name);
        newStack.setItemMeta(meta);
        this.inv.setItem(index,newStack);
        return this;
    }

    public InvGUI addItem(ItemStack stack,String name,int amount,List list,int index){
        ItemStack newStack = stack.clone();
        newStack.setAmount( amount );
        ItemMeta meta = newStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(list);
        newStack.setItemMeta(meta);
        this.inv.setItem(index,newStack);
        return this;
    }

    @Deprecated
    public List getAsList(String str){
        List list = new ArrayList();
        String[] strx = str.split( "\n" );
        for( String string : strx ){
            list.add( string );
        }
        return list;
    }
}