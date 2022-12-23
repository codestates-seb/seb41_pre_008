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
    private Long questionTagId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;


    public void setQuestion(Question question){
        this.question = question;
        if(!this.question.getQuestionTags().contains(this)){
            this.question.getQuestionTags().add(this);
        }
    }

    public void setTag(Tag tag){
        this.tag = tag;
        if(!this.tag.getQuestionTags().contains(this)){
            this.tag.setQuestionTag(this);
        }
    }

}
