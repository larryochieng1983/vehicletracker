/**
 * 
 */
package com.wizglobal.vehicletracker.scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class QuartzExtension implements Extension {

	private static Logger LOG = Logger.getLogger( QuartzExtension.class );

	private Scheduler scheduler;

	@Inject
	private static BeanManager beanManager;

	/**
	 * 
	 * @param pat
	 * @throws SchedulerException
	 */
	public void scheduleJob( @Observes
	ProcessAnnotatedType pat ) throws SchedulerException {
		AnnotatedType t = pat.getAnnotatedType();
		Scheduled schedule = t.getAnnotation( Scheduled.class );
		if( schedule == null ) {
			// no scheduled job, ignoring this class
			return;
		}
		Class<Runnable> jobClass = t.getJavaClass().asSubclass( Runnable.class );
		if( jobClass == null ) {
			LOG.error( "Can't schedule job " + t );
			return;
		}
		JobDetail job = newJob( CdiJob.class ).usingJobData( CdiJob.JOB_CLASS_NAME,
				jobClass.getName() ).build();
		Trigger trigger = newTrigger().withSchedule( cronSchedule( schedule.value() ) ).build();
		getScheduler().scheduleJob( job, trigger );
	}

	/**
	 * 
	 * @param event
	 * @throws SchedulerException
	 */
	public void initScheduler( @Observes
	BeforeBeanDiscovery event ) throws SchedulerException {
		LOG.info( "beginning the scanning process" );
		setScheduler( StdSchedulerFactory.getDefaultScheduler() );
	}

	public void afterBeanDiscovery( @Observes
	AfterBeanDiscovery abd ) {
		try {
			getScheduler().start();
		} catch( SchedulerException ex ) {
			LOG.error( "Error initialising/starting scheduler", ex );
		}
		LOG.info( "finished the scanning process" );

	}

	/**
	 * 
	 * @param event
	 * @param bm
	 */
	public void startScheduler( @Observes
	AfterDeploymentValidation event, BeanManager bm ) {
		beanManager = bm;
		try {
			getScheduler().start();
			LOG.info( "Started scheduler." );
		} catch( SchedulerException se ) {
			throw new RuntimeException( se );
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void shutdownScheduler( @Observes
	BeforeShutdown event ) {
		try {
			getScheduler().shutdown( true );
		} catch( SchedulerException se ) {
			throw new RuntimeException( se );
		}
	}

	/**
	 * @return the beanManager
	 */
	public static BeanManager getBeanManager() {
		return beanManager;
	}

	/**
	 * @return the scheduler
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}

	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler( Scheduler scheduler ) {
		this.scheduler = scheduler;
	}

}
