package ua.kiev.makson.work_in_site.requests.getvideo.page;

public class VideoDescription {
	private int id;
	private String name;
	private String viewtopic;
	private String downloadTorrent;
	private String downloadFile;
	private String description;
	private String url;
	private String jpg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getDownloadTorrent() {
		return downloadTorrent;
	}

	public void setDownloadTorrent(String downloadTorrent) {
		this.downloadTorrent = downloadTorrent;
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

	public void setJpg(String jpg) {
		this.jpg = jpg;
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

	public String getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}

	@Override
	public String toString() {
		return String.format(
				"id № %d%nname: %s%ndescription: %s%nviewtopic: %s%njpg: %s%nurl: %s%ndownloadTorrent: %s%n", id, name,
				description, viewtopic, jpg, url, downloadTorrent);
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
