package cl.buildersoft.lib.beans;

import java.util.List;
@Deprecated
public class BSCss {
	private String path = null;
	private List<String> listCssNames = null;
	public BSCss(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<String> getListCssNames() {
		return listCssNames;
	}
	public void setListCssNames(List<String> listCssNames) {
		this.listCssNames = listCssNames;
	}
	
	
}
