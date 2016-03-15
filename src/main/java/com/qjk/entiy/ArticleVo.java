package com.qjk.entiy;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("ArticleVo")
public class ArticleVo extends Vo<Article> implements Serializable{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

	}

}
