package com.whistleblower.app.entity;

import javax.persistence.*;

@Entity
@Table
public class AssignedIssue extends Issue{

    @ManyToOne()
    @JoinColumn(name = "lawyer_id", referencedColumnName = "id", nullable = false)
    private User user;
}
