package org.geoproject.ingeo.dto;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;

public class PrintInfoDto {
    private FloatProperty attachment;
    private FloatProperty page;
    private StringProperty author;

    public float getAttachment() {
        return attachment.get();
    }

    public FloatProperty attachmentProperty() {
        return attachment;
    }

    public void setAttachment(float attachment) {
        this.attachment.set(attachment);
    }

    public float getPage() {
        return page.get();
    }

    public FloatProperty pageProperty() {
        return page;
    }

    public void setPage(float page) {
        this.page.set(page);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }
}
