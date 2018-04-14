package com.marvik.apis.dbcrudgen.core.os;

/**
 * Created by victor on 7/13/17.
 **/
public class MacOperatingSystem extends OperatingSystem {
    /**
     * Returns the name of the operating system
     */
    @Override
    public String getName() {
        return "Mac";
    }

    /**
     * Return the command to restart XAMPP
     *
     * @return
     */
    @Override
    public String getXamppRestartCommand() {
        return "./lampp restart";
    }

    /**
     * Return the command to start XAMPP
     *
     * @return
     */
    @Override
    public String getXamppStartCommand() {
        return "./lampp start";
    }

    /**
     * Return the command to stop XAMPP
     *
     * @return
     */
    @Override
    public String getXamppStopCommand() {
        return "./lampp stop";
    }

    /**
     * Return the command to restart MYSQL database server
     *
     * @return
     */
    @Override
    public String getMysqlRestartCommand() {
        return "mysql restart";
    }

    /**
     * Return the command to start MYSQL database server
     *
     * @return
     */
    @Override
    public String getMysqlStartCommand() {
        return "mysql start";
    }

    /**
     * Return the command to stop MYSQL database server
     *
     * @return
     */
    @Override
    public String getMysqlStopCommand() {
        return "mysql stop";
    }

    /**
     * Return the command to restart HTTP Daemon
     *
     * @return
     */
    @Override
    public String getHttpdRestartCommand() {
        return "service httpd restart";
    }

    /**
     * Return the command to start HTTP Daemon
     *
     * @return
     */
    @Override
    public String getHttpdStartCommand() {
        return "service httpd start";
    }

    /**
     * Return the command to stop HTTP Daemon
     *
     * @return
     */
    @Override
    public String getHttpdStopCommand() {
        return "service httpd stop";
    }

    /**
     * Returns the command to restart apache server
     *
     * @return
     */
    @Override
    public String getApacheRestartCommand() {
        return "./apache2 restart";
    }

    /**
     * Returns the command to start apache server
     *
     * @return
     */
    @Override
    public String getApacheStartCommand() {
        return "./apache2 start";
    }

    /**
     * Returns the command to stop apache server
     *
     * @return
     */
    @Override
    public String getApacheStopCommand() {
        return "./apache2 stop";
    }
}
