package cl.buildersoft.framework.beans;

@Deprecated
public class BSHeadConfig {
	private BSScript script = null;
	private BSCss css = null;
	public BSScript getScript() {
		return script;
	}
	public void setScript(BSScript script) {
		this.script = script;
	}
	public BSCss getCss() {
		return css;
	}
	public void setCss(BSCss css) {
		this.css = css;
	}

}
