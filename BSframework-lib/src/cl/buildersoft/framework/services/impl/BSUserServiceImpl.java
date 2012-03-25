package cl.buildersoft.framework.services.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.framework.beans.Rol;
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
	public List<Rol> getRols(Connection conn, User user) {
		String sql = "SELECT cRol FROM tR_UserRol WHERE cUser=?";
		List<Rol> out = new ArrayList<Rol>();

		BSBeanUtils bu = new BSBeanUtils();
		ResultSet rols = queryResultSet(conn, sql, user.getId());
		try {
			while (rols.next()) {
				Rol rol = new Rol();
				rol.setId(rols.getLong(1));

				bu.search(conn, rol);
				out.add(rol);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return out;
	}
}
