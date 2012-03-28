package cl.buildersoft.framework.util;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;

public class BSPaging {
	private Boolean requiresPaging = null;
	private Integer recordPerPage = null;
	private Integer recordCount = null;
	private Integer currentPage = null;
	private Integer pageCount = null;
	private Integer firstRecord = null;

	// private Integer lastRecord = null;

	public BSPaging(Connection conn, BSmySQL mysql, BSTableConfig table,
			HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		this.currentPage = getCurrentPage(request);
		this.recordCount = recordCount(conn, mysql, table);
		this.recordPerPage = getRecordsPerPage(context);
		this.requiresPaging = requiresPaging();
		this.pageCount = calculatePageCount(this.recordCount,
				this.recordPerPage);

		this.firstRecord = this.currentPage == 1 ? 0
				: ((this.currentPage - 1) * recordPerPage);
	}

	private Integer calculatePageCount(Integer recordCount,
			Integer recordPerPage) {
		Integer out = recordCount / recordPerPage;
		out += recordCount % recordPerPage > 0 ? 1 : 0;
		return out;
	}

	private Integer getCurrentPage(HttpServletRequest request) {
		String currentPageString = request.getParameter("Page");
		Integer currentPageInteger;
		try {
			currentPageInteger = Integer.parseInt(currentPageString);
		} catch (Exception e) {
			currentPageInteger = new Integer(1);
		}
		return currentPageInteger;
	}

	private Integer recordCount(Connection conn, BSmySQL mysql,
			BSTableConfig table) {
		BSField[] fields = table.getFields();

		String fieldName = "1";
		if (fields.length > 0) {
			BSField idField = getIdField(fields);
			fieldName = idField.getName();
		}

		String sql = "SELECT COUNT(" + fieldName + ") AS cCount ";
		sql += "FROM " + table.getTableName();

		Integer out = Integer.parseInt(mysql.queryField(conn, sql, null));
		mysql.closeSQL();
		return out;
	}

	private Boolean requiresPaging() {
		return this.recordCount > this.recordPerPage;
	}

	private Integer getRecordsPerPage(ServletContext context) {
		String recordPerPageString = context
				.getInitParameter("bsframework.recordPerPage");
		Integer recordPerPageInteger;
		try {
			recordPerPageInteger = Integer.parseInt(recordPerPageString);
		} catch (Exception e) {
			recordPerPageInteger = new Integer(20);
		}
		return recordPerPageInteger;
	}

	/**********************/

	private BSField getIdField(BSField[] tableFields) {
		BSField out = null;
		if (tableFields.length == 0) {

		}
		for (BSField s : tableFields) {
			if (s.isId()) {
				out = s;
				break;
			}
		}
		return out;
	}

	public Boolean getRequiresPaging() {
		return requiresPaging;
	}

	public void setRequiresPaging(Boolean requiresPaging) {
		this.requiresPaging = requiresPaging;
	}

	public Integer getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(Integer recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getFirstRecord() {
		return firstRecord;
	}

	public void setFirstRecord(Integer firstRecord) {
		this.firstRecord = firstRecord;
	}
}
