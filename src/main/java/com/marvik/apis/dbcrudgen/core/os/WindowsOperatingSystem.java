package com.marvik.apis.dbcrudgen.core.os;

/**
 * Created by victor on 7/13/17.
 **/
public class WindowsOperatingSystem extends OperatingSystem {
    /**
     * Returns the name of the operating system
     */
    @Override
    public String getName() {
        return "Windows";
    }

    /**
     * Return the command to restart XAMPP
     *
     * @return
     */
    @Override
    public String getXamppRestartCommand() {
        return "C:\\xampp\\xampp_control.bat";
    }

    /**
     * Return the command to start XAMPP
     *
     * @return
     */
    @Override
    public String getXamppStartCommand() {
        return "C:\\xampp\\xampp_start.bat";
    }

    /**
     * Return the command to stop XAMPP
     *
     * @return
     */
    @Override
    public String getXamppStopCommand() {
        return "C:\\xampp\\xampp_stop.bat";
    }

    /**
     * Return the command to restart MYSQL database server
     *
     * @return
     */
    @Override
    public String getMysqlRestartCommand() {
        return "C:\\xampp\\mysql_restart.bat";
    }

    /**
     * Return the command to start MYSQL database server
     *
     * @return
     */
    @Override
    public String getMysqlStartCommand() {
        return "C:\\xampp\\mysql_start.bat";
    }

    /**
     * Return the command to stop MYSQL database server
     *
     * @return
     */
    @Override
    public String getMysqlStopCommand() {
        return "C:\\xampp\\mysql_stop.bat";
    }

    /**
     * Return the command to restart HTTP Daemon
     *
     * @return
     */
    @Override
    public String getHttpdRestartCommand() {
        return getApacheRestartCommand();
    }

    /**
     * Return the command to start HTTP Daemon
     *
     * @return
     */
    @Override
    public String getHttpdStartCommand() {
        return getApacheStartCommand();
    }

    /**
     * Return the command to stop HTTP Daemon
     *
     * @return
     */
    @Override
    public String getHttpdStopCommand() {
        return getApacheStopCommand();
    }

    /**
     * Returns the command to restart apache server
     *
     * @return
     */
    @Override
    public String getApacheRestartCommand() {
        return "C:\\xampp\\apache_restart.bat";
    }

    /**
     * Returns the command to start apache server
     *
     * @return
     */
    @Override
    public String getApacheStartCommand() {
        return "C:\\xampp\\apache_stop.bat";
    }

    /**
     * Returns the command to stop apache server
     *
     * @return
     */
    @Override
    public String getApacheStopCommand() {
        return "C:\\xampp\\apache_stop.bat";
    }
}
