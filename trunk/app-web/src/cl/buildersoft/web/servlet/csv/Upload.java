package cl.buildersoft.web.servlet.csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.buildersoft.framework.beans.DatabaseFile;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSSystemException;

@WebServlet("/servlet/csv/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Upload() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String desc = request.getParameter("desc");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new BSSystemException("0201", e.getMessage());
		}
		PrintWriter w = response.getWriter();
		for (FileItem item : items) {
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();
				w.println(name + "=" + value);

			} else {
				
				CsvReader products = new CsvReader(item.getInputStream(),Charset.forName("ISO-8859-1"));
				System.out.println("----- charset por defecto ---> " + Charset.defaultCharset());
				products.readHeaders();
				String[] headers = products.getHeaders();

				System.out.println("Encabezados = " + headers.toString());
				while (products.readRecord())
				{
					String cId = products.get("cId");
					String prodcRUTuctName = products.get("cRUT");
					String cSexo = products.get("cSexo");
				
					// perform program logic here
					System.out.println(cId + ":" + prodcRUTuctName + ":" + cSexo);
				}
		
				products.close();				
				
				
				
				w.println("size:" + (item.getSize() / 1024) + "kb");
				w.println("type:" + item.getContentType());
				w.println("name:" + item.getName());
				w.println("IsInMemory:" + item.isInMemory());

				w.print("\n\n");

			}

		}
		w.flush();

		BSmySQL mysql = new BSmySQL();
		Connection conn;

		conn = mysql.getConnection(getServletContext(), "bsframework");

		BSBeanUtils bu = new BSBeanUtils();

	}
}
