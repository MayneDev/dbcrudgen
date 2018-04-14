package com.marvik.apis.dbcrudgen.core.os;

/**
 * Created by victor on 4/14/18
 **/
public abstract class OperatingSystem {

    /**
     * Returns the name of the operating system
     */
    public abstract String getName();

    /**
     * Return the command to restart XAMPP
     *
     * @return
     */
    public abstract String getXamppRestartCommand();

    /**
     * Return the command to start XAMPP
     *
     * @return
     */
    public abstract String getXamppStartCommand();

    /**
     * Return the command to stop XAMPP
     *
     * @return
     */
    public abstract String getXamppStopCommand();

    /**
     * Return the command to restart MYSQL database server
     *
     * @return
     */
    public abstract String getMysqlRestartCommand();

    /**
     * Return the command to start MYSQL database server
     *
     * @return
     */
    public abstract String getMysqlStartCommand();

    /**
     * Return the command to stop MYSQL database server
     *
     * @return
     */
    public abstract String getMysqlStopCommand();

    /**
     * Return the command to restart HTTP Daemon
     *
     * @return
     */
    public abstract String getHttpdRestartCommand();

    /**
     * Return the command to start HTTP Daemon
     *
     * @return
     */
    public abstract String getHttpdStartCommand();

    /**
     * Return the command to stop HTTP Daemon
     *
     * @return
     */
    public abstract String getHttpdStopCommand();

    /**
     * Returns the command to restart apache server
     *
     * @return
     */
    public abstract String getApacheRestartCommand();

    /**
     * Returns the command to start apache server
     *
     * @return
     */
    public abstract String getApacheStartCommand();

    /**
     * Returns the command to stop apache server
     *
     * @return
     */
    public abstract String getApacheStopCommand();
}
