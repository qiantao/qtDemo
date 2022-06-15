package util;

/**
 * 
 * @author excel头部单格显示对象
 * @author title excel数据集合中对应英文key
 * @author titles excel数据集合中对应英文key集合(当需要合并时使用，根据key包含的:进行分级)
 * @author displayName excel数据集合中对应英文key的中文描述
 */
public class ExcelHeadCell {

	private String title;
	private String displayName;
	private String[] titles;
	public String getTitle() {
		if(title==null)return "";
		return title;
	}
	public void setTitle(String title) {
		if(title==null){
			return;
		}
		titles=title.split(":");
		this.title = titles[0];
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String[] getTitles(){
		if(titles==null)return new String[0];
		return titles;
	}
	
}
