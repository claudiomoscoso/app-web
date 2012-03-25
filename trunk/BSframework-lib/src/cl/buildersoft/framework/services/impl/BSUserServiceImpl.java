package cl.buildersoft.framework.services.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Option;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.Submenu;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.services.BSUserService;
import cl.buildersoft.framework.util.BSDataUtils;

public class BSUserServiceImpl extends BSDataUtils implements BSUserService {
	public BSUserServiceImpl() {
		super();
	}

	public User login(Connection conn, String mail, String password) {
		BSBeanUtils beanUtil = new BSBeanUtils();
		User user = new User();

		String sql = "SELECT cId FROM tUser WHERE cMail=? AND cPassword=MD5(?)";

		String idString;
		try {
			idString = super.queryField(conn, sql, array2List(mail, password));

			if (idString != null) {
				user.setId(Long.parseLong(idString));
				beanUtil.search(conn, user);
			} else {
				user = null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return user;
	}

	@Override
	public List<Rol> getRols(Connection conn, User user) throws Exception {
		String sql = "SELECT cRol FROM tR_UserRol WHERE cUser=?";
		List<Rol> out = new ArrayList<Rol>();

		BSBeanUtils bu = new BSBeanUtils();
		ResultSet rols = queryResultSet(conn, sql, user.getId());
		while (rols.next()) {
			Rol rol = new Rol();
			rol.setId(rols.getLong(1));

			bu.search(conn, rol);
			out.add(rol);
		}

		return out;
	}

	@Override
	public Menu getMenu(Connection conn, List<Rol> rols) throws Exception {
		List<Submenu> mainMenu = getMainMenu(conn, rols);
		Menu menu = new Menu();
		addMainMenuToMenu(mainMenu, menu);

		// List<Submenu> submenuList = null;
		for (Submenu submenu : mainMenu) {
			fillSubmenu(conn, submenu);
		}
		return menu;

		/**
		 * <code>
		String sql = "SELECT cMenu FROM tR_RolMenu r LEFT JOIN tMenu m ON r.cMenu=m.cId WHERE r.cRol = ? AND m.cParent IS NULL";
		BSBeanUtils bu = new BSBeanUtils();

		List<Long> ids = new ArrayList<Long>();

		ResultSet rs = queryResultSet(conn, sql, rol.getId());
		while (rs.next()) {
			ids.add(rs.getLong("cMenu"));
		}

		Option[] out = new Option[ids.size()];
		Option menu = null;
		int i = 0;
		for (Long id : ids) {
			menu = new Option();
			menu.setId(id);
			bu.search(conn, menu);
			out[i++] = menu;
		}

		return out;
		</code>
		 */
	}

	private void fillSubmenu(Connection conn, Submenu menu) throws Exception {
		List<Submenu> submenuList = getSubmenu(conn, menu.getOption());
		menu.addSubmenu(submenuList);

		for (Submenu submenu : submenuList) {
			fillSubmenu(conn, submenu);
		}

	}

	private void addMainMenuToMenu(List<Submenu> mainMenu, Menu menu) {
		for (Submenu submenu : mainMenu) {
			menu.addSubmenu(submenu);
		}
	}

	private List<Submenu> getSubmenu(Connection conn, Option opt)
			throws Exception {
		String sql = "SELECT cOption ";
		sql += "FROM tR_RolOption r ";
		sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
		sql += "WHERE o.cParent = ?";

		List<Object> prms = array2List(opt.getId());

		return getSubmenuList(conn, sql, prms);
	}

	private List<Submenu> getMainMenu(Connection conn, List<Rol> rols)
			throws Exception {
		List<Submenu> out = new ArrayList<Submenu>();
		String sql = "SELECT cOption ";
		sql += "FROM tR_RolOption r ";
		sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
		sql += "WHERE o.cParent IS NULL AND r.cRol = ?";

		List<Object> prms = new ArrayList<Object>();
		for (Rol rol : rols) {
			prms.add(rol.getId());
			List<Submenu> aux = getSubmenuList(conn, sql, prms);
			prms.clear();
			out.addAll(aux);
		}
		return out;
	}

	private List<Submenu> getSubmenuList(Connection conn, String sql,
			List<Object> prms) throws Exception {
		Submenu submenu = null;
		List<Submenu> submenuList = new ArrayList<Submenu>();

		List<Long> ids = new ArrayList<Long>();
		Option option = null;
		BSBeanUtils bu = new BSBeanUtils();

		ResultSet rs = queryResultSet(conn, sql, prms);
		while (rs.next()) {
			option = new Option();
			option.setId(rs.getLong("cOption"));

			bu.search(conn, option);

			submenu = new Submenu(option);
			submenuList.add(submenu);
		}

		closeSQL(rs);

		return submenuList;
	}
}