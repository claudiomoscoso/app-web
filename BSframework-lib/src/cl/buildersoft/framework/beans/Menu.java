package cl.buildersoft.framework.beans;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<Submenu> submenuList = null;
	
	public Menu(){
		this.submenuList = new ArrayList<Submenu>();
	}
	
	public void addSubmenu(Submenu submenu){
		this.submenuList.add(submenu);
	}
	
	public List<Submenu> list(){
		return this.submenuList;
	}
}
