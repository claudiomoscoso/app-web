package cl.buildersoft.framework.services.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.framework.beans.Menu;
import cl.buildersoft.framework.beans.Option;
import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.Submenu;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.util.BSDataUtils;

public class BSMenuServiceImpl extends BSDataUtils implements BSMenuService {

	@Override
	public Menu getMenu(Connection conn, List<Rol> rols) {
		Menu menu = new Menu();

		Submenu sub = null;
		if (rols == null) {
			List<Submenu> main = fillSubmenu2(conn, sub, null, menu);
			addMainMenuToMenu(main, menu);
		} else {
			for (Rol rol : rols) {
				List<Submenu> main = fillSubmenu2(conn, sub, rol, menu);
				addMainMenuToMenu(main, menu);
			}

		}

		/**
		 * <code>	
		List<Submenu> mainMenu = null;
		if (rols != null) {
			for (Rol rol : rols) {
				mainMenu = getMainMenu(conn, rol);
				addMainMenuToMenu(mainMenu, menu);
			}
		} else {
			mainMenu = getMainMenu(conn, null);
			addMainMenuToMenu(mainMenu, menu);
		}

		for (Submenu submenu : mainMenu) {
			if (rols != null) {
				for (Rol rol : rols) {
					fillSubmenu(conn, submenu, rol);
				}
			} else {
				fillSubmenu(conn, submenu, null);
			}
		}
</code>
		 */

		return menu;
	}

	private List<Submenu> fillSubmenu2(Connection conn, Submenu main, Rol rol,
			Menu menu) {
		List<Submenu> subList = getSubmenu2(conn, main, rol);

		for (Submenu sub : subList) {
			List<Submenu> auxList = fillSubmenu2(conn, sub, rol, menu);
			sub.addSubmenu(auxList, menu);
//			if (main != null && auxList.size() > 0) {
//				
//			}
		}
/**<code>
		if (main != null && subList.size() > 0) {
			main.addSubmenu(subList, menu);
		}
</code>*/		
		return subList;
	}

	private List<Submenu> getSubmenu2(Connection conn, Submenu sub, Rol rol) {
		String sql = null;
		List<Object> prms = null; // = new ArrayList<Object>();
		Option parent = sub != null ? sub.getOption() : null;

		if (parent == null && rol == null) {
			sql = "SELECT cId AS cOption ";
			sql += "FROM tOption ";
			sql += "WHERE cParent IS NULL";
			prms = null;
		} else if (parent == null && rol != null) {
			sql = "SELECT cOption ";
			sql += "FROM tR_RolOption r ";
			sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE o.cParent IS NULL AND r.cRol=?";
			prms = array2List(rol.getId());
		} else if (parent != null && rol == null) {
			sql = "SELECT cId AS cOption ";
			sql += "FROM tOption ";
			sql += "WHERE cParent=?";
			prms = array2List(parent.getId());
		} else if (parent != null && rol != null) {
			sql = "SELECT cOption ";
			sql += "FROM tR_RolOption r ";
			sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE o.cParent=? AND r.cRol=?";
			prms = array2List(parent.getId(), rol.getId());
		}

		/**
		 * <code>			
			sql = "SELECT cOption ";
			sql += "FROM tR_RolOption r ";
			sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE o.cParent=? AND r.cRol=?";
			prms = array2List(opt.getId(), rol.getId());
		} else {
			sql = "SELECT cId AS cOption ";
			sql += "FROM tOption ";
			// sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE cParent = ?";
			prms = array2List(opt.getId());
		}
</code>
		 */
		return getSubmenuFromDB(conn, sql, prms);

	}

	private List<Submenu> getSubmenu(Connection conn, Option opt, Rol rol) {
		String sql;
		List<Object> prms;

		if (rol != null) {
			sql = "SELECT cOption ";
			sql += "FROM tR_RolOption r ";
			sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE o.cParent=? AND r.cRol=?";
			prms = array2List(opt.getId(), rol.getId());
		} else {
			sql = "SELECT cId AS cOption ";
			sql += "FROM tOption ";
			// sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE cParent = ?";
			prms = array2List(opt.getId());
		}

		return getSubmenuFromDB(conn, sql, prms);
	}

	private void fillSubmenu(Connection conn, Submenu menu, Rol rol) {
		List<Submenu> submenuList = getSubmenu(conn, menu.getOption(), rol);
		menu.addSubmenu(submenuList, null);

		for (Submenu submenu : submenuList) {
			fillSubmenu(conn, submenu, rol);
		}

	}

	private void addMainMenuToMenu(List<Submenu> mainMenu, Menu menu) {
		for (Submenu submenu : mainMenu) {
			if (!submenu.optionInMenu(submenu.getOption(), menu.list())) {
				menu.addSubmenu(submenu);
			}
		}
	}

	private List<Submenu> getMainMenu(Connection conn, Rol rol) {
		List<Submenu> out = new ArrayList<Submenu>();
		if (rol != null) {
			String sql = "SELECT cOption ";
			sql += "FROM tR_RolOption r ";
			sql += "LEFT JOIN tOption o ON r.cOption=o.cId ";
			sql += "WHERE o.cParent IS NULL AND r.cRol = ?";

			List<Object> prms = new ArrayList<Object>();
			prms.add(rol.getId());
			out = getSubmenuFromDB(conn, sql, prms);

			/**
			 * <code>
			 List<Submenu> aux = getSubmenuList(conn, sql, prms);
			for (Rol rol : rols) {
				prms.add(rol.getId());
				List<Submenu> aux = getSubmenuList(conn, sql, prms);
				prms.clear();
				out.addAll(aux);
			}
</code>
			 */
		} else {
			String sql = "SELECT cId AS cOption ";
			sql += "FROM tOption ";
			sql += "WHERE cParent IS NULL";

			out = getSubmenuFromDB(conn, sql, null);
		}
		return out;
	}

	private List<Submenu> getSubmenuFromDB(Connection conn, String sql,
			List<Object> prms) {
		Submenu submenu = null;
		List<Submenu> out = new ArrayList<Submenu>();

		Option option = null;
		BSBeanUtils bu = new BSBeanUtils();

		ResultSet rs = queryResultSet(conn, sql, prms);
		try {
			while (rs.next()) {
				option = new Option();
				option.setId(rs.getLong("cOption"));
				bu.search(conn, option);

				submenu = new Submenu(option);
				out.add(submenu);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		closeSQL(rs);

		return out;
	}

	@Override
	public Boolean optionInMenu(Option opt, List<Submenu> main) {
		Boolean exists = Boolean.FALSE;

		for (Submenu sub : main) {
			if (sub.getOption().getId().equals(opt.getId())) {
				exists = Boolean.TRUE;
				break;
			} else {
				exists = optionInMenu(opt, sub.list());
				if (exists) {
					break;
				}
			}
		}
		return exists;
	}
}
