package com.jt.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EsayUITree {

	public EsayUITree(long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	private long id;
	
	private Integer parentId;
	private Integer tag;
	
	private String text;
	private String iconCls;//="icon-save";
	private String state;
	private List<EsayUITree> children;
	
	
	public void setParentId(Integer parentId) {
		if(parentId==0)
			setState("closed");
		else
			setState("open");
	}
	public void setTag(Integer tag) {
		if(tag==0)
			setState("closed");
		else
			setState("open");
	}
	
}
