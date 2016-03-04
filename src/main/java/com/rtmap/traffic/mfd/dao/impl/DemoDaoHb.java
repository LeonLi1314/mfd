package com.rtmap.traffic.mfd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.Country;

/**
 * 样例
 * 
 * @author liqingshan
 *
 */
public class DemoDaoHb extends DaoHbSupport {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String USER = "lqs";
	private static final String PASS = "1213";

	public void batchInsertOfJdbc(List<Country> list) {
		if (list == null || list.size() <= 0)
			return;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			// 1.自创建
			// conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 2.springjdbc中逆向获取
			conn = getJdbcTemplate().getDataSource().getConnection();
			String insertSql = "insert into bas_country(country_code,name_abbr_cn,name_cn,name_en) values(?,?,?,?)";
			stmt = conn.prepareStatement(insertSql);

			// 方式1：自动提交
			conn.setAutoCommit(true);
			for (int i = 0; i < list.size(); i++) {
				stmt.setString(1, "testName");
				stmt.setString(2, "testAddress");
				stmt.execute();
			}

			// 方式2：批量提交
			conn.setAutoCommit(false);
			for (int i = 0; i < list.size(); i++) {
				stmt.setString(1, list.get(i).getCountryCode());
				stmt.setString(2, list.get(i).getNameAbbrCn());
				stmt.setString(3, list.get(i).getNameCn());
				stmt.setString(4, list.get(i).getNameEn());
				stmt.addBatch();

				if (i % 100 == 0) {
					stmt.executeBatch();
					conn.commit();
				}
			}

			int[] count = stmt.executeBatch();
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void batchInsertOfJSpringJdbcA(List<Country> list) {
		if (list == null || list.size() <= 0)
			return;

		int size = list.size() * 128;
		StringBuilder sb = new StringBuilder(size);
		sb.append("insert into bas_country(country_code,name_abbr_cn,name_cn,name_en) values ");
		MessageFormat form = new MessageFormat("(''{0}'', ''{1}'', ''{2}'', ''{3}''),");

		for (Country country : list) {
			Object[] args = { country.getCountryCode(), country.getNameAbbrCn(), country.getNameCn(),
					country.getNameEn() };
			sb.append(form.format(args));
		}

		String sql = sb.toString();
		sql = sql.substring(0, sql.length() - 1);
		getJdbcTemplate().update(sql);
	}

	public void batchInsertOfJSpringJdbcB(List<Country> list) {
		if (list == null || list.size() <= 0)
			return;
		String sql = "insert into bas_country(country_code,name_abbr_cn,name_cn,name_en) values(?,?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();

		for (Country country : list) {
			Object[] args = { country.getCountryCode(), country.getNameAbbrCn(), country.getNameCn(),
					country.getNameEn() };
			batchArgs.add(args);
		}

		getJdbcTemplate().batchUpdate(sql, batchArgs);
	}
}
