package net.keylon.me.utils;

import org.bukkit.scheduler.BukkitRunnable;

import net.keylon.me.Core;
import net.keylon.me.events.GameEnd;

public class Timer extends BukkitRunnable {

    private Integer time;
    private Integer timeLeft;
    private Boolean started = false;
    public Timer(Integer totalTimeInSeconds) {
        time = totalTimeInSeconds;
        timeLeft = totalTimeInSeconds;
    }
    
    public void start() {
        if (!started) {
            timeLeft = time;
            runTaskTimer(Core.getInstance(), 0, 20);
        }
    }
    
    public void end() {
    	timeLeft = 1;
    	
    }
    
    public Integer getTimeRemaining() {
        return time;
    }
    
    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
        	GameEnd.gameEnd();
            cancel();
            started = false;
        }
    }
    
    
}

			