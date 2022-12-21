package com.codestates.pre_project.question.entity;

import com.codestates.pre_project.tag.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionTagId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(nullable = false, name = "TAG_ID")
    private Tag tag;

    public void addQuestion(Question question){
        this.question = question;
        if(!this.question.getQuestionTags().contains(this)){
            this.question.getQuestionTags().add(this);
        }
    }

    public void addTag(Tag tag){
        this.tag = tag;
        if(!this.tag.getQuestionTags().contains(this)){
            this.tag.addQuestionTag(this);
        }
    }

}
