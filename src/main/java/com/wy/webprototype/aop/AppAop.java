package com.wy.webprototype.aop;

import static com.wy.webprototype.util.ComLogger.info;
import static com.wy.webprototype.util.ComLogger.isInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AppAop {

	private boolean isMonitorRunningTime;

	public boolean isMonitorRunningTime() {
		return isMonitorRunningTime;
	}

	public void setIsMonitorRunningTime(boolean isMonitorRunningTime) {
		this.isMonitorRunningTime = isMonitorRunningTime;
	}

	public void doBefore(JoinPoint jp) {
		if (isInfo()) {
			info("Entering method - " + jp.getTarget().getClass().getName()
					+ "." + jp.getSignature().getName());
		}
	}

	public void doAfter(JoinPoint jp) {
		if (isInfo()) {
			info("Leaving method - " + jp.getTarget().getClass().getName()
					+ "." + jp.getSignature().getName());
		}
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		if (isMonitorRunningTime && isInfo()) {
			long time = System.currentTimeMillis();
			Object retVal = pjp.proceed();
			time = System.currentTimeMillis() - time;
			info("Process time: " + time + " ms");
			return retVal;
		} else {
			return pjp.proceed();
		}
	}

}
