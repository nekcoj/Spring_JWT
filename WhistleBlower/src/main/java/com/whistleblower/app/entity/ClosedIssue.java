package com.whistleblower.app.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ClosedIssue extends Issue{

    @ManyToOne
    private Lawyer lawyer;
}
