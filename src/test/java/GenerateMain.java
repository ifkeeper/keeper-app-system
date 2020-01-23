import com.mingrn.itumate.mybatis.generator.util.CodeUtil;

public class GenerateMain {

	private static final String JDBC_URL = "jdbc:mysql://localhost/itumate_system?useSSL=false";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "admin123";
	private static final String AUTHOR = "MinGRn <br > MinGRn97@gmail.com";
	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String PROJECT_PACKAGE = "com.mingrn.itumate.system";

	public static void main(String[] args) {

		System.out.println(PROJECT_PATH);
		String[][] tableNames = {
				{"sys_menu", "SysMenu", "系统菜单表", "id", "String"},
				{"sys_power", "SysPower", "菜单功能按钮表", "id", "String"},
				{"sys_menu_power", "SysMenuPower", "系统菜单与功能关联表", "id", "String"},
		};

		CodeUtil.create(tableNames, AUTHOR, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, PROJECT_PATH, PROJECT_PACKAGE);
	}
}