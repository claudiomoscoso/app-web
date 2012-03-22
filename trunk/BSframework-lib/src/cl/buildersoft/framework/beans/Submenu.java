package cl.buildersoft.framework.beans;

import java.util.ArrayList;
import java.util.List;

public class Submenu {
	private Option option = null;
	private List<Submenu> submenuList = null;

	public Submenu(Option option) {
		this.setOption(option);
		this.submenuList = new ArrayList<Submenu>();
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Option getOption() {
		return this.option;
	}

	public void addSubmenu(List<Submenu> submenuList) {
		for (Submenu submenu : submenuList) {
			this.submenuList.add(submenu);
		}
	}

	public void addSubmenu(Submenu submenu) {
		this.submenuList.add(submenu);
	}

	public List<Submenu> list() {
		return this.submenuList;
	}
}
