package ua.kiev.makson.work_in_site.page;

public class VideoDescription {
    private String name;
    private String viewtopic;
    private String downloadUrl;
    private String description;
    private String url;
    private String jpg;

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

    public String getJpg() {
        return jpg;
    }

    public void setImg(String jpg) {
        this.jpg = jpg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String
                .format("name: %s%ndescription: %s%nviewtopic: %s%njpg: %s%nurl: %s%ndownloadUrl: %s%n",
                        name, description, viewtopic, jpg, url, downloadUrl);
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
