package com.phoenix.taskapp.classes;

import java.util.List;

public class IndexClass {

    String downloadId;
    String cdDownload;
    String Id;
    String title;
    String status;
    String releaseDate;
    String authorId;
    String videoCount;
    List<String> styleTag;
    List<String> skillTag;
    List<String> seriesTag;
    List<String> curriculumTag;
    String educator;
    String owned;
    String sale;
    String purchaseOrder;
    String watched;
    String progressTracking;

    public IndexClass(String downloadId, String cdDownload, String id, String title, String status, String releaseDate, String authorId, String videoCount, List<String> styleTag, List<String> skillTag, List<String> seriesTag, List<String> curriculumTag, String educator, String owned, String sale, String purchaseOrder, String watched, String progressTracking) {
        this.downloadId = downloadId;
        this.cdDownload = cdDownload;
        Id = id;
        this.title = title;
        this.status = status;
        this.releaseDate = releaseDate;
        this.authorId = authorId;
        this.videoCount = videoCount;
        this.styleTag = styleTag;
        this.skillTag = skillTag;
        this.seriesTag = seriesTag;
        this.curriculumTag = curriculumTag;
        this.educator = educator;
        this.owned = owned;
        this.sale = sale;
        this.purchaseOrder = purchaseOrder;
        this.watched = watched;
        this.progressTracking = progressTracking;
    }

    public String getDownloadId() {
        return downloadId;
    }

    public String getCdDownload() {
        return cdDownload;
    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public List<String> getStyleTag() {
        return styleTag;
    }

    public List<String> getSkillTag() {
        return skillTag;
    }

    public List<String> getSeriesTag() {
        return seriesTag;
    }

    public List<String> getCurriculumTag() {
        return curriculumTag;
    }

    public String getEducator() {
        return educator;
    }

    public String getOwned() {
        return owned;
    }

    public String getSale() {
        return sale;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public String getWatched() {
        return watched;
    }

    public String getProgressTracking() {
        return progressTracking;
    }
}
