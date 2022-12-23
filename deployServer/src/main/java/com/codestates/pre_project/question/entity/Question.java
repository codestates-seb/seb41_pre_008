package com.codestates.pre_project.question.entity;

//import com.codestates.pre_project.comment.entity.Comment;
//import com.codestates.pre_project.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String problemContent;

    @Column(nullable = false)
    private String expectContent;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_NOTSELECT;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();


    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<QuestionTag> questionTags = new ArrayList<>();


    /*
    public Question(Member member){
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMember(Member member) {
        this.member = member;
        if(!this.member.getQuestions().contains(this)){
            this.member.getQuestions().add(this);
        }
    }

    @OneToMany(mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();

     */



    public enum QuestionStatus {
        QUESTION_SELECT("채택된 답안이 있는 문의"),
        QUESTION_NOTSELECT("채택된 답안이 없는 문의");

        @Getter
        private String status;

        QuestionStatus(String status) {
            this.status = status;
        }
    }
}

