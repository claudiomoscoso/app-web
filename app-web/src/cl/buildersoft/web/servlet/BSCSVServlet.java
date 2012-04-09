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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSSystemException;
import cl.buildersoft.framework.exception.BSUserException;
import cl.buildersoft.framework.type.BSData;
import cl.buildersoft.framework.type.BSFieldDataType;
import cl.buildersoft.framework.type.BSTypeFactory;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.web.servlet.csv.CsvReader;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

public abstract class BSCSVServlet extends AbstractServletUtil {
	private static final long serialVersionUID = 1L;

	protected abstract BSTableConfig getBSTableConfig();

	public BSCSVServlet() {
		super();
	}

	/**
	 * Lee archivo csv pasado desde la pagina como un inputstream, y realiza
	 * validaciones con cada fila del archivo
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Integer rowCount = 0;
		BSmySQL mySQL = new BSmySQL();
		Connection conn = mySQL.getConnection(request.getServletContext(),
				"remu");

		BSTableConfig table = getBSTableConfig();
		table.configFields(conn, mySQL);

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
				if (headers.length == 1) {
					throw new BSUserException(
							"",
							"Aparentemente el archivo no está correctamente definido, puede verificar el caracter de separación cvs, debe ser '"
									+ separator + "'");
				}
				List<Map<String, BSData>> allData = new ArrayList<Map<String, BSData>>();
				
				while (fileContent.readRecord()) {
					Map<String, BSData> dataRow = new LinkedHashMap<String, BSData>();

					for (int i = 0; i < headers.length; i++) {
						BSField field = new BSField(headers[i], "");
						field.setValue(fileContent.get(headers[i]));

						dataRow.put(headers[i],
								new BSData(fileContent.get(headers[i])));
					}
					dataRow.put("result", new BSData(""));
					allData.add(dataRow);
					
				}
				fileContent.close();

				Integer rightCount = compareDataType(conn, table.deleteIdMap(),
						allData);

				HttpSession session = request.getSession();
				request.setAttribute("Headers", headers);
				request.setAttribute("RightCount", rightCount);
				request.setAttribute("RowCount", allData.size());
				synchronized (session) {
					session.setAttribute("respCSV", allData);
					session.setAttribute("BSTable", table);
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

	private Integer compareDataType(Connection conn,
			Map<String, BSField> fields, List<Map<String, BSData>> fieldList) {
		Integer rightCount = 0;
		Boolean typeRight = Boolean.FALSE;
		Boolean fkRight = Boolean.FALSE;
		Boolean isUpdate = null;
		BSField field = null;
		String value = null;
		Boolean isRight = null;
		Boolean isRowRight = null;

		for (Map<String, BSData> map : fieldList) {
			Iterator it = map.entrySet().iterator();

			isRowRight = Boolean.TRUE;
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

					if (typeRight) {
						fkRight = validFK(field);
					}

					isRight = typeRight && fkRight;

					if (!isRight) {
						if (isRowRight) {
							isRowRight = Boolean.FALSE;
						}
					}

					map.get("result").setState(isRight);
					entry.getValue().setState(isRight);
				}
			}
			if (isRowRight) {
				rightCount++;
			}
		}
		return rightCount;
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


}
