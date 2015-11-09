package ua.kiev.makson.work_in_site.page;

public class Video {
    private String name;
    private String viewtopic;
    private String downloadUrl;
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s%n %s%n %s%n %s%n", name, category, viewtopic,
                downloadUrl);
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
        Video otherObj = (Video) other;
        return this.name.equals(otherObj.name);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
