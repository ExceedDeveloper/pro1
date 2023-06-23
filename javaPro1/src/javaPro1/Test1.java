package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/* ���̺귯����� 
 * - �ش�������Ʈ Build Path->add External Library -> ojdbc6.jar�߰�
 
 * 1.JDBC Driver���
 * 2.���� Connection���
   3.��ü�غ�	
   4.��������
   5.�ڿ��ݳ�
 */

//scott������   update_sal_fc ��������� �Լ��� ȣ������ϴ� Ŭ����
public class FunctionEx {

	public static void main(String[] args) {
		//1.JDBC Driver���
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("1.JDBC Driver���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.���� Connection���
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("2.����Connection���-����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3.��ü�غ�
		//String sql = "{? = call �Լ���(?,..?)}";
		String sql = "{? = call update_sal_fc(?,?)}";
		CallableStatement stmt = null;
		try {
			stmt = conn.prepareCall(sql);
			//4.��������  //exec UPDATE_SAL_PROC(7369,300);
			stmt.registerOutParameter(1, Types.NUMERIC);
			stmt.setInt(2,7369);
			stmt.setInt(3,600);
			stmt.execute();
			int result = stmt.getInt(1);
			System.out.println("result="+result);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5.�ڿ��ݳ�
			try {
				if(stmt!=null) {stmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}

