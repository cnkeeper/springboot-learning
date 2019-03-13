package com.github.cnkeep.dynamic.datasource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * 描述：
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
public class DelegatingDataSource implements DataSource {

    private DataSource targetDataSource;


	/**
	 * Create a new DelegatingDataSource.
	 * @see #setTargetDataSource
	 */
	public DelegatingDataSource() {
	}

	/**
	 * Create a new DelegatingDataSource.
	 * @param targetDataSource the target DataSource
	 */
	public DelegatingDataSource(DataSource targetDataSource) {
		setTargetDataSource(targetDataSource);
	}


	/**
	 * Set the target DataSource that this DataSource should delegate to.
	 */
	public void setTargetDataSource(DataSource targetDataSource) {
		this.targetDataSource = targetDataSource;
	}

	/**
	 * Return the target DataSource that this DataSource should delegate to.
	 */
	public DataSource getTargetDataSource() {
		return this.targetDataSource;
	}

	/**
	 * Obtain the target {@code DataSource} for actual use (never {@code null}).
	 * @since 5.0
	 */
	protected DataSource obtainTargetDataSource() {
		DataSource dataSource = getTargetDataSource();
		return dataSource;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return obtainTargetDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return obtainTargetDataSource().getConnection(username, password);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return obtainTargetDataSource().getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		obtainTargetDataSource().setLogWriter(out);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return obtainTargetDataSource().getLoginTimeout();
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		obtainTargetDataSource().setLoginTimeout(seconds);
	}


	//---------------------------------------------------------------------
	// Implementation of JDBC 4.0's Wrapper interface
	//---------------------------------------------------------------------

	@Override
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (iface.isInstance(this)) {
			return (T) this;
		}
		return obtainTargetDataSource().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return (iface.isInstance(this) || obtainTargetDataSource().isWrapperFor(iface));
	}


	//---------------------------------------------------------------------
	// Implementation of JDBC 4.1's getParentLogger method
	//---------------------------------------------------------------------

	@Override
	public Logger getParentLogger() {
		return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	}

}
