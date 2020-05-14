package com.whistleblower.app.modelDto;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.entity.PostboxPost;

import java.util.List;

public class IssueAndPostDto {

    private Issue issue;
    private  List<PostboxPost> messages;


    public List<PostboxPost> getMessages() {
        return messages;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setMessages(List<PostboxPost> messages) {
        this.messages = messages;
    }

}
