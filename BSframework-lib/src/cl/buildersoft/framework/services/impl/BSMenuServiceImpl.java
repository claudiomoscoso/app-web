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
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.services.BSMenuService;
import cl.buildersoft.framework.util.BSDataUtils;

public class BSMenuServiceImpl extends BSDataUtils implements BSMenuService {

	@Override
	public Menu getMenu(Connection conn, List<Rol> rols) {
		Menu menu = null;

		Submenu sub = null;
		if (rols == null) {
			List<Submenu> main = fillSubmenu(conn, sub, null, menu);
			menu = new Menu();
			addMainMenuToMenu(main, menu);
		} else {
			Rol rol = rols.get(0);

			List<Submenu> main = fillSubmenu(conn, sub, rol, menu);
			menu = new Menu();
			addMainMenuToMenu(main, menu);
			/** ------------- */
			for (int i = 1; i < rols.size(); i++) {
				rol = rols.get(i);

				main = fillSubmenu(conn, sub, rol, menu);
				addMainMenuToMenu(main, menu);
				
				for (Submenu sub1 : menu.list()) {
					
					complement(conn, sub1, rol, menu);
				}
			}
		}

		return menu;
	}

	private List<Submenu> fillSubmenu(Connection conn, Submenu main, Rol rol,
			Menu menu) {
		List<Submenu> subList = getSubmenu(conn, main, rol);

		for (Submenu sub : subList) {
			List<Submenu> auxList = fillSubmenu(conn, sub, rol, menu);
			sub.addSubmenu(auxList, menu);
		}
		return subList;
	}

	private void complement(Connection conn, Submenu main, Rol rol, Menu menu) {
		List<Submenu> subList = getSubmenu(conn, main, rol);

		main.addSubmenu(subList, menu);

		for (Submenu sub : main.list()) {
			complement(conn, sub, rol, menu);
		}
	}

	private List<Submenu> getSubmenu(Connection conn, Submenu sub, Rol rol) {
		String sql = null;
		List<Object> prms = null;
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

		return getSubmenuFromDB(conn, sql, prms);

	}

	private void addMainMenuToMenu(List<Submenu> mainMenu, Menu menu) {
		for (Submenu submenu : mainMenu) {
			if (!submenu.optionInMenu(submenu.getOption(), menu.list())) {
				menu.addSubmenu(submenu);
			}
		}
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
			throw new BSDataBaseException("0300",e.getMessage());
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
