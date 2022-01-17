package top.n0rthmaster123.shadeac.check;

import org.bukkit.Bukkit;
import top.n0rthmaster123.shadeac.ShadeAC;
import top.n0rthmaster123.shadeac.check.checks.combat.autoclicker.*;
import top.n0rthmaster123.shadeac.check.checks.combat.reach.*;
import top.n0rthmaster123.shadeac.check.checks.movement.badpackets.*;
import top.n0rthmaster123.shadeac.check.checks.movement.flight.*;
import top.n0rthmaster123.shadeac.check.checks.movement.groundspoof.*;
import top.n0rthmaster123.shadeac.check.checks.movement.motion.*;
import top.n0rthmaster123.shadeac.check.checks.movement.speed.*;
import top.n0rthmaster123.shadeac.check.checks.movement.step.*;

import java.util.ArrayList;

public class CheckUtil {

    public static ArrayList<Checker> checks = new ArrayList<>( );

    public static void setup(ShadeAC ac) {
        long tick = System.currentTimeMillis();
        //checks.add( new CheckClass( new Check( "CheckName" , "A" , ac ) ) );
        checks.clear();


        checks.add( new AutoClickerA( new Check( "AutoClicker","A", ac , CheckCategory.COMBAT ) ) );
        checks.add( new AutoClickerB( new Check( "AutoClicker","B", ac , CheckCategory.COMBAT ) ) );
        checks.add( new ReachA( new Check( "Reach","A", ac , CheckCategory.COMBAT ) ) );
        checks.add( new FlightA( new Check( "Flight","A", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new FlightB( new Check( "Flight","B", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new FlightC( new Check( "Flight","C", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new FlightD( new Check( "Flight","D", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new FlightE( new Check( "Flight","E", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new SpeedA( new Check( "Speed","A", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new SpeedB( new Check( "Speed","B", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new SpeedC( new Check( "Speed","C", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new SpeedD( new Check( "Speed","D", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new MotionA( new Check( "Motion","A", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new MotionB( new Check( "Motion","B", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new MotionC( new Check( "Motion","C", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new MotionD( new Check( "Motion","D", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new MotionE( new Check( "Motion","E", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new StepA( new Check( "Step","A", ac , CheckCategory.MOVEMENT ) ) );
        //checks.add( new StepB( new Check( "Step","B", ac , CheckCategory.MOVEMENT ) ) );
        checks.add( new BadPacketsA( new Check( "BadPackets","A", ac , CheckCategory.PLAYER ) ) );
        checks.add( new BadPacketsB( new Check( "BadPackets","B", ac , CheckCategory.PLAYER ) ) );
        checks.add( new GroundSpoofA( new Check( "GroundSpoof","A", ac , CheckCategory.PLAYER ) ) );

        for ( Checker check : checks ) {
            Bukkit.getPluginManager( ).registerEvents( check, ac );
            //System.out.println( "ShadeAC> " + check.check.getCheck() + " " + check.check.getStatus() + " is loaded!" );
        }

        System.out.println( "ShadeAC> Checks is loaded Successfully in " + ( System.currentTimeMillis() - tick ) + "ms." );
    }

    public static Check getCheck(String check,String type){
        for( Checker ck : checks ){
            if( ck.check.check.equalsIgnoreCase( check ) && ck.check.type.equalsIgnoreCase( type ) ){
                return ck.check;
            }
        }
        return null;
    }

    public static void setCheckStatus(ShadeAC ac,Check check){
        ac.config.set( "checks." + check.getCheck() + "." + check.getType() + ".status", !check.status );
        ac.saveConfig();
        ac.reloadConfig();
        ac.config = ac.getConfig();
        setup( ac );
    }

    public static void setCheckAutoBan(ShadeAC ac,Check check){
        ac.config.set( "checks." + check.getCheck() + "." + check.getType() + ".punish" , !check.autoban );
        ac.saveConfig();
        ac.reloadConfig();
        ac.config = ac.getConfig();
        setup( ac );
    }
}
