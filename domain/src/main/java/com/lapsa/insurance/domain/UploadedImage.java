package com.lapsa.insurance.domain;

import com.lapsa.image.ImageContent;

public class UploadedImage extends BaseEntity<Integer> {
    private static final long serialVersionUID = 6988116673010364293L;
    private static final int PRIME = 29;
    private static final int MULTIPLIER = PRIME;

    @Override
    protected int getPrime() {
	return PRIME;
    }

    @Override
    protected int getMultiplier() {
	return MULTIPLIER;
    }

    private String fileName;
    private String mimeType;
    private String comments;

    private ImageContent original;
    private ImageContent thumbnail;

    public UploadedImage() {
    }

    public UploadedImage(String fileName, String mimeType, String comments, ImageContent original,
	    ImageContent thumbnail) {
	this.fileName = fileName;
	this.mimeType = mimeType;
	this.comments = comments;
	this.original = original;
	this.thumbnail = thumbnail;
    }

    public UploadedImage(String fileName, String mimeType, ImageContent original, ImageContent thumbnail) {
	this.fileName = fileName;
	this.mimeType = mimeType;
	this.original = original;
	this.thumbnail = thumbnail;
    }

    // GENERATED

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public String getMimeType() {
	return mimeType;
    }

    public void setMimeType(String mimeType) {
	this.mimeType = mimeType;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    public ImageContent getOriginal() {
	return original;
    }

    public void setOriginal(ImageContent original) {
	this.original = original;
    }

    public ImageContent getThumbnail() {
	return thumbnail;
    }

    public void setThumbnail(ImageContent thumbnail) {
	this.thumbnail = thumbnail;
    }
}
