package org.azd.git.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitCommits {
    @JsonProperty("value")
    private List<GitCommit> commits;

    public List<GitCommit> getCommits() {
        return commits;
    }

    public void setCommits(List<GitCommit> value) {
        this.commits = value;
    }

    @Override
    public String toString() {
        return "GitCommits{" +
                "commits=" + commits +
                '}';
    }
}
