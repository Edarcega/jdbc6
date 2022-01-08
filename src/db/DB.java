package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	/*
	 * Conectar com o Banco de dados é instanciar um objeto do tipo Connections
	 */
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}

		return conn;
	}

	/*
	 * O SQL Exception é derivado de exception que somos obrigados a tratar por isso
	 * criamos o dbException, para que o tratamento seja efetuado quando julgarmos
	 * necessário.
	 */
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// Quando autocompletado obriga a tratar, neste caso,
				// Foi trocado para lançar a exceção personalizada que
				// Não obriga a tratar
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// Quando autocompletado obriga a tratar, neste caso,
				// Foi trocado para lançar a exceção personalizada que
				// Não obriga a tratar
				throw new DbException(e.getMessage());
			}
		}

	}

}