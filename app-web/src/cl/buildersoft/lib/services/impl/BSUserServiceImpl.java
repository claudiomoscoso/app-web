package cl.buildersoft.lib.services.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.lib.beans.Rol;
import cl.buildersoft.lib.beans.User;
import cl.buildersoft.lib.database.BSBeanUtils;
import cl.buildersoft.lib.exception.BSDataBaseException;
import cl.buildersoft.lib.services.BSUserService;
import cl.buildersoft.lib.util.BSDataUtils;
import static cl.buildersoft.lib.util.BSUtils.array2List;

public class BSUserServiceImpl extends BSDataUtils implements BSUserService {
	public BSUserServiceImpl() {
		super();
	}

	public User login(Connection conn, String mail, String password) {
		BSBeanUtils beanUtil = new BSBeanUtils();
		User user = new User();

		String sql = "SELECT cId FROM tUser WHERE cMail=? AND cPassword=MD5(?)";

		String idString;

		idString = super.queryField(conn, sql, array2List(mail, password));

		if (idString != null) {
			user.setId(Long.parseLong(idString));
			beanUtil.search(conn, user);
		} else {
			user = null;
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
			throw new BSDataBaseException("0300", e.getMessage());
		}
		return out;
	}
}
