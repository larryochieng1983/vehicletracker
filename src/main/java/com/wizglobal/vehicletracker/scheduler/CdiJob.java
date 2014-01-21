/**
 * 
 */
package com.wizglobal.vehicletracker.scheduler;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Otieno Lawrence
 * 
 */
public class CdiJob implements Job {

	public final static String JOB_CLASS_NAME = "ReadMessage";

	/**
	 * 
	 */
	@Override
	public void execute( JobExecutionContext context ) throws JobExecutionException {
		JobDataMap jobData = context.getJobDetail().getJobDataMap();
		String className = jobData.getString( JOB_CLASS_NAME );
		Class<? extends Runnable> jobClass;
		try {
			jobClass = Class.forName( className ).asSubclass( Runnable.class );
		} catch( ClassNotFoundException e ) {
			throw new JobExecutionException( e );
		}
		BeanManager bm = QuartzExtension.getBeanManager();
		Set<Bean<?>> jobBeans = bm.getBeans( jobClass );
		Bean<?> jobBean = bm.resolve( jobBeans );
		CreationalContext c = bm.createCreationalContext( jobBean );
		Runnable job = (Runnable) bm.getReference( jobBean, Runnable.class, c );
		try {
			job.run();
		} finally {
			//jobBean.destroy( job, c );
		}
	}
}