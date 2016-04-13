package cn.stive.mall.rest;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

public class LogbackConfigListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory
			.getLogger(LogbackConfigListener.class);

	private static final String CONFIG_LOCATION = "logbackConfigLocation";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 从web.xml中加载指定文件名的日志配置文件
		String logbackConfigLocation = event.getServletContext()
				.getInitParameter(CONFIG_LOCATION);

		URL url = LogbackConfigListener.class.getClassLoader().getResource(
				logbackConfigLocation);
		try {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory
					.getILoggerFactory();
			loggerContext.reset();
			JoranConfigurator joranConfigurator = new JoranConfigurator();
			joranConfigurator.setContext(loggerContext);
			joranConfigurator.doConfigure(url);
		} catch (JoranException e) {
			LOG.error("can loading slf4j configure file from " + url, e);
			throw new RuntimeException();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}