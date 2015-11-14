package ua.kiev.makson.work_in_site.page;

public class VideoDescription {
    private String name;
    private String viewtopic;
    private String downloadUrl;
    private String description;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getViewtopic() {
        return viewtopic;
    }

    public void setViewtopic(String viewtopic) {
        this.viewtopic = viewtopic;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return String.format(
                "name%s%ndescription%s%nviewtopic%s%ndownloadUrl%s%n", name,
                description, viewtopic, downloadUrl);
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other))
            return false;
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        VideoDescription otherObj = (VideoDescription) other;
        return this.name.equals(otherObj.name);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
