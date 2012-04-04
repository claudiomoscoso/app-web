package cl.buildersoft.web.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import cl.buildersoft.framework.type.BSTypeFactory;
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
	 * Lee archivo csv pasado desde la pagina como un inputstream,
	 * y realiza validaciones con cada fila del archivo
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
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();

			} else {

				CsvReader products = new CsvReader(item.getInputStream(),
						Charset.forName("ISO-8859-1"));
				products.readHeaders();
				String[] headers = products.getHeaders();
				List<List<BSData>> listaCampos = new ArrayList<List<BSData>>();
				while (products.readRecord()) {
					List<BSData> fila = new ArrayList<BSData>();
					//BSField[] fields = new BSField[headers.length];
					for (int i = 0; i < headers.length; i++) {

						System.out.println("campo = " + headers[i]
								+ " valor = " + products.get(headers[i]));
						BSField field = new BSField(headers[i], "");
						field.setValue(products.get(headers[i]));
						//fields[i] = field;
						fila.add(new BSData(products.get(headers[i])));
					}
					//Se agrega un campo vacio para guardar el estado de la comparacion
					listaCampos.add(fila);					
					//copyTypeTableToField(table.deleteId(), fields);
					
					//insertData(mySQL, table, fields);

					// perform program logic here
				}
				products.close();
				compareDataType(table.deleteId(), listaCampos);
				HttpSession session = request.getSession();
				session.setAttribute("respCSV", listaCampos);
				request.getRequestDispatcher("/WEB-INF/jsp/table/csvResponse.jsp").forward(request, response);
			}

		}

	}

	private void compareDataType(BSField[] fields,List<List<BSData>> listaCampos) {
		
		BSTypeFactory bsTypeFactory = new BSTypeFactory();
		for (List<BSData> fila : listaCampos) {
			int longRow = fila.size();
			for (int i = 0; i < longRow; i++) {
				BSData celda = fila.get(i);
				try{
					Boolean resp = bsTypeFactory.evaluate(celda.getValue(), fields[i]);
					if(resp)
						celda.setState(true);
					else
						celda.setState(false);
				}catch(Exception e)
				{
					
				}
			}
		}
		
		// TODO Auto-generated method stub		
	}

	private void insertData(BSmySQL mySQL, BSTableConfig table, BSField[] fields) {
		Connection conn;
		String sql = getSQL(table, fields);
		List<Object> params = getValues4Insert(fields);

		conn = mySQL.getConnection(getServletContext(),"bsframework");
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
