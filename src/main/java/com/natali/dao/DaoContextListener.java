package com.natali.dao;

import com.natali.dao.connection.ConnectionProviderException;
import com.natali.utils.ResourceUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;

@Slf4j
public class DaoContextListener implements ServletContextListener {
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";

    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initializing DAO layer");
        try (InputStream dbPropertiesStream = ResourceUtils.getResourceInputStream(DB_PROPERTIES_FILE_NAME)) {
            DaoContext.init(dbPropertiesStream);
        } catch (FileNotFoundException e) {
            log.error("File {} not found", DB_PROPERTIES_FILE_NAME);
        } catch (IOException e) {
            log.error("Exception while working with file", e);
        } catch (ConnectionProviderException e) {
            log.error("Connection provider exception", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Deinitializing DAO layer");
        try {
            DaoContext.stop();
        } catch (ConnectionProviderException e) {
            log.error("Connection provider exception", e);
        }
    }
}
