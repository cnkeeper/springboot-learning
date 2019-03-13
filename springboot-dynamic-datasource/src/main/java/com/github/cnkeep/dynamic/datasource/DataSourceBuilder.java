package com.github.cnkeep.dynamic.datasource;

import com.github.cnkeep.dynamic.exception.BeanInstantiationException;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: DataSource构建器
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2019/03/12
 */
public final class DataSourceBuilder<T extends DataSource> {

	private static final String[] DATA_SOURCE_TYPE_NAMES = new String[] {
			"com.zaxxer.hikari.HikariDataSource",
			"org.apache.tomcat.jdbc.pool.DataSource",
			"org.apache.commons.dbcp2.BasicDataSource" };

	private Class<? extends DataSource> type;

	private ClassLoader classLoader;

	private Map<String, String> properties = new HashMap<>();

	public static DataSourceBuilder<?> create() {
		return new DataSourceBuilder<>(null);
	}

	public static DataSourceBuilder<?> create(ClassLoader classLoader) {
		return new DataSourceBuilder<>(classLoader);
	}

	private DataSourceBuilder(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	/**
	 * 构建DataSource
	 * @return
	 */
	public T build() {
		Class<? extends DataSource> type = getType();
		try {
			return (T) type.newInstance();
		} catch (Exception e) {
			throw new BeanInstantiationException(MessageFormat.format("Class Type {0} can't newInstance!",type),e);
		}
	}

	@SuppressWarnings("unchecked")
	public <D extends DataSource> DataSourceBuilder<D> type(Class<D> type) {
		this.type = type;
		return (DataSourceBuilder<D>) this;
	}

	public DataSourceBuilder<T> url(String url) {
		this.properties.put("url", url);
		return this;
	}

	public DataSourceBuilder<T> driverClassName(String driverClassName) {
		this.properties.put("driverClassName", driverClassName);
		return this;
	}

	public DataSourceBuilder<T> username(String username) {
		this.properties.put("username", username);
		return this;
	}

	public DataSourceBuilder<T> password(String password) {
		this.properties.put("password", password);
		return this;
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends DataSource> findType(ClassLoader classLoader) {
		for (String name : DATA_SOURCE_TYPE_NAMES) {
			try {
				return (Class<? extends DataSource>)Class.forName(name);
			}
			catch (Exception ex) {
				// Swallow and continue
			}
		}
		return null;
	}

	private Class<? extends DataSource> getType() {
		Class<? extends DataSource> type = (this.type != null) ? this.type
				: findType(this.classLoader);
		if (type != null) {
			return type;
		}
		throw new IllegalStateException("No supported DataSource type found");
	}

}
