package com.example.dreams.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 *
 * 描述:图片处理
 *
 * @author:QJS
 */
public class ImageHandleBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9176029089737387994L;

	/**
	 * 图片名称
	 */
	private String picname;
	/**
	 * 图片后缀
	 */
	private String picsuf;
	private Bitmap bitmap;
	private String picturePath;
	
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	public String getPicsuf() {
		return picsuf;
	}
	public void setPicsuf(String picsuf) {
		this.picsuf = picsuf;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}
