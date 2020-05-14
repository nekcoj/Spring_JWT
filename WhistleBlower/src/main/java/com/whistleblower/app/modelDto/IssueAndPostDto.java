package com.whistleblower.app.modelDto;

import com.whistleblower.app.entity.PostboxPost;

import java.util.List;

public class IssueAndPostDto {

    private IssueDto issue;
    private  List<PostboxPost> messages;


    public List<PostboxPost> getMessages() {
        return messages;
    }

    public IssueDto getIssue() {
        return issue;
    }

    public void setIssue(IssueDto issue) {
        this.issue = issue;
    }

    public void setMessages(List<PostboxPost> messages) {
        this.messages = messages;
    }

}
