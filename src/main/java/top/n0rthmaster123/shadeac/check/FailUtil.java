package top.n0rthmaster123.shadeac.check;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FailUtil {


    public static HashMap<Player,List<Fail>> fails = new HashMap<>();


    public static List<Fail> getFails(Player p){
        List list = new ArrayList();
        if( fails.get( p ) == null ){
            clear( p );
            return null;
        }
        for( Fail fail : fails.get( p ) ){
            if( fail.failed ){
                list.add( fail );
            }
        }
        return list;
    }

    public static void fail(Player p,String info,Check check) {
        if( fails.get( p ) == null ){
            clear( p );
        }
        List toRemove = new ArrayList();
        List toAdd = new ArrayList();
        for( Fail fail : fails.get( p ) ){
            if( fail.check.getCheck() == check.getCheck() && fail.check.getType() == check.getType() && check.getStatus() ){
                Fail w = fail;
                w.failed = true;
                w.info = info;
                w.vbed = !info.equals( "" );
                toRemove.add( fail );
                toAdd.add( w );
            }
        }
        fails.get( p ).removeAll( toRemove );
        fails.get( p ).addAll( toAdd );

    }

    public static void clear(Player p){
        List aw = new ArrayList();
        for( Checker c : CheckUtil.checks ){
            aw.add( new Fail( "",c.check ) );
        }
        fails.put(p, aw);
    }

}
