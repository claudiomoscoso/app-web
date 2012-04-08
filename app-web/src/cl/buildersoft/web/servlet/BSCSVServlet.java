package cl.buildersoft.web.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JPopupMenu.Separator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSSystemException;
import cl.buildersoft.framework.type.BSData;
import cl.buildersoft.framework.type.BSFieldDataType;
import cl.buildersoft.framework.type.BSTypeFactory;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.web.servlet.csv.CsvReader;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

public abstract class BSCSVServlet extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;

	protected abstract BSTableConfig getBSTableConfig(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	protected abstract BSHeadConfig getBSHeadConfig();

	public BSCSVServlet() {
		super();
	}

	/**
	 * Lee archivo csv pasado desde la pagina como un inputstream, y realiza
	 * validaciones con cada fila del archivo
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BSmySQL mySQL = new BSmySQL();
		Connection conn = mySQL.getConnection(request.getServletContext(),
				"remu");

		BSTableConfig table = getBSTableConfig(request, response);
		table.configFields(conn, mySQL);
		// TODO Auto-generated method stub
		// String desc = request.getParameter("desc");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new BSSystemException("0201", e.getMessage());
		}
		for (FileItem item : items) {
			if (!item.isFormField()) {

				char separator = getSeparator(conn);

				CsvReader fileContent = new CsvReader(item.getInputStream(),
						separator, Charset.forName("ISO-8859-1"));
				fileContent.readHeaders();
				String[] headers = fileContent.getHeaders();
				List<Map<String, BSData>> listaCampos = new ArrayList<Map<String, BSData>>();
				while (fileContent.readRecord()) {
					Map<String, BSData> dataRow = new LinkedHashMap<String, BSData>();
					// BSField[] fields = new BSField[headers.length];
					for (int i = 0; i < headers.length; i++) {
						//
						// System.out.println("campo = " + headers[i]
						// + " valor = " + fileContent.get(headers[i]));

						BSField field = new BSField(headers[i], "");
						field.setValue(fileContent.get(headers[i]));
						// fields[i] = field;
						// fila.add(new BSData(products.get(headers[i])));

						dataRow.put(headers[i],
								new BSData(fileContent.get(headers[i])));

					}
					dataRow.put("result", new BSData(""));
					listaCampos.add(dataRow);

				}
				fileContent.close();

				compareDataType(conn, table.deleteIdMap(), listaCampos);

				HttpSession session = request.getSession();
				request.setAttribute("Headers", headers);
				synchronized (session) {
					session.setAttribute("respCSV", listaCampos);
				}
				request.getRequestDispatcher(
						"/WEB-INF/jsp/table/csvResponse.jsp").forward(request,
						response);
			}

		}

	}

	private char getSeparator(Connection conn) {
		BSConfig config = new BSConfig();
		String seperator = config.getString(conn, "CSV_SEPARATOR");
		return seperator.toCharArray()[0];
	}

	private void compareDataType(Connection conn, Map<String, BSField> fields,
			List<Map<String, BSData>> fieldList) {
		Boolean typeRight = Boolean.FALSE;
		Boolean fkRight = Boolean.FALSE;
		Boolean isUpdate = null;
		BSField field = null;
		String value = null;
		Boolean isRight = null;

//		BSTypeFactory bsType = new BSTypeFactory();
		for (Map<String, BSData> map : fieldList) {
			Iterator it = map.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, BSData> entry = (Map.Entry<String, BSData>) it
						.next();

				if (!entry.getKey().toString().equalsIgnoreCase("result")) {
					String key = entry.getKey();
					field = fields.get(key);
					value = entry.getValue().getValue();
					field.setValue(value);

					BSFieldDataType validator = BSTypeFactory.create(field);
					typeRight = validator.validData(conn, value);
					// typeRight = bsType.evaluate(value, field);

					if (typeRight) {
						fkRight = validFK(field);
					}

					isRight = typeRight && fkRight;

					map.get("result").setState(isRight);
					entry.getValue().setState(isRight);

				}

			}
		}
	}

	private Boolean validFK(BSField field) {
		Boolean out = Boolean.TRUE;
		if (field.isFK()) {
			List<Object[]> allData = field.getFKData();
			Long value = field.getValueAsLong();
			Long dataLong;
			Boolean found = Boolean.FALSE;
			for (Object[] data : allData) {
				dataLong = Long.parseLong(data[0].toString());
				if (dataLong.equals(value)) {
					found = Boolean.TRUE;
					break;
				}
			}
			out = found;
		}

		return out;
	}

	private void insertData(BSmySQL mySQL, BSTableConfig table, BSField[] fields) {
		Connection conn;
		String sql = getSQL(table, fields);
		List<Object> params = getValues4Insert(fields);

		conn = mySQL.getConnection(getServletContext(), "bsframework");
		mySQL.insert(conn, sql, params);
	}

	private void copyTypeTableToField(BSField[] tableField, BSField[] fields) {
		Field: for (BSField field : fields) {

			for (BSField tblField : tableField) {
				if (tblField.getName().equals(field.getName())) {
					field.setType(tblField.getType());
					continue Field;
				}
			}
		}

	}

	private String getSQL(BSTableConfig table, BSField[] fields) {
		// fields = deleteId(fields);
		String sql = "INSERT INTO " + table.getDatabase() + "."
				+ table.getTableName();
		sql += "(" + unSplit(fields, ",") + ") ";
		sql += " VALUES (" + getCommas(fields) + ")";

		return sql;
	}

	private List<Object> getValues4Insert(BSField[] fields) {

		List<Object> out = new ArrayList<Object>();
		Object value = null;

		for (BSField field : fields) {
			value = new String(field.getValue().toString());
			out.add(value);

		}
		return out;
	}

}
