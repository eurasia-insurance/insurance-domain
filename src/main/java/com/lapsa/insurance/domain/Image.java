package com.lapsa.insurance.domain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "IMAGE")
public class Image extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static class ImageProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int type;
	private final int width;
	private final int height;
	private final long size;

	public ImageProperties(final int type, final int width, final int height, final long size) {
	    this.type = type;
	    this.width = width;
	    this.height = height;
	    this.size = size;
	}

	public int getType() {
	    return type;
	}

	public int getWidth() {
	    return width;
	}

	public int getHeight() {
	    return height;
	}

	public long getSize() {
	    return size;
	}
    }

    @Basic
    @Column(name = "FILE_NAME")
    private String fileName;

    @Basic
    @Column(name = "MIME_TYPE")
    private String mimeType;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "CONTENT")
    private byte[] content;

    @Transient
    private ImageProperties imageProperties;

    public Image() {
    }

    public Image(final String fileName, final String mimeType, final byte[] content) {
	this.fileName = fileName;
	this.mimeType = mimeType;
	setContent(content);
    }

    public void setContent(final byte[] content) {
	this.content = content;
	imageProperties = null;
    }

    public ImageProperties getImageProperties() {
	if (imageProperties == null)
	    imageProperties = parseForProperties(content);
	return imageProperties;
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	return fileName;
    }

    // PRIVATE

    private static ImageProperties parseForProperties(final byte[] content) {
	if (content == null || content.length == 0)
	    return null;
	try {
	    final BufferedImage buf = ImageIO.read(new ByteArrayInputStream(content));
	    if (buf == null)
		return null;
	    return new ImageProperties(buf.getType(), buf.getWidth(), buf.getHeight(), content.length);
	} catch (final IOException ignored) {
	    return null;
	}
    }

    // GENERATED

    public String getFileName() {
	return fileName;
    }

    public void setFileName(final String fileName) {
	this.fileName = fileName;
    }

    public String getMimeType() {
	return mimeType;
    }

    public void setMimeType(final String mimeType) {
	this.mimeType = mimeType;
    }

    public byte[] getContent() {
	return content;
    }

}
