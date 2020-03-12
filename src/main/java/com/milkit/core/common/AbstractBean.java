package com.milkit.core.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* <pre>
* 1. 패키지명 : com.milkit.core.common
* 2. 타입명 : AbstractBean.java
* 3. 작성일 : 2015. 5. 28. 오후 3:18:03
* 4. 작성자 : milkit
* 5. 설명    : toString 메소드 재정의(Apache common 의 ToStringBuilder Util활용)
* </pre>
*/
public abstract class AbstractBean {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * <pre>
	 *  설명    : toString 호출 시 Class의 field값 자동 추출
	 * </pre>
	 */
	@Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
	}

}
